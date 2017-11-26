import java.util.Random;

public class Board {
	private Square[] squareArray;
	private LuckCard[] luckCardArray;
	
	protected Board() {
		squareArray = new Square[40];
		luckCardArray = new LuckCard[10];
		
		for(int i=0;i<40;i++) {
			if (i == 0) 
				squareArray[i] = new GoSquare(i); 
			else if(i == 4) 
				squareArray[i] = new IncomeTaxSquare(i);
			else if(i == 5 || i == 15 || i == 25 || i == 35) 
				squareArray[i] = new LuckCardSquare(i);
			else if(i == 10) 
				squareArray[i] =  new JailSquare(i);
			else if(i == 20) 
				squareArray[i] = new FreeParkingSquare(i);
			else if(i == 30) 
				squareArray[i] = new GoToJailSquare(i);
			else if(i == 38) 
				squareArray[i] = new LuxuryTaxSquare(i);
			else 
				squareArray[i] = new EmptySquare(i);
		}
		
		for(int i=0;i<10;i++) {
			if(i == 0)
				luckCardArray[i] = new LuckCard("GoToStart",i);				//Go to GoSquare
			else if(i == 1)	
				luckCardArray[i] = new LuckCard("GoBack3Squares",i);		//Go back 3 squares
			else if(i == 2)	
				luckCardArray[i] = new LuckCard("GoToJail",i);				//Go to jail directly
			else if(i == 3)
				luckCardArray[i] = new LuckCard("PayPoorTax",i);			//Pay 15$ to bank
			else if(i == 4)
				luckCardArray[i] = new LuckCard("ChairmanOfTheBoard",i);	//Pay 50$ to each player
			else if(i == 5)
				luckCardArray[i] = new LuckCard("CrosswordCompetition",i);	//Collect 100$
			else if(i == 6)
				luckCardArray[i] = new LuckCard("GoForward3Squares",i);		//Go forward 3 squares
			else if(i == 7)
				luckCardArray[i] = new LuckCard("GoBack3Squares",i);		//Go back 3 squares
			else if(i == 8)
				luckCardArray[i] = new LuckCard("Jackpot",i);				//Collect 300$
			else if(i == 9)
				luckCardArray[i] = new LuckCard("PayRichTax",i);			//Pay 50$ to bank
		}
		
		shuffleDeck();
			
	}
	
	//Shuffle luck cards
	public void shuffleDeck(){
	    int index;
	    LuckCard temp;
	    Random random = new Random();
	    for (int i = luckCardArray.length - 1; i > 0; i--){
	        index = random.nextInt(i + 1);
	        temp = luckCardArray[index];
	        luckCardArray[index] = luckCardArray[i];
	        luckCardArray[i] = temp;
	    }
	    for(int i = 0; i<luckCardArray.length;i++)
	    	luckCardArray[i].setLocation(i);
	}
	
	//Put the top card to bottom
	public void changeCardLocations() {
	    LuckCard temp = luckCardArray[0];
	    for (int i = 1; i < luckCardArray.length; i++){
	    	luckCardArray[i-1] = luckCardArray[i];
	    }
	    luckCardArray[9] = temp;
	    for(int i = 0; i<luckCardArray.length;i++)
	    	luckCardArray[i].setLocation(i);
	}
	public Square getSquare(int loc) {
		return squareArray[loc];
	}
	
	public LuckCard getLuckCard(int loc) {
		return luckCardArray[loc];
	}
	
}
