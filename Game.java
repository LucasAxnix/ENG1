import java.awt.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable {
    private static final double TICKRATE = 60;
    private static final int windowWidth = 1080;
    private static final int windowHeight = 720;
    private boolean isGameRunning = false;
    private boolean initialised = false;
    public JFrame window;

    public static Game instance;

    public static void main(String[] args) {
        Thread thread = new Thread(getInstance());
        thread.start();
    }

    public void run() {
        isGameRunning = true;
        setupWindow();
        update();
    }

    private void setupWindow() {
        window = new JFrame("Game name");

        window.setPreferredSize(new Dimension(windowWidth, windowHeight));
        window.getContentPane().add(instance);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        GameStateManager.getInstance().draw(g);
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

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}