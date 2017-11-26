import java.util.Random;
import java.util.Scanner;

public class MonopolyGame {
	
	static Board board;
	static Die die1;
	static Die die2;
	static Player[] player;
	static Scanner scanner;
	public static void main(String[] args) {
	
		board = new Board();
		die1 = new Die();
		die2 = new Die();
		
		int numberOfIterations=0, numberOfPlayers=0;
		String numberOfIte, numberOfP;		
		
		scanner = new Scanner(System.in);
	
		System.out.print("Enter number of players: ");
		numberOfP = scanner.next();
		numberOfPlayers = checkInteger(numberOfP);
		
		while ( numberOfPlayers < 2 || numberOfPlayers > 8) {
			System.out.print("\nPlease, enter player number between 2 and 8: ");
			numberOfP = scanner.next();
			numberOfPlayers = checkInteger(numberOfP);
		}
		player = new Player[numberOfPlayers];
		for ( int i=0 ; i<numberOfPlayers ; i++ ) {
			player[i] = new Player(i + 1);
		}
		
		System.out.print("\nEnter number of iteration: ");
		numberOfIte = scanner.next();
		numberOfIterations = checkInteger(numberOfIte);
		
		while ( numberOfIterations < 1) {
			System.out.print("\nPlease, enter positive number: ");
			numberOfIte = scanner.next();
			numberOfIterations = checkInteger(numberOfIte);
		}
		
		int turncount;
		for( ; numberOfIterations>0; numberOfIterations--) {
			System.out.println("\n============================================================");
			System.out.println("Remaining number of iterations " + numberOfIterations);
			System.out.println("============================================================");
			for (int i = 0; i<numberOfPlayers; i++) {
				turncount = 0;
				if(player[i].getBankruptcy() == true) {		//check bankrupt or not
					continue;
				}
				
				if(player[i].getJail() == true) {
					System.out.println("\nPlayer" + player[i].getPlayerID() + " is in Jail ( " + player[i].getPlayerPiece().getLocation() + "th square ).");
					if(player[i].getPlayerMoney().getMoney() > 200) {
						System.out.println("\nPlayer" + player[i].getPlayerID() +" paid 50" + player[i].getPlayerMoney().getType() + " and it got out of Jail.");
						player[i].getPlayerMoney().setMoney(-50);
						player[i].setJail(false);
					}
					else {
						die1.roll();
						die2.roll();
						if(player[i].getRolledDiceInJail() == 2) {
							System.out.println("Player" + player[i].getPlayerID() + " failed to roll double 3 times and it got out of Jail.");
							player[i].setRolledDiceInJail(0);
							player[i].setJail(false);
						}
						
						else if(die1.getFaceValue() == die2.getFaceValue()) {
							System.out.println("Player"+player[i].getPlayerID() + " rolled dice are equal and it got out of Jail." );
							player[i].setJail(false);
							player[i].setRolledDiceInJail(0);
							move(player[i],numberOfPlayers);
						}
						else {
							System.out.println("Player"+player[i].getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() + ".Dice are not equal.Player stays in jail.");
							player[i].setRolledDiceInJail(player[i].getRolledDiceInJail()+1);
						}
						continue;
					}
				}
				
				
				System.out.println("\nPlayer" + player[i].getPlayerID() + " is in Square "+ player[i].getPlayerPiece().getLocation());
				
				do {
					die1.roll();
					die2.roll();
					
					turncount++;
					if(turncount == 3) {		//if player rolls double 3 times in a row it goes to jail square.
						System.out.println("\nPlayer" + player[i].getPlayerID() + " rolled double 3 times in a row.Player" + player[i].getPlayerID() + " goes to Jail.");
						player[i].setJail(true);
						player[i].getPlayerPiece().setLocation(10);
						continue;
					}
					
					move(player[i],numberOfPlayers);
					if(player[i].getJail() == true)
						break;
					
					if(die1.getFaceValue() == die2.getFaceValue())
						System.out.println("Player"+player[i].getPlayerID() + " rolled dice are equal,  rolling again" );
					
					
				}while(die1.getFaceValue() == die2.getFaceValue() && turncount <3);
			}
			
		}
		
		System.out.println("\n\n\tGAME OVER");
	}
	
	
	


	private static void move(Player p, int numberOfPlayers) {
		System.out.println("Player"+p.getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() );
		p.getPlayerPiece().setLocation(p.getPlayerPiece().getLocation() + die1.getFaceValue() + die2.getFaceValue());
		System.out.println("Player" + p.getPlayerID() + " is in Square "+ p.getPlayerPiece().getLocation());	
		squareAction(p,p.getPlayerPiece().getLocation(),p.getPlayerID(),numberOfPlayers);
		
	}





	public static void squareAction(Player p, int squareNO, int p_no, int numberOfPlayers) {
		switch (squareNO){
	    case 0: 	//Go square
	    	
	    	System.out.println("Player" + p_no + " is in Go Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	        board.getSquare(squareNO).setMoney(p);
	        System.out.println("Player" + p_no + " gets 200" + p.getPlayerMoney().getType() + ".Player money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	        break;
	    
	    case 4: 	//Income Tax Square
	    	System.out.println("Player" + p_no + " is in Income Tax Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	board.getSquare(squareNO).setMoney(p);
	    	System.out.println("Player" + p_no + " lost %10 of its money.Player money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	        break;
	    
	    case 5:		//Luck Card Squares
	    case 15:
	    case 25:
	    case 35:
	    	System.out.println("Player" + p_no + " is in Luck Card Square." + "Player" + p_no + " will draw a luck card." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	drawLuckCard(p,numberOfPlayers);
	    	board.changeCardLocations();
	    	break;
	    	
	    case 10:	//Jail Square
	    	System.out.println("Player" + p_no + " is in Jail Square as visitor." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	break;
	    	
	    case 20:	//Free Parking Square
	    	System.out.println("Player" + p_no + " is in Free Parking Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	break;
	    
	    case 30:	//Go To Jail Square
	    	System.out.println("Player" + p_no + " is in Go to Jail Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	p.getPlayerPiece().setLocation(10);
	    	p.setJail(true);
	    	System.out.println("Player" + p_no + " moved to Jail Square.");
	    	break;
	    	
	    case 38:	//Luxury Tax Square
	    	System.out.println("Player" + p_no + " is in Luxury Tax Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney());
	    	board.getSquare(squareNO).setMoney(p);
	    	System.out.println("Player" + p_no + " lost 75" + p.getPlayerMoney().getType() + ".Player's money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	break;
	    
	    default:	//Empty Square
	    	System.out.println("Player" + p_no + " is in Empty Square." + "Player" + p_no + "'s money was "+ p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
	    	break;
		}
		
		p.checkBankruptcy();
		checkGameEnds(numberOfPlayers);
	}
	
	public static void drawLuckCard(Player p,int numberOfPlayers) {
		String card_type = board.getLuckCard(0).getType();
		
		if(card_type.equals("GoToStart")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will go to GoSquare(Square 0).");
			p.getPlayerPiece().setLocation(0);
			System.out.println("Player" + p.getPlayerID() + " is in Go Square.");
	        board.getSquare(0).setMoney(p);
	        System.out.println("Player" + p.getPlayerID()+ " gets 200" + p.getPlayerMoney().getType() + ".Player money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
		}
		else if(card_type.equals("GoBack3Squares")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will go back 3 squares.But player will not do the action of that square.");
			p.getPlayerPiece().setLocation(p.getPlayerPiece().getLocation()-3);
		}
		else if(card_type.equals("GoToJail")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will go to Jail.");
			p.getPlayerPiece().setLocation(10);
	    	p.setJail(true);
		}
		else if(card_type.equals("PayPoorTax")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will pay 15" + p.getPlayerMoney().getType() + ".");
			p.getPlayerMoney().setMoney(-15);
			System.out.println("Player" + p.getPlayerID() + "'s money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
		}
		else if(card_type.equals("ChairmanOfTheBoard")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will give 50" + p.getPlayerMoney().getType() + " to each other player.");
			for(int i=0;i<numberOfPlayers;i++) {
				if(i+1 == p.getPlayerID() || p.getBankruptcy() == true)
					continue;
				if(p.getPlayerMoney().getMoney()-50 > 0) {
					p.getPlayerMoney().setMoney(-50);
					player[i].getPlayerMoney().setMoney(50);
				}
			}
		}
		else if(card_type.equals("CrosswordCompetition")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will collect 100" + p.getPlayerMoney().getType() + ".");
			p.getPlayerMoney().setMoney(100);
			System.out.println("Player" + p.getPlayerID() + "'s money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
		}
		else if(card_type.equals("GoForward3Squares")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will go back 3 squares.But player will not do the action of that square.");
			p.getPlayerPiece().setLocation(p.getPlayerPiece().getLocation()+3);
		}
		else if(card_type.equals("Jackpot")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will collect 300" + p.getPlayerMoney().getType() + ".");
			p.getPlayerMoney().setMoney(300);
			System.out.println("Player" + p.getPlayerID() + "'s money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
		}
		else if(card_type.equals("PayRichTax")) {
			System.out.println("Player" + p.getPlayerID() + " drew " + card_type + " card." + "Player" + p.getPlayerID() + " will pay 50" + p.getPlayerMoney().getType() + ".");
			p.getPlayerMoney().setMoney(-50);
			System.out.println("Player" + p.getPlayerID() + "'s money is " + p.getPlayerMoney().getMoney() + p.getPlayerMoney().getType() + ".");
		}

	}





	public static void checkGameEnds(int numberOfPlayers) {
		int bankruptedPlayers=0, winner=0;
		
		for(int i=0; i<numberOfPlayers; i++) {
			if(player[i].getBankruptcy())
				bankruptedPlayers++;
			else
				winner = i;
		}
		
		if(numberOfPlayers - bankruptedPlayers == 1) {
			System.out.println("\nPlayer" + player[winner].getPlayerID() + " won the game with " + player[winner].getPlayerMoney().getMoney() + player[winner].getPlayerMoney().getType() + ".");
			System.out.println("\n\n\tGAME OVER");
			System.exit(0);
		}
	}

	
	//Checks the value is integer or not 
	public static int checkInteger(String s) {
		int numberOfPlayers=0;
		do {
			try {
				numberOfPlayers = Integer.parseInt(s);
				break;
			} catch(NumberFormatException e) {
				System.out.print("You have to enter an integer: ");
				s = scanner.next();
			}
		}while(true);
		return numberOfPlayers;
	}
}
