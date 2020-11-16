import java.awt.Graphics;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RaceState extends GameState {

    private ArrayList<BufferedImage> boatImages;
    private BufferedImage backgroundImage;
    private River river;

    public ArrayList<Boat> boats;

    public RaceState() {
        super();
        instantiateRiver();
    }

    public void instantiateRiver() {
        river = new River(backgroundImage);
    }

    public void instantiateBoats(BoatType boatType) {
        int playerBoat = boatType.ordinal();

        boats = new ArrayList<Boat>();

<<<<<<< Updated upstream
        boats.add(new Boat(1, 4, 1, 4, boatImages.get(0)));
        boats.add(new Boat(2, 3, 2, 3, boatImages.get(1)));
        boats.add(new Boat(3, 2, 3, 2, boatImages.get(2)));
        boats.add(new Boat(4, 1, 4, 1, boatImages.get(3)));
=======
        boats.add(new Boat(4, 4, 20, 4, boatImages.get(0)));
        boats.add(new Boat(5, 3, 30, 3, boatImages.get(1)));
        boats.add(new Boat(4, 2, 40, 2, boatImages.get(2)));
        boats.add(new Boat(5, 1, 50, 4, boatImages.get(3)));
>>>>>>> Stashed changes

        boats.get(playerBoat).setPlayerBoat();
        boats.get((7 + playerBoat) % 4).setOpponentBoat(1);
        boats.get((6 + playerBoat) % 4).setOpponentBoat(2);
        boats.get((5 + playerBoat) % 4).setOpponentBoat(3);
    }

    public void resetBoats() {
        for (Boat boat : boats) {
            boat.resetPosition();
        }
    }

    @Override
    public void draw(Graphics g) {
        river.draw(g);
        for (Boat b : boats) {
            b.draw(g);
        }
    }

    @Override
    public void hideButtons() {

    }

    @Override
    public void initButtons() {

    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showButtons() {

    }

    @Override
    public void update() {
        river.update();
        FinishLine fl = river.getFinishLine();
        for (Boat b : boats) {
            b.update();
            if (fl.collision(b)) {
                GameStateManager.getInstance().setState(GameStateManager.ENDRACESTATE);
                EndRaceState ers = (EndRaceState) GameStateManager.getInstance().getCurrentState();
                ers.nextRace = river.raceNumber + 1;
            }
        }
    }

    public River getRiver() {
        return river;
    }
}

enum BoatType {
    GREEN, RED, LILAC, ORANGE
}
