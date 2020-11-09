  
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
}