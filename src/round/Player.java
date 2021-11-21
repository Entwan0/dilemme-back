package round;

public class Player {
	
	private String name;
	private int points; 
	private Choice choice;
	private boolean abandoned;
	
	
	public Player(String name, int points) {
		this.name = name;
		this.points = points;
		this.abandoned = false;
	}
	
	public Player(String name) {
		this.name = name;
		this.points = 0;
	}
	
	public Player() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Choice getChoice() {
		return choice;
	}
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	


}
