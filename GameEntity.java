  
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
        int[] tL1 = {x,y};//top left position of the first object
        int[] bL1 = {x,y + sprite.getHeight()}; //bottom left position of the first object
        int[] tR1 = {x + sprite.getWidth(), y}; //top right position of the first object
        int[] bR1 = {x,y + sprite.getHeight()}; //bottom right position of the first object

        int[] tL2 = {gameEntity2.x,gameEntity2.y};//top left position of the second object
        int[] bL2 = {gameEntity2.x,gameEntity2.y + sprite.getHeight()};//bottom left position of the second object
        int[] tR2 = {gameEntity2.x + gameEntity2.sprite.getWidth(), gameEntity2.y};//top right position of the second object
        int[] bR2 = {gameEntity2.x,gameEntity2.y + sprite.getHeight()};//bottom right position of the second object

        //top left 1 inside object 2?
        if ((tL1[0] > tL2[0]  && tL1[0] < tR2[0]) && (tL1[1] > tL2[1] && tL1[1] < bL2[1])){ 
            return true;
        }
        //bottom left 1 inside object 2?
        if ((bL1[0] > tL2[0]  && bL1[0] < tR2[0]) && (bL1[1] > tL2[1] && bL1[1] < bL2[1])){
            return true;
        }
        //top right 1 inside object 2?
        if ((tR1[0] > tL2[0]  && tR1[0] < tR2[0]) && (tR1[1] > tL2[1] && tR1[1] < bL2[1])){
            return true;
        }
        //bottom right 1 inside object 2?
        if ((bR1[0] > tL2[0]  && bR1[0] < tR2[0]) && (bR1[1] > tL2[1] && bR1[1] < bL2[1])){
            return true;
        }

        //top left 2 inside object 1?
        if ((tL2[0] > tL1[0]  && tL2[0] < tR1[0]) && (tL2[1] > tL1[1] && tL2[1] < bL1[1])){ 
            return true;
        }
        //bottom left 2 inside object 1?
        if ((bL2[0] > tL1[0]  && bL2[0] < tR1[0]) && (bL2[1] > tL1[1] && bL2[1] < bL1[1])){
            return true;
        }
        //top right  2 inside object 1?
        if ((tR2[0] > tL1[0]  && tR2[0] < tR1[0]) && (tR2[1] > tL1[1] && tR2[1] < bL1[1])){
            return true;
        }
        //bottom right 2 inside object 1?
        if ((bR2[0] > tL1[0]  && bR2[0] < tR1[0]) && (bR2[1] > tL1[1] && bR2[1] < bL1[1])){
            return true;
        }
        
        return false;
    }
}