package swc.file;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.util.List;
import java.util.Vector;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

import swc.data.*;

public class XMLFiles {
	public static SoccerWC readFromXML(String filename, SoccerWC worldCup) throws JDOMException, IOException {
		SAXBuilder sxbuild = new SAXBuilder();
		InputSource is = new InputSource(filename);
		Document doc = sxbuild.build(is);
		worldCup.setFilename(filename);
		processFile(doc, worldCup);
		return worldCup;
	}

	public static SoccerWC readFromRemoteFile(String path, SoccerWC worldCup) throws IOException, JDOMException{
		URL url = new URL(path);
		InputStream remoteStream = url.openStream();
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(remoteStream);
		processFile(doc, worldCup);
		worldCup.setFilename(path);
		return worldCup;
	}

	private static void processFile(Document doc, SoccerWC worldCup) {
		Element root = doc.getRootElement();

		// clear world cup
		Vector<Group> groupObjects = worldCup.getGroups();
		groupObjects.clear();

		worldCup.setName(root.getChild("meta").getChildText("name"));
		Element groups = root.getChild("groups");

		@SuppressWarnings("rawtypes")
		List allGroups = groups.getChildren("group");
		for (Object obj : allGroups) {
			if (obj instanceof Element) {
				Group newGroup = new Group(((Element) obj).getChildText("groupName"));
				createTeams(newGroup, (Element) obj, worldCup);
				createGames(newGroup, (Element) obj, worldCup);
				worldCup.addGroup(newGroup);
			}
		}

		Element finals = root.getChild("finals");
		Element round16 = finals.getChild("round16");
		Vector<Game> r16 = worldCup.getFinals().getRoundOf16();
		r16.clear();
		createFinalGames(r16, round16, worldCup);

		Element quarter = finals.getChild("quarter");
		Vector<Game> q = worldCup.getFinals().getQuarterFinals();
		q.clear();
		createFinalGames(q, quarter, worldCup);

		Element semi = finals.getChild("semi");
		Vector<Game> s = worldCup.getFinals().getSemiFinals();
		s.clear();
		createFinalGames(s, semi, worldCup);

		Element thirdGame = finals.getChild("third");
		Vector<Game> t = new Vector<Game>();
		t.clear();
		createFinalGames(t, thirdGame, worldCup);
		worldCup.getFinals().setThirdGame(t.get(0));

		Element finalGame = finals.getChild("final");
		Vector<Game> f = new Vector<Game>();
		f.clear();
		createFinalGames(f, finalGame, worldCup);
		worldCup.getFinals().setFinalGame(f.get(0));

		worldCup.getFinals().setWinner(finals.getChildText("winner"));
	}

	private static void createTeams(Group newGroup, Element group, SoccerWC worldCup) {
		for (Object xmlTeam : group.getChildren("team")) {
			if (xmlTeam instanceof Element) {
				Team newTeam = new Team();
				newTeam.setStrName(((Element) xmlTeam).getChildText("teamName"));
				newTeam.setPoints(Integer.parseInt((((Element) xmlTeam).getChildText("points"))));
				newTeam.setGf(Integer.parseInt((((Element) xmlTeam).getChildText("gf"))));
				newTeam.setGa(Integer.parseInt((((Element) xmlTeam).getChildText("ga"))));
				newTeam.setPlayed(Integer.parseInt((((Element) xmlTeam).getChildText("played"))));
				newTeam.setWon(Integer.parseInt((((Element) xmlTeam).getChildText("won"))));
				newTeam.setLoss(Integer.parseInt((((Element) xmlTeam).getChildText("loss"))));
				newTeam.setDraw(Integer.parseInt((((Element) xmlTeam).getChildText("draw"))));
				newGroup.addTeam(newTeam);
			}
		}
	}

	private static void createGames(Group newGroup, Element group, SoccerWC worldCup) {
		for (Object xmlGame : group.getChildren("game")) {
			if (xmlGame instanceof Element) {
				Game newGame = new Game();
				newGame.setIntId(Integer.parseInt((((Element) xmlGame).getChildText("gameId"))));
				newGame.setTime(((Element) xmlGame).getChildText("time"));
				newGame.setDate(((Element) xmlGame).getChildText("date"));
				newGame.setLocation(((Element) xmlGame).getChildText("location"));
				newGame.setGoalsH(Integer.parseInt((((Element) xmlGame).getChildText("goalsH"))));
				newGame.setGoalsG(Integer.parseInt((((Element) xmlGame).getChildText("goalsG"))));
				if ((((Element) xmlGame).getChildText("isPlayed")).equals("true"))
					newGame.setPlayed(true);
				else
					newGame.setPlayed(false);
				for (Team team : newGroup.getTeams()) {
					if (team.getStrName().equals((((Element) xmlGame).getChildText("teamH"))))
						newGame.setTeamH(team);
					if (team.getStrName().equals((((Element) xmlGame).getChildText("teamG"))))
						newGame.setTeamG(team);
				}
				newGroup.addGame(newGame);
			}
		}
	}

	private static void createFinalGames(Vector<Game> newGames, Element parent, SoccerWC worldCup) {
		for (Object xmlGame : parent.getChildren("game")) {
			if (xmlGame instanceof Element) {
				Game newGame = new Game();
				newGame.setIntId(Integer.parseInt((((Element) xmlGame).getChildText("gameId"))));
				newGame.setTime(((Element) xmlGame).getChildText("time"));
				newGame.setDate(((Element) xmlGame).getChildText("date"));
				newGame.setLocation(((Element) xmlGame).getChildText("location"));
				newGame.setGoalsH(Integer.parseInt((((Element) xmlGame).getChildText("goalsH"))));
				newGame.setGoalsG(Integer.parseInt((((Element) xmlGame).getChildText("goalsG"))));
				if ((((Element) xmlGame).getChildText("isPlayed")).equals("true"))
					newGame.setPlayed(true);
				else
					newGame.setPlayed(false);

				Team home = new Team();
				home.setStrName((((Element) xmlGame).getChildText("teamH")));
				newGame.setTeamH(home);

				Team guest = new Team();
				guest.setStrName((((Element) xmlGame).getChildText("teamG")));
				newGame.setTeamG(guest);

				for (Group g : worldCup.getGroups()) {
					for (Team team : g.getTeams()) {
						if (team.getStrName().equals((((Element) xmlGame).getChildText("teamH"))))
							newGame.setTeamH(team);
						if (team.getStrName().equals((((Element) xmlGame).getChildText("teamG"))))
							newGame.setTeamG(team);
					}
				}
				newGames.add(newGame);
			}
		}
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
		outputter.output(doc,output);
		output.close();
	}

	private static void addTeams(Element groupElement, Vector<Team> teams) {
		for (Team team : teams) {
			Element teamElement = new Element("team");
			teamElement.addContent(new Element("teamName").setText(team.getStrName()));
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
			gameElement.addContent(new Element("teamH").setText("" + game.getTeamH().getStrName()));
			gameElement.addContent(new Element("teamG").setText("" + game.getTeamG().getStrName()));
			groupElement.addContent(gameElement);
		}
	}
}