package model;

public class Injury extends Action {

	private Player injured;

	public Injury(int timeHappened, Player injured) {
		super(timeHappened);
		this.injured = injured;
	}

	public Player getInjured() {
		return injured;
	}

	public void setInjured(Player injured) {
		this.injured = injured;
	}

}
