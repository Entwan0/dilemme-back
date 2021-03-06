package local.game;

import java.util.*;

import local.round.*;

public class Game {

	private List<Round> rounds;
	private Player player1;
	private Player player2;

	public Game(List<Round> rounds, Player player1, Player player2) {
		this.rounds = rounds;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Game() {
		this.rounds = new ArrayList<Round>();
	}

	public Game(Player player1, Player player2) {
		this.rounds = new ArrayList<Round>();
		this.player1 = player1;
		this.player2 = player2;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void run_game(String namep1, String namep2, int numberOfRounds) {

		setPlayer1(new Player(namep1));
		setPlayer2(new Player(namep2));

		for (int i = 1; i <= numberOfRounds; i++) {
			Round round = new Round(i, player1, player2);
			round.run_round();
			rounds.add(round);
		}
		determine_winner(numberOfRounds);

	}

	public void determine_winner(int nb_rencontre) {
		int pointsplayer1 = rounds.get(nb_rencontre - 1).getPlayer1().getPoints();
		int pointsplayer2 = rounds.get(nb_rencontre - 1).getPlayer2().getPoints();

		if (pointsplayer2 < pointsplayer1) {
			System.out.println(" Winner is " + rounds.get(nb_rencontre - 1).getPlayer1().getName() + " with "
					+ pointsplayer1 + " points ");
		} else if (pointsplayer1 < pointsplayer2) {
			System.out.println(" Winner is " + rounds.get(nb_rencontre - 1).getPlayer2().getName() + " with "
					+ pointsplayer2 + " points ");
		} else {
			System.out.println(" Equal with " + pointsplayer1);
		}

	}
}
