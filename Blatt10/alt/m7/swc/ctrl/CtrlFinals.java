package swc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

public class CtrlFinals {

	public static String getStatus(SoccerWC worldCup) {
		int count = 0;
		for (Group g : worldCup.getGroups()) {
			for (Game game : g.getGames()) {
				if(game.isPlayed())
					count++;
			}
		}
		for (Game g: worldCup.getFinals().getRoundOf16()){
			if(g.isPlayed())
				count++;
		}	
		for (Game g: worldCup.getFinals().getQuarterFinals()){
			if(g.isPlayed())
				count++;
		}
		for (Game g: worldCup.getFinals().getSemiFinals()){
			if(g.isPlayed())
				count++;
		}
		if(worldCup.getFinals().getThirdGame().isPlayed())
			count++;
		if(worldCup.getFinals().getFinalGame().isPlayed())
			count++;
		if(count >= 48 && count < 64)
			return "" + count + " played, finals ongoing.";
		if(count == 64){
			return "" + count + " played, World Cup completed!";
		}	
		return "" + count + " played, group phase ongoing.";
	}

	public static void createDefaultFinals(SoccerWC worldCup) throws NumberFormatException, IOException {
		BufferedReader br = null;
		try {
			URL confUrl = CtrlGroup.class.getResource("/data/config/finals.cfg");
			InputStreamReader isR = new InputStreamReader(confUrl.openStream());
			br = new BufferedReader(isR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Vector<Game> round16 = worldCup.getFinals().getRoundOf16();
		round16.clear();
		Vector<Game> quarter = worldCup.getFinals().getQuarterFinals();
		quarter.clear();
		Vector<Game> semi = worldCup.getFinals().getSemiFinals();
		semi.clear();
		Game thirdGame = worldCup.getFinals().getThirdGame();
		Game finalGame = worldCup.getFinals().getFinalGame();
		worldCup.getFinals().setWinner("");
		
		for (int i = 0; i < 8; i++) {
			round16.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			round16.get(i).getTeamH().setName(br.readLine());
			round16.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 4; i++) {
			quarter.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			quarter.get(i).getTeamH().setName(br.readLine());
			quarter.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 2; i++) {
			semi.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			semi.get(i).getTeamH().setName(br.readLine());
			semi.get(i).getTeamG().setName(br.readLine());
		}
		thirdGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		thirdGame.setDate(br.readLine());
		thirdGame.setTime(br.readLine());
		thirdGame.setLocation(br.readLine());
		thirdGame.setTeamH(new Team());
		thirdGame.setTeamG(new Team());
		thirdGame.setGoalsH(0);
		thirdGame.setGoalsG(0);
		thirdGame.setPlayed(false);
		
		thirdGame.getTeamH().setName(br.readLine());
		thirdGame.getTeamG().setName(br.readLine());
		
		
		finalGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		finalGame.setDate(br.readLine());
		finalGame.setTime(br.readLine());
		finalGame.setLocation(br.readLine());
		finalGame.setTeamH(new Team());
		finalGame.setTeamG(new Team());
		finalGame.setGoalsH(0);
		finalGame.setGoalsG(0);
		finalGame.setPlayed(false);
		
		finalGame.getTeamH().setName(br.readLine());
		finalGame.getTeamG().setName(br.readLine());
	}
	
	public static void calculateFinals(SoccerWC worldCup){
		Vector<Game> roundOf16 = worldCup.getFinals().getRoundOf16();
		Vector<Game> quarterFinals = worldCup.getFinals().getQuarterFinals();
		Vector<Game> semiFinals = worldCup.getFinals().getSemiFinals();
		Game thirdGame = worldCup.getFinals().getThirdGame();
		Game finalGame = worldCup.getFinals().getFinalGame();
	
		// round of 16
		Group g = worldCup.getGroups().get(0);
		if(g.isGroupCompleted()){
			roundOf16.get(0).setTeamH(g.getTeams().get(0));
			roundOf16.get(3).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(1);
		if(g.isGroupCompleted()){
			roundOf16.get(3).setTeamH(g.getTeams().get(0));
			roundOf16.get(0).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(2);
		if(g.isGroupCompleted()){
			roundOf16.get(1).setTeamH(g.getTeams().get(0));
			roundOf16.get(2).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(3);
		if(g.isGroupCompleted()){
			roundOf16.get(2).setTeamH(g.getTeams().get(0));
			roundOf16.get(1).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(4);
		if(g.isGroupCompleted()){
			roundOf16.get(4).setTeamH(g.getTeams().get(0));
			roundOf16.get(6).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(5);
		if(g.isGroupCompleted()){
			roundOf16.get(6).setTeamH(g.getTeams().get(0));
			roundOf16.get(4).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(6);
		if(g.isGroupCompleted()){
			roundOf16.get(5).setTeamH(g.getTeams().get(0));
			roundOf16.get(7).setTeamG(g.getTeams().get(1));
		}
		g = worldCup.getGroups().get(7);
		if(g.isGroupCompleted()){
			roundOf16.get(7).setTeamH(g.getTeams().get(0));
			roundOf16.get(5).setTeamG(g.getTeams().get(1));
		}
		
		// quarter finals
		Team winner;
		if(roundOf16.get(0).isPlayed()){
			winner = calculateWinner(roundOf16.get(0));
			quarterFinals.get(1).setTeamH(winner);
		}
		if(roundOf16.get(1).isPlayed()){
			winner = calculateWinner(roundOf16.get(1));
			quarterFinals.get(1).setTeamG(winner);
		}
		if(roundOf16.get(2).isPlayed()){
			 winner = calculateWinner(roundOf16.get(2));
			quarterFinals.get(2).setTeamG(winner);
		}
		if(roundOf16.get(3).isPlayed()){
			winner = calculateWinner(roundOf16.get(3));
			quarterFinals.get(2).setTeamH(winner);
		}
		if(roundOf16.get(4).isPlayed()){
			winner = calculateWinner(roundOf16.get(4));
			quarterFinals.get(0).setTeamH(winner);
		}
		if(roundOf16.get(5).isPlayed()){
			winner = calculateWinner(roundOf16.get(5));
			quarterFinals.get(0).setTeamG(winner);
		}
		if(roundOf16.get(6).isPlayed()){
			winner = calculateWinner(roundOf16.get(6));
			quarterFinals.get(3).setTeamH(winner);
		}
		if(roundOf16.get(7).isPlayed()){
			winner = calculateWinner(roundOf16.get(7));
			quarterFinals.get(3).setTeamG(winner);
		}
		
		// semi finals
		if(quarterFinals.get(0).isPlayed()){
			winner = calculateWinner(quarterFinals.get(0));
			semiFinals.get(0).setTeamG(winner);
		}
		if(quarterFinals.get(1).isPlayed()){
			winner = calculateWinner(quarterFinals.get(1));
			semiFinals.get(0).setTeamH(winner);
		}
		if(quarterFinals.get(2).isPlayed()){
			winner = calculateWinner(quarterFinals.get(2));
			semiFinals.get(1).setTeamH(winner);
		}
		if(quarterFinals.get(3).isPlayed()){
			winner = calculateWinner(quarterFinals.get(3));
			semiFinals.get(1).setTeamG(winner);
		}
		
		// match for third
		if(semiFinals.get(0).isPlayed()){
			if(semiFinals.get(0).getGoalsH() < semiFinals.get(0).getGoalsG())
				thirdGame.setTeamH(semiFinals.get(0).getTeamH());
			else
				thirdGame.setTeamH(semiFinals.get(0).getTeamG());
		}
		if(semiFinals.get(1).isPlayed()){
			if(semiFinals.get(1).getGoalsH() < semiFinals.get(1).getGoalsG())
				thirdGame.setTeamG(semiFinals.get(1).getTeamH());
			else
				thirdGame.setTeamG(semiFinals.get(1).getTeamG());
		}
		
		// final
		if(semiFinals.get(0).isPlayed()){
			winner = calculateWinner(semiFinals.get(0));
			finalGame.setTeamH(winner);
		}
		if(semiFinals.get(1).isPlayed()){
			winner = calculateWinner(semiFinals.get(1));
			finalGame.setTeamG(winner);
		}
		
		// winner
		if(finalGame.isPlayed()){
			winner = calculateWinner(finalGame);
			worldCup.getFinals().setWinner(winner.getName());
		}	
	}

	public static Team calculateWinner(Game game) {
		return game.getGoalsH() > game.getGoalsG() ? 
				game.getTeamH() : game.getTeamG();
	}

}
