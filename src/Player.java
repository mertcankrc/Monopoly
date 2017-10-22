
public class Player {
	int playerID;
	
	Piece piece;
	
	protected Player(int playerID) {
		piece = new Piece();
		this.playerID = playerID ;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
}
