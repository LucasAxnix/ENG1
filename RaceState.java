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

    private ArrayList<Boat> boats;
    private int playerBoatIndex;
    /**
     * RaceState constructor
     */
    public RaceState() {
        super();
        nextRaceTimer = (int) (4 * Game.TICK_RATE);
        boats = new ArrayList<Boat>();
        instantiateRiver();
    }
    /**
     * instantiate river
     */
    public void instantiateRiver() {
        river = new River(backgroundImage);
    }
    /**
     *  instantiate boats
     * 
     * @param boatType the type of boat the player selected
     */
    public void instantiateBoats(BoatType boatType) {
        playerBoatIndex = boatType.ordinal();
        boats.clear();
        boats.add(new Boat(8, 4, 5, 3, boatImages.get(0), "Green")); // dur
        boats.add(new Boat(9, 3, 8, 4, boatImages.get(1), "Red")); // man
        boats.add(new Boat(7, 10, 9, 3, boatImages.get(2), "Lilac")); // acc
        boats.add(new Boat(10, 3, 10, 2, boatImages.get(3), "Orange")); // speed

        boats.get(playerBoatIndex).setPlayerBoat();
        boats.get((7 + playerBoatIndex) % 4).setOpponentBoat(1);
        boats.get((6 + playerBoatIndex) % 4).setOpponentBoat(2);
        boats.get((5 + playerBoatIndex) % 4).setOpponentBoat(3);

        Game.instance.requestFocus();
    }
    /**
     * resets the boats
     * 
     * @param raceNumber the current race number
     */
    public void resetBoats(int raceNumber) {
        for (Boat boat : boats) {
            boat.reset();
            boat.increaseFatigue(raceNumber);
        }
        playerBoatFinished = false;
        nextRaceTimer = (int) (4 * Game.TICK_RATE);
    }
    /**
     * draws all the content related to the game state class
     */
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
                    g.drawString(String.format("%.2f", b.getFinalTime()), timeXPos, timeYPos);
                }
            }
        }
        for (Boat b : boats) {
            b.draw(g);
        }
        Boat playerBoat = boats.get(playerBoatIndex);
            g.setColor(Color.black);
            if (!playerBoatFinished) {
                g.setFont(RaceState.TIMER_FONT);
                String timer = String.format("%.2f", playerBoat.getTimer());
                String penalty = String.format("%.2f", playerBoat.getPenalty());
                g.drawString(timer, Game.WINDOW_WIDTH / 2 - 50, 30);
                g.setColor(Color.red);
                g.drawString("+ " + penalty, Game.WINDOW_WIDTH / 2 + 50, 30);
                g.setColor(Color.black);
                g.fillRect(Game.WINDOW_WIDTH - 200, 0, 200, 50);
                g.setColor(Color.red);
                g.fillRect(Game.WINDOW_WIDTH - 200, 0, Math.max(0, playerBoat.getHealth() * 2), 50);
            }
        if (isRaceFinished()) {
            g.setFont(TIMER_FONT);
            g.setColor(Color.black);
            g.drawString((int) (nextRaceTimer / Game.TICK_RATE) + "...", Game.WINDOW_WIDTH / 2,
                    Game.WINDOW_HEIGHT / 4 * 3 + 50);
        }
    }

    @Override
    public void hideButtons() {}

    @Override
    public void initButtons() {}

    @Override
    public void showButtons() {}

    /**
     * initialises the images
     */
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

    /**
     * updates everything in the race state
     */
    @Override
    public void update() {
        river.update();

        FinishLine fl = river.getFinishLine();
        for (int i = 0; i < boats.size(); i++) {
            Boat b = boats.get(i);
            if (fl.collision(b)) {
                b.setFinished();
                sortTimes();
                EndRaceState ers = (EndRaceState) GameStateManager.getInstance()
                        .getState(GameStateManager.ENDRACESTATE);
                ers.setResult(i, b.getFinalTime(), b.getPosition());
                
            }
            b.update();
        }
        if (isRaceFinished()) {
            nextRaceTimer--;
            if (nextRaceTimer <= 0) {
                if (river.raceNumber < 3) {
                    GameStateManager.getInstance().setState(GameStateManager.ENDRACESTATE);
                    EndRaceState ers = (EndRaceState) GameStateManager.getInstance()
                            .getState(GameStateManager.ENDRACESTATE);
                    ers.nextRace = river.raceNumber + 1;
                } else {
                    GameStateManager.getInstance().setState(GameStateManager.PODIUMSTATE);
                    PodiumState ps = (PodiumState) GameStateManager.getInstance()
                            .getState(GameStateManager.PODIUMSTATE);
                    ps.finalPositions();
                }
            }
        }
    }

    /**
     * gets the amount of boats that have finished
     * 
     * @return an integer of how many boats have finished the race
     */
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

    /**
     * gets the river instance
     * 
     * @return the river instance
     */
    public River getRiver() {
        return river;
    }

    /**
     * has the current race finished
     * 
     * @return a boolean of whether the race has finished
     */
    private boolean isRaceFinished() {
        return getBoatsFinished() == 4;
    }

    /**
     * gets the array of boats
     */
    public ArrayList<Boat> getBoats() {
        return boats;
    }
    
    /**
     * sorts the times of the boats
     */
    private void sortTimes() {
        int pos = 4;
        for (Boat b1 : boats) {
            if (!b1.getFinished()) continue;
            pos = 4;
            for (Boat b2 : boats) {
                if (b1 == b2) continue;
                if (!b2.getFinished()) {
                    pos--;
                }
                else if (b2.getFinalTime() > b1.getFinalTime()) {
                    pos--;
                } else if (b2.getFinalTime() == b1.getFinalTime()) {
                    if (boats.indexOf(b1) < boats.indexOf(b2)) {
                        pos--;
                    }
                }
                
            }
            b1.setPosition(pos);
        }
    }
}

enum BoatType {
    GREEN, RED, LILAC, ORANGE
}
