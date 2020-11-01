import java.util.ArrayList;
import java.awt.*;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class GameState {

    private ArrayList<GameEntity> GameEntitys;
    protected GameStateManager gsm;

    public GameState(GameStateManager Gsm){
        gsm = Gsm;
        GameEntitys = new ArrayList<GameEntity>();
    }

    public void initButtons(){ }

    public void update(){ }

    public void draw(Graphics g){
        /*for (GameEntity ge : GameEntitys) {
            ge.draw(g);
        }*/
    }

    public void loadImages(String[] images){}

    public void showButtons(){}
    public void hideButtons(){}
}
