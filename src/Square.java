
public abstract class Square {
	private int squareLocation;
	
	
	protected Square(int squareLocation) {
		this.squareLocation = squareLocation;
	}
	
	public int getSquareLocation(){
		return this.squareLocation;
	}

	public void setMoney(Player p) {
		p.getPlayerMoney().setMoney(200);
	}
}
