import java.util.ArrayList;

public class GameStateManager{

    private ArrayList<GameState> gameStateList;
    public int currentState;

    public static final int MENUSTATE = 0;
    public static final int RACESTATE = 1;
    public static final int ENDRACESTATE = 2;
    public static final int PODIUMSTATE = 3;

    public GameStateCtrl(){
        gameStateList = new ArrayList<GameState>();
        currentState = 0;
    }

    public void setState(int state){
        currentState = state;
        gameStateList.get(currentState).init();
    }

    public void update(){
        gameStateList.get(currentState).update();
    }

    public void draw(){
        gameStateList.get(currentState).draw();
    }
}
