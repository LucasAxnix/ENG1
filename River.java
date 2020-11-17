import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.*;

public class River extends GameEntity {

	private BufferedImage duck;
	private BufferedImage log;
	private BufferedImage rock;
	private ArrayList<Obstacle> obstacles;
	private FinishLine finishLine;
	private BufferedImage finishLineImage;
	private boolean initialised = false;

	private float speed;
	private int playerX;

	public int raceNumber;

	private int[][] obstacleShapes = new int[][] { { 520, 470, 420, 370, 320, 270 }, { 75, 125, 175, 225, 275, 325 },
			{ 520, 470, 420, 75, 125, 175 }, { 175, 470 }, {}, { 370, 320, 270 } };

	public River(BufferedImage background) {
		super(0, 0, background);
		try {
			duck = ImageIO.read(getClass().getResource("/Resources/duck.png"));
			log = ImageIO.read(getClass().getResource("/Resources/log.png"));
			rock = ImageIO.read(getClass().getResource("/Resources/rock.png"));
			finishLineImage = ImageIO.read(getClass().getResource("/Resources/finishLine.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		obstacles = new ArrayList<Obstacle>();
		finishLine = new FinishLine(0, 0, null);
	}

	public void initRace(int raceNumber) {
		this.raceNumber = raceNumber;
		obstacles.clear();
		Random rd = new Random();
		int raceLength = 6000;
		int numberOfShapes = 10;
		playerX = 0;
		speed = 0;
		for (int x = 600; x < raceLength; x += raceLength / numberOfShapes) {
			int[] yValues = obstacleShapes[rd.nextInt(obstacleShapes.length)];
			for (int y : yValues) {
				BufferedImage sprite = null;
				int random = rd.nextInt(3);
				if (random == 0)
					sprite = duck;
				else if (random == 1)
					sprite = log;
				else
					sprite = rock;
				Obstacle obstacle = new Obstacle(x, y, sprite);
				obstacles.add(obstacle);
			}
		}
		RaceState rs = (RaceState) GameStateManager.getInstance().getState(GameStateManager.RACESTATE);
		rs.resetBoats(raceNumber);
		finishLine = new FinishLine(raceLength + (raceLength / numberOfShapes), 0, finishLineImage);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, 0, null);
		finishLine.draw(g);
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.draw(g);
		}
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}

	@Override
	public void update() {
		if (!initialised) {
			initRace(0);
			initialised = true;
		}
		if (x <= -1080) {
			x = 0;
		}
		x -= speed;
		playerX -= speed;
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.x = playerX + o.obstaclePositionX;
		}
		finishLine.x = playerX + finishLine.finishLinePositionX;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void removeObstacle(Obstacle obstacle) {
		obstacles.remove(obstacle);
	}

	public FinishLine getFinishLine() {
		return finishLine;
	}
}
