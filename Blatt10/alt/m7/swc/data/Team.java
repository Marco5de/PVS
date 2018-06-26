package swc.data;

public class Team implements Comparable <Team>{
	
	private String strName;
	private int points;
	private int gf;
	private int ga;
	private int played;
	private int won;
	private int loss;
	private int draw;

	public Team(){
		
	}
	
	public Team(String name, int points, int gf, int ga, int played, int won, int loss, int draw) {
		this.setName(name);
		this.setPoints(points);
		this.setDraw(draw);
		this.setGa(ga);
		this.setGf(gf);
		this.setLoss(loss);
		this.setPlayed(played);
		this.setWon(won);
	}

	@Override
	public int compareTo(Team arg0) {
		if(this.points != arg0.points)
			return (this.points > arg0.points ? -1 : 1);
		else if(this.gf-this.ga != arg0.gf - arg0.ga)
			return (this.gf-this.ga > arg0.gf - arg0.ga ? -1 : 1);
		else if(this.gf != arg0.gf)
			return (this.gf > arg0.gf ? -1 : 1);
		return 0;
	}
	
	public void clearTeam(){
		setDraw(0);
		setWon(0);
		setLoss(0);
		setGa(0);
		setGf(0);
		setPlayed(0);
		setPoints(0);
	}
	
	public String getName() {
		return strName;
	}
	
	public void setName(String name) {
		this.strName = name;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setGf(int gf) {
		this.gf = gf;
	}

	public int getGf() {
		return gf;
	}

	public void setGa(int ga) {
		this.ga = ga;
	}

	public int getGa() {
		return ga;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getPlayed() {
		return played;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getWon() {
		return won;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getLoss() {
		return loss;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getDraw() {
		return draw;
	}
}
