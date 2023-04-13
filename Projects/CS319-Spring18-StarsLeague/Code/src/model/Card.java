package model;

public abstract class Card extends Action {

	private Player player;

	public Card(int timeHappened, Player player) {
		super(timeHappened);
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
