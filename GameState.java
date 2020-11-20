import java.util.ArrayList;
import java.awt.*;

public abstract class GameState {

    protected ArrayList<GameEntity> gameEntities;

    /**
     * GameState contructor
     */
    public GameState() {
        gameEntities = new ArrayList<GameEntity>();
        initImages();
        initButtons();
    }
    
    public abstract void initButtons();

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void initImages();

    public abstract void showButtons();

    public abstract void hideButtons();
}