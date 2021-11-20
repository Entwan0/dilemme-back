package round;

public class Round {

	private int numRound;	
	private Player player1;
	private Player player2;
	


	public Round(int numRound, Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void askchoice(Player player) {
		Choice choice  = null; 
		System.out.println(player.getName() + "'s turn");
		System.out.println("Cooperate or Betray ?");  
		System.out.println(" C to cooperate, B to betray"); 
		

		
		String choice_user = LectureClavier.lireChaine();
			
		System.out.println("You entered string " + choice_user);

					
		switch (choice_user) {
				case ("c") : 
					System.out.println("You've choosed to cooperate");
					choice = Choice.Cooperate;
					break;
				
				case ("b") : 
					System.out.println("You've choosed to betray"); 
					choice = Choice.Betray;
					break;
			}
		
 
        player.setChoice(choice);
		
		
		
	}
	

	public void treat_choices() {
		Choice choice_p1 = player1.getChoice();
		Choice choice_p2 = player2.getChoice();
		

		
		if (choice_p1 == choice_p2) {
			
			if (choice_p1 == Choice.Cooperate) {
				player1.setPoints(player1.getPoints() + Point.C.getValue());  
				player2.setPoints(player2.getPoints() + Point.C.getValue());    
				System.out.println("You cooperate both you won 3 points"); 
			}
			else if (choice_p1 == Choice.Betray) {
				player1.setPoints(player1.getPoints() + Point.P.getValue());  
				player2.setPoints(player2.getPoints() + Point.P.getValue());  
				System.out.println("You betrayed both you won 1 point"); 
			}
		}
		
		else {
			
			if((choice_p1 == Choice.Cooperate) && (choice_p2 == Choice.Betray)) {
				
				player1.setPoints( player1.getPoints() + Point.D.getValue());  
				player2.setPoints( player2.getPoints() + Point.T.getValue());  
				System.out.println(player1.getName() + " cooperated but  " + player2.getName() + " betrayed "); 
				System.out.println(player1.getName() + " got" + Point.D.getValue() + "points and" + player2.getName() + " won " + Point.T.getValue()); 
				
			}
			else {
				player1.setPoints(player1.getPoints() + Point.T.getValue());  
				player2.setPoints(player2.getPoints() + Point.D.getValue()); 
				System.out.println(player2.getName() + " cooperated but " + player1.getName() + " betrayed "); 
				System.out.println(player2.getName() + " got " + Point.D.getValue() + " points and " + player1.getName() + " won " + Point.T.getValue()); 

			}


			}
			
		}
		

	public void run_round(){
		askchoice(player1);
		askchoice(player2);
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
