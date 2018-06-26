package swc.data;

import java.util.Vector;

public class Group {
	
	private String strGroupName;
	private Vector<Team> teams;
	private Vector<Game> games;
	private boolean isGroupCompleted;

	//Constructor creats new Group-Object
	//clears teams Vector
	public Group(String groupName) {
		this.teams = new Vector<Team>();
		this.games = new Vector<Game>();
		this.setStrGroupName(groupName);
	}
	
	//Gets an team object and adds it to the group
	public void addTeam(Team teamName) {
		this.teams.add(teamName);
	}
	
	public void addGame(Game newGame) {
		this.games.add(newGame);
	}
	
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	public String getStrGroupName() {
		return strGroupName;
	}
	
	public Vector<Team> getTeams() {
		return teams;
	}

	public Vector<Game> getGames() {
		return games;
	}

	public void setGroupCompleted(boolean isGroupCompleted) {
		this.isGroupCompleted = isGroupCompleted;
	}

	public boolean isGroupCompleted() {
		return isGroupCompleted;
	}
}
