import java.awt.*;

public abstract class GameEntity {

    private int x;
    private int y;
    private int width;
    private int height;

    public GameEntity(){}

    public void draw(Graphics g){}

    public void update(){}
}
//g.setColor(new Color(66,134,244));
// g.drawRect(x,y,width,height);