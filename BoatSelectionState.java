import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoatSelectionState extends GameState{

    private BufferedImage background;
    private ImageIcon back;
    private JButton backButton;

    public BoatSelectionState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void loadImages(String[] images) {
        try {
            for (int i = 0; i < images.length;i++){
                if (images[i] == "SelectBoatStateBackground.png"){
                    background = ImageIO.read(getClass().getResource("/Resources/"+images[i]));
                }
                if (images[i] == "Back.png"){
                    back = new ImageIcon(getClass().getResource("/Resources/" + images[i])) {};
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initButtons(){
        backButton = new JButton(back);

        backButton.setBounds(0, 0, 109, 39);

        ButtonModel backButtonModel = backButton.getModel();
        backButtonModel.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (backButtonModel.isPressed()){
                    gsm.setState(0);
                }
            }
        });
    }

    @Override
    public void showButtons() {
        Game.instance.add(backButton);
    }

    @Override
    public void hideButtons(){
        Game.instance.remove(backButton);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
