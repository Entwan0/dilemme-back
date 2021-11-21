package local.round;

import Strategy.*;

public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public Choice executeStrategy(Player currplayer, Player othplayer) {
		return strategy.getChoice(currplayer, othplayer);
	}

}
