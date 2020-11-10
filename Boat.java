  
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boat extends GameEntity {
	private int speed; // pixels per second
	private int acceleration; // 
	private int durability;
	private int maneuverability;
	public boolean isPlayer;

	public Boat(int speed, int acceleration, int durability, int maneuverability, BufferedImage sprite) {
		super(0, 360, sprite);
		this.speed = speed;
		this.acceleration = acceleration;
		this.durability = durability;
		this.maneuverability = maneuverability;
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
		}
	}
}
	//Initiate by calling "Boat boatname = new Boat(speed, acc, dur, man)"