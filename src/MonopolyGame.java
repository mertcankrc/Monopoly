import java.util.Scanner;

public class MonopolyGame {
	
	static Board board;
	static Die die1;
	static Die die2;
	static Player[] player;
	
	public static void main(String[] args) {
	
		board = new Board();
		die1 = new Die();
		die2 = new Die();
		int die1_val;
		int die2_val;
		
		int numberOfIterations, numberOfPlayers;
				
		Scanner scanner = new Scanner(System.in);
	
		System.out.print("Enter player number: ");
		
		// integer girip girmediðini çek et
		numberOfPlayers = scanner.nextInt();
		
		while ( numberOfPlayers < 2 || numberOfPlayers > 8) {
			System.out.print("\nPlease, enter player number between 2 and 8: ");

			numberOfPlayers = scanner.nextInt();
		}
		player = new Player[numberOfPlayers];
		
		for ( int i = 0 ; i < numberOfPlayers ; i++ ) {
			player[i] = new Player(i + 1);
		}
		
		System.out.print("\nEnter number of iteration: ");
		
		numberOfIterations = scanner.nextInt();
		
		while ( numberOfIterations < 1) {
			System.out.print("\nPlease, enter positive number: ");
			numberOfIterations = scanner.nextInt();
		}
		
		for(; numberOfIterations > 0; numberOfIterations--) {
			System.out.println("\nRemaining number of iterations " + numberOfIterations);
			for (int i = 0; i<numberOfPlayers; i++) {
				System.out.println("\nPlayer" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());
				
				die1.roll();
				die2.roll();
				System.out.println("Player"+player[i].getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() );
				
				player[i].piece.setLocation(player[i].piece.getLocation() + die1.getFaceValue() + die2.getFaceValue());
				
				System.out.println("Player" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());
				
				if ( die1.getFaceValue() == die2.getFaceValue()) {
					
					System.out.println("Player"+player[i].getPlayerID() + " rolled dice are equal,  rolling again" );
					die1.roll();
					die2.roll();
					System.out.println("Player"+player[i].getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() );
					
					player[i].piece.setLocation(player[i].piece.getLocation() + die1.getFaceValue() + die2.getFaceValue());
					
					System.out.println("Player" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());
					
				}
				
				if ( die1.getFaceValue() == die2.getFaceValue()) {
					
					System.out.println("Player"+player[i].getPlayerID() + " rolled dice are equal,  rolling again" );
					die1.roll();
					die2.roll();
					System.out.println("Player"+player[i].getPlayerID() + " rolled " + die1.getFaceValue() + " , " + die2.getFaceValue() );
					
					player[i].piece.setLocation(player[i].piece.getLocation() + die1.getFaceValue() + die2.getFaceValue());
					
					System.out.println("Player" + player[i].getPlayerID() + " is in Square "+ player[i].piece.getLocation());
					
				}
				
				
			}
			
			
		}
	}

}
