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
		int [] listHome = {0, 2, 3, 1, 4, 6, 5, 7},
				listGuest = {1, 3, 2, 0, 5, 7, 4, 6};
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
		int [] listHome2 = {4, 0, 3, 6},
		listGuest2 = {5, 1, 2, 7};
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
				quarterFinal.setTeamH(guestGame.getTeamH());
			else
				quarterFinal.setTeamH(guestGame.getTeamG());
		}
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
