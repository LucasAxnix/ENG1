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
    public static final int GAMEOVERSTATE = 4;
    public static final int PODIUMSTATE = 5;

    /**
     * GameStateManager contructor
     * <p>
     * creates all the states for the game
     */
    private GameStateManager() {
        gameStateList = new ArrayList<GameState>();
        gameStateList.add(new MenuState());
        gameStateList.add(new BoatSelectionState());
        gameStateList.add(new RaceState());
        gameStateList.add(new EndRaceState());
        gameStateList.add(new GameOverState());
        gameStateList.add(new PodiumState());
    }

    /**
     * gets the state that is passed
     * 
     * @param state the state to get
     * @return the game state requested
     */
    public GameState getState(int state) {
        if (state > 5 || state < 0) state = 0;
        return gameStateList.get(state);
    }

    /**
     * gets the singleton instance of GameStateManager
     * <p>
     * if instance is null, this method will create a new instance and return it
     * 
     * @return the singleton instance of GameStateManager
     */
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    /**
     * sets the state of the game
     * 
     * @param state the state to be set to
     */
    public void setState(int state) {
        gameStateList.get(currentState).hideButtons();
        currentState = state;
        gameStateList.get(currentState).initButtons();
        gameStateList.get(currentState).showButtons();
    }

    /**
     * gets the current state the game is in
     * 
     * @return the current state the game is in
     */
    public GameState getCurrentState() {
        return gameStateList.get(currentState);
    }

    /**
     * updates all the game states
     */
    public void update() {
        gameStateList.get(currentState).update();
    }

    /**
     * draws all the game states
     * 
     * @param g the graphics object to draw to
     */
    public void draw(Graphics g) {
        gameStateList.get(currentState).draw(g);
    }
}
