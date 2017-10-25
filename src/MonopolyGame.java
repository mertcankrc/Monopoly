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
				System.out.println("\nPlayer" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());
				
				do {
					die1.roll();
					die2.roll();
					System.out.println("Player"+player[i].getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() );
					player[i].piece.setLocation(player[i].piece.getLocation() + die1.getFaceValue() + die2.getFaceValue());
					System.out.println("Player" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());	
					
					if(die1.getFaceValue() == die2.getFaceValue())
						System.out.println("Player"+player[i].getPlayerID() + " rolled dice are equal,  rolling again" );
					turncount++;
				}while(die1.getFaceValue() == die2.getFaceValue() && turncount <3);
	
			}
		}
		
		System.out.println("\n\n\tGAME OVER");
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
