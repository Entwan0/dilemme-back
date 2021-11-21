package server;

public class Round {
	private Game game = new Game();
	
	public void treat_choices(String J1, String J2) {
		Choice choiceJ1;
		Choice choiceJ2;

		if(J1.equals("betray")) {
			choiceJ1 = Choice.Betray;
		}else {
			choiceJ1 = Choice.Cooperate;
		}
		
		if(J2.equals("betray")) {
			choiceJ2 = Choice.Betray;
		}else {
			choiceJ2 = Choice.Cooperate;
		}
		
		if(choiceJ1 == choiceJ2) {
			if (choiceJ1 == Choice.Cooperate) {
				this.game.addPointToPLayers(Point.C.getValue());
			}else {
				this.game.addPointToPLayers(Point.P.getValue());
			}
		}else {
			if ((choiceJ1 == Choice.Cooperate) && (choiceJ2 == Choice.Betray)) {
				this.game.addPointToPLayer(1, Point.D.getValue());
				this.game.addPointToPLayer(2, Point.T.getValue());
			}else {
				this.game.addPointToPLayer(1, Point.T.getValue());
				this.game.addPointToPLayer(2, Point.D.getValue());
			}
		}
	}
	
	public Game getGame() {
		return this.game;
	}
}
