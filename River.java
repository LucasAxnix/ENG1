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

	private int speed = 5;
	private int playerX = 0;

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
<<<<<<< Updated upstream
		initRace(0);
		finishLine = new FinishLine(500, 0, finishLineImage);
=======
>>>>>>> Stashed changes
	}

	public void initRace(int _raceNumber) {
		raceNumber = _raceNumber;
		obstacles.clear();
		Random rd = new Random();
		int raceLength = 6000;
		int numberOfShapes = 10;
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
		RaceState rs = (RaceState) GameStateManager.getInstance().getCurrentState();
		rs.resetBoats();
		finishLine = new FinishLine(raceLength + (raceLength / numberOfShapes), 0, finishLineImage);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, 0, null);
		for (Obstacle o : obstacles) {
			o.draw(g);
		}
		finishLine.draw(g);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}

	@Override
	public void update() {
<<<<<<< Updated upstream
		if (x == -1080) {
=======
		if (!initialised) {
			initRace(0);
		}
		if (x <= -1080) {
>>>>>>> Stashed changes
			x = 0;
		}
		x -= speed;
		playerX -= speed;
		for (Obstacle o : obstacles) {
			o.x = playerX + o.obstaclePositionX;
		}
		finishLine.x = playerX + finishLine.finishLinePositionX;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

<<<<<<< Updated upstream
	public FinishLine getFinishLine(){
=======
	public void removeObstacle(Obstacle obstacle) {
		obstacles.remove(obstacle);
	}

	public FinishLine getFinishLine() {
>>>>>>> Stashed changes
		return finishLine;
	}
}
