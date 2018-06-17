package swc.data;


import java.util.Vector;

public class Group {
    private String strGroupName;
    private Vector<Team> teams = new Vector<>();
    private Vector<Game> games = new Vector<>();
    boolean isGroupCompleted;

    public Group(String strGroupName){
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

    public boolean isGroupCompleted() {
        return isGroupCompleted;
    }

    public void setStrGroupName(String strGroupName) {
        this.strGroupName = strGroupName;
    }

    public void setTeams(Vector<Team> teams) {
        this.teams = teams;
    }

    public void setGames(Vector<Game> games) {
        this.games = games;
    }

    public void setGroupCompleted(boolean groupCompleted) {
        isGroupCompleted = groupCompleted;
    }

    public void addTeam(Team teamName){
        teams.add(teamName);

    }
    public void addGame(Game newGame){
        games.add(newGame);
    }
}
