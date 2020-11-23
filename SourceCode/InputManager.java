import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    private static boolean up;
    private static boolean down;
    private static boolean left;
    private static boolean right;
    private static InputManager instance;

    /**
     * gets the singleton instance of InputManager
     * 
     * @return the singleton instance of InputManager
     */
    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
            Game.instance.addKeyListener(instance);
            Game.instance.setFocusable(true);
            Game.instance.requestFocus();
        }
        return instance;
    }

    /**
     * sets the movement input to true when a key is pressed
     */
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
        }
    }

    /**
     * sets the movement input to false when a key is released
     */
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
        }
    }
    
    /**
     * gets the up input
     * 
     * @return the up input
     */
    public boolean getUp() {
        return up;
    }

    /**
     * gets the up input
     * 
     * @return the down input
     */
    public boolean getDown() {
        return down;
    }

    /**
     * gets the up input
     * 
     * @return the left input
     */
    public boolean getLeft() {
        return left;
    }

    /**
     * gets the up input
     * 
     * @return the right input
     */
    public boolean getRight() {
        return right;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}