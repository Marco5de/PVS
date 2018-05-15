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

	public Game() {
		
	}

	public Game(int intId, String time, String date, String location, int goalsH, int goalsG, boolean isPlayed,
			Team teamH, Team teamG) {
		this.intId = intId;
		this.time = time;
		this.date = date;
		this.location = location;
		this.goalsH = goalsH;
		this.goalsG = goalsG;
		this.isPlayed = isPlayed;
		this.teamH = teamH;
		this.teamG = teamG;
	}

	/**
	 * @return the intId
	 */
	public int getIntId() {
		return intId;
	}

	/**
	 * @param intId the intId to set
	 */
	public void setIntId(int intId) {
		this.intId = intId;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the goalsH
	 */
	public int getGoalsH() {
		return goalsH;
	}

	/**
	 * @param goalsH the goalsH to set
	 */
	public void setGoalsH(int goalsH) {
		this.goalsH = goalsH;
	}

	/**
	 * @return the goalsG
	 */
	public int getGoalsG() {
		return goalsG;
	}

	/**
	 * @param goalsG the goalsG to set
	 */
	public void setGoalsG(int goalsG) {
		this.goalsG = goalsG;
	}

	/**
	 * @return the isPlayed
	 */
	public boolean isPlayed() {
		return isPlayed;
	}

	/**
	 * @param isPlayed the isPlayed to set
	 */
	public void setPlayed(boolean isPlayed) {
		this.isPlayed = isPlayed;
	}

	/**
	 * @return the teamH
	 */
	public Team getTeamH() {
		return teamH;
	}

	/**
	 * @param teamH the teamH to set
	 */
	public void setTeamH(Team teamH) {
		this.teamH = teamH;
	}

	/**
	 * @return the teamG
	 */
	public Team getTeamG() {
		return teamG;
	}

	/**
	 * @param teamG the teamG to set
	 */
	public void setTeamG(Team teamG) {
		this.teamG = teamG;
	}
}