import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Obstacle extends GameEntity {

    int obstaclePositionX;

    public Obstacle(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
        obstaclePositionX = x;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

    @Override
    public void update() {
    }
}