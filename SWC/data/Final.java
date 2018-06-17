package swc.data;


import java.util.Vector;

public class Final {
    private Vector<Game> roundOf16 = new Vector<>();
    private Vector<Game> quarterFinals = new Vector<>();
    private Vector<Game> semiFinals = new Vector<>();
    private Game thirdGame = new Game();
    private Game finalGame = new Game();
    private String winner;

    public Final(){}

    public Vector<Game> getRoundOf16() {
        return roundOf16;
    }

    public Vector<Game> getQuarterFinals() {
        return quarterFinals;
    }

    public Vector<Game> getSemiFinals() {
        return semiFinals;
    }

    public Game getThirdGame() {
        return thirdGame;
    }

    public Game getFinalGame() {
        return finalGame;
    }

    public String getWinner() {
        return winner;
    }

    public void setRoundOf16(Vector<Game> roundOf16) {
        this.roundOf16 = roundOf16;
    }

    public void setQuarterFinals(Vector<Game> quarterFinals) {
        this.quarterFinals = quarterFinals;
    }

    public void setSemiFinals(Vector<Game> semiFinals) {
        this.semiFinals = semiFinals;
    }

    public void setThirdGame(Game thirdGame) {
        this.thirdGame = thirdGame;
    }

    public void setFinalGame(Game finalGame) {
        this.finalGame = finalGame;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
