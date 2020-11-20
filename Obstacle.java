import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Obstacle extends GameEntity {

    int obstaclePositionX;

    /**
     * Obstacle constructor
     *  
     * @param x the x position of the obstacle
     * @param y the y position of the obstacle
     * @param sprite the image used to represent the obstacle
     */
    public Obstacle(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
        obstaclePositionX = x;
    }

    /**
     * draws the obstacle at position (x, y) 
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
    
    @Override
    public void update() {
    }
}