import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputManager implements KeyListener {

    private static boolean up;
    private static boolean down;
    private static boolean left;
    private static boolean right;
    private static int speed_up = 0;

    private static InputManager instance;

    private InputManager() {

    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
            Game.instance.addKeyListener(instance);
            Game.instance.setFocusable(true);
            Game.instance.requestFocus();
        }
        return instance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            speed_up =10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            speed_up =0;
        }
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public int get_new_speed(){
        return speed_up;
    }// return the new speed_up given by the user.

    @Override
    public void keyTyped(KeyEvent e) {}
}