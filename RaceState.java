import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class RaceState extends GameState {

    private ArrayList<BufferedImage> boatImages;
    private BufferedImage backgroundImage;
    private River river;
    private BufferedImage leaderboardImage;
    private boolean playerBoatFinished;
    private int nextRaceTimer;

    public static final Font TIMER_FONT = new Font("Verdana", Font.BOLD, 30);
    private static final Font LEADERBOARD_FONT = new Font("Verdana", Font.BOLD, 50);

    public ArrayList<Boat> boats;
    public int playerBoatIndex;

    public RaceState() {
        super();
        nextRaceTimer = (int)(4 * Game.TICK_RATE);
        boats = new ArrayList<Boat>();
        instantiateRiver();
    }

    public void instantiateRiver() {
        river = new River(backgroundImage);
    }

    public void instantiateBoats(BoatType boatType) {
        playerBoatIndex = boatType.ordinal();
        boats.clear();
        boats.add(new Boat(4, 4, 20, 4, boatImages.get(0), "Green"));
        boats.add(new Boat(5, 3, 30, 3, boatImages.get(1), "Red"));
        boats.add(new Boat(4, 2, 40, 2, boatImages.get(2), "Lilac"));
        boats.add(new Boat(5, 2, 50, 4, boatImages.get(3), "Orange"));
        /// DEBUG BOATS
        // boats.add(new Boat(16, 100, 0, 10, boatImages.get(0), "Green"));
        // boats.add(new Boat(10, 100, 0, 10, boatImages.get(1), "Red"));
        // boats.add(new Boat(10, 100, 0, 10, boatImages.get(2), "Lilac"));
        // boats.add(new Boat(11, 100, 0, 10, boatImages.get(3), "Orange"));

        boats.get(playerBoatIndex).setPlayerBoat();
        boats.get((7 + playerBoatIndex) % 4).setOpponentBoat(1);
        boats.get((6 + playerBoatIndex) % 4).setOpponentBoat(2);
        boats.get((5 + playerBoatIndex) % 4).setOpponentBoat(3);

        Game.instance.requestFocus();
    }

    public void resetBoats(int raceNumber) {
        for (Boat boat : boats) {
            boat.reset();
            boat.increaseFatigue(raceNumber);
        }
    }

    @Override
    public void draw(Graphics g) {
        river.draw(g);
        if (playerBoatFinished) {
            g.drawImage(leaderboardImage, 270, 180, null);
            g.setFont(LEADERBOARD_FONT);
            for (Boat b : boats) {
                if (b.getFinished()) {
                    int nameXPos = Game.WINDOW_WIDTH / 2 - Game.WINDOW_WIDTH / 4 + 10;
                    int nameYPos = b.getPosition() * Game.WINDOW_HEIGHT / 8 + Game.WINDOW_HEIGHT / 4 - 10;
                    int timeXPos = Game.WINDOW_WIDTH / 2 + 10;
                    int timeYPos = b.getPosition() * (Game.WINDOW_HEIGHT / 8) + Game.WINDOW_HEIGHT / 4 - 10;
                    g.drawString(b.getName(), nameXPos, nameYPos);
                    g.drawString(String.format("%.2f", b.getTimer()), timeXPos, timeYPos);
                }
            }
        }
        for (Boat b : boats) {
            b.draw(g);
        }
        Boat playerBoat = boats.get(playerBoatIndex);
        if (playerBoat.isPlayer()) {
            g.setColor(Color.black);
            if (!playerBoatFinished)  {
                g.setFont(RaceState.TIMER_FONT);
                String timer = String.format("%.2f", playerBoat.getTimer());
                g.drawString(timer, Game.WINDOW_WIDTH / 2 - timer.length() * RaceState.TIMER_FONT.getSize() / 2, 30);
            }
            g.fillRect(Game.WINDOW_WIDTH - 200, 0, 200, 50);
            g.setColor(Color.red);
            g.fillRect(Game.WINDOW_WIDTH - 200, 0, Math.max(0, playerBoat.getHealth() * 2), 50);
        }
        if (isRaceFinished()) {
            g.setFont(TIMER_FONT);
            g.setColor(Color.black);
            g.drawString((int)(nextRaceTimer / Game.TICK_RATE) + "...", Game.WINDOW_WIDTH / 2, Game.WINDOW_HEIGHT / 4 * 3 + 50);
        }
    }

    @Override
    public void hideButtons() {}

    @Override
    public void initButtons() {}

    @Override
    public void showButtons() {}

    @Override
    public void initImages() {
        try {
            // Load boat images and store them in boat images list
            boatImages = new ArrayList<BufferedImage>();
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/greenBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/redBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/lilacBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/orangeBoat.png")));
            backgroundImage = ImageIO.read(getClass().getResource("/Resources/RaceBackground2160.png"));
            leaderboardImage = ImageIO.read(getClass().getResource("/Resources/leaderboard.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        river.update();

        FinishLine fl = river.getFinishLine();
        for (int i = 0; i < boats.size(); i++) {
            Boat b = boats.get(i);
            if (fl.collision(b)) {
                b.finished(getBoatsFinished() + 1);
            }
            b.update();
        }
        if (isRaceFinished()) {
            nextRaceTimer--;
            if (nextRaceTimer <= 0) {
                GameStateManager.getInstance().setState(GameStateManager.ENDRACESTATE);
                EndRaceState ers = (EndRaceState) GameStateManager.getInstance()
                        .getState(GameStateManager.ENDRACESTATE);
                ers.nextRace = river.raceNumber + 1;
            }
        }
    }

    private int getBoatsFinished() {
        int count = 0;
        for (Boat b : boats) {
            if (b.getFinished()) {
                count++;
                if (b.isPlayer()) {
                    playerBoatFinished = true;
                }
            }
        }
        return count;
    }

    public boolean getPlayerFinished() {
        return playerBoatFinished;
    }

    public River getRiver() {
        return river;
    }

    private boolean isRaceFinished() {
        return getBoatsFinished() == 4;
    }
}

enum BoatType {
    GREEN, RED, LILAC, ORANGE
}
