import java.util.ArrayList;
import java.awt.*;

public class GameStateManager {
    private static GameStateManager instance;

    private ArrayList<GameState> gameStateList;
    public int currentState;

    public static final int MENUSTATE = 0;
    public static final int SELECTBOATSTATE = 1;
    public static final int RACESTATE = 2;
    public static final int ENDRACESTATE = 3;
    public static final int PODIUMSTATE = 4;

    private GameStateManager() {
        gameStateList = new ArrayList<GameState>();
        gameStateList.add(new MenuState());
        gameStateList.add(new BoatSelectionState());
        gameStateList.add(new RaceState());
    }

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public void setState(int state) {
        gameStateList.get(currentState).hideButtons();
        currentState = state;
        gameStateList.get(currentState).initButtons();
        gameStateList.get(currentState).showButtons();
    }

    public GameState getCurrentState() {
        return gameStateList.get(currentState);
    }

    public void update() {
        gameStateList.get(currentState).update();
    }

    public void draw(Graphics g) {
        gameStateList.get(currentState).draw(g);
    }

    public void loadImages() {
        for (int i = 0; i < gameStateList.size(); i++) {
            gameStateList.get(i).initImages();
        }
    }
}
