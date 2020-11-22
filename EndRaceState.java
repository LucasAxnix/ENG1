
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class EndRaceState extends GameState {
    private BufferedImage background;
    private float[][] times;
    private int[][] results;
    private Icon startNewRace;
    private JButton nextRaceButton;
    private BufferedImage[] tables;

    private int[][] tablesPositions = {{50,50}, {710,50}, {50,420}, {710,420}};
    private int[][] textOffests = {{60,80}, {60, 136}, {60,192}};
    private String[] ordinals = {"1st", "2nd", "3rd", "4th"};

    public static final Font RESULTS_FONT = new Font("Verdana", Font.BOLD, 30);

    public int nextRace = 0;

    /**
     * EndRaceState constructor
     */
    public EndRaceState() {
        super();
        times = new float[4][4];
        results = new int[4][4];
    }

    /**
     * initialises the images for the end race state
     */
    @Override
    public void initImages() {
        tables = new BufferedImage[4];
        try {
            background = ImageIO.read(getClass().getResourceAsStream("Resources/blueEndRace.png"));
            tables[0] = ImageIO.read(getClass().getResourceAsStream("Resources/greenTable.png"));
            tables[1] = ImageIO.read(getClass().getResourceAsStream("Resources/redTable.png"));
            tables[2] = ImageIO.read(getClass().getResourceAsStream("Resources/lilacTable.png"));
            tables[3] = ImageIO.read(getClass().getResourceAsStream("Resources/orangeTable.png"));
            startNewRace = new ImageIcon(getClass().getResource("Resources/startNextRaceButton.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initialises the buttons for the end race state
     */
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

    /**
     * draws the content for the end race state to the panel
     * 
     * @param g the graphics object to draw to
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setFont(RESULTS_FONT);
        for (int i = 0; i < 4; i ++){
            g.drawImage(tables[i], tablesPositions[i][0], tablesPositions[i][1], null);
            for(int j = 0; j < nextRace; j ++){
                g.drawString(String.format("%.2f", times[i][j]), tablesPositions[i][0] + textOffests[j][0] + 70, tablesPositions[i][1] + textOffests[j][1]);
                g.drawString(ordinals[results[i][j]-1], tablesPositions[i][0] + textOffests[j][0], tablesPositions[i][1] + textOffests[j][1]);
            }
        }
    }

    @Override
    public void update() {}

    /**
     * adds the buttons to the panel
     */
    @Override
    public void showButtons() {
        Game.instance.add(nextRaceButton);
    }

    /**
     * removes buttons from the panel
     */
    @Override
    public void hideButtons() {
        Game.instance.remove(nextRaceButton);
    }

    /**
     * sets the results for the specified boat
     * 
     * @param boatNumber the boat number in the array
     * @param time the time the boat got in the last race
     * @param position the position the boat ranks out of the other boats
     */
    public void setResult(int boatNumber, float time, int position) {
        times[boatNumber][nextRace] = time;
        results[boatNumber][nextRace] = position;
    }
}