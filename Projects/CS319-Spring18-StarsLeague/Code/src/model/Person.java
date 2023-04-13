package model;

import java.io.Serializable;

public abstract class Person implements Serializable{

	private String name;
	private int age;
	private int height;
	private int weight;
	private String nationality;

	public Person(String name, int age, int height, int weight, String nationality) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.nationality = nationality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
