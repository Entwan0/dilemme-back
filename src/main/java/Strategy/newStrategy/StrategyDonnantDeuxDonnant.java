package Strategy.newStrategy;

import Strategy.Strategy;
import local.round.Choice;
import local.round.Player;
import tools.Tools;

/**
 * 
 * @author Yanis Donnant pour deux donnants - Comme donnant donnant sauf que
 *         l'adversaire doit faire le m�me choix deux fois de suite avant la
 *         r�ciprocit�.
 *
 */
public class StrategyDonnantDeuxDonnant implements Strategy {

	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		if (otherPlayer.getChoices().size() >= 2) {
			if (otherPlayer.getChoices().get(otherPlayer.getChoices().size() - 1) == otherPlayer.getChoices().get(otherPlayer.getChoices().size() - 2)) {
				return otherPlayer.getChoices().get(otherPlayer.getChoices().size() - 1);
			} else {
				return Tools.generateRandomChoice();
			}
		} else {
			return Tools.generateRandomChoice();
		}
	}

}