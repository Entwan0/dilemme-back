package Strategy;

import round.Choice;
import round.Player;

public class GiveOrTake implements Strategy {
	
	
	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		
		
		Choice choiceoth = otherPlayer.getChoice(otherPlayer.getChoices().size());
		Choice choicecurr;
		
		switch (choiceoth) {
			case Betray :
				System.out.println("AUTOMATIC"); 
				System.out.println(currentPlayer.getName() + " choosed to betray"); 
				choicecurr = Choice.Betray;				
				break;
			default: 
				System.out.println("AUTOMATIC"); 
				System.out.println(currentPlayer.getName() + " choosed to cooperate "); 
				choicecurr = Choice.Cooperate;				
				break;
		}
		
		return choicecurr;

		
	}

}
