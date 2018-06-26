package swc.data;

import java.util.Vector;

public class Final {
	private Vector<Game> roundOf16;
	private Vector<Game> quarterFinals;
	private Vector<Game> semiFinals;
	private Game thirdGame;
	private Game finalGame;
	private String winner;
	
	public Final(){
		setRoundOf16(new Vector<Game>());
		setQuarterFinals(new Vector<Game>());
		setSemiFinals(new Vector<Game>());
		setThirdGame(new Game());
		setFinalGame(new Game());
	}

	public void setRoundOf16(Vector<Game> roundOf16) {
		this.roundOf16 = roundOf16;
	}

	public Vector<Game> getRoundOf16() {
		return roundOf16;
	}

	public void setQuarterFinals(Vector<Game> quarterFinals) {
		this.quarterFinals = quarterFinals;
	}

	public Vector<Game> getQuarterFinals() {
		return quarterFinals;
	}

	public void setSemiFinals(Vector<Game> semiFinals) {
		this.semiFinals = semiFinals;
	}

	public Vector<Game> getSemiFinals() {
		return semiFinals;
	}

	public void setFinalGame(Game finalGame) {
		this.finalGame = finalGame;
	}

	public Game getFinalGame() {
		return finalGame;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWinner() {
		return winner;
	}

	public void setThirdGame(Game thirdGame) {
		this.thirdGame = thirdGame;
	}

	public Game getThirdGame() {
		return thirdGame;
	}
}
