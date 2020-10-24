import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;


public class GameStateManager{

    private ArrayList<GameState> gameStateList;
    public int currentState;

    public static final int MENUSTATE = 0;
    public static final int RACESTATE = 1;
    public static final int ENDRACESTATE = 2;
    public static final int PODIUMSTATE = 3;

    public GameStateManager(){
        gameStateList = new ArrayList<GameState>();
        currentState = 0;
        //gameStateList.add(new game)
    }

    public void setState(int state){
        currentState = state;
    }

    public void update(){
        gameStateList.get(currentState).update();
    }

    public void draw(Graphics g){
        gameStateList.get(currentState).draw(g);
    }

    /*public static void main(String[] args){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("TMBoat.png"));
        } catch (IOException e) {
        }
        Graphics g = img.getGraphics();
        g.drawImage(img,0,0,null);
    }*/
}

