
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

<<<<<<< Updated upstream
    public boolean collision(GameEntity gameEntity2) {
        int[] tL1 = { x, y };// top left position of the first object
        int[] bL1 = { x, y + sprite.getHeight() }; // bottom left position of the first object
        int[] tR1 = { x + sprite.getWidth(), y }; // top right position of the first object
        int[] bR1 = { x, y + sprite.getHeight() }; // bottom right position of the first object

        int[] tL2 = { gameEntity2.x, gameEntity2.y };// top left position of the second object
        int[] bL2 = { gameEntity2.x, gameEntity2.y + sprite.getHeight() };// bottom left position of the second object
        int[] tR2 = { gameEntity2.x + gameEntity2.sprite.getWidth(), gameEntity2.y };// top right position of the second
                                                                                     // object
        int[] bR2 = { gameEntity2.x, gameEntity2.y + sprite.getHeight() };// bottom right position of the second object
=======
    public boolean collision(GameEntity entity) {
        // If one rectangle is on left side of other
        if (x >= entity.x + entity.sprite.getWidth() || entity.x >= x + sprite.getWidth())
            return false;
        // If one rectangle is above other
        if (y >= entity.y + entity.sprite.getHeight() || entity.y >= y + sprite.getHeight())
            return false;
>>>>>>> Stashed changes

        return true;
    }

}