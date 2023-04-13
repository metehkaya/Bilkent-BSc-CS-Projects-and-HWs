package model;

import java.io.Serializable;

public class Manager extends Person implements Serializable {

	private int overall;
	private int experience;

	public Manager(String name, int age, int height, int weight, String nationality, int overall, int experience) {
		super(name, age, height, weight, nationality);
		this.overall = overall;
		this.experience = experience;
	}

	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
