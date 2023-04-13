package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.util.Pair;

public class Match implements Serializable {

	private Team home;
	private Team away;
	private String referee;
	private String weather;
	private ArrayList<Action> actions;

	private int day;
	private int month;
	private int year;

	private boolean delay;

	private int goalHome;
	private int goalAway;
	private int pointHome;
	private int pointAway;

	// Match Algo
	private int actionChance;
	private int eventGoalChance;
	private int eventInjuryChance;
	private int eventRedCardChance;
	private int eventYellowCardChance;

	// Match Algo
	private boolean[][] tookRedCard;
	private boolean[][] tookYellowCard;
	private String[][] playerNames;
	private int[][] injuryCount;
	private int[][] playerPerformance;

	private final int MATCH_DURATION = 90;
	private final int NUMBER_OF_PLAYERS = 11;

	private final int POINTS_WIN = 3;
	private final int POINTS_DRAW = 1;
	private final int POINTS_LOSS = 0;

	// Match Algo
	private final int ALGO_GENERATE_ADD = 1;
	private final int ALGO_GENERATE_SUB = 1;
	private final int ALGO_GENERATE_INITIAL = 1;
	private final int ALGO_GENERATE_DEN = 40;

	// Match Algo - CHANGE !
	private final int ALGO_TYPE_ADD_GOAL = 3;
	private final int ALGO_TYPE_ADD_YELLOW = 4;
	private final int ALGO_TYPE_ADD_RED = 1;
	private final int ALGO_TYPE_ADD_INJURY = 2;
	private final int ALGO_TYPE_SUB_GOAL = 3;
	private final int ALGO_TYPE_SUB_YELLOW = 4;
	private final int ALGO_TYPE_SUB_RED = 1;
	private final int ALGO_TYPE_SUB_INJURY = 2;
	private final int ALGO_TYPE_INITIAL_GOAL = 30;
	private final int ALGO_TYPE_INITIAL_YELLOW = 40;
	private final int ALGO_TYPE_INITIAL_RED = 10;
	private final int ALGO_TYPE_INITIAL_INJURY = 20;

	/**
	 * @param home
	 * @param away
	 * @param referee
	 * @param weather
	 * @param actions
	 */
	public Match(int day, int month, int year, Team home, Team away, String referee, String weather, ArrayList<Action> actions) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.home = home;
		this.away = away;
		this.referee = referee;
		this.weather = weather;
		this.actions = actions;
		this.goalHome = -1;
		this.goalAway = -1;
		this.setDelay(false);
	}

	public Match(int day, int month, int year, Team home, Team away, String referee, String weather, ArrayList<Action> actions, boolean delay) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.home = home;
		this.away = away;
		this.referee = referee;
		this.weather = weather;
		this.actions = actions;
		this.goalHome = -1;
		this.goalAway = -1;
		this.setDelay(delay);
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}

	public boolean checkExtensionStop() {
		Tournament t = Tournament.getInstance();
		if (!t.getStatusEliminationStage())
			return true;
		int matchId = t.getKnockout().getMatchId(t.getCurrentDay(), t.getCurrentMonth(), t.getCurrentYear());
		if (matchId == -1)
			return true;
		if (matchId % 2 == 1)
			return true;
		if (matchId == 0) {
			if (goalHome == goalAway)
				return false;
			return true;
		}
		Match prev = t.getKnockout().getKnockout().getMatches()[matchId - 1];
		if (goalHome == prev.getGoalHome() && goalAway == prev.getGoalAway())
			return false;
		return true;
	}

	public ArrayList<Action> matchSimulation() throws InterruptedException {
		goalHome = goalAway = 0;
		for (int i = 1; true; i++) {
			if (delay) {
				TimeUnit.SECONDS.sleep(1);
			}
			Action a = actionGenerator(i);
			if (a != null)
				actions.add(a);
			if (i > MATCH_DURATION && checkExtensionStop())
				break;
		}
		if (goalHome > goalAway) {
			setPointHome(POINTS_WIN);
			setPointAway(POINTS_LOSS);
		}
		else if (goalHome < goalAway) {
			setPointHome(POINTS_LOSS);
			setPointAway(POINTS_WIN);
		}
		else {
			setPointHome(POINTS_DRAW);
			setPointAway(POINTS_DRAW);
		}
		// Player Change -> Bug
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			home.getPlayers().get(i).setCntMatch(home.getPlayers().get(i).getCntMatch() + 1);
			away.getPlayers().get(i).setCntMatch(away.getPlayers().get(i).getCntMatch() + 1);
		}
		return getActions();
	}

	public void initAlgo() {

		actionChance = ALGO_GENERATE_INITIAL;
		eventGoalChance = ALGO_TYPE_INITIAL_GOAL;
		eventInjuryChance = ALGO_TYPE_INITIAL_INJURY;
		eventRedCardChance = ALGO_TYPE_INITIAL_RED;
		eventYellowCardChance = ALGO_TYPE_INITIAL_YELLOW;

		int sizeHome = home.getPlayers().size();
		int sizeAway = away.getPlayers().size();

		tookRedCard = new boolean[2][];
		tookRedCard[0] = new boolean[sizeHome];
		tookRedCard[1] = new boolean[sizeAway];
		for (int i = 0; i < sizeHome; i++)
			tookRedCard[0][i] = false;
		for (int i = 0; i < sizeAway; i++)
			tookRedCard[1][i] = false;

		tookYellowCard = new boolean[2][];
		tookYellowCard[0] = new boolean[sizeHome];
		tookYellowCard[1] = new boolean[sizeAway];
		for (int i = 0; i < sizeHome; i++)
			tookYellowCard[0][i] = false;
		for (int i = 0; i < sizeAway; i++)
			tookYellowCard[1][i] = false;

		playerNames = new String[2][];
		playerNames[0] = new String[sizeHome];
		playerNames[1] = new String[sizeAway];
		for (int i = 0; i < sizeHome; i++)
			playerNames[0][i] = home.getPlayers().get(i).getName();
		for (int i = 0; i < sizeAway; i++)
			playerNames[1][i] = away.getPlayers().get(i).getName();

		injuryCount = new int[2][];
		injuryCount[0] = new int[sizeHome];
		injuryCount[1] = new int[sizeAway];
		for (int i = 0; i < sizeHome; i++)
			injuryCount[0][i] = 0;
		for (int i = 0; i < sizeAway; i++)
			injuryCount[1][i] = 0;

		playerPerformance = new int[2][];
		playerPerformance[0] = new int[sizeHome];
		playerPerformance[1] = new int[sizeAway];

	}

	void calcPerformance(int minute) {

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {

			int id = getPlayerId(0, home.getPlayers().get(i).getName());

			if (tookRedCard[0][id])
				playerPerformance[0][id] = 0;
			else if (home.getPlayers().get(i).getPosition().equals("GK")) {
				if (minute < 90)
					playerPerformance[0][id] = home.getPlayers().get(i).getOverall() / 20;
				else
					playerPerformance[0][id] = home.getPlayers().get(i).getOverall() / 2;
			}
			else {
				playerPerformance[0][id] = home.getPlayers().get(i).getOverall();
				if (playerPerformance[0][id] > 100)
					playerPerformance[0][id] = 77;
				if (tookYellowCard[0][id])
					playerPerformance[0][id] /= 2;
				for (int j = 0; j < injuryCount[0][id]; j++)
					playerPerformance[0][id] /= 5;
			}

			if (home.getStyle().equals("Attack"))
				playerPerformance[0][id] *= 2;
			else if (home.getStyle().equals("Defensive"))
				playerPerformance[0][id] /= 2;
			else if (home.getStyle().equals("Holding"))
				;
			if (away.getStyle().equals("Attack"))
				playerPerformance[0][id] *= 2;
			else if (away.getStyle().equals("Defensive"))
				playerPerformance[0][id] /= 2;
			else if (away.getStyle().equals("Holding"))
				;

			if (home.getTempo().equals("Fast"))
				playerPerformance[0][id] *= 2;
			else if (home.getTempo().equals("Slow"))
				playerPerformance[0][id] /= 2;
			else if (home.getTempo().equals("Normal"))
				;
		}

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {

			int id = getPlayerId(1, away.getPlayers().get(i).getName());

			if (tookRedCard[1][id])
				playerPerformance[1][id] = 0;
			else if (away.getPlayers().get(i).getPosition().equals("GK")) {
				if (minute < 90)
					playerPerformance[1][id] = away.getPlayers().get(i).getOverall() / 20;
				else
					playerPerformance[1][id] = away.getPlayers().get(i).getOverall() / 2;
			}
			else {
				playerPerformance[1][id] = away.getPlayers().get(i).getOverall();
				if (tookYellowCard[1][id])
					playerPerformance[1][id] /= 2;
				for (int j = 0; j < injuryCount[1][id]; j++)
					playerPerformance[1][id] /= 5;
			}

			if (away.getStyle().equals("Attack"))
				playerPerformance[1][id] *= 2;
			else if (away.getStyle().equals("Defensive"))
				playerPerformance[1][id] /= 2;
			else if (away.getStyle().equals("Holding"))
				;
			if (home.getStyle().equals("Attack"))
				playerPerformance[1][id] *= 2;
			else if (home.getStyle().equals("Defensive"))
				playerPerformance[1][id] /= 2;
			else if (home.getStyle().equals("Holding"))
				;

			if (away.getTempo().equals("Fast"))
				playerPerformance[1][id] *= 2;
			else if (away.getTempo().equals("Slow"))
				playerPerformance[1][id] /= 2;
			else if (away.getTempo().equals("Normal"))
				;
		}

	}

	public int getPlayerId(int teamId, String pName) {

		int teamSize = -1;
		if (teamId == 0)
			teamSize = home.getPlayers().size();
		else
			teamSize = away.getPlayers().size();

		for (int i = 0; i < teamSize; i++)
			if (playerNames[teamId][i].equals(pName))
				return i;

		return -1;

	}

	public Action actionGenerator(int minute) {

		if (minute == 1)
			initAlgo();

		calcPerformance(minute);

		int randomActionExist = (int) ((new Random()).nextDouble() * ALGO_GENERATE_DEN);
		randomActionExist %= ALGO_GENERATE_DEN;

		int oldActionChance = actionChance;
		if (randomActionExist >= actionChance) {
			actionChance += ALGO_GENERATE_ADD;
			return null;
		}
		// actionChance = Math.max(ALGO_GENERATE_INITIAL, actionChance -
		// ALGO_GENERATE_SUB);
		actionChance = ALGO_GENERATE_INITIAL;

		int totalEventChance = 2 * (eventGoalChance + eventInjuryChance + eventRedCardChance + eventYellowCardChance);
		int randomEvent = (int) ((new Random()).nextDouble() * totalEventChance);
		randomEvent %= totalEventChance;

		// Goal
		if (randomEvent < eventGoalChance) {

			int sumPerformance1 = 0;
			ArrayList<Pair<Pair<Integer, Integer>, Integer>> playerIds1 = new ArrayList<>();

			for (int k = 0; k < 2; k++) {
				Team team;
				if (k == 0)
					team = home;
				else
					team = away;
				for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
					int id = getPlayerId(k, team.getPlayers().get(i).getName());
					if (!tookRedCard[k][id]) {
						sumPerformance1 += playerPerformance[k][id];
						playerIds1.add(new Pair<Pair<Integer, Integer>, Integer>(new Pair<Integer, Integer>(k, i), playerPerformance[k][id]));
					}
				}
			}

			int randomPerformanceScorer = (int) ((new Random()).nextDouble() * sumPerformance1);
			randomPerformanceScorer %= sumPerformance1;

			int scorerTeamId = -1;
			Team scorerTeam = null;
			Player playerScorer = null;
			for (int i = 0, sum = 0; i < playerIds1.size(); i++) {
				sum += playerIds1.get(i).getValue();
				if (randomPerformanceScorer < sum) {
					int k = playerIds1.get(i).getKey().getKey();
					int id = playerIds1.get(i).getKey().getValue();
					if (k == 0) {
						scorerTeamId = 0;
						scorerTeam = home;
						playerScorer = home.getPlayers().get(id);
						goalHome++;
					}
					else {
						scorerTeamId = 1;
						scorerTeam = away;
						playerScorer = away.getPlayers().get(id);
						goalAway++;
					}
					break;
				}
			}

			int sumPerformance2 = 0;
			ArrayList<Pair<Pair<Integer, Integer>, Integer>> playerIds2 = new ArrayList<>();

			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(scorerTeamId, scorerTeam.getPlayers().get(i).getName());
				if (!tookRedCard[scorerTeamId][id] && scorerTeam.getPlayers().get(i) != playerScorer) {
					sumPerformance2 += playerPerformance[scorerTeamId][id];
					playerIds2.add(new Pair<Pair<Integer, Integer>, Integer>(new Pair<Integer, Integer>(scorerTeamId, i), playerPerformance[scorerTeamId][id]));
				}
			}

			int randomPerformanceAssister = (int) ((new Random()).nextDouble() * sumPerformance2);
			randomPerformanceAssister %= sumPerformance2;

			Player playerAssister = null;
			for (int i = 0, sum = 0; i < playerIds2.size(); i++) {
				sum += playerIds2.get(i).getValue();
				if (randomPerformanceAssister < sum) {
					int id = playerIds2.get(i).getKey().getValue();
					playerAssister = scorerTeam.getPlayers().get(id);
					break;
				}
			}

			eventGoalChance = Math.max(ALGO_TYPE_INITIAL_GOAL, eventGoalChance - ALGO_TYPE_SUB_GOAL);
			eventInjuryChance += ALGO_TYPE_ADD_INJURY;
			eventRedCardChance += ALGO_TYPE_ADD_RED;
			eventYellowCardChance += ALGO_TYPE_ADD_YELLOW;

			playerScorer.setCntGoal(playerScorer.getCntGoal() + 1);
			playerAssister.setCntAssist(playerAssister.getCntAssist() + 1);
			return new Goal(minute, playerScorer, playerAssister);

		}

		// Injury
		else if (randomEvent < eventGoalChance + eventInjuryChance) {

			int sizeHome = 0, sizeAway = 0;
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(0, home.getPlayers().get(i).getName());
				if (!tookRedCard[0][id] && !home.getPlayers().get(i).getPosition().equals("GK"))
					sizeAway++;
			}
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(1, away.getPlayers().get(i).getName());
				if (!tookRedCard[1][id] && !away.getPlayers().get(i).getPosition().equals("GK"))
					sizeHome++;
			}
			if (home.getTempo().equals("Fast"))
				sizeHome *= 2;
			else if (home.getTempo().equals("Slow"))
				sizeHome /= 2;
			else if (home.getTempo().equals("Normal"))
				;
			if (away.getTempo().equals("Fast"))
				sizeAway *= 2;
			else if (away.getTempo().equals("Slow"))
				sizeAway /= 2;
			else if (away.getTempo().equals("Normal"))
				;

			int randomTeamValue = (int) ((new Random()).nextDouble() * (sizeHome + sizeAway));
			randomTeamValue %= (sizeHome + sizeAway);

			int k;
			Team team;
			if (randomTeamValue < sizeHome) {
				k = 0;
				team = home;
			}
			else {
				k = 1;
				team = away;
			}

			ArrayList<Pair<Integer, Integer>> playerIds = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(k, team.getPlayers().get(i).getName());
				if (!tookRedCard[k][id] && !team.getPlayers().get(i).getPosition().equals("GK"))
					playerIds.add(new Pair<Integer, Integer>(i, id));
			}

			int randomPerformanceInjury = (int) ((new Random()).nextDouble() * playerIds.size());
			randomPerformanceInjury %= playerIds.size();

			int id = playerIds.get(randomPerformanceInjury).getKey();
			Player player = team.getPlayers().get(id);

			eventGoalChance += ALGO_TYPE_ADD_GOAL;
			eventInjuryChance = Math.max(ALGO_TYPE_INITIAL_INJURY, eventInjuryChance - ALGO_TYPE_SUB_INJURY);
			eventRedCardChance += ALGO_TYPE_ADD_RED;
			eventYellowCardChance += ALGO_TYPE_ADD_YELLOW;

			injuryCount[k][playerIds.get(randomPerformanceInjury).getValue()]++;
			return new Injury(minute, player);

		}

		// Red Card
		else if (randomEvent < eventGoalChance + eventInjuryChance + eventRedCardChance) {

			int sizeHome = 0, sizeAway = 0;
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(0, home.getPlayers().get(i).getName());
				if (!tookRedCard[0][id] && !home.getPlayers().get(i).getPosition().equals("GK"))
					sizeAway++;
			}
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(1, away.getPlayers().get(i).getName());
				if (!tookRedCard[1][id] && !away.getPlayers().get(i).getPosition().equals("GK"))
					sizeHome++;
			}
			if (home.getTempo().equals("Fast"))
				sizeHome *= 2;
			else if (home.getTempo().equals("Slow"))
				sizeHome /= 2;
			else if (home.getTempo().equals("Normal"))
				;
			if (away.getTempo().equals("Fast"))
				sizeAway *= 2;
			else if (away.getTempo().equals("Slow"))
				sizeAway /= 2;
			else if (away.getTempo().equals("Normal"))
				;

			int randomTeamValue = (int) ((new Random()).nextDouble() * (sizeHome + sizeAway));
			randomTeamValue %= (sizeHome + sizeAway);

			int k;
			Team team;
			if (randomTeamValue < sizeHome) {
				k = 0;
				team = home;
			}
			else {
				k = 1;
				team = away;
			}

			ArrayList<Pair<Integer, Integer>> playerIds = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(k, team.getPlayers().get(i).getName());
				if (!tookRedCard[k][id] && !team.getPlayers().get(i).getPosition().equals("GK"))
					playerIds.add(new Pair<Integer, Integer>(i, id));
			}

			int randomPerformanceRed = (int) ((new Random()).nextDouble() * playerIds.size());
			randomPerformanceRed %= playerIds.size();

			int id = playerIds.get(randomPerformanceRed).getKey();
			Player player = team.getPlayers().get(id);

			eventGoalChance += ALGO_TYPE_ADD_GOAL;
			eventInjuryChance += ALGO_TYPE_ADD_INJURY;
			eventRedCardChance = Math.max(ALGO_TYPE_INITIAL_RED, eventRedCardChance - ALGO_TYPE_SUB_RED);
			eventYellowCardChance += ALGO_TYPE_ADD_YELLOW;

			tookRedCard[k][playerIds.get(randomPerformanceRed).getValue()] = true;
			player.setCntRedCard(player.getCntRedCard() + 1);
			return new RedCard(minute, player);

		}

		// Yellow Card
		else if (randomEvent < eventGoalChance + eventInjuryChance + eventRedCardChance + eventYellowCardChance) {

			int sizeHome = 0, sizeAway = 0;
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(0, home.getPlayers().get(i).getName());
				if (!tookRedCard[0][id] && !tookYellowCard[0][id] && !home.getPlayers().get(i).getPosition().equals("GK"))
					sizeAway++;
			}
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(1, away.getPlayers().get(i).getName());
				if (!tookRedCard[1][id] && !tookYellowCard[1][id] && !away.getPlayers().get(i).getPosition().equals("GK"))
					sizeHome++;
			}
			if (home.getTempo().equals("Fast"))
				sizeHome *= 2;
			else if (home.getTempo().equals("Slow"))
				sizeHome /= 2;
			else if (home.getTempo().equals("Normal"))
				;
			if (away.getTempo().equals("Fast"))
				sizeAway *= 2;
			else if (away.getTempo().equals("Slow"))
				sizeAway /= 2;
			else if (away.getTempo().equals("Normal"))
				;

			int randomTeamValue = (int) ((new Random()).nextDouble() * (sizeHome + sizeAway));
			randomTeamValue %= (sizeHome + sizeAway);

			int k;
			Team team;
			if (randomTeamValue < sizeHome) {
				k = 0;
				team = home;
			}
			else {
				k = 1;
				team = away;
			}

			ArrayList<Pair<Integer, Integer>> playerIds = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				int id = getPlayerId(k, team.getPlayers().get(i).getName());
				if (!tookRedCard[k][id] && !tookYellowCard[k][id] && !team.getPlayers().get(i).getPosition().equals("GK"))
					playerIds.add(new Pair<Integer, Integer>(i, id));
			}

			int randomPerformanceYellow = (int) ((new Random()).nextDouble() * playerIds.size());
			randomPerformanceYellow %= playerIds.size();

			int id = playerIds.get(randomPerformanceYellow).getKey();
			Player player = team.getPlayers().get(id);

			eventGoalChance += ALGO_TYPE_ADD_GOAL;
			eventInjuryChance += ALGO_TYPE_ADD_INJURY;
			eventRedCardChance += ALGO_TYPE_ADD_RED;
			eventYellowCardChance = Math.max(ALGO_TYPE_INITIAL_YELLOW, eventYellowCardChance - ALGO_TYPE_SUB_YELLOW);

			tookYellowCard[k][playerIds.get(randomPerformanceYellow).getValue()] = true;
			player.setCntYellowCard(player.getCntYellowCard() + 1);
			return new YellowCard(minute, player);

		}

		actionChance = oldActionChance + ALGO_GENERATE_ADD;
		// Is there any other case ???
		// System.out.println( "WTF - Event Type" );

		return null;

	}

	public int getGoalHome() {
		return goalHome;
	}

	public void setGoalHome(int goalHome) {
		this.goalHome = goalHome;
	}

	public int getGoalAway() {
		return goalAway;
	}

	public void setGoalAway(int goalAway) {
		this.goalAway = goalAway;
	}

	public int getPointHome() {
		return pointHome;
	}

	public void setPointHome(int pointHome) {
		this.pointHome = pointHome;
	}

	public int getPointAway() {
		return pointAway;
	}

	public void setPointAway(int pointAway) {
		this.pointAway = pointAway;
	}

	public boolean isDelay() {
		return delay;
	}

	public void setDelay(boolean delay) {
		this.delay = delay;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMATCH_DURATION() {
		return MATCH_DURATION;
	}

	public int getNUMBER_OF_PLAYERS() {
		return NUMBER_OF_PLAYERS;
	}

	public int getPOINTS_WIN() {
		return POINTS_WIN;
	}

	public int getPOINTS_DRAW() {
		return POINTS_DRAW;
	}

	public int getPOINTS_LOSS() {
		return POINTS_LOSS;
	}

	public boolean[][] getTookRedCard() {
		return tookRedCard;
	}

	public void setTookRedCard(boolean[][] tookRedCard) {
		this.tookRedCard = tookRedCard;
	}

	public boolean[][] getTookYellowCard() {
		return tookYellowCard;
	}

	public void setTookYellowCard(boolean[][] tookYellowCard) {
		this.tookYellowCard = tookYellowCard;
	}

	public int[][] getPlayerPerformance() {
		return playerPerformance;
	}

	public void setPlayerPerformance(int[][] playerPerformance) {
		this.playerPerformance = playerPerformance;
	}

	public int getALGO_GENERATE_ADD() {
		return ALGO_GENERATE_ADD;
	}

	public int getALGO_GENERATE_INITIAL() {
		return ALGO_GENERATE_INITIAL;
	}

	public int getALGO_GENERATE_DEN() {
		return ALGO_GENERATE_DEN;
	}

	public int getALGO_TYPE_INITIAL_GOAL() {
		return ALGO_TYPE_INITIAL_GOAL;
	}

	public int getALGO_TYPE_INITIAL_YELLOW() {
		return ALGO_TYPE_INITIAL_YELLOW;
	}

	public int getALGO_TYPE_INITIAL_RED() {
		return ALGO_TYPE_INITIAL_RED;
	}

	public int getALGO_TYPE_INITIAL_INJURY() {
		return ALGO_TYPE_INITIAL_INJURY;
	}

	public int getActionChance() {
		return actionChance;
	}

	public void setActionChance(int actionChance) {
		this.actionChance = actionChance;
	}

	public int getEventGoalChance() {
		return eventGoalChance;
	}

	public void setEventGoalChance(int eventGoalChance) {
		this.eventGoalChance = eventGoalChance;
	}

	public int getEventInjuryChance() {
		return eventInjuryChance;
	}

	public void setEventInjuryChance(int eventInjuryChance) {
		this.eventInjuryChance = eventInjuryChance;
	}

	public int getEventRedCardChance() {
		return eventRedCardChance;
	}

	public void setEventRedCardChance(int eventRedCardChance) {
		this.eventRedCardChance = eventRedCardChance;
	}

	public int getEventYellowCardChance() {
		return eventYellowCardChance;
	}

	public void setEventYellowCardChance(int eventYellowCardChance) {
		this.eventYellowCardChance = eventYellowCardChance;
	}

	public String[][] getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(String[][] playerNames) {
		this.playerNames = playerNames;
	}

	public int[][] getInjuryCount() {
		return injuryCount;
	}

	public void setInjuryCount(int[][] injuryCount) {
		this.injuryCount = injuryCount;
	}

	public int getALGO_TYPE_ADD_GOAL() {
		return ALGO_TYPE_ADD_GOAL;
	}

	public int getALGO_TYPE_ADD_YELLOW() {
		return ALGO_TYPE_ADD_YELLOW;
	}

	public int getALGO_TYPE_ADD_RED() {
		return ALGO_TYPE_ADD_RED;
	}

	public int getALGO_TYPE_ADD_INJURY() {
		return ALGO_TYPE_ADD_INJURY;
	}

	public int getALGO_TYPE_SUB_GOAL() {
		return ALGO_TYPE_SUB_GOAL;
	}

	public int getALGO_TYPE_SUB_YELLOW() {
		return ALGO_TYPE_SUB_YELLOW;
	}

	public int getALGO_TYPE_SUB_RED() {
		return ALGO_TYPE_SUB_RED;
	}

	public int getALGO_TYPE_SUB_INJURY() {
		return ALGO_TYPE_SUB_INJURY;
	}

	public int getALGO_GENERATE_SUB() {
		return ALGO_GENERATE_SUB;
	}

}
