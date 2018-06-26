package swc.data;

public class Game {

	private int intId;
	
	private String time;
	private String date;
	private String location;
	private int goalsH;
	private int goalsG;
	private boolean isPlayed;
	
	private Team teamH;
	private Team teamG;
	
	// Constructor with internal game id
	// and home + guest team

	public Game(){
		
	}
	
	public Game (int id, String date, String time, String location,  Team home, Team guest, int goalsH, int goalsG, boolean isPlayed) {
		this.setIntId(id);
		this.setTeamH(home);
		this.setTeamG(guest);
		this.setDate(date);
		this.setTime(time);
		this.setLocation(location);
		this.setGoalsG(goalsG);
		this.setGoalsH(goalsH);
		this.setPlayed(isPlayed);
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public int getIntId() {
		return intId;
	}

	public void setTeamH(Team teamH) {
		this.teamH = teamH;
	}

	public Team getTeamH() {
		return teamH;
	}

	public void setTeamG(Team teamG) {
		this.teamG = teamG;
	}

	public Team getTeamG() {
		return teamG;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setGoalsH(int goalsH) {
		this.goalsH = goalsH;
	}

	public int getGoalsH() {
		return goalsH;
	}

	public void setGoalsG(int goalsG) {
		this.goalsG = goalsG;
	}

	public int getGoalsG() {
		return goalsG;
	}

	public void setPlayed(boolean isPlayed){
		this.isPlayed = isPlayed;
	}
	
	public boolean isPlayed() {
		return isPlayed;
	}
	
}
