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
        river = new River();
    }

    private void instantiateBoats() {
        boats = new ArrayList<Boat>() {
            {
                add(new Boat(0, 10, 1, 1, 1, 1, boatImages.get(0), false));
                add(new Boat(0, 300, 1, 1, 1, 1, boatImages.get(0), true));
                add(new Boat(0, 580, 1, 1, 1, 1, boatImages.get(0), false));
                add(new Boat(0, 660, 1, 1, 1, 1, boatImages.get(0), false));
            }
        };
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
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

            /// TEMP
            boatImages.add(ImageIO.read(getClass().getResource("/Resources/greenBoat.png")));
            ///
            backgroundImage = ImageIO.read(getClass().getResource("/Resources/Water.png"));
            instantiateBoats();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showButtons() {

    }

    @Override
    public void update() {
        for (Boat b : boats) {
            b.update();
        }
    }
}