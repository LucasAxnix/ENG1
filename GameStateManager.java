import java.util.ArrayList;
import java.awt.*;


public class GameStateManager{

    private ArrayList<GameState> gameStateList;
    public int currentState;

    public static final int MENUSTATE = 0;
    public static final int SELECTBOATSTATE = 1;
    public static final int RACESTATE = 2;
    public static final int ENDRACESTATE = 3;
    public static final int PODIUMSTATE = 4;

    public GameStateManager(){
        gameStateList = new ArrayList<GameState>();
        currentState = 0;
        //gameStateList.add(new game)
        gameStateList.add(new MenuState(this));
        gameStateList.add(new BoatSelectionState(this));

        gameStateList.get(currentState).showButtons();
    }

    public void setState(int state){
        gameStateList.get(currentState).hideButtons();
        currentState = state;
        gameStateList.get(currentState).initButtons();
        gameStateList.get(currentState).showButtons();
    }

    public void update(){
        gameStateList.get(currentState).update();
    }

    public void draw(Graphics g){
        gameStateList.get(currentState).draw(g);
    }

    public void loadState(int stateNumber, String[] images){
        gameStateList.get(stateNumber).loadImages(images);
    }
}

