import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {
    private boolean isGameRunning = false;
    private boolean initialised = false;
    public JFrame window;
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    public static final float TICK_RATE = 144;
    public static Game instance;

    public static void main(String[] args) {
        instance = new Game();
        instance.start();
    }

    /**
     * initialises the game window and starts the game loop
     */
    public void start() {
        instance.isGameRunning = true;
        setupWindow();
        initialised = true;
        GameStateManager.getInstance().setState(0);
        instance.update();
    }

    /**
     * initialises the window
     */
    private void setupWindow() {
        window = new JFrame("Game name");

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(instance);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    /**
     * calls draw in GameStateManager once the window is initialised
     * 
     * @param g the graphics object to draw to
     */
    @Override
    public void paintComponent(Graphics g) {
        if (initialised) {
            GameStateManager.getInstance().draw(g);
        }
    }

    /**
     * the main game loop
     * <p>
     * starts the update loop, it will continue to run until isGameRunning is set to false.
     * calls draw as frequently as possible and calls tick TICK_RATE times per second
     */
    public void update() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / TICK_RATE;
        double delta = 0;
        int update = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (isGameRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                update++;
                delta--;
            }
            // draw
            repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(update + " Ticks, FPS " + frames);
                update = 0;
                frames = 0;
            }
        }
    }

    /**
     * calls tick in GameStateManager
     */
    private void tick() {
        GameStateManager.getInstance().update();
    }
}