import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GameOverState extends GameState {

    private JButton restartButton;
    private BufferedImage background;
    private Icon restart;

    /**
     * initialies the buttons for the game over state
     */
    @Override
    public void initButtons() {
        restartButton = new JButton(restart);
        restartButton.setBounds(290, 210, restart.getIconWidth(), restart.getIconHeight());
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
                rs.instantiateRiver();
                GameStateManager.getInstance().setState(GameStateManager.MENUSTATE);
            }
        });
    }

    /**
     * initialises the images for the game over state
     */
    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResource("/Resources/blue.png"));
            restart = new ImageIcon(getClass().getResource("/Resources/restart.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * draws the content for the game over state
     * 
     * @param g the graphics object to draw to
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    /**
     * adds the buttons to the panel
     */
    @Override
    public void showButtons() {
        Game.instance.add(restartButton);
    }

    /**
     * removes the buttons from the panel
     */
    @Override
    public void hideButtons() {
        Game.instance.remove(restartButton);
    }

    @Override
    public void update() {}
}
