package Strategy;

import local.round.*;
public class AlwaysCooperate implements Strategy {
	
	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		System.out.println("AUTOMATIC"); 
		System.out.println(currentPlayer.getName() + " choosed to cooperate"); 
		return Choice.Cooperate;
		
	}
}
