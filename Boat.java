
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boat extends GameEntity {
	private int maxSpeed;
	private float acceleration;
	private int durability;
	private int maneuverability;
	private boolean isPlayer;
	private int boatHealth;
	private float speed;

	public Boat(int maxSpeed, float acceleration, int durability, int maneuverability, BufferedImage sprite) {
		super(0, 0, sprite);
		speed = 0;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.durability = durability;
		this.maneuverability = maneuverability;
		boatHealth = 100;
	}

	public Boat copyObject(Boat bt) {
		this.maxSpeed = bt.maxSpeed;
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
		if (isPlayer) {
			g.fillRect(Game.WINDOW_WIDTH - 200, 0, 200, 50);
			g.setColor(Color.red);
			g.fillRect(Game.WINDOW_WIDTH - 200, 0, Math.max(0, boatHealth * 2), 50);
		}
	}

	@Override
	public void update() {
		if (!isPlayer) {
			x += maxSpeed / 60;
		} else {
			speed += acceleration * 0.05f;
			speed = Math.min(speed, maxSpeed);
			RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
			River river = rs.getRiver();
			river.setSpeed(speed);
			ArrayList<Obstacle> obstacles = river.getObstacles();
			for (int i = 0; i < obstacles.size(); i++) {
				if (collision(obstacles.get(i))) {
					speed = 0;
					river.removeObstacle(obstacles.get(i));
					boatHealth -= durability;
					if (boatHealth < 0) {
						// TODO: end game
					}
				}
			}
			if (InputManager.getInstance().getUp()) {
				y -= maneuverability;
			}
			if (InputManager.getInstance().getDown()) {
				y += maneuverability;
			}
		}
	}
}
