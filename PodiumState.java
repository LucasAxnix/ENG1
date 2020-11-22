import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class PodiumState extends GameState {
    private Icon mainMenu;
    private BufferedImage background;
    private int time;

    private HashMap<String, BufferedImage> boatImages;

    private JButton mainMenuButton;

    private Boat[] boatPositions;

    /**
     * PodiumState constructor
     */
    public PodiumState() {
        super();
        boatPositions = new Boat[3];
    }

    /**
     * initialises the buttons
     */
    @Override
    public void initButtons() {
        mainMenuButton = new JButton(mainMenu);
        mainMenuButton.setBounds(312, 558, mainMenu.getIconWidth(), mainMenu.getIconHeight());
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameStateManager.getInstance().setState(GameStateManager.MENUSTATE);
                RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
                rs.instantiateRiver();
            }
        });

    }

    /**
     * updates the poduim state
     */
    @Override
    public void update() {
        increaseTime();
    }

    /**
     * draws all the content related to the podium state
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(getBoatImage(boatPositions[0]), 500, 160 + (int) (Math.sin(time / (Game.TICK_RATE * 2)) * 10), null);
        g.drawImage(getBoatImage(boatPositions[1]), 325, 240 + (int) (Math.sin(time / (Game.TICK_RATE * 2)) * 10), null);
        g.drawImage(getBoatImage(boatPositions[2]), 700, 250 + (int) (Math.sin(time / (Game.TICK_RATE * 2)) * 10), null);
    }

    /**
     * initialises the images
     */
    @Override
    public void initImages() {
        try {
            boatImages = new HashMap<String, BufferedImage>();
            background = ImageIO.read(getClass().getResourceAsStream("Resources/podiumScreen.png"));
            boatImages.put("Lilac", ImageIO.read(getClass().getResourceAsStream("Resources/lilacBoatVertical.png")));
            boatImages.put("Red", ImageIO.read(getClass().getResourceAsStream("Resources/redBoatVertical.png")));
            boatImages.put("Green", ImageIO.read(getClass().getResourceAsStream("Resources/greenBoatVertical.png")));
            boatImages.put("Orange", ImageIO.read(getClass().getResourceAsStream("Resources/orangeBoatVertical.png")));
            mainMenu = new ImageIcon(getClass().getResource("Resources/mainMenu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * adds the buttons to the panel
     */
    @Override
    public void showButtons() {
        Game.instance.add(mainMenuButton);

    }

    /**
     * removes the buttons from the panel
     */
    @Override
    public void hideButtons() {
        Game.instance.remove(mainMenuButton);

    }

    /**
     * calculates the final positions of the boats
     */
    public void finalPositions() {
        RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
        ArrayList<Boat> boats = rs.getBoats();
        for (Boat b : boats) {
            int pos = b.getPosition();
            if (pos <= 3) {
                boatPositions[pos - 1] = b;
            }
        }
    }

    /**
     * gets the boat image of the boat passed
     * 
     * @param boat the boat to get it's image
     * @return the boat's image
     */
    private BufferedImage getBoatImage(Boat boat) {
        return boatImages.get(boat.getName());
    }

    /**
     * increases the ticks that the player has been on the poduim state
     */
    private void increaseTime() {
        time++;
    }
}
