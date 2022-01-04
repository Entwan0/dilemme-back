package Strategy.newStrategy;

import java.util.List;

import local.round.Choice;
import local.round.Player;
import Strategy.Strategy;

/*
 * 
 * @author Allassane 
 * Graduel coopérer jusqu'a ce que l'adversaire trahisse 
 * puis trahir autant de fois que l'adversaire a trahi et continuer avec deux coopérations
 *
 */

public class StrategyGraduel implements Strategy {

	private boolean firstDecision = true;
	private boolean cooperate = true;
	private int countTrahison;

	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		if (this.firstDecision) {
			this.firstDecision = false;
		}
		if (currentPlayer.getChoices().get(otherPlayer.getChoices().size() - 1) == Choice.Betray && this.cooperate) {
			this.cooperate = false;
			this.setTrahison(otherPlayer.getChoices());
		}
		if (this.cooperate) {
			return Choice.Cooperate;
		} else {
			if (this.countTrahison > 0) {
				this.countTrahison--;
				return Choice.Betray;
			} else {
				if (this.countTrahison == -1) {
					this.cooperate = true;
				}
				this.countTrahison--;
				return Choice.Cooperate;
			}
		}
	}

	private void setTrahison(List<Choice> ennemiesList) {
		this.countTrahison = 0;
		for (Choice c : ennemiesList) {
			if (c == Choice.Betray) {
				this.countTrahison++;
			}
		}
	}
}