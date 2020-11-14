
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boat extends GameEntity {
	private int speed;
	private int acceleration;
	private int durability;
	private int maneuverability;
	private boolean isPlayer;

	public Boat(int speed, int acceleration, int durability, int maneuverability, BufferedImage sprite) {
		super(0, 0, sprite);
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

	public void setPlayerBoat() {
		this.isPlayer = true;
		super.x = 50;
		super.y = 300;
	}

	public void setOpponentBoat(int racePos) {
		super.x = 50;
		switch (racePos) {
			case 1:
				super.y = 10;
				break;
			case 2:
				super.y = 580;
				break;
			case 3:
				super.y = 660;
				break;

		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(super.sprite, x, y, null);
	}

	@Override
	public void update() {
		if (!isPlayer) {
			x += speed / 60;
		} else {
			RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
			for (Obstacle o : rs.getRiver().getObstacles()) {
				if (collision(o))
					System.out.println("collision");
			}
			if (InputManager.getInstance().getUp()) {
				y -= 1;
			}
			if (InputManager.getInstance().getDown()) {
				y += 1;
			}
		}
	}
}
// Initiate by calling "Boat boatname = new Boat(speed, acc, dur, man)"