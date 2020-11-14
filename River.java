import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class River{
	private int raceNumber;
	private BufferedImage background;
	private BufferedImage duck;
	private BufferedImage log;
	private BufferedImage rock;
	private ArrayList<Obstacle> obstacles;

	private int[][][] obstaclePositions = {
		{//race one positions
			{400, 500},
			{1000, 300},
			{1200, 400},
			{1600, 300},
			{2000, 400}
		},
		{//race two positions TODO: add more obstacles
			{400, 500},
			{1000, 300},
			{1200, 400},
			{1600, 300},
			{2000, 400}
		},
		{//race three positions TODO: add more obstacles
			{400, 500},
			{1000, 300},
			{1200, 400},
			{1600, 300},
			{2000, 400}
		},
		{//race four positions TODO: add more obstacles
			{400, 500},
			{1000, 300},
			{1200, 400},
			{1600, 300},
			{2000, 400}
		}
	};


	public River(){
		try{
			background = ImageIO.read(getClass().getResource("/Resources/water.png"));
			duck = ImageIO.read(getClass().getResource("/Resources/duck.png"));
			log = ImageIO.read(getClass().getResource("/Resources/log.png"));
			rock = ImageIO.read(getClass().getResource("/Resources/rock.png"));
		} catch(Exception e){
			e.printStackTrace();
		}
		obstacles = new ArrayList<Obstacle>();
	}

	public void initRace(int _raceNumber){
		raceNumber = _raceNumber;
		Random rd = new Random();
		int[][] positions = obstaclePositions[raceNumber];
		for(int i = 0; i < obstacles.size(); i++){
			BufferedImage sprite = null;
			int random = rd.nextInt(2);
			if (random == 0){
				sprite = duck;
			}
			if (random == 0){
				sprite = log;
			}
			if (random == 0){
				sprite = rock;
			}
			Obstacle obstacle = new Obstacle(positions[i][0], positions[i][1], sprite);
			obstacles.add(obstacle);
		}
	}

	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
		for (Obstacle o : obstacles){
			o.draw(g); 
		}
	}
}
	
	
