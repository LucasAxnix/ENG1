import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boat extends GameEntity {
	private float maxSpeed;
	private float acceleration;
	private int durability;
	private int maneuverability;
	private boolean isPlayer;
	private int boatHealth;
	private float speed;
	private River river;
	private int startYPosition;
	private int timer;
	private int penalty;
	private boolean finishedRace;
	private int racePos;
	private String name;

	/**
	 * boat constructor
	 * <p>
	 * sets all the boats stats
	 * 
	 * @param maxSpeed the max speed the boat can accelerate to
	 * @param acceleration the amount of speed the boat gains per tick
	 * @param durability the amount of health the boat loses when colliding with an object
	 * @param maneuverability the speed the boat can move up and down
	 * @param sprite the image used to represent the boat
	 * @param name the name of the boat used to display on the leaderboard
	 */
	public Boat(float maxSpeed, float acceleration, int durability, int maneuverability, BufferedImage sprite,
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

	/**
	 * resets the boat's race dependant stats
	 */
	public void reset() {
		x = 50;
		y = startYPosition;
		finishedRace = false;
		timer = 0;
		penalty = 0;
		speed = 0;
	}

	
	/** 
	 * increases the fatigue of the boat
	 * <p>
	 * decreases the boats stats as the people in the boat get more tired after each race
	 * 
	 * @param raceNumber the current race number the game is at
	 */
	public void increaseFatigue(int raceNumber) {
		if (raceNumber > 0) {
			maxSpeed *= 0.9f;
			acceleration *= 0.9f;
			maneuverability *= 0.9f;
		}
	}

	/**
	 * sets the boat to be the boat the player controls
	 */
	public void setPlayerBoat() {
		finishedRace = false;
		this.isPlayer = true;
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
		river = rs.getRiver();
		x = 50;
		y = 300;
		startYPosition = y;
	}

	
	/** 
	 * sets the boat to be a non player controlled boat
	 * 
	 * @param raceLane the lane that the boat is occupying
	 */
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

	
	/** 
	 * draws the boat
	 * 
	 * @param g the graphics object to draw to
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(super.sprite, x, y, null);
	}

	/**
	 * updates the boat's status in the game
	 * <p>
	 * manages the boat's speed, collisions with obstacles, health and bounds checking
	 */
	@Override
	public void update() {
		increaseTimer();
		speed += acceleration * 0.01f;
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
				speed = maxSpeed / 4f;
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
				y -= maneuverability;
			}
			if (InputManager.getInstance().getDown()) {
				y += maneuverability;
			}
		}
	}

	/**
	 * increases the boat's timer (in ticks) in the current race
	 */
	private void increaseTimer() {
		if (!finishedRace)
			timer++;
	}

	/**
	 * sets the boat's finished status to true
	 */
	public void setFinished() {
		finishedRace = true;
	}

	
	/** 
	 * gets whether the boat has finished
	 * 
	 * @return the boat's finished status 
	 */
	public boolean getFinished() {
		return finishedRace;
	}

	
	/** 
	 * gets the boat's current timer in seconds
	 * 
	 * @return the boat's timer in seconds
	 */
	public float getTimer() {
		return timer / Game.TICK_RATE;
	}

	
	/** 
	 * gets the boat's final timer in seconds
	 * 
	 * @return the boat's timer in seconds
	 */
	public float getFinalTime() {
		return (timer + penalty) / Game.TICK_RATE;
	}
	
	/** 
	 * gets the boat's position in the race
	 * 
	 * @return the boat's position
	 */
	public int getPosition() {
		return racePos;
	}

	
	/** 
	 * sets the boat's position in the race
	 * 
	 * @param racePos the position for the boat to be set to
	 */
	public void setPosition(int racePos) {
		this.racePos = racePos;
	}

	
	/** 
	 * gets the name of the boat
	 * 
	 * @return the boat's name
	 */
	public String getName() {
		return name;
	}

	
	/** 
	 * is the boat the players's boat
	 * 
	 * @return whether the boat is the player boat
	 */
	public boolean isPlayer() {
		return isPlayer;
	}

	
	/** 
	 * gets the health of the boat
	 * 
	 * @return the boat's health
	 */
	public int getHealth() {
		return boatHealth;
	}

	
	/** 
	 * gets the current time penalty of the boat in seconds
	 * 
	 * @return the current time penalty of the boat in seconds
	 */
	public float getPenalty() {
		return (float) (penalty / Game.TICK_RATE);
	}
}
