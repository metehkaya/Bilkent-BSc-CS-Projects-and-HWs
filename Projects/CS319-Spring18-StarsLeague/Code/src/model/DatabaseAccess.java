package model;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseAccess {

	private static final int DIFF_SCORE = 10;
	private static final int MIN_SCORE = 90;

	public static int[] getAttributes() {
		return new int[8];
	}

	public static Team createBarcelona() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();
		// GK
		players.add(new Player("terStegen", 30, 180, 70, "Germany", "GK", 85, 'R', 30000000,
				70000000, true, getAttributes(), actions));
		// RB
		players.add(new Player("Semedo", 30, 180, 70, "Portugal", "RB", 81, 'R', 4000000 , 60000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Pique", 30, 180, 70, "Spain", "CB", 87, 'R', 5000000, 60000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Umtiti", 30, 180, 70, "Spain", "CB", 84, 'R', 4000000, 30000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Alba", 30, 180, 70, "France", "LB", 86, 'R', 5000000, 55000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Rakitic", 30, 180, 70, "Croatia", "CM", 86, 'R', 6500000, 25000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Busquets", 30, 180, 70, "Spain", "CM", 87, 'R', 5500000, 30000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Iniesta", 30, 180, 70, "Spain", "CM", 88, 'R', 6000000, 33000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Dembele", 30, 180, 70, "Spain", "RW", 83, 'R', 3000000, 65000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Suarez", 30, 180, 70, "Uruguay", "ST", 89, 'R', 7000000, 90000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Messi", 30, 180, 70, "Argentina", "LW", 92, 'R', 3000000, 500000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Cillessen", 30, 180, 70, "Netherlands", "GK", 80, 'R', 4000000,
				8000000, true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Coutinho", 30, 180, 70, "Brazil", "LW", 87, 'R', 7000000, 60000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Paulinho", 30, 180, 70, "Brazil", "CM", 81, 'R', 5000000, 40000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Alcacer", 30, 180, 70, "Spain", "ST", 80, 'R', 2000000, 8000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Roberto", 30, 180, 70, "Spain", "RB", 82, 'R', 3000000, 20000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Gomes", 30, 180, 70, "Portugal", "CM", 83, 'R', 5000000, 25000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Vidal", 30, 180, 70, "Spain", "RB", 79, 'R', 2500000, 11000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Digne", 30, 180, 70, "France", "LB", 77, 'R', 1500000, 8000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Vermaelen", 30, 180, 70, "Belgium", "CB", 76, 'R', 2500000, 14000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Ernesto Valverde", 54, 180, 70, "Spain", 80, 2);
		// President
		President president = new President("Josep Maria Bartomeu", 55, 180, 70, "Spain");
		// Team
		Team team = new Team("FC Barcelona", "red", "4-3-3", "Camp Nou", "Barça", "Spain",
				"FCB", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createGalatasaray() {
		// Galatasaray
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Muslera", 30, 180, 70, "Uruguay", "GK", 83, 'R', 4000000, 10000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Mariano", 30, 180, 70, "Brazil", "RB", 80, 'R', 2200000, 8000000, true, getAttributes(),
				actions));
		// CB
		players.add(new Player("Maicon", 30, 180, 70, "Brazil", "CB", 82, 'R', 2300000, 14000000, true, getAttributes(),
				actions));
		// CB
		players.add(new Player("Serdar", 30, 180, 70, "Turkey", "CB", 79, 'R', 1500000, 9000000, true, getAttributes(),
				actions));
		// LB
		players.add(new Player("Nagatomo", 30, 180, 70, "Japan", "LB", 78, 'R', 1700000, 6000000, true, getAttributes(),
				actions));
		// CM
		players.add(new Player("Selçuk", 30, 180, 70, "Turkey", "CM", 79, 'R', 2700000, 5500000, true, getAttributes(),
				actions));
		// CM
		players.add(new Player("Fernando", 30, 180, 70, "Brazil", "CM", 82, 'R', 3000000, 9000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Belhanda", 30, 180, 70, "Morocco", "CM", 80, 'R', 3500000, 12000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Feghouli", 30, 180, 70, "Algeria", "RW", 81, 'R', 4000000, 14000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Gomis", 30, 180, 70, "France", "ST", 83, 'R', 4200000, 10000000, true, getAttributes(),
				actions));
		// LW
		players.add(new Player("Garry", 30, 180, 70, "Capeverde", "LW", 79, 'R', 1000000, 16000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Carrasso", 30, 180, 70, "France", "GK", 78, 'R', 1000000, 3000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Linnes", 30, 180, 70, "Norway", "RB", 77, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Ahmet", 30, 180, 70, "Turkey", "CB", 73, 'R', 1000000, 2500000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Denayer", 30, 180, 70, "Belgium", "CB", 78, 'R', 1300000, 5000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Latovlevici", 30, 180, 70, "Romania", "LB", 70, 'R', 700000, 1000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Donk", 30, 180, 70, "Netherlands", "CM", 76, 'R', 1500000, 1500000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Eren", 30, 180, 70, "Switzerland", "ST", 77, 'R', 2000000, 3500000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Sinan", 30, 180, 70, "Turkey", "RW", 77, 'R', 1000000, 6500000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Yasin", 30, 180, 70, "Turkey", "LW", 78, 'R', 1500000, 4000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Fatih Terim", 65, 180, 70, "Turkey", 99, 2);
		// President
		President president = new President("Mustafa Cengiz", 71, 180, 70, "Turkey");
		// Team
		Team team = new Team("Galatasaray", "red", "4-3-3", "Ali Sami Yen", "Cimbom", "Turkey", "GAL", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createPsg() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Trapp", 30, 180, 70, "Germany", "GK", 81, 'R', 2000000, 5000000, true, getAttributes(),
				actions));
		// RB
		players.add(new Player("Meunier", 30, 180, 70, "Belgium", "RB", 81, 'R', 3000000, 11000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Marquinhos", 30, 180, 70, "Brazil", "CB", 82, 'R', 4000000, 19000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Silva", 30, 180, 70, "Brazil", "CB", 88, 'R', 8000000, 16000000, true, getAttributes(),
				actions));
		// LB
		players.add(new Player("Kurzawa", 30, 180, 70, "France", "LB", 82, 'R', 3500000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Veratti", 30, 180, 70, "Italy", "CM", 84, 'R', 5500000, 55000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Rabiot", 30, 180, 70, "France", "CM", 81, 'R', 2500000, 10000000, true, getAttributes(),
				actions));
		// CM
		players.add(new Player("DiMaria", 30, 180, 70, "Argentina", "CM", 84, 'R', 6000000, 15000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Mbappe", 30, 180, 70, "France", "LW", 83, 'R', 4500000, 100000000, true, getAttributes(),
				actions));
		// ST
		players.add(new Player("Cavani", 30, 180, 70, "Uruguay", "ST", 88, 'R', 7500000, 90000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Neymar", 30, 180, 70, "Brazil", "LW", 89, 'R', 20000000, 200000000, true, getAttributes(),
				actions));

		// Subs
		// SUB1
		players.add(new Player("Areola", 30, 180, 70, "France", "GK", 80, 'R', 1500000, 7500000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Draxler", 30, 180, 70, "Germany", "CM", 84, 'R', 3500000, 14000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Pastore", 30, 180, 70, "Argentina", "CM", 84, 'R', 3000000, 19000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Lass", 30, 180, 70, "France", "CM", 82, 'R', 2200000, 5000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Yuri", 30, 180, 70, "Spain", "LB", 79, 'R', 1000000, 7000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Giovani", 30, 180, 70, "Argentina", "CM", 70, 'R', 1500000, 1000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Alves", 30, 180, 70, "Brazil", "RB", 83, 'R', 4500000, 8500000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Motta", 30, 180, 70, "Italy", "CM", 81, 'R', 2300000, 11000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Kimpembe", 30, 180, 70, "France", "CB", 80, 'R', 2000000, 8000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Emery", 65, 180, 70, "Spain", 80, 2);
		// President
		President president = new President("El Khalafi", 71, 180, 70, "Quatar");
		// Team
		Team team = new Team("Paris Saint Germain", "blue", "4-3-3", "Parc de Prince", "Les Parisiens", "France", "PSG", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createManu() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("DeGea", 30, 180, 70, "Spain", "GK", 90, 'R', 6000000, 75000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Valencia", 30, 180, 70, "Ecuador", "RB", 85, 'R', 3500000, 14000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Smalling", 30, 180, 70, "England", "CB", 83, 'R', 3000000, 15000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Bailly", 30, 180, 70, "IvoryCoast", "CB", 83, 'R', 3500000, 15000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Shaw", 30, 180, 70, "England", "LB", 79, 'R', 1500000, 7000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Pogba", 30, 180, 70, "France", "CM", 88, 'R', 7500000, 55000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Herrera", 30, 180, 70, "Spain", "CM", 83, 'R', 3000000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Matic", 30, 180, 70, "Serbia", "CM", 86, 'R', 3500000, 35000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Rashford", 30, 180, 70, "France", "RW", 81, 'R', 4000000, 13000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Lukaku", 30, 180, 70, "Belgium", "ST", 85, 'R', 5000000, 59000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Martial", 30, 180, 70, "France", "LW", 85, 'R', 4500000, 45000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Romero", 30, 180, 70, "Argentina", "GK", 78, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Young", 30, 180, 70, "England", "RB", 81, 'R', 2500000, 7000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Blind", 30, 180, 70, "Netherlands", "LB", 79, 'R', 3000000, 8000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Sanchez", 30, 180, 70, "Chile", "RW", 85, 'R', 4000000, 44000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Mata", 30, 180, 70, "Spain", "CM", 84, 'R', 4500000, 23000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Darmian", 30, 180, 70, "Italy", "RB", 79, 'R', 1500000, 7000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Carrick", 30, 180, 70, "England", "CM", 78, 'R', 2500000, 5000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Ibrahimovic", 30, 180, 70, "Sweden", "ST", 86, 'R', 8000000, 8000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Mctominay", 30, 180, 70, "England", "CM", 77, 'R', 1000000, 3000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Mourinho", 65, 180, 70, "Portugal", 85, 2);
		// President
		President president = new President("Henry Davies", 71, 180, 70, "England");
		// Team
		Team team = new Team("Manchester United", "red", "4-3-3", "Old Trafford", "Red Devils", "England", "MUN",
				5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createChelsea() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Courtois", 30, 180, 70, "Belgium", "GK", 89, 'R', 6000000, 53000000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Azpilicueta", 30, 180, 70, "Spain", "RB", 82, 'R', 4000000, 23000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Rudiger", 30, 180, 70, "Germany", "CB", 84, 'R', 3000000, 13000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Cahill", 30, 180, 70, "England", "CB", 82, 'R', 3500000, 11000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Alonso", 30, 180, 70, "Italy", "LB", 83, 'R', 3000000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Kante", 30, 180, 70, "France", "CM", 87, 'R', 6000000, 59000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Fabregas", 30, 180, 70, "Spain", "CM", 85, 'R', 4000000, 45000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Bakayoko", 30, 180, 70, "France", "CM", 85, 'R', 3000000, 29000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Willian", 30, 180, 70, "Brazil", "RW", 86, 'R', 4000000, 32000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Morata", 30, 180, 70, "Spain", "ST", 84, 'R', 5000000, 30000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Hazard", 30, 180, 70, "Belgium", "LW", 89, 'R', 7500000, 90000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Caballero", 30, 180, 70, "Argentina", "GK", 80, 'R', 2000000, 1000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Luiz", 30, 180, 70, "Brazil", "CB", 88, 'R', 4000000, 25000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Emerson", 30, 180, 70, "Brazil", "LB", 83, 'R', 2500000, 14000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Eduardo", 30, 180, 70, "Portugal", "GK", 82, 'R', 1000000, 5000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Barkley", 30, 180, 70, "England", "CM", 80, 'R', 2500000, 13000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Moses", 30, 180, 70, "Nigeria", "LB", 81, 'R', 3300000, 11000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Drinkwater", 30, 180, 70, "Italy", "CM", 82, 'R', 3500000, 12000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Christensen", 30, 180, 70, "Denmark", "CB", 79, 'R', 1000000, 15000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Chalobah", 30, 180, 70, "England", "CB", 75, 'R', 75, 1000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Conte", 65, 180, 70, "Italy", 83, 2);
		// President
		President president = new President("Abramovich", 71, 180, 70, "France");
		// Team
		Team team = new Team("Chelsea", "blue", "4-3-3", "Stamford Bridge", "Blues", "England", "CHE", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createPorto() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Casillas", 30, 180, 70, "Spain", "GK", 80, 'R', 2500000, 4500000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Pereira", 30, 180, 70, "Portugal", "RB", 79, 'R', 1900000, 3000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Marcano", 30, 180, 70, "Germany", "CB", 77, 'R', 2200000, 5000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Felipe", 30, 180, 70, "Brazil", "CB", 78, 'R', 2100000, 7000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Telles", 30, 180, 70, "Brazil", "LB", 81, 'R', 2000000, 19000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Quintero", 30, 180, 70, "Colombia", "CM", 77, 'R', 2000000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Gonzalez", 30, 180, 70, "Argentina", "CM", 78, 'R', 2500000, 6000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Oliver", 30, 180, 70, "Spain", "CM", 80, 'R', 3000000, 9000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Brahimi", 30, 180, 70, "Algeria", "RW", 81, 'R', 4000000, 14000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Aboubakar", 30, 180, 70, "Cameroon", "ST", 82, 'R', 3000000, 18000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Hernani", 30, 180, 70, "Portugal", "LW", 79, 'R', 2400000, 9000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Sa", 30, 180, 70, "Portugal", "GK", 77, 'R', 1000000, 8000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Maxi", 30, 180, 70, "Uruguay", "RB", 75, 'R', 1500000, 5000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Reyes", 30, 180, 70, "Mexico", "CB", 76, 'R', 1500000, 6000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Luizao", 30, 180, 70, "Brazil", "CM", 77, 'R', 2000000, 3000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Herrera", 30, 180, 70, "Mexico", "CM", 78, 'R', 1500000, 4000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Moses", 30, 180, 70, "Nigeria", "LB", 75, 'R', 1700000, 2000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Osvaldo", 30, 180, 70, "Italy", "CM", 79, 'R', 1500000, 2000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Marega", 30, 180, 70, "Ghana", "ST", 79, 'R', 1000000, 7500000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Waris", 30, 180, 70, "Ghana", "ST", 77, 'R', 750000, 1000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Sergio Conceicao", 65, 180, 70, "Portugal", 75, 2);
		// President
		President president = new President("Reyes", 71, 180, 70, "Portugal");
		// Team
		Team team = new Team("FC Porto", "blue", "4-3-3", "Estadio do Dragao", "Dragões", "Portugal", "POR", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createRoma() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Alisson", 30, 180, 70, "Brazil", "GK", 82, 'R', 3500000, 29000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Florenzi", 30, 180, 70, "Italy", "RB", 83, 'R', 2500000, 18000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Jesus", 30, 180, 70, "Brazil", "CB", 81, 'R', 2000000, 15000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Manolas", 30, 180, 70, "Greece", "CB", 84, 'R', 3000000, 18000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Peres", 30, 180, 70, "Brazil", "LB", 82, 'R', 2000000, 22000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("De Rossi", 30, 180, 70, "Italy", "CM", 82, 'R', 3500000, 6000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Strootman", 30, 180, 70, "Italy", "CM", 85, 'R', 4000000, 17000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Nainggolan", 30, 180, 70, "Belgium", "CM", 87, 'R', 5000000, 52000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Cengiz", 30, 180, 70, "Turkey", "RW", 85, 'R', 2300000, 24000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Dzeko", 30, 180, 70, "Bosnia", "ST", 87, 'R', 2400000, 19000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Perotti", 30, 180, 70, "Argentina", "LW", 83, 'R', 2200000, 14000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Skorupski", 30, 180, 70, "Argentina", "GK", 79, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Karsdrop", 30, 180, 70, "Poland", "RB", 77, 'R', 1500000, 7000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Shaarawy", 30, 180, 70, "Egypt", "LW", 78, 'R', 2200000, 9000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Kolarov", 30, 180, 70, "Serbia", "LB", 80, 'R', 1500000, 6000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Pellegrini", 30, 180, 70, "Italy", "CM", 77, 'R', 1000000, 4000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Gerson", 30, 180, 70, "Brazil", "CM", 79, 'R', 2900000, 16000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Antonucci", 30, 180, 70, "Italy", "ST", 73, 'R', 2100000, 5000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Defrel", 30, 180, 70, "France", "ST", 75, 'R', 2400000, 8000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Fazio", 30, 180, 70, "Argentina", "CB", 77, 'R', 1900000, 12000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Eusebio Di Francesco", 65, 180, 70, "Italy", 80, 2);
		// President
		President president = new President("Pallotta", 71, 180, 70, "Italy");
		// Team
		Team team = new Team("AS Roma", "red", "4-3-3", "Stadio Olimpico", "Wolfs", "Italy", "ROM", 5.0, "Attack",
				"Fast", manager, president, players);
		return team;
	}

	public static Team createLyon() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Lopes", 30, 180, 70, "Portugal", "GK", 81, 'R', 2800000, 11000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Rafael", 30, 180, 70, "Brazil", "RB", 82, 'R', 2500000, 7000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Diakhaby", 30, 180, 70, "France", "CB", 82, 'R', 1300000, 9000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Marcelo", 30, 180, 70, "Brazil", "CB", 82, 'R', 1700000, 12000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Marcal", 30, 180, 70, "Brazil", "LB", 81, 'R', 1200000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Ferri", 30, 180, 70, "France", "CM", 79, 'R', 1400000, 12000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Cheikh", 30, 180, 70, "Spain", "CM", 77, 'R', 1300000, 9000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Tousart", 30, 180, 70, "France", "CM", 77, 'R', 900000, 7000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Traore", 30, 180, 70, "Burkinafaso", "RW", 78, 'R', 2300000, 14000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Fekir", 30, 180, 70, "France", "ST", 82, 'R', 2500000, 19000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Depay", 30, 180, 70, "Netherlands", "LW", 84, 'R', 3500000, 22000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Mocio", 30, 180, 70, "France", "GK", 77, 'R', 1000000, 3000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Morel", 30, 180, 70, "France", "RB", 75, 'R', 1100000, 2000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Mbiwa", 30, 180, 70, "France", "CB", 77, 'R', 1900000, 9000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Mendy", 30, 180, 70, "France", "LB", 74, 'R', 13000000, 6000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Owusu", 30, 180, 70, "France", "CM", 78, 'R', 1200000, 4000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Cornet", 30, 180, 70, "IvoryCoast", "CM", 77, 'R', 1500000, 11000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Mariano", 30, 180, 70, "France", "ST", 73, 'R', 1100000, 5000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Dzabana", 30, 180, 70, "France", "ST", 72, 'R', 900000, 3000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Diop", 30, 180, 70, "France", "CM", 72, 'R', 700000, 3000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Eusebio Di Francesco", 65, 180, 70, "Italy", 82, 2);
		// President
		President president = new President("Genesio", 71, 180, 70, "Portugal");
		// Team
		Team team = new Team("OlympiqueLyonnais", "blue", "4-3-3", "Stade de Gerland", "Les Gones", "France", "LYO", 5.0, "Attack",
				"Fast", manager, president, players);
		return team;
	}

	public static Team createLeverkusen() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Leno", 30, 180, 70, "Germany", "GK", 82, 'R', 2500000, 8000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Tah", 30, 180, 70, "Germany", "RB", 80, 'R', 2200000, 7000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Bender", 30, 180, 70, "Germany", "CB", 81, 'R', 1000000, 8000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Jedvaj", 30, 180, 70, "Croatia", "CB", 79, 'R', 1600000, 9000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Wendell", 30, 180, 70, "Brazil", "LB", 78, 'R', 1800000, 9000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Aranguiz", 30, 180, 70, "Chile", "CM", 79, 'R', 1900000, 7000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Bender", 30, 180, 70, "Germany", "CM", 78, 'R', 1500000, 6000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Yurchenko", 30, 180, 70, "Ukraine", "CM", 77, 'R', 1000000, 5000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Bellerabi", 30, 180, 70, "Germany", "RW", 77, 'R', 2700000, 14000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Kiessling", 30, 180, 70, "Germany", "ST", 80, 'R', 1800000, 9000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Volland", 30, 180, 70, "Germany", "LW", 83, 'R', 2400000, 11000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Ramazan", 30, 180, 70, "Austria", "GK", 77, 'R', 800000, 1000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Handwerker", 30, 180, 70, "Germany", "RB", 78, 'R', 700000, 2500000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Atakan", 30, 180, 70, "Germany", "CM", 72, 'R', 500000, 1500000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Brandt", 30, 180, 70, "Germany", "ST", 77, 'R', 600000, 5000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Pohjanpalo", 30, 180, 70, "Finland", "ST", 78, 'R', 1500000, 6000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Brasnic", 30, 180, 70, "Croatia", "CM", 73, 'R', 1700000, 2000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Baumgartlinger", 30, 180, 70, "Austria", "CM", 73, 'R', 1500000, 7000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Bailey", 30, 180, 70, "Jamaica", "ST", 84, 'R', 2200000, 18000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Kohr", 30, 180, 70, "Germany", "CM", 76, 'R', 2100000, 3000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Eusebio Di Francesco", 65, 180, 70, "Italy", 77, 2);
		// President
		President president = new President("Genesio", 71, 180, 70, "Portugal");
		// Team
		Team team = new Team("Bayer Leverkusen", "black", "4-3-3", "Bay Arena", "Die Werkself", "Germany", "LEV", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createRbl() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Mvogo", 30, 180, 70, "Switzerland", "GK", 77, 'R', 1500000, 8000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Orban", 30, 180, 70, "Germany", "RB", 77, 'R', 1300000, 7000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Franke", 30, 180, 70, "Germany", "CB", 76, 'R', 1200000, 6000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Luiaso", 30, 180, 70, "Germany", "CB", 75, 'R', 1400000, 7000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Klostermann", 30, 180, 70, "Germany", "LB", 78, 'R', 1700000, 5000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Sabitzer", 30, 180, 70, "Austria", "CM", 74, 'R', 1800000, 5500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Keita", 30, 180, 70, "Nigeria", "CM", 82, 'R', 2200000, 38000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Forsberg", 30, 180, 70, "Sweden", "CM", 80, 'R', 2300000, 14000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Bruma", 30, 180, 70, "Portugal", "RW", 83, 'R', 2800000, 19000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Werner", 30, 180, 70, "Germany", "ST", 85, 'R', 2600000, 34000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Kampl", 30, 180, 70, "Austria", "LW", 82, 'R', 2200000, 12000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Gulacsi", 30, 180, 70, "Hungary", "GK", 77, 'R', 1500000, 4000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Franke", 30, 180, 70, "Germany", "CB", 78, 'R', 1200000, 2000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Ilsanker", 30, 180, 70, "Austria", "CM", 79, 'R', 1100000, 3000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Paulsen", 30, 180, 70, "Denmark", "ST", 75, 'R', 1400000, 2000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Augustin", 30, 180, 70, "France", "ST", 74, 'R', 1600000, 5000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Kuhn", 30, 180, 70, "Germany", "ST", 72, 'R', 1800000, 1000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Schmitz", 30, 180, 70, "Germany", "CB", 71, 'R', 1500000, 3000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Konate", 30, 180, 70, "France", "CB", 73, 'R', 1400000, 2500000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Lookman", 30, 180, 70, "England", "ST", 74, 'R', 800000, 1500000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Hasenhuttl", 65, 180, 70, "Germany", 75, 2);
		// President
		President president = new President("Müller", 71, 180, 70, "Germany");
		// Team
		Team team = new Team("RB Leipzig", "red", "4-3-3", "RB Arena", "Bulls", "Germany", "RBL", 5.0, "Attack",
				"Fast", manager, president, players);
		return team;
	}

	public static Team createBenfica() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Svilar", 30, 180, 70, "Belgium", "GK", 78, 'R', 1000000, 7000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Eliseu", 30, 180, 70, "Portugal", "RB", 78, 'R', 1200000, 4000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Jardel", 30, 180, 70, "Brazil", "CB", 82, 'R', 2500000, 12000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Luisao", 30, 180, 70, "Brazil", "CB", 84, 'R', 2200000, 7000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Grimaldo", 30, 180, 70, "Spain", "LB", 78, 'R', 2100000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Fejsa", 30, 180, 70, "Serbia", "CM", 82, 'R', 2200000, 6000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Salvio", 30, 180, 70, "Argentina", "CM", 80, 'R', 2100000, 7500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Cervi", 30, 180, 70, "Argentina", "CM", 81, 'R', 2300000, 6000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Jimenez", 30, 180, 70, "Mexico", "RW", 80, 'R', 1800000, 7000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Jonas", 30, 180, 70, "Brazil", "ST", 84, 'R', 2500000, 14000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("OlaJohn", 30, 180, 70, "Netherlands", "LW", 80, 'R', 1700000, 9000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Lopes", 30, 180, 70, "Portugal", "GK", 77, 'R', 2000000, 4000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Douglas", 30, 180, 70, "Brazil", "RB", 76, 'R', 100000, 3000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Rojas", 30, 180, 70, "Paraguay", "CM", 75, 'R', 1300000, 2500000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Almeida", 30, 180, 70, "Portugal", "CM", 80, 'R', 1600000, 8000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Rafa", 30, 180, 70, "Portugal", "CM", 79, 'R', 800000, 6000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Samaris", 30, 180, 70, "Greece", "CM", 80, 'R', 1500000, 4000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Gabriel", 30, 180, 70, "Brazil", "ST", 76, 'R', 1200000, 2000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Seferovic", 30, 180, 70, "Switzerland", "ST", 77, 'R', 1700000, 7500000,
				true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Ruben", 30, 180, 70, "Portugal", "CB", 76, 'R', 1900000, 1000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Rui Vitoria", 65, 180, 70, "Portugal", 77, 2);
		// President
		President president = new President("Sergio", 71, 180, 70, "Portugal");
		// Team
		Team team = new Team("SLBenfica", "red", "4-3-3", "Estadio da Luz", "As Águias", "Portugal", "BEN", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createMilan() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Donnarumma", 30, 180, 70, "Germany", "GK", 85, 'R', 3500000, 24000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Conti", 30, 180, 70, "Italy", "RB", 79, 'R', 1500000, 7000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Zapata", 30, 180, 70, "Ecuador", "CB", 78, 'R', 2000000, 1100000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Musacchio", 30, 180, 70, "Argentina", "CB", 82, 'R', 2900000, 17000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Antonelli", 30, 180, 70, "Italy", "LB", 81, 'R', 2600000, 4000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Montolivo", 30, 180, 70, "Italy", "CM", 78, 'R', 2200000, 6000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Kessie", 30, 180, 70, "IvoryCoast", "CM", 80, 'R', 2800000, 16000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Biglia", 30, 180, 70, "Argentina", "CM", 81, 'R', 2500000, 9000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Suso", 30, 180, 70, "Spain", "RW", 82, 'R', 2200000, 1800000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Silva", 30, 180, 70, "Portugal", "ST", 82, 'R', 2600000, 11000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Kalinic", 30, 180, 70, "Croatia", "LW", 83, 'R', 3500000, 15000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Abbiati", 30, 180, 70, "Italy", "GK", 76, 'R', 1000000, 1000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Paletta", 30, 180, 70, "Italy", "RB", 78, 'R', 1100000, 2000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Abate", 30, 180, 70, "Germany", "RB", 80, 'R', 1200000, 8000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Rodriguez", 30, 180, 70, "Switzerland", "LB", 83, 'R', 2500000, 16000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Bonaventura", 30, 180, 70, "Italy", "CM", 84, 'R', 2500000, 8000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Locatelli", 30, 180, 70, "Italy", "CM", 77, 'R', 800000, 4000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Borini", 30, 180, 70, "Italy", "ST", 79, 'R', 7000000, 7000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Cutrone", 30, 180, 70, "Italy", "ST", 79, 'R', 1100000 , 6000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Locatelli", 30, 180, 70, "Italy", "CM", 75, 'R', 1100000, 2000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Gattuso", 65, 180, 70, "Italy", 80, 2);
		// President
		President president = new President("Xi Yhang", 71, 180, 70, "China");
		// Team
		Team team = new Team("AC Milan", "red", "4-3-3", "San Siro", "Rossoneri", "Italy", "ACM", 5.0, "Attack",
				"Fast", manager, president, players);
		return team;
	}

	public static Team createBasel() {
		// Initialization
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Signori", 30, 180, 70, "Switzerland", "GK", 77, 'R', 1000000, 2000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Lang", 30, 180, 70, "Switzerland", "RB", 78, 'R', 1200000, 4000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Balanta", 30, 180, 70, "Colombia", "CB", 80, 'R', 1500000, 8000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Riveros", 30, 180, 70, "Paraguay", "CB", 76, 'R', 900000, 4000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Goncalves", 30, 180, 70, "Switzerland", "LB", 77, 'R', 600000, 3000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Okafor", 30, 180, 70, "Switzerland", "CM", 75, 'R', 500000, 2000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Die", 30, 180, 70, "IvoryCoast", "CM", 76, 'R', 1200000, 4500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Simic", 30, 180, 70, "Serbia", "CM", 77, 'R', 800000, 1000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Younoussi", 30, 180, 70, "Norway", "RW", 78, 'R', 700000, 9000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Wolfswinkel", 30, 180, 70, "Netherlands", "ST", 78, 'R', 1700000, 7500000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Ajeti", 30, 180, 70, "Switzerland", "LW", 77, 'R', 1500000, 2500000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Thurkauf", 30, 180, 70, "Switzerland", "GK", 75, 'R', 900000, 1000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Huser", 30, 180, 70, "Switzerland", "RB", 76, 'R', 700000, 500000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Okafor", 30, 180, 70, "Switzerland", "RB", 74, 'R', 650000, 1500000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Campo", 30, 180, 70, "Switzerland", "CB", 73, 'R', 500000, 750000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Stocker", 30, 180, 70, "Switzerland", "CM", 72, 'R', 1000000, 1250000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Cani", 30, 180, 70, "Switzerland", "CM", 74, 'R', 1100000, 1500000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Oberlin", 30, 180, 70, "Switzerland", "ST", 71, 'R', 1400000, 2000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Pululu", 30, 180, 70, "France", "ST", 72, 'R', 1600000, 1000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Manzambi", 30, 180, 70, "Switzerland", "ST", 70, 'R', 1800000, 700000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Wicky", 65, 180, 70, "Switzerland", 69, 2);
		// President
		President president = new President("Jakob", 71, 180, 70, "Switzerland");
		// Team
		Team team = new Team("FC Basel", "blue", "4-3-3", "St Jakob-Park", "Switz", "Switzerland", "BAS", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createSpartak() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Rebrov", 30, 180, 70, "Russia", "GK", 77, 'R', 1200000, 10000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Petkovic", 30, 180, 70, "Serbia", "RB", 76, 'R', 900000, 10000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Serdar", 30, 180, 70, "Germany", "CB", 78, 'R', 800000, 10000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Bocchetti", 30, 180, 70, "Italy", "CB", 79, 'R', 1100000, 10000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Kombarov", 30, 180, 70, "Russia", "LB", 77, 'R', 1000000, 10000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Bakaev", 30, 180, 70, "Russia", "CM", 76, 'R', 800000, 10000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Romula", 30, 180, 70, "Brazil", "CM", 77, 'R', 900000, 10000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Fernando", 30, 180, 70, "Brazil", "CM", 78, 'R', 1500000, 10000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Promes", 30, 180, 70, "Netherlands", "RW", 83, 'R', 3000000, 10000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Nimely", 30, 180, 70, "Malaysia", "ST", 79, 'R', 1900000, 10000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Kozlov", 30, 180, 70, "Russia", "LW", 80, 'R', 1700000, 10000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Antipov", 30, 180, 70, "Russia", "GK", 74, 'R', 700000, 10000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Tiglev", 30, 180, 70, "Russia", "RB", 76, 'R', 900000, 10000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Khomukha", 30, 180, 70, "Russia", "RB", 77, 'R', 800000, 10000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Romulo", 30, 180, 70, "Brazil", "CB", 75, 'R', 500000, 10000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Ananidze", 30, 180, 70, "Georgia", "CM", 76, 'R', 1100000, 10000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Malgarejo", 30, 180, 70, "Paraguay", "CM", 77, 'R', 800000, 10000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Eremenko", 30, 180, 70, "Finland", "ST", 79, 'R', 1000000, 10000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Zobnin", 30, 180, 70, "Russia", "CM", 78, 'R', 700000, 10000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Neves", 30, 180, 70, "Brazil", "CM", 78, 'R', 800000, 10000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Carrera", 65, 180, 70, "Brazil", 74, 2);
		// President
		President president = new President("Igor", 71, 180, 70, "Russia");
		// Team
		Team team = new Team("Spartak Moscow", "red", "4-3-3", "Otkrytie Arena", "Spartak", "Russia", "SMO", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createSporting() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Patricio", 30, 180, 70, "Portugal", "GK", 82, 'R', 2200000, 12000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Fokobo", 30, 180, 70, "Cameroon", "RB", 77, 'R', 1000000, 4000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Coates", 30, 180, 70, "Uruguay", "CB", 82, 'R', 1500000, 8000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Mathieu", 30, 180, 70, "France", "CB", 81, 'R', 1700000, 3500000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Coentrao", 30, 180, 70, "Portugal", "LB", 79, 'R', 1800000, 7500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Ruiz", 30, 180, 70, "CostaRica", "CM", 81, 'R', 1100000, 2000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Battaglia", 30, 180, 70, "Argentina", "CM", 82, 'R', 1400000, 6500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Wendel", 30, 180, 70, "Peru", "CM", 79, 'R', 900000, 7000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Martins", 30, 180, 70, "Portugal", "RW", 82, 'R', 2600000, 11000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Dost", 30, 180, 70, "Netherlands", "ST", 83, 'R', 3200000, 14000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Doumbia", 30, 180, 70, "IvoryCoast", "LW", 81, 'R', 2500000, 5500000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Salin", 30, 180, 70, "France", "GK", 77, 'R', 1200000, 2000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Silva", 30, 180, 70, "Portugal", "RB", 74, 'R', 1200000, 2500000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Ristovski", 30, 180, 70, "Macedonia", "RB", 75, 'R', 1400000, 1000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Merih", 30, 180, 70, "Turkey", "CB", 77, 'R', 1300000, 3000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Acuna", 30, 180, 70, "Argentina", "CM", 78, 'R', 1800000, 4000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Podence", 30, 180, 70, "Portugal", "CM", 77, 'R', 1600000, 7000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Palninha", 30, 180, 70, "Portugal", "ST", 76, 'R', 1900000, 6000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Montero", 30, 180, 70, "Ecuador", "ST", 78, 'R', 800000, 4000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Petrovic", 30, 180, 70, "Serbia", "CM", 78, 'R', 700000, 3500000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Jorge Jesus", 65, 180, 70, "Portugal", 83, 2);
		// President
		President president = new President("Garcia", 71, 180, 70, "Portugal");
		// Team
		Team team = new Team("Sporting Lisbon", "green", "4-3-3", "Sporting Arena", "Rossoneri", "Portugal", "SPL",
				5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createLiverpool() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Karius", 30, 180, 70, "Germany", "GK", 81, 'R', 2200000, 8000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Clyne", 30, 180, 70, "England", "RB", 81, 'R', 1800000, 12000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Dijk", 30, 180, 70, "Netherlands", "CB", 84, 'R', 3200000, 65000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Lovren", 30, 180, 70, "Croatia", "CB", 82, 'R', 2500000, 13000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Moreno", 30, 180, 70, "England", "LB", 79, 'R', 1600000, 8000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Emre", 30, 180, 70, "Germany", "CM", 83, 'R', 2300000, 25000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Wijnaldum", 30, 180, 70, "Netherlands", "CM", 81, 'R', 1500000,
				8000000, true, getAttributes(), actions));
		// CM
		players.add(new Player("Lallana", 30, 180, 70, "England", "CM", 82, 'R', 1600000, 12000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Salah", 30, 180, 70, "Egypt", "RW", 90, 'R', 3200000,
				150000000, true, getAttributes(), actions));
		// ST
		players.add(new Player("Firmino", 30, 180, 70, "Brazil", "ST", 86, 'R', 3000000, 75000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Mane", 30, 180, 70, "Senegal", "LW", 87, 'R', 2800000, 58000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Mignolet", 30, 180, 70, "Belgium", "GK", 79, 'R', 1000000, 8000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Klavan", 30, 180, 70, "Estonia", "CB", 77, 'R', 1200000, 9000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Henderson", 30, 180, 70, "England", "CM", 81, 'R', 2000000, 7500000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Gomez", 30, 180, 70, "England", "CM", 79, 'R', 1000000, 4000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Chamberlein", 30, 180, 70, "England", "RW", 83, 'R', 2600000, 15000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Robertson", 30, 180, 70, "Scotland", "DF", 82, 'R', 1100000, 12000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Woodburn", 30, 180, 70, "Wales", "CM", 73, 'R', 1400000, 1500000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Arnold", 30, 180, 70, "Spain", "ST", 73, 'R', 900000,
				2000000, true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Matip", 30, 180, 70, "Cameroon", "CB", 82, 'R', 1700000, 14000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Jurgen Klopp", 45, 180, 70, "Germany", 87, 2);
		// President
		President president = new President("John Houlding", 71, 180, 70, "Spain");
		// Team
		Team team = new Team("Liverpool", "red", "4-3-3", "Anfield Road", "You Will Never Walk Alone", "England",
				"LIV", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createRealMadrid() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Navas", 30, 180, 70, "CostaRica", "GK", 86, 'R', 2000000, 13000000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Carvajal", 30, 180, 70, "Spain", "RB", 82, 'R', 1900000, 19000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Ramos", 30, 180, 70, "Spain", "CB", 89, 'R', 4000000, 85000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Varane", 30, 180, 70, "France", "CB", 87, 'R', 3200000, 50000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Marcelo", 30, 180, 70, "Brazil", "LB", 88, 'R', 3000000, 70000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Modric", 30, 180, 70, "Croatia", "CM", 88, 'R', 5000000, 50000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Asensio", 30, 180, 70, "Spain", "CM", 86, 'R', 4000000, 60000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Kroos", 30, 180, 70, "Germany", "CM", 89, 'R', 6000000, 55000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Ronaldo", 30, 180, 70, "Portugal", "LW", 94, 'R', 12000000, 1000000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Benzema", 30, 180, 70, "France", "ST", 86, 'R', 5000000, 25000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Bale", 30, 180, 70, "Wales", "LW", 90, 'R', 8000000, 60000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Casilla", 30, 180, 70, "Spain", "GK", 82, 'R', 2000000, 9500000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Nacho", 30, 180, 70, "Spain", "CB", 84, 'R', 3000000, 22000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Borja", 30, 180, 70, "Spain", "ST", 77, 'R', 1000000, 7500000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Casemiro", 30, 180, 70, "Brazil", "CM", 86, 'R', 2500000, 35000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Hernandez", 30, 180, 70, "France", "CB", 79, 'R', 2000000, 8000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Vazquez", 30, 180, 70, "Spain", "LW", 79, 'R', 1500000, 17000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Llorente", 30, 180, 70, "Spain", "CM", 74, 'R', 1000000, 5000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Isco", 30, 180, 70, "Spain", "CM", 85, 'R', 5000000, 50000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Kovacic", 30, 180, 70, "Croatia", "CM", 83, 'R', 3000000, 20000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Zidane", 45, 180, 70, "France", 82, 2);
		// President
		President president = new President("Florentino Perez", 71, 180, 70, "Spain");
		// Team
		Team team = new Team("Real Madrid", "purple", "4-3-3", "Santiago Bernabeu", "Merengues", "Spain", "RMC", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createDortmund() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Bürki", 27, 187, 70, "Switzerland", "GK", 82, 'R', 2000000, 7200000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Piszchek", 18, 196, 70, "Poland", "RB", 82, 'R', 2500000, 8500000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Omer", 22, 190, 70, "Turkey", "CB", 83, 'R', 3000000, 14000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Sokratis", 22, 190, 70, "Greece", "CB", 85, 'R', 4000000, 20000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Schmelzer", 22, 190, 70, "Germany", "CB", 82, 'R', 2000000, 9000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Nuri", 17, 180, 70, "Turkey", "CM", 81, 'R', 2000000, 7500000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Kagawa", 29, 180, 70, "Japan", "CM", 81, 'R', 3000000, 9000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Götze", 30, 180, 70, "Germany", "CM", 82, 'R', 3500000, 14000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Yarmolenko", 30, 180, 70, "Ukraine", "RW", 83, 'R', 3000000, 25000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Batshuayi", 30, 180, 70, "Belgium", "ST", 84, 'R', 4000000, 22000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Schürrle", 30, 180, 70, "Germany", "LW", 83, 'R', 2500000, 17000000,
				true, getAttributes(), actions));

		// Subs
		players.add(new Player("Weidenfeller", 28, 170, 70, "Germany", "GK", 77, 'R', 1000000, 3200000,
				true, getAttributes(), actions));
		// SUB1
		players.add(new Player("Durm", 25, 183, 70, "Germany", "RB", 78, 'R', 1500000, 55000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Rode", 27, 190, 70, "Germany", "CM", 76, 'R', 2000000, 8000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Weigl", 22, 177, 70, "Germany", "CM", 79, 'R', 1000000, 13000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Toljan", 23, 182, 70, "Germany", "RB", 79, 'R', 900000, 7000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Castro", 30, 171, 70, "Germany", "CM", 78, 'R', 1500000, 11000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Reus", 25, 176, 70, "Germany", "RW", 83, 'R', 6000000, 19000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Pulisic", 28, 190, 70, "USA", "RW", 82, 'R', 2000000, 17000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Philip", 28, 180, 70, "Germany", "LW", 77, 'R', 1200000, 6500000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Isak", 28, 180, 70, "Sweden", "ST", 76, 'R', 1100000, 5500000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Peter Stoger", 51, 176, 70, "Austrian", 78, 2);
		// President
		President president = new President("Reinhard Rauball", 71, 175, 70, "Germany");
		// Team
		Team team = new Team("Borussia Dortmund", "yellow", "4-4-2", "Westfalen", "Die Schwarz-Gelben", "Germany",
				"BVB", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createCska() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Akinfeev", 21, 190, 70, "Russia", "GK", 81, 'R', 2000000, 7500000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Fernandes", 27, 189, 70, "Russia", "RB", 79, 'R', 2500000, 5000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Ignashevich", 38, 186, 70, "Russia", "CB", 77, 'R', 2000000, 6000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Berezutski", 29, 192, 70, "Russia", "CB", 80, 'R', 1500000, 5500000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Schennikov", 35, 190, 70, "Russia", "LB", 77, 'R', 1000000, 4500000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Wernbloom", 31, 187, 70, "Sweden", "CM", 78, 'R', 2000000, 7000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Dzagoev", 26, 180, 70, "Russia", "CM", 79, 'R', 2000000, 8500000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Natcho", 30, 180, 70, "Argentina", "CM", 76, 'R', 1500000, 5500000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Musa", 27, 179, 70, "Nigeria", "RW", 82, 'R', 3000000, 14000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Vitinho", 24, 178, 70, "Brazil", "ST", 83, 'R', 2000000, 11000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Milanov", 19, 180, 70, "Bulgaria", "LW", 78, 'R', 1500000, 7000000,
				true, getAttributes(), actions));
		// Subs
		// SUB1
		players.add(new Player("Pomazun", 31, 186, 70, "Russia", "GK", 76, 'R', 1000000, 3500000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Ignashevich", 31, 184, 70, "Russia", "CB", 74, 'R', 1500000, 4500000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Bistrovic", 35, 189, 70, "Croatia", "CM", 72, 'R', 1200000, 1000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Golovin", 26, 180, 70, "Russia", "CM", 73, 'R', 1500000, 3500000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Vasin", 22, 188, 70, "Russia", "CB", 74, 'R', 1100000, 2500000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Chalov", 21, 180, 70, "Russia", "ST", 76, 'R', 1400000, 2000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Milanov", 19, 183, 70, "Bulgaria", "CM", 77, 'R', 900000,
				3000000, true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Nababkin", 30, 175, 70, "Russia", "CM", 78, 'R', 800000, 2500000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Kuchaev", 20, 177, 70, "Russia", "CM", 71, 'R', 700000,
				3000000, true, getAttributes(), actions));
		// Manager
		Manager manager = new Manager("Viktor Goncharenko", 51, 176, 70, "Russia", 73, 2);
		// President
		President president = new President("Andrey Vatutin", 71, 175, 70, "Russia");
		// Team
		Team team = new Team("CSKA Moscow", "red", "4-4-2", "VEB Arena", "Armeitsy", "Russia", "CSK ", 5.0, "Attack",
				"Fast", manager, president, players);
		return team;
	}

	public static Team createBesiktas() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Fabri", 21, 190, 70, "Spain", "GK", 79, 'R', 2000000, 7200000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Gokhan", 21, 189, 70, "Turkey", "RB", 77, 'R', 2200000, 3000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Pepe", 20, 190, 70, "Portugal", "CB", 84, 'R', 5000000, 5000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Tosic", 26, 182, 70, "Serbia", "CB", 79, 'L', 1500000, 3000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Adriano", 32, 175, 70, "Brazil", "LB", 73, 'R', 2000000, 5000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Atiba", 20, 176, 70, "Canada", "CM", 78, 'R', 1500000, 3000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Tolgay", 26, 175, 70, "Turkey", "CM", 78, 'R', 1400000, 10000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Talisca", 22, 175, 70, "Brazil", "CM", 80, 'R', 300000, 22000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Quaresma", 23, 174, 70, "Portugal", "RW", 79, 'R', 3300000, 7500000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Negredo", 22, 177, 70, "Spain", "ST", 78, 'R', 4300000, 8000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Babel ", 25, 175, 70, "Netherlands", "LW", 79, 'R', 2500000,8000000,
				true, getAttributes(), actions));
		// Subs
		// SUB1
		players.add(new Player("Tolga", 34, 187, 70, "Turkey", "GK", 71, 'R', 1000000, 120000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Vida", 25, 176, 70, "Croatia", "CB", 77, 'R', 2300000, 9000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Caner", 20, 189, 70, "Turkey", "LB", 77, 'R', 2400000, 7000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Medel", 21, 191, 70, "Chile", "CB", 80, 'R', 2700000, 10000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Oguzhan", 19, 185, 70, "Turkey", "CM", 76, 'R', 1600000, 7000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Tore", 25, 178, 70, "Turkey", "RW", 78, 'R', 1200000, 8000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Love", 17, 185, 70, "Brazil", "ST", 75, 'R', 2300000, 5000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Necip", 32, 172, 70, "Turkey", "CM", 73, 'R', 1100000, 2000000,
				true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Larin", 25, 179, 70, "Canada", "ST", 69, 'R', 1000000, 1000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Þenol Güneþ", 41, 187, 70, "Turkey", 80, 2);
		// President
		President president = new President("Fikret Orman", 71, 175, 70, "Russia");
		// Team
		Team team = new Team("Besiktas", "black", "4-3-3", "Vodafone Arena", "Kara Kartallar",
				"Turkey", "__", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createManchesterCity() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Bravo", 34, 183, 70, "Chile", "GK", 82, 'R', 250000, 8000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Walker", 27, 178, 70, "England", "RB", 83, 'R', 3000000, 40000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Kompany", 31, 193, 70, "Belgium", "CB", 85, 'R', 4000000, 30000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Stones", 23, 188, 70, "England", "CB", 85, 'R', 3500000, 25000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Mendy", 26, 183, 70, "Brazil", "LW", 80, 'R', 2900000, 24000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Ilkay", 27, 180, 70, "Germany", "CM", 89, 'R', 4500000, 62000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("DeBruyne", 26, 180, 70, "Belgium", "CM", 91, 'R', 7000000, 120000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Silva", 28, 173, 70, "England", "CM", 90, 'R', 4400000, 45000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Sane", 22, 183, 70, "Germany", "RW", 87, 'R', 4000000, 55000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Aguero", 21, 177, 70, "Argentina", "ST", 90, 'R', 5000000, 90000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player(" Sterling ", 23, 170, 70, "England", "LW", 87, 'R', 4500000, 80000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Ederson", 29, 173, 70, "Brazil", "GK", 85, 'R', 3000000, 35000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Danilo", 18, 176, 70, "Brazil", "RB", 82, 'R', 2400000, 16000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Fernandinho", 18, 189, 70, "Brazil", "CM", 84, 'R', 3000000, 12000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Otamendi", 21, 191, 70, "Argentina", "CB", 85, 'R', 4000000, 25000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Laporte", 19, 185, 70, "France", "CB", 85, 'R', 3000000, 20000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Silva", 25, 178, 70, "Portugal", "RW", 84, 'R', 2000000, 24000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Jesus", 17, 185, 70, "Brazil", "ST", 85, 'R', 3400000, 55000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Toure", 30, 183, 70, "IvoryCoast", "CM", 83, 'R', 83, 8000000,
				true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Zinchenko", 20, 196, 70, "Ukraine", "CM", 77, 'R', 77, 5000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Josep Guardiola", 47, 180, 70, "Spanish", 90, 2);
		// President
		President president = new President("Sheikh Mansour", 47, 175, 70, "UAE");
		// Team
		Team team = new Team("Manchester City", "blue", "4-4-2", "Etihad Stadium", "The Sky Blues",
				"United Kingdom", "__", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createNapoli() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Reina", 27, 185, 70, "Spain", "GK", 82, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Hysaj", 20, 175, 70, "Albania", "RB", 81, 'R', 2500000, 9000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Koulibaly", 28, 183, 70, "France", "CB", 85, 'R', 3500000, 40000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Albiol", 32, 170, 70, "Spain", "CB", 83, 'R', 2000000, 15000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Ghoulam", 26, 188, 70, "Algeria", "LB", 83, 'R', 2200000, 24000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Hamsik", 21, 180, 70, "Slovakia", "CM", 87, 'R', 3000000, 60000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Allan", 33, 165, 70, "Brazil", "CM", 83, 'R', 2700000, 22000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Jorginho", 24, 188, 70, "Brazil", "CM", 79, 'R', 1900000, 38000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Insigne", 23, 183, 70, "Italy", "RW", 85, 'R', 4500000, 50000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Mertens", 21, 177, 70, "Belgium", "ST", 88, 'R', 3500000, 55000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Callejon", 31, 173, 70, "Spain", "LW", 83, 'R', 4000000, 20000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Sepe", 26, 173, 70, "Italy", "GK", 77, 'R', 1000000, 500000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Maggio", 36, 183, 70, "Italy", "RB", 79, 'R', 1500000, 3000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Tonelli", 18, 189, 70, "Italy", "CB", 77, 'R', 1300000, 7000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Rui", 28, 191, 70, "Portugal", "LB", 80, 'R', 1200000, 10000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Zielinski", 28, 185, 70, "Poland", "CM", 79, 'R', 1800000, 8000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Rog", 26, 178, 70, "Croatia", "CM", 78, 'R', 1500000, 7500000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Milik", 23, 185, 70, "Poland", "ST", 83, 'R', 2200000, 14000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Leandrinho", 30, 183, 70, "Brazil", "LW", 72, 'R', 1400000, 1500000,
				true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Machach", 19, 196, 70, "France", "CM", 64, 'R', 500000, 2000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Maurizio Sarri", 59, 185, 70, "Italian", 86, 2);
		// President
		President president = new President("Aurelio De Laurentiis", 68, 175, 70, "Italy");
		// Team
		Team team = new Team("Napoli", "blue", "4-4-2", "Stadio San Paolo", "Partenopei", "Italy", "__",
				5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createPsv() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Zoet", 27, 185, 70, "Netherlands", "GK", 77, 'R', 1500000, 7000000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Vlerken", 22, 175, 70, "Netherlands", "RB", 78, 'R', 2000000,
				12000000, true, getAttributes(), actions));
		// CB
		players.add(new Player("Mirin", 26, 185, 70, "France", "CB", 78, 'R', 1200000,
				11000000, true, getAttributes(), actions));
		// CB
		players.add(new Player("Luckassen", 22, 170, 70, "Netherlands", "CB", 77, 'R', 1000000,
				8000000, true, getAttributes(), actions));
		// LB
		players.add(new Player("Arias", 26, 188, 70, "Colombia", "LB", 72, 'R', 1200000, 7000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("DeCesare", 26, 180, 70, "USA", "CM", 73, 'R', 1400000, 5000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Pereiro", 22, 188, 70, "Uruguay", "CM", 77, 'R', 1700000, 7500000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Hendrix", 27, 179, 70, "Netherlands", "CM", 78, 'R',1600000, 9000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Verreth", 20, 183, 70, "Belgium", "RW", 75, 'R', 1000000, 7000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("deJong", 27, 185, 70, "Netherlands", "ST", 77, 'R', 1200000, 9000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Lozano", 22, 173, 70, "Mexico", "LW", 82, 'R', 2200000, 18000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Room", 29, 185, 70, "Netherlands", "GK", 76, 'R', 1000000, 300000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Schwaab", 29, 185, 70, "Germany", "RB", 73, 'R', 1200000, 2500000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Brenet", 23, 175, 70, "Netherlands", "CB", 74, 'R', 1400000, 1500000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Alberto", 28, 191, 70, "Netherlands", "CB", 75, 'R', 1600000,
				2000000, true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Rigo", 19, 185, 70, "Belgium", "CB", 76, 'R', 1700000, 3500000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Rosario", 21, 178, 70, "Netherlands", "CM", 77, 'R', 1300000, 2000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Ramselaar", 21, 185, 70, "Netherlands", "CM", 79, 'R', 1800000, 8000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Lundqvist", 30, 183, 70, "Sweden", "CM", 69, 'R', 800000,
				2000000, true, getAttributes(), actions));
		// SUB9
		players.add(new Player("vanGinkel", 25, 175, 70, "Netherlands", "CM", 78, 'R', 900000,
				9000000, true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Phillip Cocu", 47, 185, 70, "Netherlands", 76, 2);
		// President
		President president = new President("Jan Albers", 68, 175, 70, "Netherlands");
		// Team
		Team team = new Team("PSV Eindhoven", "red", "4-4-2", "Philips Stadion", "Boeren", "Netherlands", "PSV", 5.0,
				"Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createZenit() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Lodygin", 27, 185, 70, "Russia", "GK", 80, 'R', 1400000, 5000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Anyukov", 35, 175, 70, "Russia", "RB", 79, 'R', 1200000, 6000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Terentyev", 25, 185, 70, "Russia", "CB", 78, 'R', 1600000, 6500000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player( "Criscito", 31, 183, 70, "Italy", "CB", 80, 'R', 1300000, 8000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Nabiullin", 23, 188, 70, "Russia", "LB", 79, 'R', 1200000, 3000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Yusupov", 28, 180, 70, "Russia", "CM", 77, 'R', 1000000, 1000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Kranevitter", 22, 188, 70, "Argentina", "CM", 77, 'R', 1000000, 9000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Rigoni", 27, 179, 70, "Argentina", "CM", 78, 'R', 1600000, 8000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Sheydaev", 20, 183, 70, "Azerbaijan", "RW", 81, 'R', 900000, 6000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player(" Poloz", 27, 185, 70, "Russia", "ST", 82, 'R', 1000000, 5000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player(" Kokorin", 22, 173, 70, "Russia", "LW", 83, 'R', 2500000, 12000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player(" Kerzhakov", 29, 185, 70, "Russia", "GK", 79, 'R', 1200000, 2000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player(" Zhirkov", 29, 185, 70, "Russia", "RB", 77, 'R', 1400000, 3000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player(" Smolnikov", 23, 175, 70, "Russia", "CB", 79, 'R', 900000, 5500000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player(" Mevlja", 28, 191, 70, "Slovenia", "CB", 76, 'R', 1300000, 2000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player(" Mammana", 19, 185, 70, "Argentina", "CB", 80, 'R', 2500000, 8000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Paredes", 23, 165, 70, "Argentina", "CM", 77, 'R', 1500000, 2000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player(" Fayzulin", 21, 185, 70, "Russia", "CM", 78, 'R', 1000000, 3000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Erokhin", 30, 183, 70, "Russia", "CM", 79, 'R', 1200000, 1000000,
				true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Ozdoev", 25, 175, 70, "Russia", "CM", 74, 'R', 1500000, 1400000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Roberto Mancini", 47, 185, 70, "Russia", 81, 2);
		// President
		President president = new President("Sergey Fursenko", 68, 175, 70, "Russia");
		// Team
		Team team = new Team("Zenit", "blue", "4-4-2", "Petrovski Stadium",
				"The Anti-aircraft Gunners", "Russia", "ZEN", 5.0, "Attack", "Fast", manager,
				president, players);
		return team;
	}

	public static Team createBayern() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Neuer", 27, 185, 70, "Germany", "GK", 91, 'R', 4500000, 70000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Kimmich", 35, 175, 70, "Germany", "RB", 85, 'R', 2500000, 50000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Hummels", 25, 185, 70, "Germany", "CB", 89, 'R', 4000000, 95000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Boateng", 31, 183, 70, "Germany", "CB", 88, 'R', 6000000, 90000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Alaba", 23, 188, 70, "Austria", "LB", 86, 'R', 4000000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Martinez", 28, 180, 70, "Spain", "CM", 84, 'R', 3000000, 2000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Thiago", 23, 165, 70, "Spain", "CM", 87, 'R', 4000000, 40000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Rodriguez", 22, 188, 70, "Colombia", "CM", 84, 'R', 5500000, 35000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Robben", 27, 179, 70, "Netherlands", "RW", 89, 'R', 4000000, 12000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Lewandowski", 20, 183, 70, "Poland", "ST", 91, 'R', 6500000, 80000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Coman", 22, 173, 70, "France", "LW", 87, 'R', 7500000, 65000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Ulreich", 29, 185, 70, "Germany", "GK", 78, 'R', 1000000, 5000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Sule", 28, 191, 70, "Germany", "CB", 82, 'R', 3500000, 15000000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Ribery", 25, 175, 70, "France", "LW", 86, 'R', 2500000, 11000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Vidal", 29, 185, 70, "Chile", "CM", 87, 'R', 4500000, 60000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Tolisso", 28, 191, 70, "France", "CM", 85, 'R', 3500000, 30000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Rafinha", 25, 175, 70, "Brazil", "RB", 82, 'R', 2000000, 14000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Müller", 29, 185, 70, "Germany", "ST", 86, 'R', 2700000, 3500000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Starke", 28, 191, 70, "Germany", "GK", 79, 'R', 1500000, 1000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Bernat", 25, 175, 70, "Spain", "LB", 80, 'R', 2000000, 7500000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Jupp Heynckes", 47, 185, 70, "Germany", 91, 2);
		// President
		President president = new President("Uli Hoeneß", 68, 175, 70, "Germany");
		// Team
		Team team = new Team("Bayern Munchen", "red", "4-4-2", "Allianz Arena", "The Reds", "Germany",
				"BMU", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createAtletico() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Oblak", 27, 185, 70, "Slovenia", "GK", 87, 'R', 5000000, 27200000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Juanfran", 23, 165, 70, "Spain", "RB", 82, 'R', 2500000, 5000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Godin", 35, 175, 70, "Uruguay", "CB", 86, 'R', 4000000, 14000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Savic", 31, 183, 70, "Montenegro", "CB", 82, 'R', 2500000, 19000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Luis", 25, 185, 70, "Brazil", "LB", 87, 'R', 3700000, 7000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Partey", 23, 188, 70, "Ghana", "CM", 78, 'R', 3000000, 8000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Koke", 28, 180, 70, "Spain", "CM", 83, 'R', 3000000, 19000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Saul", 22, 188, 70, "Spain", "CM", 82, 'R', 2000000,
				20000000, true, getAttributes(), actions));
		// RW
		players.add(new Player("Griezmann", 27, 179, 70, "France", "RW", 88, 'R', 5500000, 90000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Costa", 20, 183, 70, "Spain", "ST", 86, 'R', 5000000, 40000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Carrasco", 27, 185, 70, "Belgium", "LW", 85, 'R', 4000000, 20000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Hernandez", 29, 185, 70, "France", "GK", 78, 'R', 1000000, 7200000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Correa", 29, 185, 70, "Argentina", "RW", 79, 'R', 2500000, 12000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Gimenez", 23, 175, 70, "Uruguay", "CB", 83, 'R', 3500000, 25000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Torres", 28, 191, 70, "Spain", "ST", 81, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Gabi", 19, 185, 70, "Spain", "CB", 80, 'R', 3500000, 12000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Gaitan", 21, 178, 70, "Argentina", "LW", 83, 'R', 3000000, 16000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Vitolo", 21, 185, 70, "Spain", "CM", 82, 'R', 2500000, 8000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Gameiro", 30, 183, 70, "France", "ST", 81, 'R', 3500000, 14000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Vrsaljko", 25, 175, 70, "Croatia", "RB", 83, 'R', 3000000, 29000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Diego Simeone", 47, 185, 70, "Argentine", 86, 2);
		// President
		President president = new President("Enrique Cerezo", 70, 175, 70, "Spain");
		// Team
		Team team = new Team("Atletico Madrid", "red", "4-4-2", "Vicente Calderon", "Los Colchoneros", "Spain",
				"ATM", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createInter() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Handanovic", 27, 185, 70, "Slovenia", "GK", 87, 'R', 3500000, 37000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Cancelo", 35, 175, 70, "Portugal", "RB", 79, 'R', 3000000, 12000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Miranda", 25, 185, 70, "Brazil", "CB", 83, 'R', 2500000, 15000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Skriniar", 31, 183, 70, "Slovenia", "CB", 79, 'R', 2000000, 9000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Dalbert", 23, 188, 70, "Brazil", "LB", 81, 'R', 1800000, 9000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Brozovic", 28, 180, 70, "Croatia", "CM", 82, 'R', 2400000,
				21000000, true, getAttributes(), actions));
		// CM
		players.add(new Player("Rafinha", 23, 165, 70, "Brazil", "CM", 81, 'R', 1650000, 13000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Valero", 22, 188, 70, "Spain", "CM", 83, 'R', 1400000, 4000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Eder", 20, 183, 70, "Italy", "RW", 83, 'R', 1700000, 7000000,
				true, getAttributes(), actions));
		// ST
		players.add(new Player("Icardi", 27, 185, 70, "Argentina", "ST", 86, 'R', 4500000, 90000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Perisic", 22, 173, 70, "Croatia", "LW", 85, 'R', 2900000, 40000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Padelli", 29, 185, 70, "Italy", "GK", 77, 'R', 750000, 1500000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Ranocchia", 29, 185, 70, "Italy", "CB", 75, 'R', 1250000, 5500000, true,
				getAttributes(), actions));
		// SUB3
		players.add(new Player("Santon", 23, 175, 70, "Italy", "LB", 77, 'R', 1800000, 6500000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Vecino", 28, 191, 70, "Uruguay", "CM", 78, 'R', 2000000, 2300000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Gagliardini", 19, 185, 70, "Italy", "CM", 79, 'R', 1300000, 4000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Ambrosio", 21, 178, 70, "Italy", "RB", 74, 'R', 1200000, 2000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Candreva", 21, 185, 70, "Italy", "RW", 75, 'R', 3400000, 20000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Karamoh", 30, 183, 70, "France", "RW", 73, 'R', 9000000, 1000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Pinamonti", 25, 175, 70, "Italy", "ST", 71, 'R', 750000, 20000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Luciano Spalletti", 51, 185, 70, "Italy", 78, 2);
		// President
		President president = new President("Erick Thohir", 47, 175, 70, "Indonesia");
		// Team
		Team team = new Team("Inter Milan", "blue", "4-2-3-1", "San Siro Stadium", "The Black and Blues", "Italy",
				"INT", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createFenerbahce() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Volkan", 27, 185, 70, "Turkey", "GK", 80, 'R', 3000000, 3000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Isla", 35, 175, 70, "Chile", "RB", 79, 'R', 2500000, 7000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Neustadter", 25, 185, 70, "Russia", "CB", 78, 'R', 1900000, 5000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Skrtel", 31, 183, 70, "Slovakia", "CB", 80, 'R', 3200000, 4000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Hasan", 23, 188, 70, "Turkey", "CB", 77, 'R', 1500000, 3000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Mehmet", 28, 180, 70, "Turkey", "CM", 78, 'R', 2250000, 5000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Souza", 23, 165, 70, "Brazil", "CM", 79, 'R', 2700000, 11000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Guilano", 22, 188, 70, "Brazil", "CM", 81, 'R', 3500000, 9000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Dirar", 20, 183, 70, "Morocco", "RW", 76, 'R', 2700000, 6000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Fernandao", 27, 185, 70, "Brazil", "ST", 79, 'R', 3000000, 4000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Valbuena", 22, 173, 70, "France", "LW", 81, 'R', 2900000, 8000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Kameni", 29, 185, 70, "Cameroon", "GK", 78, 'R', 1800000, 3000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Þener", 29, 185, 70, "Turkey", "RB", 77, 'R', 1400000, 5000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Neto", 23, 175, 70, "Portugal", "CB", 78, 'R', 1400000, 8000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Koybasi", 28, 191, 70, "Turkey", "LB", 76, 'R', 1800000, 4000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Janssen", 26, 185, 75, "Turkey", "ST", 79, 'R', 3400000, 15000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Alper", 21, 178, 70, "Turkey", "CM", 77, 'R', 1700000, 7000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Mehmet", 21, 185, 70, "Turkey", "CM", 78, 'R', 1400000, 8000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Ozan ", 25, 175, 70, "Turkey", "CM", 76, 'R', 1800000, 6000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Soldado ", 25, 175, 70, "Spain", "ST", 79, 'R', 3200000, 8000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Aykut Kocaman", 52, 174, 70, "Turkey", 80, 2);
		// President
		President president = new President("Aziz Yýldýrým", 47, 175, 70, "Turkey");
		// Team
		Team team = new Team("Fenerbahce", "yellow", "4-4-2", "Ülker Stadyumu", "Sarý Kanaryalar", "Turkey",
				"FEN", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createTottenham() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Lloris", 27, 185, 70, "France", "GK", 88, 'R', 3200000, 35000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Aurier", 35, 175, 70, "IvoryCoast", "RB", 83, 'R', 3500000, 15000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Alderweireld", 31, 183, 70, "Belgium", "CB", 85, 'R', 4000000, 22000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Vertonghen", 23, 188, 70, "Belgium", "CB", 86, 'R', 3000000, 25000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Rose", 25, 185, 70, "England", "LB", 83, 'R', 2500000, 14000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Dembele", 28, 180, 70, "Belgium", "CM", 85, 'R', 2700000, 15000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Lamela", 23, 165, 70, "Argentina", "CM", 83, 'R', 3300000, 11000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Wanyama", 22, 188, 70, "Kenya", "CM", 84, 'R', 3500000, 15000000, true,
				getAttributes(), actions));
		// RW
		players.add(new Player("Son", 27, 179, 70, "SouthKorea", "RW", 87, 'R', 2900000, 27000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Kane", 20, 183, 70, "England", "ST", 89, 'R', 4800000, 80000000, true,
				getAttributes(), actions));
		// LW
		players.add(new Player("Alli", 27, 185, 70, "England", "LW", 86, 'R', 2800000, 60000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Vorm", 29, 185, 70, "Netherlands", "GK", 74, 'R', 2000000, 5000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Sanchez", 29, 185, 70, "Colombia", "RB", 82, 'R', 2400000, 14000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Lucas", 23, 175, 70, "Brazil", "RW", 83, 'R', 3500000, 15000000, true,
				getAttributes(), actions));
		// SUB4
		players.add(new Player("Davies", 19, 185, 70, "Wales", "CB", 79, 'R', 1500000, 12000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Sissoko", 21, 178, 70, "France", "CM", 81, 'R', 2800000, 9000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Eriksen", 25, 175, 70, "Denmark", "CM", 85, 'R', 4800000, 50000000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Trippier", 29, 185, 70, "England", "RB", 79, 'R', 1800000, 8000000,
				true, getAttributes(), actions));
		// SUB8
		players.add(new Player("Dier", 23, 175, 70, "England", "CM", 69, 'R', 1200000, 5000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Llorente", 19, 185, 70, "Spain", "ST", 79, 'R', 1800000, 9000000, true,
				getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Mauricio Pochettino", 46, 182, 70, "Argentina", 88, 2);
		// President
		President president = new President("Daniel Levy", 47, 175, 70, "England");
		// Team
		Team team = new Team("Tottenham Hotspur", "blue", "4-4-2", "Wembley Stadium", "Spurs",
				"United Kingdom", "TOT", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createJuventus() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Buffon", 27, 185, 70, "Italy", "GK", 86, 'R', 3500000, 5000000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Lichtsteiner", 35, 175, 70, "Switzerland", "RB", 82, 'R', 2400000, 3000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Benatia", 25, 185, 70, "Morocco", "CB", 84, 'R', 3700000, 16000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Chiellini", 31, 183, 70, "Italy", "CB", 88, 'R', 3200000, 40000000, true,
				getAttributes(), actions));
		// LB
		players.add(new Player("Sandro", 23, 188, 70, "Brazil", "LB", 87, 'R', 4300000, 52000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Pjanic", 28, 180, 70, "Bosnia", "CM", 86, 'R', 3900000,
				2000000, true, getAttributes(), actions));
		// CM
		players.add(new Player("Marchisio", 23, 165, 70, "Italy", "CM", 84, 'R', 2500000, 11000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Khedira", 22, 188, 70, "Germany", "CM", 84, 'R', 3100000, 8000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Cuadrado", 20, 183, 70, "Colombia", "RW", 84, 'R', 2900000, 20000000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Higuain", 27, 185, 70, "Argentina", "ST", 87, 'R', 4900000, 30000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Dybala", 22, 173, 70, "Argentina", "LW", 89, 'R', 5200000, 90000000,
				true, getAttributes(), actions));
		// Subs
		// SUB1
		players.add(new Player("Szczesny", 29, 185, 70, "Poland", "GK", 82, 'R', 2000000, 5000000, true,
				getAttributes(), actions));
		// SUB2
		players.add(new Player("Rugani", 29, 185, 70, "Italy", "CB", 80, 'R', 2600000, 4000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("DeSciglio", 23, 175, 70, "Italy", "RB", 79, 'R', 2100000, 2000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Matuidi", 28, 191, 70, "France", "CB", 85, 'R', 3700000, 20000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Sturaro", 19, 185, 70, "Italy", "CM", 79, 'R', 1500000, 7000000,
				true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Asamoah", 21, 178, 70, "Ghana", "CM", 82, 'R', 2000000,
				9000000, true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Bernardeschi", 21, 185, 70, "Italy", "RW", 81, 'R', 2300000, 15000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Mandzukic", 30, 183, 70, "Croatia", "ST", 84, 'R', 2600000,
				15000000, true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Höwedes", 25, 175, 70, "Germany", "CB", 79, 'R', 2800000, 8000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Allegri", 46, 182, 70, "Italy", 88, 2);
		// President
		President president = new President(" Agnelli", 50, 175, 70, "Italy");
		// Team
		Team team = new Team("Juventus", "black", "4-4-2", "Juventus Stadium", "La Vecchia Signora", "Italy",
				"JUV", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}

	public static Team createMonaco() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Subasic", 27, 185, 70, "Croatia", "GK", 84, 'R', 2900000, 9000000,
				true, getAttributes(), actions));
		// RB
		players.add(new Player("Sidibe", 23, 188, 70, "France", "RB", 82, 'R', 1500000, 14000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("Jemerson", 35, 175, 70, "Brazil", "CB", 83, 'R', 2000000, 12000000, true,
				getAttributes(), actions));
		// CB
		players.add(new Player("Raggi", 25, 185, 70, "Italy", "CB", 82, 'R', 3000000, 6000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Jorge", 31, 183, 70, "Brazil", "LB", 79, 'R', 2700000, 5000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Fabinho", 28, 180, 70, "Brazil", "CM", 81, 'R', 1900000, 13000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("Ghezzal", 23, 165, 70, "Algeria", "CM", 80, 'R', 3000000, 8000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player("Moutinho", 22, 188, 70, "Portugal", "CM", 82, 'R', 4500000, 14000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Lopes", 27, 185, 70, "Portugal", "RW", 78, 'R', 2500000, 8500000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Falcao", 20, 183, 70, "Colombia", "ST", 83, 'R', 5500000, 25000000,
				true, getAttributes(), actions));
		// LW
		players.add(new Player("Keita", 22, 173, 70, "Senegal", "LW", 82, 'R', 3000000, 18000000,
				true, getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("Benaglio", 29, 185, 70, "Switzerland", "GK", 77, 'R', 2000000, 5000000,
				true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Toure", 29, 185, 70, "Cameroon", "RB", 78, 'R', 2100000, 2000000,
				true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Serrano", 23, 175, 70, "France", "LB", 77, 'R', 2400000, 3000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Tielemans", 28, 191, 70, "Belgium", "CM", 79, 'R', 1900000, 14000000, true,
				getAttributes(), actions));
		// SUB5
		players.add(new Player("Glik", 19, 185, 70, "Poland", "CB", 84, 'R', 3500000, 15000000, true,
				getAttributes(), actions));
		// SUB6
		players.add(new Player("Traore", 21, 178, 70, "Cameroon", "CM", 79, 'R', 3000000, 7000000, true,
				getAttributes(), actions));
		// SUB7
		players.add(new Player("Boschilia", 21, 185, 70, "Brazil", "CM", 79, 'R', 2000000, 6000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("Lemar", 30, 183, 70, "France", "CM", 77, 'R', 4900000, 24000000, true,
				getAttributes(), actions));
		// SUB9
		players.add(new Player("Jovetic", 25, 175, 70, "Montenegro", "ST", 76, 'R', 3500000, 12000000,
				true, getAttributes(), actions));
		// Manager
		Manager manager = new Manager("Leonardo Jardim", 46, 182, 70, "Venezuelan", 82, 2);
		// President
		President president = new President("Dmitry Rybolovlev", 51, 175, 70, "Russia");
		// Team
		Team team = new Team("AS Monaco", "red", "4-4-2", "Stade Louis II", "Les Rouges et Blancs", "France",
				"MON", 5.0, "Attack", "Fast", manager, president, players);
		return team;
	}


	public static Team createAjax() {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Action> actions = new ArrayList<Action>();

		// GK
		players.add(new Player("Onana", 27, 185, 70, "Cameroon", "GK", 76, 'R', 2500000, 7000000, true,
				getAttributes(), actions));
		// RB
		players.add(new Player("Veltman", 35, 175, 70, "Netherlands", "RB", 75, 'R', 3000000, 9000000,
				true, getAttributes(), actions));
		// CB
		players.add(new Player("deLigt", 25, 185, 70, "Netherlands", "CB", 74, 'R', 2000000,
				14000000, true, getAttributes(), actions));
		// CB
		players.add(new Player("Wober", 23, 188, 70, "Austria", "CB", 78, 'R', 1300000, 6000000,
				true, getAttributes(), actions));
		// LB
		players.add(new Player("Tagliafaco", 28, 180, 70, "Argentina", "LB", 79, 'R', 1200000,
				11000000, true, getAttributes(), actions));
		// CM
		players.add(new Player("Nouri", 23, 165, 70, "Netherlands", "CM", 80, 'R', 1300000, 6000000,
				true, getAttributes(), actions));
		// CM
		players.add(new Player(" Ziyech", 22, 188, 70, "Morocco", "CM", 84, 'R', 1100000, 19000000, true,
				getAttributes(), actions));
		// CM
		players.add(new Player("De Jong", 27, 179, 70, "Netherlands", "CM", 82, 'R', 1600000, 8000000,
				true, getAttributes(), actions));
		// RW
		players.add(new Player("Cerny", 20, 183, 70, "Czech", "RW", 78, 'R', 1800000, 6500000, true,
				getAttributes(), actions));
		// ST
		players.add(new Player("Huntelaar", 27, 185, 70, "Netherlands", "ST", 82, 'R', 2200000,
				3000000, true, getAttributes(), actions));
		// LW
		players.add(new Player("Younes", 22, 173, 70, "Germany", "LW", 81, 'R', 1600000, 17000000, true,
				getAttributes(), actions));

		// Subs
		// SUB1
		players.add(new Player("vanLeer", 29, 185, 70, "Netherlands", "GK", 77, 'R', 1200000,
				5000000, true, getAttributes(), actions));
		// SUB2
		players.add(new Player("Sinkgraven", 29, 185, 70, "Netherlands", "RB", 78, 'R', 1400000,
				7500000, true, getAttributes(), actions));
		// SUB3
		players.add(new Player("Viergever", 23, 175, 70, "Netherlands", "CB", 79, 'R', 2200000, 7000000,
				true, getAttributes(), actions));
		// SUB4
		players.add(new Player("Orejuela", 28, 191, 70, "Colombia", "CB", 74, 'R', 1100000, 4000000,
				true, getAttributes(), actions));
		// SUB5
		players.add(new Player("Johnsen", 19, 185, 70, "Denmark", "LW", 73, 'R', 1200000,
				3000000, true, getAttributes(), actions));
		// SUB6
		players.add(new Player("Dolberg", 21, 178, 70, "Denmark", "ST", 77, 'R', 1400000, 6500000,
				true, getAttributes(), actions));
		// SUB7
		players.add(new Player("Schone", 21, 185, 70, "Denmark", "CM", 79, 'R', 1500000, 7000000, true,
				getAttributes(), actions));
		// SUB8
		players.add(new Player("DeJong", 30, 183, 70, "Netherlands", "CM", 75, 'R', 1700000,
				4500000, true, getAttributes(), actions));
		// SUB9
		players.add(new Player("Siem", 25, 175, 70, "Netherlands", "CM", 78, 'R', 1200000, 5000000,
				true, getAttributes(), actions));

		// Manager
		Manager manager = new Manager("Erik ten Hag", 48, 182, 70, "Netherlands", 75, 2);
		// President
		President president = new President("Hennie Henrichs", 51, 175, 70, "Netherlands");
		// Team
		Team team = new Team("AFC Ajax", "red", "4-4-2", "Amsterdam Arena", "Goedenzonen", "Netherlands",
				"AJA", 5.0, "Attack", "Fast", manager, president, players);

		return team;

	}

	public static Team[] buildTeams() {
		Team[] teams = new Team[32];
		teams[0] = createInter();
		teams[1] = createMonaco();
		teams[2] = createBarcelona();
		teams[3] = createGalatasaray();
		teams[4] = createPsg();
		teams[5] = createManu();
		teams[6] = createChelsea();
		teams[7] = createPorto();
		teams[8] = createRoma();
		teams[9] = createLyon();
		teams[10] = createLeverkusen();
		teams[11] = createRbl();
		teams[12] = createBenfica();
		teams[13] = createMilan();
		teams[14] = createBasel();
		teams[15] = createSpartak();
		teams[16] = createSporting();
		teams[17] = createLiverpool();
		teams[18] = createRealMadrid();
		teams[19] = createDortmund();
		teams[20] = createCska();
		teams[21] = createBesiktas();
		teams[22] = createManchesterCity();
		teams[23] = createNapoli();
		teams[24] = createZenit();
		teams[25] = createBayern();
		teams[26] = createAtletico();
		teams[27] = createAjax();
		teams[28] = createFenerbahce();
		teams[29] = createTottenham();
		teams[30] = createJuventus();
		teams[31] = createPsv();

		for(int i=0; i<32; i++) {
			ArrayList<Player> pl = teams[i].getPlayers();
			for(int j=0; j<20; j++) {
				pl.get(j).modifyAttributes();
			}
		}

		return teams;
	}


}