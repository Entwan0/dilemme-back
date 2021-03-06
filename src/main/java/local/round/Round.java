package local.round;

import Strategy.*;
import Strategy.newStrategy.*;

public class Round {

	private int numRound;
	private Player player1;
	private Player player2;

	public Round(int numRound, Player player1, Player player2) {
		this.numRound = numRound;
		this.player1 = player1;
		this.player2 = player2;
	}

	public void askchoice(Player currPlayer, Player othPlayer) {
		System.out.println(currPlayer.getName() + "'s turn");
		System.out.println("Cooperate or Betray ?");
		System.out.println(" C to cooperate, B to betray");
		System.out.println(" Any other button to leave the game ");

		if (numRound > 1) {

			System.out.println(othPlayer.getName() + "'s last choice was " + othPlayer.getChoice(numRound - 1));
		}

		System.out.println("");
		
		Choice choice;
		if (!currPlayer.isAbandoned()) {
			String choice_user = LectureClavier.lireChaine();

			System.out.println("You entered " + choice_user);

			switch (choice_user) {
			case "C":
			case "c":
				System.out.println("You've choosed to cooperate");
				choice = Choice.Cooperate;
				break;

			case "b":
			case "B":
				System.out.println("You've choosed to betray");
				choice = Choice.Betray;
				break;

			default:
				System.out.println(" Thanks for playing ! ");
				InitautomaticPlay(currPlayer, othPlayer);
				choice = runAutomatic(currPlayer, othPlayer);
				break;
			}
		} else {
			choice = runAutomatic(currPlayer, othPlayer);
		}

		currPlayer.addChoice(choice);

	}

	public void InitautomaticPlay(Player currPlayer, Player othPlayer) {
		System.out.println(" You abondonned the game !");
		currPlayer.setAbandoned(true);
		System.out.println(" Choose a strategy for the rest of your choices  ");
		System.out.println(
				" G for Give or take : In every round you follow the other player's choices on the round before ( this option is not available on the firs round ");
		System.out.println(" B to always betray ");
		System.out.println(" C to always cooperate");
		System.out.println(" GR to gradual");
		System.out.println(" GTG to give to give");
		System.out.println(" GTWOG to give two give");
		System.out.println(" GTWOGR to give two give randomly");

		String choice_user = LectureClavier.lireChaine();
		while (("G".equals(choice_user)) ||
				("g".equals(choice_user)) ||
				("GR".equals(choice_user)) ||
				("gr".equals(choice_user)) &&
				(numRound == 1)) {
			System.out.println(" this Strategy is not possible on the first round  ");
			choice_user = LectureClavier.lireChaine();
		}

		switch (choice_user) {
		case "C":
		case "c":
			currPlayer.setStrategy(Strat.AlwaysCooperate);
			break;

		case "B":
		case "b":
			currPlayer.setStrategy(Strat.AlwaysBetray);
			break;
			
		case "G":
		case "g":
			currPlayer.setStrategy(Strat.GiveOrTake);
			break;
			
		case "GR":
		case "gr":
			currPlayer.setStrategy(Strat.Gradual);
			break;
			
		case "GTG":
		case "gtg":
			currPlayer.setStrategy(Strat.GiveToGive);
			break;
			
		case "GTWOG":
		case "gtwog":
			currPlayer.setStrategy(Strat.GiveTwoGive);
			break;
			
		case "GTWOGR":
		case "gtwogr":
			currPlayer.setStrategy(Strat.GiveTwoGiveRandomly);
			break;

		default:
			currPlayer.setStrategy(Strat.AlwaysCooperate);
			break;

		}
	}

	public Choice runAutomatic(Player currPlayer, Player othPlayer) {
		Strat strategy = currPlayer.getStrategy();
		Choice choice;
		Context context;
		switch (strategy) {
		case AlwaysBetray:
			context = new Context(new AlwaysBetray());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;

		case AlwaysCooperate:
			System.out.println("You've choosed to cooperate");
			context = new Context(new AlwaysCooperate());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;
			
		case GiveOrTake:
			System.out.println("You've choosed to cooperate");
			context = new Context(new GiveOrTake());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;
			
		case Gradual:
			System.out.println("You've choosed to Gradual");
			context = new Context(new StrategyGraduel());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;
			
		case GiveTwoGive:
			System.out.println("You've choosed to give two give");
			context = new Context(new StrategyDonnantDeuxDonnant());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;
			
		case GiveTwoGiveRandomly:
			System.out.println("You've choosed to give two give randomly");
			context = new Context(new StrategyDonnantDeuxDonnantAleatoire());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;
			
		case GiveToGive:
			System.out.println("You've choosed to give to give");
			context = new Context(new StrategyDonnantDonnant());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;

		default:
			currPlayer.setStrategy(Strat.AlwaysCooperate);
			context = new Context(new AlwaysCooperate());
			choice = context.executeStrategy(currPlayer, othPlayer);
			break;

		}
		return choice;
	}

	public void treat_choices() {
		System.out.println(numRound);
		Choice choice_p1 = player1.getChoice(numRound);
		Choice choice_p2 = player2.getChoice(numRound);

		if (choice_p1 == choice_p2) {

			if (choice_p1 == Choice.Cooperate) {
				player1.setPoints(player1.getPoints() + Point.C.getValue());
				player2.setPoints(player2.getPoints() + Point.C.getValue());
				System.out.println("You cooperate both you won 3 points \n");
			} else if (choice_p1 == Choice.Betray) {
				player1.setPoints(player1.getPoints() + Point.P.getValue());
				player2.setPoints(player2.getPoints() + Point.P.getValue());
				System.out.println("You betrayed both you won 1 point \n");
			}
		}

		else {

			if ((choice_p1 == Choice.Cooperate) && (choice_p2 == Choice.Betray)) {

				player1.setPoints(player1.getPoints() + Point.D.getValue());
				player2.setPoints(player2.getPoints() + Point.T.getValue());
				System.out.println(player1.getName() + " cooperated but  " + player2.getName() + " betrayed ");
				System.out.println(player1.getName() + " got " + Point.D.getValue() + " points and" + player2.getName()
						+ " won " + Point.T.getValue());

			} else if ((choice_p2 == Choice.Cooperate) && (choice_p1 == Choice.Betray)) {
				player1.setPoints(player1.getPoints() + Point.T.getValue());
				player2.setPoints(player2.getPoints() + Point.D.getValue());
				System.out.println(player2.getName() + " cooperated but " + player1.getName() + " betrayed ");
				System.out.println(player2.getName() + " got " + Point.D.getValue() + " points and " + player1.getName()
						+ " won " + Point.T.getValue());

			}

		}

	}

	public void run_round() {
		askchoice(player1, player2);
		askchoice(player2, player1);
		treat_choices();
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

}
