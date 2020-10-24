import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel implements Runnable {
    private static final double TICKRATE = 60;
    private static final int windowWidth = 640;
    private static final int windowHeight = 480;
    private boolean isGameRunning = false;
    
    public static Game instance;

    public static void main(String[] args) {
        instance = new Game();
        Thread thread = new Thread(instance);
        thread.start();
    }

    public void run() {
        instance.isGameRunning = true;
        setupWindow();
        instance.update();
    }

    private void setupWindow() {
        JFrame window = new JFrame("Game name");

        window.setPreferredSize(new Dimension(windowWidth, windowHeight));
        window.getContentPane().add(instance);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(66, 134, 244));
        g.fillRect(0, 0, windowWidth, windowHeight);
    }

    public void update() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / TICKRATE;
        double delta = 0;
        int update = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(isGameRunning) {
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

    }
}