
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EndRaceState extends GameState {
    private BufferedImage background;
    private BufferedImage table1;
    private BufferedImage table2;
    private BufferedImage table3;
    private BufferedImage table4;
    private Icon startNewRace;
    private JButton nextRaceButton;

    public int nextRace = 0;

    public EndRaceState() {
        super();
    }

    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResource("/Resources/blue.png"));
            table1 = ImageIO.read(getClass().getResource("/Resources/table.png"));
            table2 = ImageIO.read(getClass().getResource("/Resources/table.png"));
            table3 = ImageIO.read(getClass().getResource("/Resources/table.png"));
            table4 = ImageIO.read(getClass().getResource("/Resources/table.png"));
            startNewRace = new ImageIcon(getClass().getResource("/Resources/startNextRaceButton.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initButtons() {
        nextRaceButton = new JButton(startNewRace);
        nextRaceButton.setBounds(415, 285, startNewRace.getIconWidth(), startNewRace.getIconHeight());
        nextRaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameStateManager.getInstance().setState(GameStateManager.RACESTATE);
                RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
                rs.getRiver().initRace(nextRace);
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(table1, 50, 50, null);
        g.drawImage(table2, 680, 50, null);
        g.drawImage(table3, 50, 420, null);
        g.drawImage(table4, 680, 420, null);
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
