package tools;
import java.util.Random;
import local.round.Choice;

public class Tools {
	
	private static Random r = new Random();
	
	public static int randomBetween0And1() {
		return Tools.r.nextInt(2);
	}
	
	public static int randomBetween0And2() {
		return Tools.r.nextInt(3);
	}
	
	public static Choice generateRandomChoice() {
		if(Tools.randomBetween0And1()==0) {
			return Choice.Betray;
		}
		return Choice.Cooperate;
	}
}
