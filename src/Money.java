
public class Money {
	int money;
	String type = "$";
			
	protected Money(int money) {
		this.money = money;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money += money;
	}
	
}
