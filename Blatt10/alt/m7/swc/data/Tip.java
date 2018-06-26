package swc.data;

public class Tip {
	private int gameId;
	private int goalsHome;
	private int goalsGuest;
	
	public Tip(){
		
	}
	
	public Tip(int gameId){
		setGameId(gameId);
	}
	
	public Tip(int id, int gh, int gg) {
		setGameId(id);
		setGoalsHome(gh);
		setGoalsGuest(gg);
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGoalsHome() {
		return goalsHome;
	}

	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	public int getGoalsGuest() {
		return goalsGuest;
	}

	public void setGoalsGuest(int goalsGuest) {
		this.goalsGuest = goalsGuest;
	}

	
}
