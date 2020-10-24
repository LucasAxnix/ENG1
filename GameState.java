import java.util.ArrayList;
import java.awt.*;

public class GameState {

    private ArrayList<GameEntity> GameEntitys;

    public GameState(){
        GameEntitys = new ArrayList<GameEntity>();
    }

    public void update(){
        for (GameEntity ge : GameEntitys) {
            ge.update();
        }
    }

    public void draw(Graphics g){
        for (GameEntity ge : GameEntitys) {
            ge.draw(g);
        }
    }

}
