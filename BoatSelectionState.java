<<<<<<< Updated upstream
=======
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

>>>>>>> Stashed changes
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.PortUnreachableException;

public class BoatSelectionState extends GameState{

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

    public BoatSelectionState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void loadImages(String[] images) {
        super.loadImages(images);
        try{
            for (int i = 0; i < images.length;i++){
<<<<<<< Updated upstream
                if(images[i] == "SelectBoatStateBackground.png"){
                    background = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if(images[i] == "Back.png"){
                    back = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {
                    };
=======
                if (images[i] == "blue.png"){
                    background = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "greenBoat.png"){
                    greenBoat = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "lilacBoat.png"){
                    redBoat = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "redBoat.png"){
                    lilacBoat = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "orangeBoat.png"){
                    orangeBoat = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "selectBoatTitle.png"){
                    title = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "Back.png"){
                    back = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
>>>>>>> Stashed changes
                }
                if (images[i] == "selectBoat1.png"){
                    selectBoat1 = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
                }
                if (images[i] == "selectBoat2.png"){
                    selectBoat2 = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
                }
                if (images[i] == "selectBoat3.png"){
                    selectBoat3 = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
                }
                if (images[i] == "selectBoat4.png"){
                    selectBoat4 = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initButtons(){
        backButton = new JButton(back);
<<<<<<< Updated upstream

        backButton.setBounds(0,0, 109, 39);

        ButtonModel model = backButton.getModel();
        model.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (model.isPressed()){
=======
        backButton.setBounds(0, 0, 109, 39);
        ButtonModel backButtonModel = backButton.getModel();
        backButtonModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (backButtonModel.isPressed()){
                    System.out.println("Pressed");
>>>>>>> Stashed changes
                    gsm.setState(0);
                }
            }
        });

        selectBoat1Button = new JButton(selectBoat1);
        selectBoat1Button.setBounds(200, 200, 266, 50);
        selectBoat1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(2);
            }
        });

        selectBoat2Button = new JButton(selectBoat2);
        selectBoat2Button.setBounds(200, 300, 266, 50);
        selectBoat2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(2);
            }
        });

        selectBoat3Button = new JButton(selectBoat3);
        selectBoat3Button.setBounds(200, 400, 266, 50);
        selectBoat3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(2);
            }
        });

        selectBoat4Button = new JButton(selectBoat4);
        selectBoat4Button.setBounds(200, 500, 266, 50);
        selectBoat4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(2);
            }
        });
    }

    @Override
<<<<<<< Updated upstream
    public void showButtons(){
=======
    public void showButtons() {
        Game.instance.add(selectBoat1Button);
        Game.instance.add(selectBoat2Button);
        Game.instance.add(selectBoat3Button);
        Game.instance.add(selectBoat4Button);
>>>>>>> Stashed changes
        Game.instance.add(backButton);
    }

    @Override
    public void hideButtons(){
        Game.instance.remove(selectBoat1Button);
        Game.instance.remove(selectBoat2Button);
        Game.instance.remove(selectBoat3Button);
        Game.instance.remove(selectBoat4Button);
        Game.instance.remove(backButton);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics g) {
<<<<<<< Updated upstream
        super.draw(g);
        g.drawImage(background, 0, 0,null);
=======
        g.drawImage(background, 0, 0, null);
        g.drawImage(greenBoat, 30, 200, null);
        g.drawImage(lilacBoat, 30, 300, null);
        g.drawImage(redBoat, 30, 400, null);
        g.drawImage(orangeBoat, 30, 500, null);
        g.drawImage(title,290,50, null);
>>>>>>> Stashed changes
    }
}
