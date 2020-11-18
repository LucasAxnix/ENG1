import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PodiumState extends GameState {
    private Icon mainMenu;
    private BufferedImage background;
    private int time;

    private HashMap<String, BufferedImage> boatImages;

    private JButton mainMenuButton;

    private Boat[] boatPositions;

    public PodiumState() {
        super();
        boatPositions = new Boat[3];
    }

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

    @Override
    public void update() {
        increaseTime();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(getBoatImage(boatPositions[0]), 500, 160 + (int) (Math.sin(time / 60f) * 10), null);
        g.drawImage(getBoatImage(boatPositions[1]), 325, 240 + (int) (Math.sin(time / 60f) * 10), null);
        g.drawImage(getBoatImage(boatPositions[2]), 700, 250 + (int) (Math.sin(time / 60f) * 10), null);
    }

    @Override
    public void initImages() {
        try {
            boatImages = new HashMap<String, BufferedImage>();
            background = ImageIO.read(getClass().getResource("/Resources/podiumScreen.png"));
            boatImages.put("Lilac", ImageIO.read(getClass().getResource("/Resources/lilacBoatVertical.png")));
            boatImages.put("Red", ImageIO.read(getClass().getResource("/Resources/redBoatVertical.png")));
            boatImages.put("Green", ImageIO.read(getClass().getResource("/Resources/greenBoatVertical.png")));
            boatImages.put("Orange", ImageIO.read(getClass().getResource("/Resources/orangeBoatVertical.png")));
            mainMenu = new ImageIcon(getClass().getResource("/Resources/mainMenu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showButtons() {
        Game.instance.add(mainMenuButton);

    }

    @Override
    public void hideButtons() {
        Game.instance.remove(mainMenuButton);

    }

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

    private BufferedImage getBoatImage(Boat boat) {
        return boatImages.get(boat.getName());
    }

    private void increaseTime() {
        time++;
    }
}
