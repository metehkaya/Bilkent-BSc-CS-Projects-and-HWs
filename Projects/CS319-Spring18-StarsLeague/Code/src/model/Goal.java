package model;

import java.io.Serializable;

public class Goal extends Action implements Serializable{

	private Player scored;
	private Player assisted;

	public Goal(int timeHappened, Player scored, Player assisted) {
		super(timeHappened);
		this.scored = scored;
		this.assisted = assisted;
	}

	public Player getScored() {
		return scored;
	}

	public void setScored(Player scored) {
		this.scored = scored;
	}

	public Player getAssisted() {
		return assisted;
	}

	public void setAssisted(Player assisted) {
		this.assisted = assisted;
	}

}
