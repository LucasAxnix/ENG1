import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FinishLine extends GameEntity {

	int finishLinePositionX;

    public FinishLine(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		finishLinePositionX = x;
    }

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

	@Override
	public void update() {}

}