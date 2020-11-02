public class Boat{
	private int Speed;
	private int Acceleration;
	private int Durability;
	private int Maneuverability;
	
	public Boat(){
	}
	
	public Boat(int Speed, int Acceleration,int Durability, int Maneuverability){
		this.Speed = Speed;
		this.Acceleration = Acceleration;
		this.Durability = Durability;
		this.Maneuverability = Maneuverability;
	}
	
	public Boat copyObject(Boat bt){
		this.Speed = bt.Speed;
		this.Acceleration = bt.Acceleration;
		this.Durability = bt.Durability;
		this.Maneuverability = bt.Maneuverability;
		return bt;
	}
	//Initiate by calling "Boat boatname = new Boat(speed, acc, dur, man)"
