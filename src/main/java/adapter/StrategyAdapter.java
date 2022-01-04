package adapter;

import Strategy.Strategy;
import local.round.Choice;
import local.round.Player;
import local.round.Strat;
import Strategy.newStrategy.*;

public class StrategyAdapter implements Strategy {
	
	private Strategy stratChoose;
	
	public StrategyAdapter(int stratChoose) {
		super();
		this.stratChoose = this.defineSrategy(stratChoose);
	}
	
	private Strategy defineSrategy(int stratChoose) {
		switch(stratChoose) {
		case 0:
			return new StrategyGraduel();			
		default:
			return new StrategyGraduel();			
		}
	}
	
	public Strategy setStrategyToPlayer(Player player,Strat strat) {
		player.setStrategy(strat);
		return stratChoose;
	}
	
	@Override
	public Choice getChoice(Player currentPlayer, Player otherPlayer) {
		return this.stratChoose.getChoice(currentPlayer, otherPlayer);
	}
}
