package swc.data;

public class Team implements Comparable<Team> {
	private String strName;
	private int points;
	private int gf;
	private int ga;
	private int played;
	private int won;
	private int lost;
	private int draws;
	
	public Team(String strName, int points, int gf, int ga, int played, int won, int lost, int draws) {
		this.strName = strName;
		this.points = points;
		this.gf = gf;
		this.ga = ga;
		this.played = played;
		this.won = won;
		this.lost = lost;
		this.draws = draws;
	}

	/**
	 * @return the strName
	 */
	public String getStrName() {
		return strName;
	}

	/**
	 * @param strName the strName to set
	 */
	public void setStrName(String strName) {
		this.strName = strName;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the gf
	 */
	public int getGf() {
		return gf;
	}

	/**
	 * @param gf the gf to set
	 */
	public void setGf(int gf) {
		this.gf = gf;
	}

	/**
	 * @return the ga
	 */
	public int getGa() {
		return ga;
	}

	/**
	 * @param ga the ga to set
	 */
	public void setGa(int ga) {
		this.ga = ga;
	}

	/**
	 * @return the played
	 */
	public int getPlayed() {
		return played;
	}

	/**
	 * @param played the played to set
	 */
	public void setPlayed(int played) {
		this.played = played;
	}

	/**
	 * @return the won
	 */
	public int getWon() {
		return won;
	}

	/**
	 * @param won the won to set
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * @return the lost
	 */
	public int getLost() {
		return lost;
	}

	/**
	 * @param lost the lost to set
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}

	/**
	 * @return the draws
	 */
	public int getDraws() {
		return draws;
	}

	/**
	 * @param draws the draws to set
	 */
	public void setDraws(int draws) {
		this.draws = draws;
	}

	@Override
	public int compareTo(Team arg0) {
		if(arg0 == null)
			throw new NullPointerException();
		if(this == arg0)
			return 0;

		if(arg0.points < this.points)
			return -1;
		if(arg0.points > this.points)
			return 1;

		int gd1 = gf-ga,
				gd2 = arg0.gf-arg0.ga;
		if(gd1 < gd2)
			return 1;
		if(gd1 > gd2)
			return -1;

		if(arg0.gf < this.gf)
			return -1;
		if(arg0.gf > this.gf)
			return 1;

		return 0;
	}
}