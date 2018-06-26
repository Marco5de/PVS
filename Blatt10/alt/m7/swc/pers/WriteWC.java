package swc.pers;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

public class WriteWC {

	public static void writeToCSV(String filename, SoccerWC worldCup) throws IOException {
		FileWriter writer = new FileWriter(filename);
 
		for (Group g : worldCup.getGroups()) {
			writer.append(worldCup.getName() + "\n");
			writer.append(g.getStrGroupName() + ": \n");
			writer.append("Teams: \n");
			writer.append("Position, Team, Played, Won, Draw, Loss, GF, GA, Points\n");
			for (int i = 0; i < g.getTeams().size(); i++) {
				Team current = g.getTeams().get(i);
				writer.append(i + ",");
				writer.append(current.getName() + ",");
				writer.append(current.getPlayed() + ",");
				writer.append(current.getWon() + ",");
				writer.append(current.getDraw() + ",");
				writer.append(current.getLoss() + ",");
				writer.append(current.getGf() + ",");
				writer.append(current.getGa() + ",");
				writer.append(current.getPoints() + "\n");
			}
			writer.append("Games: \n");
			writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
			for (int i = 0; i < g.getGames().size(); i++) {
				Game current = g.getGames().get(i);
				writer.append(current.getIntId() + ",");
				writer.append(current.getDate() + ",");
				writer.append(current.getTime() + ",");
				writer.append(current.getLocation() + ",");
				writer.append(current.getTeamH().getName() + ",");
				writer.append(current.getTeamG().getName() + ",");
				writer.append(current.getGoalsH() + ",");
				writer.append(current.getGoalsG() + ",");
				writer.append(current.isPlayed() + "\n");
			}
		}
		
		writer.append("Finals: \n");
		writer.append("Round of 16: \n");
		writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
		for (int i = 0; i < worldCup.getFinals().getRoundOf16().size(); i++) {
			Game current = worldCup.getFinals().getRoundOf16().get(i);
			writer.append(current.getIntId() + ",");
			writer.append(current.getDate() + ",");
			writer.append(current.getTime() + ",");
			writer.append(current.getLocation() + ",");
			writer.append(current.getTeamH().getName() + ",");
			writer.append(current.getTeamG().getName() + ",");
			writer.append(current.getGoalsH() + ",");
			writer.append(current.getGoalsG() + ",");
			writer.append(current.isPlayed() + "\n");
		}
		
		writer.append("Quarterfinals: \n");
		writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
		for (int i = 0; i < worldCup.getFinals().getQuarterFinals().size(); i++) {
			Game current = worldCup.getFinals().getQuarterFinals().get(i);
			writer.append(current.getIntId() + ",");
			writer.append(current.getDate() + ",");
			writer.append(current.getTime() + ",");
			writer.append(current.getLocation() + ",");
			writer.append(current.getTeamH().getName() + ",");
			writer.append(current.getTeamG().getName() + ",");
			writer.append(current.getGoalsH() + ",");
			writer.append(current.getGoalsG() + ",");
			writer.append(current.isPlayed() + "\n");
		}
		
		writer.append("Semifinals: \n");
		writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
		for (int i = 0; i < worldCup.getFinals().getSemiFinals().size(); i++) {
			Game current = worldCup.getFinals().getSemiFinals().get(i);
			writer.append(current.getIntId() + ",");
			writer.append(current.getDate() + ",");
			writer.append(current.getTime() + ",");
			writer.append(current.getLocation() + ",");
			writer.append(current.getTeamH().getName() + ",");
			writer.append(current.getTeamG().getName() + ",");
			writer.append(current.getGoalsH() + ",");
			writer.append(current.getGoalsG() + ",");
			writer.append(current.isPlayed() + "\n");
		}
		
		writer.append("Match for third Place: \n");
		writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
			Game current = worldCup.getFinals().getThirdGame();
			writer.append(current.getIntId() + ",");
			writer.append(current.getDate() + ",");
			writer.append(current.getTime() + ",");
			writer.append(current.getLocation() + ",");
			writer.append(current.getTeamH().getName() + ",");
			writer.append(current.getTeamG().getName() + ",");
			writer.append(current.getGoalsH() + ",");
			writer.append(current.getGoalsG() + ",");
			writer.append(current.isPlayed() + "\n");
		
		writer.append("Final: \n");
		writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
			current = worldCup.getFinals().getFinalGame();
			writer.append(current.getIntId() + ",");
			writer.append(current.getDate() + ",");
			writer.append(current.getTime() + ",");
			writer.append(current.getLocation() + ",");
			writer.append(current.getTeamH().getName() + ",");
			writer.append(current.getTeamG().getName() + ",");
			writer.append(current.getGoalsH() + ",");
			writer.append(current.getGoalsG() + ",");
			writer.append(current.isPlayed() + "\n");
		
		writer.flush();
		writer.close();

	}
	
	public static void writeToXML(String filename, SoccerWC worldCup) throws IOException {
		// setup document
		Element root = new Element("worldCup");
		Document doc = new Document(root);
		
		// setup meta info
		Element wcInfo = new Element("meta");
		wcInfo.addContent(new Element("name").setText(worldCup.getName()));
		root.addContent(wcInfo);
		
		// prepare groups
		Element groups = new Element("groups");
		
		for (Group gr : worldCup.getGroups()) {
			Element groupElement = new Element("group");
			groupElement.addContent(new Element("groupName").setText(gr.getStrGroupName()));
			addTeams(groupElement, gr.getTeams());
			addGames(groupElement, gr.getGames());
			groups.addContent(groupElement);
		}
		root.addContent(groups);
		
		// prepare finals
		Element finals = new Element("finals");
		
		Element round16 = new Element("round16");
		finals.addContent(round16);
		addGames(round16, worldCup.getFinals().getRoundOf16());
		
		Element quarter = new Element("quarter");
		finals.addContent(quarter);
		addGames(quarter, worldCup.getFinals().getQuarterFinals());
		
		Element semi = new Element("semi");
		finals.addContent(semi);
		addGames(semi, worldCup.getFinals().getSemiFinals());
		
		Element thirdGame = new Element("third");
		finals.addContent(thirdGame);
		Vector<Game> third = new Vector<Game>();
		third.add(worldCup.getFinals().getThirdGame());
		addGames(thirdGame, third);
		
		Element finalGame = new Element("final");
		finals.addContent(finalGame);
		Vector<Game> tmp = new Vector<Game>();
		tmp.add(worldCup.getFinals().getFinalGame());
		addGames(finalGame, tmp);
		
		Element winner = new Element("winner").setText(worldCup.getFinals().getWinner());
		finals.addContent(winner);
		
		root.addContent(finals);
		
		FileOutputStream output = new FileOutputStream(filename);
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		outputter.output(doc,output);
		output.close();
	}

	private static void addTeams(Element groupElement, Vector<Team> teams) {
		for (Team team : teams) {
			Element teamElement = new Element("team");
			teamElement.addContent(new Element("teamName").setText(team.getName()));
			teamElement.addContent(new Element("points").setText("" + team.getPoints()));
			teamElement.addContent(new Element("gf").setText("" + team.getGf()));
			teamElement.addContent(new Element("ga").setText("" + team.getGa()));
			teamElement.addContent(new Element("played").setText("" + team.getPlayed()));
			teamElement.addContent(new Element("won").setText("" + team.getWon()));
			teamElement.addContent(new Element("loss").setText("" + team.getLoss()));
			teamElement.addContent(new Element("draw").setText("" + team.getDraw()));
			groupElement.addContent(teamElement);
		}
	}

	private static void addGames(Element groupElement, Vector<Game> games) {
		for (Game game : games) {
			Element gameElement = new Element("game");
			gameElement.addContent(new Element("gameId").setText("" + game.getIntId()));
			gameElement.addContent(new Element("time").setText("" + game.getTime()));
			gameElement.addContent(new Element("date").setText("" + game.getDate()));
			gameElement.addContent(new Element("location").setText("" + game.getLocation()));
			gameElement.addContent(new Element("goalsH").setText("" + game.getGoalsH()));
			gameElement.addContent(new Element("goalsG").setText("" + game.getGoalsG()));
			gameElement.addContent(new Element("isPlayed").setText("" + game.isPlayed()));
			gameElement.addContent(new Element("teamH").setText("" + game.getTeamH().getName()));
			gameElement.addContent(new Element("teamG").setText("" + game.getTeamG().getName()));
			groupElement.addContent(gameElement);
		}
	}
}
