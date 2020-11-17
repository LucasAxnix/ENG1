
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameEntity {

    public int x;
    public int y;
    public BufferedImage sprite;

    public GameEntity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void draw(Graphics g);

    public abstract void update();

    public boolean collision(GameEntity entity) {
        // If one rectangle is on left side of other
        if (x >= entity.x + entity.sprite.getWidth() || entity.x >= x + sprite.getWidth())
            return false;
        // If one rectangle is above other
        if (y >= entity.y + entity.sprite.getHeight() || entity.y >= y + sprite.getHeight())
            return false;

        return true;
    }

}