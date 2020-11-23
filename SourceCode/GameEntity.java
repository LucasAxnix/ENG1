
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameEntity {

    public int x;
    public int y;
    public BufferedImage sprite;
    
    /**
     * GameEntity constructor
     * <p>
     * sets the entities position in the world and sets the image to represent the entity
     * 
     * @param x the x position of the entity
     * @param y the y position of the entity
     * @param sprite the image used to represent the entity
     */
    public GameEntity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void draw(Graphics g);

    public abstract void update();
    
    /**
     * checks if two game entities are occupying the same space
     * 
     * @param entity the entity to check if this entity is colliding with
     * @return a boolean of whether they are colliding or not
     */
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