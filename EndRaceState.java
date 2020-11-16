
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EndRaceState extends GameState {
    private BufferedImage background;
    private Icon startNewRace;
    private JButton nextRaceButton;

    public int nextRace = 0;

    public EndRaceState() {
        super();
    }

    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResource("/Resources/menuStateBackground.png"));
            startNewRace = new ImageIcon(getClass().getResource("/Resources/startNextRaceButton.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initButtons() {
        nextRaceButton = new JButton(startNewRace);
        nextRaceButton.setBounds(startNewRace.getIconWidth(), startNewRace.getIconHeight(), 167, 47);
        nextRaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameStateManager.getInstance().setState(GameStateManager.RACESTATE);
                RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
                rs.getRiver().initRace(nextRace);
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public void update() {

    }

    @Override
    public void showButtons() {
        Game.instance.add(nextRaceButton);
    }

    @Override
    public void hideButtons() {
        Game.instance.remove(nextRaceButton);

    }
}
