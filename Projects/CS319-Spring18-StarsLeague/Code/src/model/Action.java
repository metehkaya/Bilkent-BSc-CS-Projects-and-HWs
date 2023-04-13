package model;

import java.io.Serializable;

public abstract class Action implements Serializable {

	private int timeHappened;

	public Action(int timeHappened) {
		this.timeHappened = timeHappened;
	}

	public int getTimeHappened() {
		return timeHappened;
	}

	public void setTimeHappened(int timeHappened) {
		this.timeHappened = timeHappened;
	}

	public String toString() {
		String str = "";
		if (this.getClass() == Goal.class) {
			Goal goal = (Goal) this;
			str = "GOAL: " + goal.getTimeHappened() + "' - " + goal.getScored().getName() + " (Assist: "
					+ goal.getAssisted().getName() + ")";
		} else if (this.getClass() == YellowCard.class) {
			YellowCard yellowCard = (YellowCard) this;
			str = "Yellow Card: " + yellowCard.getTimeHappened() + "' - " + yellowCard.getPlayer().getName();
		} else if (this.getClass() == RedCard.class) {
			RedCard redCard = (RedCard) this;
			str = "RED CARD!: " + redCard.getTimeHappened() + "' - " + redCard.getPlayer().getName();
		} else {
			Injury injury = (Injury) this;
			str = "Injury: " + injury.getTimeHappened() + "' - " + injury.getInjured().getName();
		}
		return str;
	}

}
