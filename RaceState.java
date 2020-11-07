import java.awt.Graphics;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class RaceState extends GameState {

    private ArrayList<BufferedImage> boatImages;
    private ArrayList<Boat> boats;

    private BufferedImage backgroundImage;

    public RaceState(GameStateManager gsm) {
        super(gsm);
        boatImages = new ArrayList<BufferedImage>();
    }

    private void instantiateBoats() {
        boats = new ArrayList<Boat>() {
            {
                add(new Boat(1, 1, 1, 1, boatImages.get(0)));
                add(new Boat(1, 1, 1, 1, boatImages.get(0)));
                add(new Boat(1, 1, 1, 1, boatImages.get(0)));
                add(new Boat(1, 1, 1, 1, boatImages.get(0)));
            }
        };
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
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
    public void loadImages(String[] images) {
        try {
            // Load boat images and store them in boat images list

            /// TEMP
            boatImages.add(ImageIO.read(getClass().getResource("/Resources//greenBoat.png")));
            ///
            backgroundImage = ImageIO.read(getClass().getResource("/Resources/" + images[0]));
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
        
    }


}
