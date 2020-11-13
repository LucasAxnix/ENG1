  
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boat extends GameEntity {
	private int speed;
	private int acceleration;
	private int durability;
	private int maneuverability;
	public boolean isPlayer;

	public Boat(int x, int y, int speed, int acceleration, int durability, int maneuverability, BufferedImage sprite, boolean isPlayer) {
		super(x, y, sprite);
		this.speed = speed;
		this.acceleration = acceleration;
		this.durability = durability;
		this.maneuverability = maneuverability;
		this.isPlayer = isPlayer;
	}

	public Boat copyObject(Boat bt) {
		this.speed = bt.speed;
		this.acceleration = bt.acceleration;
		this.durability = bt.durability;
		this.maneuverability = bt.maneuverability;
		return bt;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(super.sprite, x, y, null);
	}

	@Override
	public void update() {
		if (!isPlayer){
			x += speed / 60;
		} else {
			if (InputManager.getInstance().getUp()) {
				y -= 1;
			}
			if (InputManager.getInstance().getDown()) {
				y += 1;
			}
		}
	}
}
	//Initiate by calling "Boat boatname = new Boat(speed, acc, dur, man)"