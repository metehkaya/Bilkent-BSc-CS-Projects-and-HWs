package model;

import java.io.Serializable;

import java.util.ArrayList;

public class Group implements Serializable{

	private int[][] statistics;
	// Played - Win - Draw - Loss - Scored - Conceded - Points

	private Team[] teams;
	private Match[] matches;

	private int[] matchCalendar;

	private int[][] orderedStats;
	private String[] orderedNames;

	private final int TEAMS_PER_GROUP = 4;
	private final int NUMBER_OF_STATS = 7;
	private final int NUMBER_OF_MATCHES = 12;

	private final int POINTS_WIN = 3;
	private final int POINTS_DRAW = 1;
	private final int POINTS_LOSS = 0;

	private final int TOTAL_PLAYED = 0;
	private final int TOTAL_WINS = 1;
	private final int TOTAL_DRAWS = 2;
	private final int TOTAL_LOSSES = 3;
	private final int TOTAL_SCORED = 4;
	private final int TOTAL_CONCEDED = 5;
	private final int TOTAL_POINTS = 6;

	/**
	 * @param statistics
	 * @param teams
	 * @param matches
	 */
	public Group(int[][] statistics, Team[] teams, Match[] matches) {
		this.statistics = statistics;
		this.teams = teams;
		this.matches = matches;
	}

	public Group() {
		matches = new Match[NUMBER_OF_MATCHES];
		statistics = new int[TEAMS_PER_GROUP][NUMBER_OF_STATS];
	}

	public void orderGroup() {

		orderedNames = new String[TEAMS_PER_GROUP];
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			orderedNames[i] = "Team Name";

		orderedStats = new int[TEAMS_PER_GROUP][NUMBER_OF_STATS];
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			for( int j = 0 ; j < NUMBER_OF_STATS ; j++ )
				orderedStats[i][j] = -1;

		// Only points
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			for( int j = 0 ; j < TEAMS_PER_GROUP ; j++ )
				if( statistics[i][TOTAL_POINTS] > orderedStats[j][TOTAL_POINTS] || ( statistics[i][TOTAL_POINTS] == orderedStats[j][TOTAL_POINTS] && statistics[i][TOTAL_SCORED] - statistics[i][TOTAL_CONCEDED] > orderedStats[j][TOTAL_SCORED] - orderedStats[j][TOTAL_CONCEDED] ) ) {
					for( int k = TEAMS_PER_GROUP - 1 ; k > j ; k-- ) {
						orderedNames[k] = orderedNames[k-1];
						for( int l = 0 ; l < NUMBER_OF_STATS ; l++ )
							orderedStats[k][l] = orderedStats[k-1][l];
					}
					orderedNames[j] = teams[i].getName();
					for( int k = 0 ; k < NUMBER_OF_STATS ; k++ )
						orderedStats[j][k] = statistics[i][k];
					break;
				}

	}

	public boolean checkGroupMatchesFinished() {
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			if( statistics[i][TOTAL_PLAYED] < NUMBER_OF_MATCHES / 2 )
				return false;
		return true;
	}

	public void modifyGroupStatistics( Match match ) {

		int idMatch = -1;
		for( int i = 0 ; i < NUMBER_OF_MATCHES ; i++ )
			if( match.getHome() == matches[i].getHome() )
				if( match.getAway() == matches[i].getAway() ) {
					idMatch = i;
					break;
				}

		int idHome = -1;
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			if( match.getHome() == teams[i] ) {
				idHome = i;
				break;
			}

		int idAway = -1;
		for( int i = 0 ; i < TEAMS_PER_GROUP ; i++ )
			if( match.getAway() == teams[i] ) {
				idAway = i;
				break;
			}

		if( idMatch == -1 || idHome == -1 || idAway == -1 ) {
			System.out.println( "Ha siktir" );
			return;
		}

		modifyGroupStatistics( idMatch , idHome , idAway );

	}

	public void modifyGroupStatistics( int idMatch , int idHome , int idAway ) {

		// Statistics - Played
		statistics[idHome][TOTAL_PLAYED] += 1;
		statistics[idAway][TOTAL_PLAYED] += 1;

		// Statistics - Win
		if( matches[idMatch].getPointHome() == POINTS_WIN )
			statistics[idHome][TOTAL_WINS] += 1;
		if( matches[idMatch].getPointAway() == POINTS_WIN )
			statistics[idAway][TOTAL_WINS] += 1;

		// Statistics - Draw
		if( matches[idMatch].getPointHome() == POINTS_DRAW )
			statistics[idHome][TOTAL_DRAWS] += 1;
		if( matches[idMatch].getPointAway() == POINTS_DRAW )
			statistics[idAway][TOTAL_DRAWS] += 1;

		// Statistics - Loss
		if( matches[idMatch].getPointHome() == POINTS_LOSS )
			statistics[idHome][TOTAL_LOSSES] += 1;
		if( matches[idMatch].getPointAway() == POINTS_LOSS )
			statistics[idAway][TOTAL_LOSSES] += 1;

		// Statistics - Scored
		statistics[idHome][TOTAL_SCORED] += matches[idMatch].getGoalHome();
		statistics[idAway][TOTAL_SCORED] += matches[idMatch].getGoalAway();

		// Statistics - Conceded
		statistics[idHome][TOTAL_CONCEDED] += matches[idMatch].getGoalAway();
		statistics[idAway][TOTAL_CONCEDED] += matches[idMatch].getGoalHome();

		// Statistics - Points
		statistics[idHome][TOTAL_POINTS] += matches[idMatch].getPointHome();
		statistics[idAway][TOTAL_POINTS] += matches[idMatch].getPointAway();

	}

	// new Match()
	public void createMatch( int idMatch , int day , int month , int year ) {
		int idHome = matchCalendar[idMatch * 2];
		int idAway = matchCalendar[idMatch * 2 + 1];
		ArrayList<Action> actions = new ArrayList<Action>();
		matches[idMatch] = new Match( day , month , year , teams[idHome] , teams[idAway] , "Pierluigi Collina" , "Sunny" , actions );
	}

	public void createMatch( int idWeek , int dayOrder , int day , int month , int year ) {
		createMatch( 2 * idWeek + dayOrder , day , month , year );
	}

	public void playMatch( int idMatch ) throws InterruptedException {
		int idHome = matchCalendar[idMatch * 2];
		int idAway = matchCalendar[idMatch * 2 + 1];
		matches[idMatch].matchSimulation();
		modifyGroupStatistics( idMatch , idHome , idAway );
	}

	public void playMatch( int idWeek , int dayOrder ) throws InterruptedException {
		playMatch( 2 * idWeek + dayOrder );
	}

	public void setMatchCalendarOrder() {

		matchCalendar = new int[NUMBER_OF_MATCHES * 2];

		// 1st week
		matchCalendar[0] = 0;
		matchCalendar[1] = 1;
		matchCalendar[2] = 2;
		matchCalendar[3] = 3;

		// 2nd week
		matchCalendar[4] = 1;
		matchCalendar[5] = 2;
		matchCalendar[6] = 3;
		matchCalendar[7] = 0;

		// 3rd week
		matchCalendar[8] = 2;
		matchCalendar[9] = 0;
		matchCalendar[10] = 3;
		matchCalendar[11] = 1;

		// 4th week
		matchCalendar[12] = 2;
		matchCalendar[13] = 1;
		matchCalendar[14] = 0;
		matchCalendar[15] = 3;

		// 5th week
		matchCalendar[16] = 1;
		matchCalendar[17] = 0;
		matchCalendar[18] = 3;
		matchCalendar[19] = 2;

		// 6th week
		matchCalendar[20] = 0;
		matchCalendar[21] = 2;
		matchCalendar[22] = 1;
		matchCalendar[23] = 3;

	}

	public int[][] getStatistics() {
		return statistics;
	}

	public void setStatistics(int[][] statistics) {
		this.statistics = statistics;
	}

	public Team[] getTeams() {
		return teams;
	}

	public Team getTeam( int teamId ) {
		return teams[teamId];
	}

	public void setTeams(Team[] teams) {
		this.teams = teams;
	}

	public Match[] getMatches() {
		return matches;
	}

	public void setMatches(Match[] matches) {
		this.matches = matches;
	}

	public int[] getMatchCalendar() {
		return matchCalendar;
	}

	public Team getMatchCalendarSingleTeamObject( int no ) {
		return teams[getMatchCalendarSingleTeamId(no)];
	}

	public int getMatchCalendarSingleTeamId( int no ) {
		return matchCalendar[no];
	}

	public void setMatchCalendar(int[] matchCalendar) {
		this.matchCalendar = matchCalendar;
	}

	public int[][] getOrderedStats() {
		return orderedStats;
	}

	public void setOrderedStats(int[][] orderedStats) {
		this.orderedStats = orderedStats;
	}

	public String[] getOrderedNames() {
		return orderedNames;
	}

	public String getOrderedTeamName( int idTeam ) {
		return orderedNames[idTeam];
	}

	public void setOrderedNames(String[] orderedNames) {
		this.orderedNames = orderedNames;
	}

	public Match getMatch( int id ) {
		return matches[id];
	}

	public int getMatchDay( int id ) {
		return matches[id].getDay();
	}

	public int getMatchMonth( int id ) {
		return matches[id].getMonth();
	}

	public int getMatchYear( int id ) {
		return matches[id].getYear();
	}

	public int getTEAMS_PER_GROUP() {
		return TEAMS_PER_GROUP;
	}

	public int getNUMBER_OF_STATS() {
		return NUMBER_OF_STATS;
	}

	public int getNUMBER_OF_MATCHES() {
		return NUMBER_OF_MATCHES;
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

	public int getTOTAL_PLAYED() {
		return TOTAL_PLAYED;
	}

	public int getTOTAL_WINS() {
		return TOTAL_WINS;
	}

	public int getTOTAL_DRAWS() {
		return TOTAL_DRAWS;
	}

	public int getTOTAL_LOSSES() {
		return TOTAL_LOSSES;
	}

	public int getTOTAL_SCORED() {
		return TOTAL_SCORED;
	}

	public int getTOTAL_CONCEDED() {
		return TOTAL_CONCEDED;
	}

	public int getTOTAL_POINTS() {
		return TOTAL_POINTS;
	}

}
