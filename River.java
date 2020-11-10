public class River{
	private int Level;
	private String Background;

	int[][] Obstacles_1 = new int[10][2];
	Obstacles_1[0][0] = 100;
	Obstacles_1[0][1] = 100;
	Obstacles_1[1][0] = 350;
	Obstacles_1[1][1] = 100;
	Obstacles_1[2][0] = 600;
	Obstacles_1[2][1] = 300;
	Obstacles_1[3][0] = 850;
	Obstacles_1[3][1] = 500;
	Obstacles_1[4][0] = 1100;
	Obstacles_1[4][1] = 300;
	Obstacles_1[5][0] = 1350;
	Obstacles_1[5][1] = 250;
	Obstacles_1[6][0] = 1600;
	Obstacles_1[6][1] = 100;
	Obstacles_1[7][0] = 1850;
	Obstacles_1[7][1] = 250;
	Obstacles_1[8][0] = 2100;
	Obstacles_1[8][1] = 400;
	Obstacles_1[9][0] = 2350;
	Obstacles_1[9][1] = 200;

	int[][] Obstacles_2 = new int[10][2];
	Obstacles_2[0][0] = 200;
	Obstacles_2[0][1] = 50;
	Obstacles_2[1][0] = 450;
	Obstacles_2[1][1] = 200;
	Obstacles_2[2][0] = 700;
	Obstacles_2[2][1] = 200;
	Obstacles_2[3][0] = 750;
	Obstacles_2[3][1] = 600;
	Obstacles_2[4][0] = 1200;
	Obstacles_2[4][1] = 500;
	Obstacles_2[5][0] = 1250;
	Obstacles_2[5][1] = 350;
	Obstacles_2[6][0] = 1500;
	Obstacles_2[6][1] = 150;
	Obstacles_2[7][0] = 1750;
	Obstacles_2[7][1] = 350;
	Obstacles_2[8][0] = 2200;
	Obstacles_2[8][1] = 500;
	Obstacles_2[9][0] = 2350;
	Obstacles_2[9][1] = 400;

	int[][] Obstacles_3 = new int[10][2];
	Obstacles_3[0][0] = 300;
	Obstacles_3[0][1] = 500;
	Obstacles_3[1][0] = 350;
	Obstacles_3[1][1] = 600;
	Obstacles_3[2][0] = 400;
	Obstacles_3[2][1] = 700;
	Obstacles_3[3][0] = 850;
	Obstacles_3[3][1] = 500;
	Obstacles_3[4][0] = 1000;
	Obstacles_3[4][1] = 100;
	Obstacles_3[5][0] = 1150;
	Obstacles_3[5][1] = 150;
	Obstacles_3[6][0] = 1800;
	Obstacles_3[6][1] = 500;
	Obstacles_3[7][0] = 1850;
	Obstacles_3[7][1] = 650;
	Obstacles_3[8][0] = 1900;
	Obstacles_3[8][1] = 600;
	Obstacles_3[9][0] = 2350;
	Obstacles_3[9][1] = 500;

	int[][] Obstacles_4 = new int[10][2];
	Obstacles_4[0][0] = 500;
	Obstacles_4[0][1] = 200;
	Obstacles_4[1][0] = 550;
	Obstacles_4[1][1] = 300;
	Obstacles_4[2][0] = 600;
	Obstacles_4[2][1] = 400;
	Obstacles_4[3][0] = 650;
	Obstacles_4[3][1] = 700;
	Obstacles_4[4][0] = 1100;
	Obstacles_4[4][1] = 700;
	Obstacles_4[5][0] = 1350;
	Obstacles_4[5][1] = 550;
	Obstacles_4[6][0] = 1700;
	Obstacles_4[6][1] = 200;
	Obstacles_4[7][0] = 150;
	Obstacles_4[7][1] = 250;
	Obstacles_4[8][0] = 2000;
	Obstacles_4[8][1] = 800;
	Obstacles_4[9][0] = 2350;
	Obstacles_4[9][1] = 100;

	public River(){
	}
	
	public River(int Level){
		if (Level == 1){
			this.Background = "/images/Background1.jpg";
			this.Obstacles = Obstacles_1;
		}
		elif (Level == 2){
			this.Background = "/images/Background2.jpg";
			this.Obstacles = Obstacles_2;
		}
		elif (Level == 3){
			this.Background = "/images/Background3.jpg";
			this.Obstacles = Obstacles_3;
		}
		elif (Level == 4){
			this.Background = "/images/Background4.jpg";
			this.Obstacles = Obstacles_4;
		}
	}
	
	
