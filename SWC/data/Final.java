package swc.data;

import java.util.Vector;

public class Final {
	private Vector<Game> roundOf16 = new Vector<>();
	private Vector<Game> quarterFinals = new Vector<>();
	private Vector<Game> semiFinals = new Vector<>();
	private Game thirdGame;
	private Game finalGame;
	private String winner;

	public Final() {}

	/**
	 * @return the roundOf16
	 */
	public Vector<Game> getRoundOf16() {
		return roundOf16;
	}

	/**
	 * @param roundOf16 the roundOf16 to set
	 */
	public void setRoundOf16(Vector<Game> roundOf16) {
		this.roundOf16 = roundOf16;
	}

	/**
	 * @return the quarterFinals
	 */
	public Vector<Game> getQuarterFinals() {
		return quarterFinals;
	}

	/**
	 * @param quarterFinals the quarterFinals to set
	 */
	public void setQuarterFinals(Vector<Game> quarterFinals) {
		this.quarterFinals = quarterFinals;
	}

	/**
	 * @return the semiFinals
	 */
	public Vector<Game> getSemiFinals() {
		return semiFinals;
	}

	/**
	 * @param semiFinals the semiFinals to set
	 */
	public void setSemiFinals(Vector<Game> semiFinals) {
		this.semiFinals = semiFinals;
	}

	/**
	 * @return the thirdGame
	 */
	public Game getThirdGame() {
		return thirdGame;
	}

	/**
	 * @param thirdGame the thirdGame to set
	 */
	public void setThirdGame(Game thirdGame) {
		this.thirdGame = thirdGame;
	}

	/**
	 * @return the finalGame
	 */
	public Game getFinalGame() {
		return finalGame;
	}

	/**
	 * @param finalGame the finalGame to set
	 */
	public void setFinalGame(Game finalGame) {
		this.finalGame = finalGame;
	}

	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}
}