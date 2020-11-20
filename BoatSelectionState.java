import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BoatSelectionState extends GameState {

    private BufferedImage background;
    private BufferedImage greenBoat;
    private BufferedImage redBoat;
    private BufferedImage lilacBoat;
    private BufferedImage orangeBoat;
    private BufferedImage title;
    private ImageIcon back;
    private ImageIcon selectBoat1;
    private ImageIcon selectBoat2;
    private ImageIcon selectBoat3;
    private ImageIcon selectBoat4;
    private JButton backButton;
    private JButton selectBoat1Button;
    private JButton selectBoat2Button;
    private JButton selectBoat3Button;
    private JButton selectBoat4Button;

    /**
     * initialises the images for the boat selection state
     */
    @Override
    public void initImages() {
        try {
            background = ImageIO.read(getClass().getResource("/Resources/blue.png"));
            greenBoat = ImageIO.read(getClass().getResource("/Resources/greenBoat.png"));
            redBoat = ImageIO.read(getClass().getResource("/Resources/lilacBoat.png"));
            lilacBoat = ImageIO.read(getClass().getResource("/Resources/redBoat.png"));
            orangeBoat = ImageIO.read(getClass().getResource("/Resources/orangeBoat.png"));
            title = ImageIO.read(getClass().getResource("/Resources/selectBoatTitle.png"));

            back = new ImageIcon(getClass().getResource("/Resources/back.png"));
            selectBoat1 = new ImageIcon(getClass().getResource("/Resources/selectBoat1.png"));
            selectBoat2 = new ImageIcon(getClass().getResource("/Resources/selectBoat2.png"));
            selectBoat3 = new ImageIcon(getClass().getResource("/Resources/selectBoat3.png"));
            selectBoat4 = new ImageIcon(getClass().getResource("/Resources/selectBoat4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initialises the buttons for the selection state
     */
    @Override
    public void initButtons() {
        backButton = new JButton(back);
        backButton.setBounds(0, 0, 109, 39);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameStateManager.getInstance().setState(0);
            }
        });

        selectBoat1Button = new JButton(selectBoat1);
        selectBoat1Button.setBounds(200, 200, 266, 50);
        selectBoat1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerBoat(BoatType.GREEN);
                GameStateManager.getInstance().setState(2);
            }
        });

        selectBoat2Button = new JButton(selectBoat2);
        selectBoat2Button.setBounds(200, 300, 266, 50);
        selectBoat2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerBoat(BoatType.RED);
                GameStateManager.getInstance().setState(2);
            }
        });

        selectBoat3Button = new JButton(selectBoat3);
        selectBoat3Button.setBounds(200, 400, 266, 50);
        selectBoat3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerBoat(BoatType.LILAC);
                GameStateManager.getInstance().setState(2);
            }
        });

        selectBoat4Button = new JButton(selectBoat4);
        selectBoat4Button.setBounds(200, 500, 266, 50);
        selectBoat4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerBoat(BoatType.ORANGE);
                GameStateManager.getInstance().setState(2);
            }
        });
    }

    /**
     * sets the player's boat
     * 
     * @param boatType the boat type the player has selected
     */
    private void setPlayerBoat(BoatType boatType) {
        RaceState rs = (RaceState) (GameStateManager.getInstance().getState(GameStateManager.RACESTATE));
        rs.instantiateBoats(boatType);
    }

    /**
     * adds the buttons to the panel
     */
    @Override
    public void showButtons() {
        Game.instance.add(selectBoat1Button);
        Game.instance.add(selectBoat2Button);
        Game.instance.add(selectBoat3Button);
        Game.instance.add(selectBoat4Button);
        Game.instance.add(backButton);
    }

    /**
     * removes the buttons from the panel
     */
    @Override
    public void hideButtons() {
        Game.instance.remove(selectBoat1Button);
        Game.instance.remove(selectBoat2Button);
        Game.instance.remove(selectBoat3Button);
        Game.instance.remove(selectBoat4Button);
        Game.instance.remove(backButton);
    }

    @Override
    public void update() {}

    /**
     * draws all the images for the selection state
     * 
     * @param g the graphics object to draw to
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(greenBoat, 30, 200, null);
        g.drawImage(redBoat, 30, 400, null);
        g.drawImage(lilacBoat, 30, 300, null);
        g.drawImage(orangeBoat, 30, 500, null);
        g.drawImage(title, 290, 50, null);
    }
}
