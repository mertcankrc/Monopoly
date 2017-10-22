
public class Board {
	Square[] squareArray;
	
	protected Board() {
		squareArray = new Square[40];
		
		for(int i=0;i<40;i++) {
			squareArray[i] = new Square(i);
		}
			
	}
	
	
	
}
