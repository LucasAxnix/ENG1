import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FinishLine extends GameEntity {

	int finishLinePositionX;

	/**
	 * FinishLine constructor
	 * 
	 * @param x the x position of the finish line
	 * @param y the y position of the finish line
	 * @param sprite the image for the finish line
	 */
	public FinishLine(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		finishLinePositionX = x;
	}

	/**
	 * draws the finish line
	 * 
	 * @param g the graphics object to draw to
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

	@Override
	public void update() {}
}