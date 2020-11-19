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

    public GameOverState() {
        super();
    }

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

    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResource("/Resources/blue.png"));
            restart = new ImageIcon(getClass().getResource("/Resources/restart.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public void showButtons() {
        Game.instance.add(restartButton);
    }

    @Override
    public void hideButtons() {
        Game.instance.remove(restartButton);
    }

    @Override
    public void update() {
    }

}
