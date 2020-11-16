
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boat extends GameEntity {
	private int speed;
	private int acceleration;
	private int durability;
	private int maneuverability;
	private boolean isPlayer;
<<<<<<< Updated upstream
=======
	private int boatHealth;
	private float speed;
	private River river;
	private int startYPosition;
>>>>>>> Stashed changes

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

	public void resetPosition() {
		x = 50;
		y = startYPosition;
	}

	public void setPlayerBoat() {
		this.isPlayer = true;
		RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
		river = rs.getRiver();
		x = 50;
		y = 300;
		startYPosition = y;
	}

	public void setOpponentBoat(int racePos) {
		RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
		river = rs.getRiver();
		x = 50;
		switch (racePos) {
			case 1:
				y = 10;
				break;
			case 2:
				y = 580;
				break;
			case 3:
				y = 660;
				break;
		}
		startYPosition = y;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(super.sprite, x, y, null);
	}

	@Override
	public void update() {
		speed += acceleration * 0.05f;
		speed = Math.min(speed, maxSpeed);
		if (!isPlayer) {
<<<<<<< Updated upstream
			x += speed / 60;
		} else {
			RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
			for (Obstacle o : rs.getRiver().getObstacles()) {
				if (collision(o))
					System.out.println("collision");
=======
			x += speed - river.getSpeed();
		} else {
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
>>>>>>> Stashed changes
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