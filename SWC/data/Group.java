package swc.data;

import java.util.Vector;

public class Group {
	private String strGroupName;
	private Vector<Team> teams = new Vector<>();
	private Vector<Game> games = new Vector<>();
	private boolean isGroupCompleted = false;

	public Group(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	public void addTeam(Team teamName) {
		teams.add(teamName);
	}

	public void addGame(Game newGame) {
		games.add(newGame);
	}

	/**
	 * @return the strGroupName
	 */
	public String getStrGroupName() {
		return strGroupName;
	}

	/**
	 * @param strGroupName the strGroupName to set
	 */
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	/**
	 * @return the teams
	 */
	public Vector<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(Vector<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @return the games
	 */
	public Vector<Game> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(Vector<Game> games) {
		this.games = games;
	}

	/**
	 * @return the isGroupCompleted
	 */
	public boolean isGroupCompleted() {
		return isGroupCompleted;
	}

	/**
	 * @param isGroupCompleted the isGroupCompleted to set
	 */
	public void setGroupCompleted(boolean isGroupCompleted) {
		this.isGroupCompleted = isGroupCompleted;
	}
}