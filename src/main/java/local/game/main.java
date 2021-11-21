package local.game;

import local.round.LectureClavier;

public class main {

	public static void main(String[] args) {
		int nb = LectureClavier.lireEntier("Entrez le nombre de rencontre  : ");

		Game game = new Game();
		game.run_game("Islam", "Antoine", nb);
	}

}
