import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private BufferedImage background;
    private BufferedImage startGame;
    private JButton startButton;

    public MenuState(GameStateManager gsm){
        super(gsm);
        initButtons();
    }

    @Override
    public void loadImages(String[] images) {
        try{
            for (int i = 0; i < images.length; i++){
                if(images[i] == "MenuStateBackground.png"){
                    background = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if(images[i] == "MenuStateStartGame.png"){
                    startGame = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initButtons(){
        startButton = new JButton(new ImageIcon(getClass().getResource("/Resources/MenuStateStartGame.png")));

        startButton.setBounds(150, 200, 167, 47);

        ButtonModel startButtonModel = startButton.getModel();
        startButtonModel.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (startButtonModel.isPressed()){
                    gsm.setState(1);
                }
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
    public void showButtons(){
        Game.instance.add(startButton);
    }

    @Override
    public void hideButtons(){
        Game.instance.remove(startButton);
    }
}
