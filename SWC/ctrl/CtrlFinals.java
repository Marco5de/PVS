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
}
