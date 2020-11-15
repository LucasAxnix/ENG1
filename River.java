import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class River extends GameEntity {
	private int raceNumber;
	private BufferedImage background;
	private BufferedImage duck;
	private BufferedImage log;
	private BufferedImage rock;
	private ArrayList<Obstacle> obstacles;
	private FinishLine finishLine;
	private BufferedImage finishLineImage;

	private float speed = 0;
	private int playerX = 0;

	private int[][][] obstaclePositions = { { // race one positions
			{ 600, 520 }, { 600, 470 }, { 600, 420 }, { 600, 370 }, { 600, 320 }, { 600, 270 },
			{ 1400, 75 }, { 1400, 125 }, { 1400, 175 }, { 1400, 225 }, { 1400, 275 }, { 1400, 325 },
			{ 2500, 75 }, { 2500, 125 }, { 2500, 175 }, { 2500, 520 }, { 2500, 470 }, { 2500, 420 } },
			{ // race two positions TODO: add more obstacles
					{ 400, 500 }, { 1000, 300 }, { 1200, 400 }, { 1600, 300 }, { 2000, 400 } },
			{ // race three positions TODO: add more obstacles
					{ 400, 500 }, { 1000, 300 }, { 1200, 400 }, { 1600, 300 }, { 2000, 400 } },
			{ // race four positions TODO: add more obstacles
					{ 400, 500 }, { 1000, 300 }, { 1200, 400 }, { 1600, 300 }, { 2000, 400 } } };

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
		initRace(0);
		finishLine = new FinishLine(2500, 0, finishLineImage);
	}

	public void initRace(int _raceNumber) {
		raceNumber = _raceNumber;
		Random rd = new Random();
		int[][] positions = obstaclePositions[raceNumber];
		for (int i = 0; i < positions.length; i++) {
			BufferedImage sprite = null;
			int random = rd.nextInt(3);
			if (random == 0) {
				sprite = duck;
			}
			if (random == 1) {
				sprite = log;
			}
			if (random == 2) {
				sprite = rock;
			}
			Obstacle obstacle = new Obstacle(positions[i][0], positions[i][1], sprite);
			obstacles.add(obstacle);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, x, 0, null);
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g);
		}
		finishLine.draw(g);
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void update() {
		if (x <= -1080) {
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

	public void removeObstacle(Obstacle obstacle) {
		obstacles.remove(obstacle);
	}

	public FinishLine getFinishLine(){
		return finishLine;
	}
}
