import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel implements Runnable {
    private static final double TICKRATE = 60;
    private static final int windowWidth = 1080;
    private static final int windowHeight = 720;
    private boolean isGameRunning = false;
    private GameStateManager gsm;
    public JFrame window;

    public static Game instance;

    public static void main(String[] args) {
        instance = new Game();
        Thread thread = new Thread(instance);
        thread.start();
    }

    public void run() {
        instance.isGameRunning = true;
        setupWindow();
        initGameStates();
        instance.update();
    }

    private void initGameStates(){
        gsm = new GameStateManager();

        String[] menuStateImages = {"MenuStateBackground.png", "MenuStateStartGame.png", "MenuStateStartGameArmed.png"};
        gsm.loadState(0, menuStateImages);

        String[] selectBoatStateImages = {"SelectBoatStateBackground.png", "Back.png"};
        gsm.loadState(1, selectBoatStateImages);

        //String[] raceStateImages = {};
        //gsm.loadState(2, raceStateImages);

        //String[] EndRaceStateImages = {};
        //gsm.loadState(3, raceStateImages);

        //String[] PodiumStateImages = {};
        //gsm.loadState(4, raceStateImages);
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
        gsm.draw(g);
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
            gsm.update();
        }
    }

    private void tick() {
    }
}