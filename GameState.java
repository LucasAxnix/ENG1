import java.util.ArrayList;
import java.awt.*;

public abstract class GameState {

    protected ArrayList<GameEntity> gameEntities;
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        gameEntities = new ArrayList<GameEntity>();
    }

    public abstract void initButtons();
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void loadImages(String[] images);
    public abstract void showButtons();
    public abstract void hideButtons();
}