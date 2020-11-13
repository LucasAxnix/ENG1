import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Obstacle extends GameEntity {

    public Obstacle(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(super.sprite, x, y, null);
    }

    @Override
    public void update() {

    }

}