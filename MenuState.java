import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MenuState extends GameState{

    private BufferedImage background;
    private BufferedImage startGame;
    private JButton button1;

    public MenuState(GameStateManager gsm){
        super(gsm);
        initButtons();
    }

    @Override
    public void loadImages(String[] images) {
        super.loadImages(images);
        try{
            for (int i = 0; i < images.length;i++){
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
        button1 = new JButton(new ImageIcon(getClass().getResource("/Resources/MenuStateStartGame.png")));

        button1.setBounds(150,200, 167, 47);

        ButtonModel model = button1.getModel();
        model.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (model.isPressed()){
                    gsm.setState(1);
                }
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(background,0,0,null);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void showButtons(){
        Game.instance.add(button1);
    }

    @Override
    public void hideButtons(){
        Game.instance.remove(button1);
    }
}
