import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private BufferedImage background;
    private Icon startGame;
    private JButton startButton;

    /**
     * initialises the images
     */
    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("Resources/menuStateBackground.png"));
            startGame = new ImageIcon(getClass().getResource("Resources/menuStateStartgame.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initialises the buttons
     */
    @Override
    public void initButtons() {
        startButton = new JButton(startGame);
        startButton.setBounds(150, 200, 167, 47);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameStateManager.getInstance().setState(1);
            }
        });
    }

    /**
     * draws the content for the menu state
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public void update() {}

    /**
     * adds the buttons to the panel
     */
    @Override
    public void showButtons() {
        Game.instance.add(startButton);
    }

    /**
     * removes the buttons from the panel
     */
    @Override
    public void hideButtons() {
        Game.instance.remove(startButton);
    }
}
