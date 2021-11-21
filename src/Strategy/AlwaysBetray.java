package Strategy;

import round.*;

public class AlwaysBetray implements Strategy {
	
	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer ) {
		System.out.println(" AUTOMATIC "); 
		System.out.println(currentPlayer.getName() + " choosed to betray"); 
		return Choice.Betray;
		
	}
}
