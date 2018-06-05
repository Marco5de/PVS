package swc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import swc.data.*;

public class CtrlFinals {
	/**
	 * This field is used by the {@link #calculateFinals(SoccerWC)} method.
	 * When set to true, no caclulation will take place before all
	 * teams have been completed.
	 */
	private static boolean demandCompleteTeams = false;

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
			round16.get(i).getTeamH().setStrName(br.readLine());
			round16.get(i).getTeamG().setStrName(br.readLine());
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
			quarter.get(i).getTeamH().setStrName(br.readLine());
			quarter.get(i).getTeamG().setStrName(br.readLine());
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
			semi.get(i).getTeamH().setStrName(br.readLine());
			semi.get(i).getTeamG().setStrName(br.readLine());
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
		
		thirdGame.getTeamH().setStrName(br.readLine());
		thirdGame.getTeamG().setStrName(br.readLine());
		
		
		finalGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		finalGame.setDate(br.readLine());
		finalGame.setTime(br.readLine());
		finalGame.setLocation(br.readLine());
		finalGame.setTeamH(new Team());
		finalGame.setTeamG(new Team());
		finalGame.setGoalsH(0);
		finalGame.setGoalsG(0);
		finalGame.setPlayed(false);
		
		finalGame.getTeamH().setStrName(br.readLine());
		finalGame.getTeamG().setStrName(br.readLine());
		worldCup.getFinals().setWinner("No winner known");
	}

	/**
	 * Calculates the teams which end up in the final
	 * round of the world cup.
	 * 
	 * @param worldCup - SoccerWC
	 * @see {@link #demandCompleteTeams}
	 */
	public static void calculateFinals(SoccerWC worldCup) {
		/*
		 * Verify that the group phase has been completed.
		 */
		if(demandCompleteTeams) {
			for(Group group: worldCup.getGroups()) {
				for(Game game: group.getGames()) {
					if(!game.isPlayed())
						return;
				}
			}
		}

		// Operate on a dummy Final object.
		SoccerWC dummyWC = new SoccerWC();
		try {
			CtrlFinals.createDefaultFinals(dummyWC);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Final dummyFinals = dummyWC.getFinals(),
				finals = worldCup.getFinals();
		/*
		 * Step 1: Determine the teams in the
		 * round of 16.
		 * Use two lists to assign teams.
		 */
		int [] listHome = {0, 2, 1, 3, 4, 6, 5, 7},
				listGuest = {1, 3, 0, 2, 5, 7, 4, 6};
		for(int i = 0; i < 8; i++) {
			Game game = dummyFinals.getRoundOf16().elementAt(i);
			game.setTeamH(worldCup.getGroups().get(listHome[i]).getTeams().get(0));
			game.setTeamG(worldCup.getGroups().get(listGuest[i]).getTeams().get(1));
		}
		/*
		 * Now, look on the actual finals and determine if the team selections
		 * stay the same. If they differ, the "new" game from the dummyFinals
		 * replaces the actual game from the round of 16, and it becomes not played. 
		 */
		for(int i = 0; i < 8; i++) {
			Game gameOld = finals.getRoundOf16().get(i);
			Game gameNew = dummyFinals.getRoundOf16().get(i);
			if(!teamsAreShared(gameOld, gameNew)) {
				finals.getRoundOf16().set(i, gameNew);
			}
		}

		/*
		 * Step 2: Calculate the quarter finals.
		 * Again, use lists to find winners of the round of 16.
		 * Also, those games must be played before the quarter finals
		 * can be calculated.
		 */
		int [] listHome2 = {0, 4, 2, 6},
		listGuest2 = {1, 5, 3, 7};
		for(int i = 0; i < 4; i++) {
			Game quarterFinal = dummyFinals.getQuarterFinals().get(i),
					homeGame = finals.getRoundOf16().get(listHome2[i]),
					guestGame = finals.getRoundOf16().get(listGuest2[i]);
			if(!homeGame.isPlayed() || !guestGame.isPlayed())
				continue;

			if(homeTeamWins(homeGame)) 
				quarterFinal.setTeamH(homeGame.getTeamH());
			else
				quarterFinal.setTeamH(homeGame.getTeamG());

			if(homeTeamWins(guestGame)) 
				quarterFinal.setTeamG(guestGame.getTeamH());
			else
				quarterFinal.setTeamG(guestGame.getTeamG());
			dummyFinals.getQuarterFinals().set(i, quarterFinal);
		}

		/*
		 * Again, check whether recalculation of
		 * quarter final teams is needed.
		 */
		for(int i = 0; i < 4; i++) {
			Game gameOld2 = finals.getQuarterFinals().get(i),
					gameNew2 = dummyFinals.getQuarterFinals().get(i);
			if(!teamsAreShared(gameOld2, gameNew2))
				finals.getQuarterFinals().set(i, gameNew2);
		}

		/*
		 * Step 3: Calculate the members of the semifinals.
		 */
		int [] listHome3 = {0, 1},
				listGuest3 = {2, 3};
		for(int i = 0; i < 2; i++) {
			Game semifinal = dummyFinals.getSemiFinals().get(i),
					homeGame = finals.getQuarterFinals().get(listHome3[i]),
					guestGame = finals.getQuarterFinals().get(listGuest3[i]);

			if(!homeGame.isPlayed() || !guestGame.isPlayed())
				continue;

			if(homeTeamWins(homeGame)) 
				semifinal.setTeamH(homeGame.getTeamH());
			else
				semifinal.setTeamH(homeGame.getTeamG());

			if(homeTeamWins(guestGame)) 
				semifinal.setTeamG(guestGame.getTeamH());
			else
				semifinal.setTeamG(guestGame.getTeamG());
			dummyFinals.getSemiFinals().set(i, semifinal);
		}

		/*
		 * Again, check whether recalculation of
		 * quarter final teams is needed.
		 */
		for(int i = 0; i < 2; i++) {
			Game gameOld3 = finals.getSemiFinals().get(i),
					gameNew3 = dummyFinals.getSemiFinals().get(i);
			if(!teamsAreShared(gameOld3, gameNew3))
				finals.getSemiFinals().set(i, gameNew3);
		}

		for(Game semifinal: finals.getSemiFinals())
			if(!semifinal.isPlayed())
				return;
		/*
		 * Do calculations for the match for third place,
		 * and for the actual finals.
		 */
		Game semifinal1 = finals.getSemiFinals().get(0),
				semifinal2 = finals.getSemiFinals().get(1);
		if(homeTeamWins(semifinal1)) {
			dummyFinals.getFinalGame().setTeamH(semifinal1.getTeamH());
			dummyFinals.getThirdGame().setTeamH(semifinal1.getTeamG());
		}
		else {
			dummyFinals.getFinalGame().setTeamH(semifinal1.getTeamG());
			dummyFinals.getThirdGame().setTeamH(semifinal1.getTeamH());
		}
		if(homeTeamWins(semifinal2)) {
			dummyFinals.getFinalGame().setTeamG(semifinal2.getTeamH());
			dummyFinals.getThirdGame().setTeamG(semifinal2.getTeamG());
		}
		else {
			dummyFinals.getFinalGame().setTeamG(semifinal2.getTeamG());
			dummyFinals.getThirdGame().setTeamG(semifinal2.getTeamH());
		}

		if(!teamsAreShared(finals.getThirdGame(), dummyFinals.getThirdGame())) {
			Game thirdGame = finals.getThirdGame();
			thirdGame.setGoalsG(0);
			thirdGame.setGoalsH(0);
			thirdGame.setTeamG(dummyFinals.getThirdGame().getTeamG());
			thirdGame.setTeamH(dummyFinals.getThirdGame().getTeamH());
			thirdGame.setPlayed(false);
		}
		if(!teamsAreShared(finals.getFinalGame(), dummyFinals.getFinalGame())) {
			Game finalGame = finals.getFinalGame();
			finalGame.setGoalsG(0);
			finalGame.setGoalsH(0);
			finalGame.setTeamG(dummyFinals.getFinalGame().getTeamG());
			finalGame.setTeamH(dummyFinals.getFinalGame().getTeamH());
			finalGame.setPlayed(false);
		}
		/*
		 * Determine the winner of the tournament.
		 */
		if(!finals.getFinalGame().isPlayed()){
			finals.setWinner("No winner known");
			return;
		}
		if(homeTeamWins(finals.getFinalGame()))
			finals.setWinner(finals.getFinalGame().getTeamH().getStrName());
		else
			finals.setWinner(finals.getFinalGame().getTeamG().getStrName());
	}

	private static boolean homeTeamWins(Game g) {
		int goalDiff = g.getGoalsH()-g.getGoalsG();
		return (goalDiff > 0);
	}

	private static boolean teamsAreShared(Game g1, Game g2) {
		String home1 = g1.getTeamH().getStrName(),
				home2 = g2.getTeamH().getStrName();
		String guest1 = g1.getTeamG().getStrName(),
				guest2 = g2.getTeamG().getStrName();
		return(home1.equals(home2) && guest1.equals(guest2));
	}
}
