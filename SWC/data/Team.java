package swc.data;

public class Team implements Comparable<Team> {
    private String strName;
    private int points;
    private int gf;
    private int ga;
    private int played;
    private int won;
    private int loss;
    private int draw;

    public Team(String name, int points, int gf, int ga, int played ,int won, int loss, int draw){
        this.strName = name;
        this.points = points;
        this.gf = gf;
        this.ga = ga;
        this.played = played;
        this.won = won;
        this.loss = loss;
        this.draw = draw;
    }

    public Team() {}

    public String getStrName() {
        return strName;
    }

    public int getPoints() {
        return points;
    }

    public int getGf() {
        return gf;
    }

    public int getGa() {
        return ga;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getLoss() {
        return loss;
    }

    public int getDraw() {
        return draw;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    @Override
    public int compareTo(Team compTeam) {
        if(compTeam.points < this.points)
            return -1;
        else if(compTeam.points > this.points)
            return 1;
        else {
            if((compTeam.gf - compTeam.ga) < (this.gf - this.ga))
                return -1;
            else if((compTeam.gf - compTeam.ga) > (this.gf - this.ga))
                return 1;
            else{
                if(compTeam.gf < this.gf)
                    return -1;
                else
                    return 1;
            }
        }

    }

    public void clearTeam(){

    }

}
