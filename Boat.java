import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boat extends GameEntity {
	private int maxSpeed;
	private int acceleration;
	private int durability;
	private int maneuverability;
	private boolean isPlayer;
	private int boatHealth;
	private float speed;
	private River river;
	private int startYPosition;
	private float timer;
	private float penalty;
	private boolean finishedRace;
	private int racePos;
	private String name;

	public Boat(int maxSpeed, int acceleration, int durability, int maneuverability, BufferedImage sprite,
			String name) {
		super(0, 0, sprite);
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.durability = durability;
		this.maneuverability = maneuverability;
		this.name = name;
		boatHealth = 100;
		timer = 0;
	}

	public Boat copyObject(Boat bt) {
		this.speed = bt.speed;
		this.acceleration = bt.acceleration;
		this.durability = bt.durability;
		this.maneuverability = bt.maneuverability;
		return bt;
	}

	public void reset() {
		x = 50;
		y = startYPosition;
		finishedRace = false;
		timer = 0;
		penalty = 0;
	}

	public void increaseFatigue(int raceNumber) {
		if (raceNumber > 0) {
			maxSpeed *= 0.8f;
			acceleration *= 0.8f;
			maneuverability *= 0.8f;
		}
	}

	public void setPlayerBoat() {
		finishedRace = false;
		this.isPlayer = true;
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
		river = rs.getRiver();
		x = 50;
		y = 300;
		startYPosition = y;
	}

	public void setOpponentBoat(int raceLane) {
		finishedRace = false;
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
		river = rs.getRiver();
		x = 50;
		switch (raceLane) {
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
		increaseTimer();
		speed += acceleration * 0.05f;
		speed = Math.min(speed, maxSpeed);

		ArrayList<Obstacle> obstacles = river.getObstacles();
		for (int i = 0; i < obstacles.size(); i++) {
			if (collision(obstacles.get(i))) {
				if (isPlayer) {
					boatHealth -= durability;
					if (boatHealth <= 0) {
						GameStateManager.getInstance().setState(GameStateManager.GAMEOVERSTATE);
					}
				}
				speed = 0;
				river.removeObstacle(obstacles.get(i));
			}
		}

		if (!isPlayer) {
			x += speed - river.getSpeed();
		} else {
			if (!finishedRace) {
				if (y + sprite.getHeight() > Game.WINDOW_HEIGHT || y < 0) {
					GameStateManager.getInstance().setState(GameStateManager.GAMEOVERSTATE);
				}
				if (y + sprite.getHeight() > 574 || y < 72) {
					penalty++;
				}
			}
			river.setSpeed(speed);

			if (InputManager.getInstance().getUp()) {
				y -= 1;
			}
			if (InputManager.getInstance().getDown()) {
				y += 1;
			}
		}
	}

	private void increaseTimer() {
		if (!finishedRace)
			timer++;
	}

	public void finished() {
		if (finishedRace)
			return;
		finishedRace = true;
	}

	public boolean getFinished() {
		return finishedRace;
	}

	public float getTimer() {
		return (float) (timer / Game.TICK_RATE);
	}

	public float getFinalTime() {
		return (float) ((timer + penalty) / Game.TICK_RATE);
	}

	public int getPosition() {
		return racePos;
	}

	public void setPosition(int racePos) {
		this.racePos = racePos;
	}

	public String getName() {
		return name;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public int getHealth() {
		return boatHealth;
	}

	public float getPenalty() {
		return (float) (penalty / Game.TICK_RATE);
	}
}
