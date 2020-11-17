import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

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
	private int time;
	private boolean finishedRace;

	private Font timerFont = new Font("Verdana", Font.BOLD, 30);

	public Boat(int maxSpeed, int acceleration, int durability, int maneuverability, BufferedImage sprite) {
		super(0, 0, sprite);
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.durability = durability;
		this.maneuverability = maneuverability;
		boatHealth = 100;
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

	public void increaseFatigue(int raceNumber) {
		if (raceNumber > 0) {
			maxSpeed *= 0.8f;
			acceleration *= 0.8f;
			maneuverability *= 0.8f;
		}
	}

	public void setPlayerBoat() {
		this.isPlayer = true;
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
		river = rs.getRiver();
		x = 50;
		y = 300;
		startYPosition = y;
	}

	public void setOpponentBoat(int racePos) {
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
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
		if (isPlayer) {
			g.fillRect(Game.WINDOW_WIDTH - 200, 0, 200, 50);
			g.setColor(Color.red);
			g.fillRect(Game.WINDOW_WIDTH - 200, 0, Math.max(0, boatHealth * 2), 50);
			g.setFont(timerFont);
			g.setColor(Color.black);
			g.drawString(String.valueOf(time / Game.TICK_RATE), Game.WINDOW_WIDTH / 2, 0);
		}
	}

	@Override
	public void update() {
		increaseTimer();
		speed += acceleration * 0.05f;
		speed = Math.min(speed, maxSpeed);
		if (!isPlayer) {
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

						GameStateManager.getInstance().setState(GameStateManager.GAMEOVERSTATE);
					}
				}
			}
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
			time++;
	}
}
