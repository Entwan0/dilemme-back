package Strategy.newStrategy;

import Strategy.Strategy;
import local.round.Player;
import tools.Tools;
import local.round.Choice;

/**
 * 
 * @author Yanis Donnant donnant - Jouer comme le dernier coup de l'adversaire
 *
 */
public class StrategyDonnantDonnant implements Strategy {

	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		if (!otherPlayer.getChoices().isEmpty()) {
			return otherPlayer.getChoices().get(otherPlayer.getChoices().size() - 1);
		} else {
			return Tools.generateRandomChoice();
		}
	}

}
