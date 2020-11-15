import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {
    private static final double TICKRATE = 60;
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    private boolean isGameRunning = false;
    private boolean initialised = false;
    public JFrame window;

    public static Game instance;

    public static void main(String[] args) {
        instance = new Game();
        instance.start();
    }

    public void start() {
        instance.isGameRunning = true;
        setupWindow();
        initialised = true;
        GameStateManager.getInstance().loadImages();
        GameStateManager.getInstance().setState(0);
        instance.update();
    }

    private void setupWindow() {
        window = new JFrame("Game name");

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(instance);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (initialised) {
            GameStateManager.getInstance().draw(g);
        }
    }

    public void update() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / TICKRATE;
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

    private void tick() {
        GameStateManager.getInstance().update();
    }
}