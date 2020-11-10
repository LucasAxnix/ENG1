  
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GameEntity {

    protected int x;
    protected int y;
    protected BufferedImage sprite;

    public GameEntity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void draw(Graphics g){
        g.drawImage(sprite,x,y,null);
    }

    public void update(){}

    public void moveLeft(int distance){
        x -= distance;
    }

    public void moveRight(int distance){
        x += distance;
    }

    public boolean collision(GameEntity gameEntity2){
        int[] topLeft1 = {x,y};
        int[] bottomLeft1 = {x,y + sprite.getHeight()};
        int[] topRight1 = {x + sprite.getWidth(), y};
        int[] bottomRight1 = {x,y + sprite.getHeight()};

        int[] topLeft2 = {gameEntity2.x,gameEntity2.y};
        int[] topRight2 = {gameEntity2.x + gameEntity2.sprite.getWidth(), gameEntity2.y};

        //top left inside object 2?
        if ((topLeft1[0] > topLeft2[0]  && topLeft1[0] < topRight2[0]) && (topLeft1[1] > topLeft1[1] && topLeft1[1] < bottomLeft1[1])){ 
            return true;
        }

        //bottom left inside object 2?
        if ((bottomLeft1[0] > topLeft2[0]  && bottomLeft1[0] < topRight2[0]) && (bottomLeft1[1] > topLeft1[1] && bottomLeft1[1] < bottomLeft1[1])){
            return true;
        }

        //top right inside object 2?
        if ((topRight1[0] > topLeft2[0]  && topRight1[0] < topRight2[0]) && (topRight1[1] > topLeft1[1] && topRight1[1] < bottomLeft1[1])){
            return true;
        }

        //bottom right inside object 2?
        if ((bottomRight1[0] > topLeft2[0]  && bottomRight1[0] < topRight2[0]) && (bottomRight1[1] > topLeft1[1] && bottomRight1[1] < bottomLeft1[1])){
            return true;
        }

        return false;
    }
}