import java.awt.Graphics;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RaceState extends GameState {

    private ArrayList<BufferedImage> boatImages;
    private ArrayList<Boat> boats;
    private BufferedImage backgroundImage;
    private River river;

    public RaceState() {
        super();
        boatImages = new ArrayList<BufferedImage>();
    }

    public void instantiateRiver() {
        river = new River(backgroundImage);
    }

    public void instantiateBoats(BoatType boatType) {
        int playerBoat = boatType.ordinal();

        boats = new ArrayList<Boat>();

        boats.add(new Boat(1, 4, 1, 4, boatImages.get(0)));
        boats.add(new Boat(2, 3, 2, 3, boatImages.get(1)));
        boats.add(new Boat(3, 2, 3, 2, boatImages.get(2)));
        boats.add(new Boat(4, 1, 4, 1, boatImages.get(3)));

        boats.get(playerBoat).setPlayerBoat();
        boats.get((7 + playerBoat) % 4).setOpponentBoat(1);
        boats.get((6 + playerBoat) % 4).setOpponentBoat(2);
        boats.get((5 + playerBoat) % 4).setOpponentBoat(3);
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
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/greenBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/redBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/lilacBoat.png")));
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/orangeBoat.png")));
            backgroundImage = ImageIO.read(getClass().getResource("/Resources/RaceBackground2160.png"));
            instantiateRiver();
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
        for (Boat b : boats) {
            b.update();
        }
    }

    public River getRiver() {
        return river;
    }
}

enum BoatType {
    GREEN, RED, LILAC, ORANGE
}
