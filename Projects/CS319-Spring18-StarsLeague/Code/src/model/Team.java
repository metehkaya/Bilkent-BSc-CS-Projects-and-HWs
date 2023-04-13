package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {

	private String name;
	private String color;
	private String tactic;
	private String stadium;
	private String history;
	private String nationality;
	private String nick;
	private double stars;
	private String style;
	private String tempo;
	private Manager manager;
	private President president;
	private ArrayList<Player> players;

	public Team(String name, String color, String tactic, String stadium, String history, String nationality,
			String nick, double stars, String style, String tempo, Manager manager, President president,
			ArrayList<Player> players) {
		this.name = name;
		this.color = color;
		this.tactic = tactic;
		this.stadium = stadium;
		this.history = history;
		this.nationality = nationality;
		this.nick = nick;
		this.stars = stars;
		this.style = style;
		this.tempo = tempo;
		this.manager = manager;
		this.president = president;
		this.players = players;
	}

	public boolean contains(Player p) {
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getName().equals(p.getName()))
				return true;
		return false;
	}

	public int indexOfPlayer(String name) {
		int ind = -1;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getName().equals(name)) {
				ind = i;
				break;
			}
		}
		return ind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTactic() {
		return tactic;
	}

	public void setTactic(String tactic) {
		this.tactic = tactic;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public President getPresident() {
		return president;
	}

	public void setPresident(President president) {
		this.president = president;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
