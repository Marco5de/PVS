package swc.file;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.XMLOutputter;

import swc.data.*;

/**
 * XMLFiles contains two methods to save SoccerWC objects
 * into XML files, and to load them from those files.
 * 
 * This subpackage contains the file "WorldCupSchema.xsd";
 * which describes how a correct file for this program is built.
 * We use it to verify XML files.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class XMLFiles {
	/**
	 * The team elements possess these children.
	 */
	private static String [] teamFields = {"strName",
			"played", "won", "drawn", "lost", "gf", "ga", "points"
	};
	/**
	 * The game elements possess these children.
	 */
	private static String [] gameFields = {"date", "time", "location", "teamH",
			"teamG", "goalsH", "goalsG",  "isPlayed"
	};
	/**
	 * "tns" is the default namespace of our program.
	 */
	private static Namespace ns = Namespace.getNamespace("tns", "http://www.example.org/WorldCupSchema");

	/**
	 * Creates a SoccerWC object from a XMl file.
	 * @param filepath - String
	 * @return new SoccerWC
	 * @throws JDOMException - Thrown when the XML file is invalid.
	 * @throws IOException - Thrown when the file cannot be read.
	 * @throws IllegalArgumentException - Thrown by {@link XMLFiles#createGame(Element, boolean, Vector)}
	 */
	public static SoccerWC createFromXMLFile(String filepath) throws JDOMException, IOException, IllegalArgumentException {
		/*
		 * Attempt to open the schema file, create the SAXBuilder
		 * and build the document representing the world cup.
		 * Appropriate exceptions can be thrown.
		 */
		Document doc;
		try{
			URL xsdFile = XMLFiles.class.getResource("WorldCupSchema.xsd");
			XMLReaderXSDFactory factory = new XMLReaderXSDFactory(xsdFile);
			SAXBuilder builder = new SAXBuilder(factory);
			doc = builder.build(filepath);
		}
		catch(IOException ie){
			throw new IOException("An error has occurred while reading a file: "+ie.toString());
		}
		catch(JDOMException je){
			throw new JDOMException("The input XML file is invalid. Cannot load the world cup from this file!");
		}

		/*
		 * Create the world cup, first setting the name and file name.
		 * teamPool will be used by createGame.
		 */
		Element rootElement = doc.getRootElement();
		String wcName = rootElement.getChildText("groupName", ns);
		SoccerWC newWorldCup = new SoccerWC(filepath, wcName);
		Vector<Team> teamPool = new Vector<>();

		/*
		 * Create and add the group elements.
		 */
		for(Element groupElement: rootElement.getChildren("groups", ns)){
			// Set the group name.
			String groupName = groupElement.getChildText("groupName", ns);
			Group group = new Group(groupName);

			// Create the teams.
			for(Element teamElement: groupElement.getChildren("teams", ns)){
				Team newTeam = createTeam(teamElement);
				teamPool.add(newTeam);
				group.addTeam(newTeam);
			}

			// Create the games.
			Vector<Team> allTeams = group.getTeams();
			for(Element gameElement: groupElement.getChildren("games", ns)){
				Game newGame = createGame(gameElement, false, allTeams);
				group.addGame(newGame);
			}

			// Add the group to the world cup.
			newWorldCup.addGroup(group);
		}

		/*
		 * Create the Final object.
		 */
		Final newFinals = new Final();
		Element finalsElement = rootElement.getChild("finals", ns);

		/*
		 * Load the XML elements representing the final games
		 * and convert them into the appropriate game objects.
		 */
		for(Element gamesOf16Element: finalsElement.getChildren("roundOf16", ns)){
			Game gameOf16 = createGame(gamesOf16Element, true, teamPool);
			newFinals.getRoundOf16().add(gameOf16);
		}
		for(Element quarterFinalElement: finalsElement.getChildren("quarterFinals", ns)){
			Game quarterFinal = createGame(quarterFinalElement, true, teamPool);
			newFinals.getQuarterFinals().add(quarterFinal);
		}
		for(Element semiFinalElement: finalsElement.getChildren("semiFinals", ns)){
			Game semiFinal = createGame(semiFinalElement, true, teamPool);
			newFinals.getSemiFinals().add(semiFinal);
		}
		Element thirdGameElement = finalsElement.getChild("thirdGame", ns);
		newFinals.setThirdGame(createGame(thirdGameElement, true, teamPool));
		Element finalGameElement = finalsElement.getChild("finalGame", ns);
		newFinals.setFinalGame(createGame(finalGameElement, true, teamPool));

		/*
		 * Set the finals and return the world cup.
		 */
		newWorldCup.setFinals(newFinals);
		return newWorldCup;
	}

	/**
	 * Writes a SoccerWC object to a XML file.
	 * 
	 * @param destination - String
	 * @param worldCup - SoccerWC
	 * @throws IOException - Trhown when the file under destination cannot be written to.
	 */
	public static void writeToXMLFile(String destination, SoccerWC worldCup) throws IOException {
		/*
		 * Create a new XML element which
		 * is the root of our document and add our namespaces.
		 */
		XMLOutputter out = new XMLOutputter();
		Element rootElement = new Element("worldCup", ns);
		rootElement.addNamespaceDeclaration(Namespace.getNamespace("xsl", "http://www.w3.org/2001/XMLSchema-instance"));

		// Add the document name.
		Element nameElement = new Element("name", ns).setText(worldCup.getName());
		rootElement.addContent(nameElement);

		/*
		 * Add the groups:
		 * -- first the names,
		 * -- then the teams,
		 * -- then the games.
		 * Create the necessary XML elements for then and add them to
		 * the group element. 
		 */
		for(Group group: worldCup.getGroups()){
			String groupName = group.getStrGroupName();
			Element groupElement = new Element("groups", ns);
			groupElement.addContent(new Element("groupName", ns).setText(groupName));

			for(Team team: group.getTeams()){
				Element teamElement = teamToElement(team);
				groupElement.addContent(teamElement);
			}
			for(Game game: group.getGames()){
				Element gameElement = gameToElement(game, "games");
				groupElement.addContent(gameElement);
			}

			rootElement.addContent(groupElement);
		}

		/*
		 * Create the finals element and add the
		 * game elements associated with it.
		 */
		Element finalsElement = new Element("finals", ns);
		Final finals = worldCup.getFinals();
		for(Game round1: finals.getRoundOf16()){
			Element gameElement = gameToElement(round1, "roundOf16");
			finalsElement.addContent(gameElement);
		}
		for(Game round2: finals.getQuarterFinals()){
			Element gameElement = gameToElement(round2, "quarterFinals");
			finalsElement.addContent(gameElement);
		}
		for(Game round3: finals.getSemiFinals()){
			Element gameElement = gameToElement(round3, "semiFinals");
			finalsElement.addContent(gameElement);
		}
		finalsElement.addContent(XMLFiles.gameToElement(finals.getThirdGame(), "thirdGame"));
		finalsElement.addContent(XMLFiles.gameToElement(finals.getFinalGame(), "finalGame"));

		/*
		 * Try to create a XML document and write it
		 * into the file under destination. If that fails,
		 * throw an IO exception.
		 */
		rootElement.addContent(finalsElement);
		Document doc = new Document(rootElement);
		try{
			out.output(doc, new FileWriter(destination));
		}
		catch(IOException e){
			throw new IOException("An error occured while writing to the XML file: "+e.toString());
		}
	}

	/**
	 * Creates a new game from an XML element.
	 * isFinalsGame and possiblePlayers are used to determine the teams
	 * from the string in the XML file. In the finals, the teams which play may ot
	 * have been set already.
	 * 
	 * @param gameElement - Element
	 * @param isFinalsGame - boolean
	 * @param possiblePlayers - Vector<Team>
	 * @return new Game
	 * @throws IllegalArgumentException
	 */
	private static Game createGame(Element gameElement,
			boolean isFinalsGame,
			Vector<Team> possiblePlayers) throws IllegalArgumentException{
		/*
		 * Load and parse the attributes and elements which make up
		 * a game object. 
		 */
		int gameId = 0; Integer.parseInt(gameElement.getAttributeValue("id"));
		String date = gameElement.getChildText("date", ns);
		String time = gameElement.getChildText("time", ns);
		String location = gameElement.getChildText("location", ns);
		String teamH = gameElement.getChildText("teamH", ns);
		String teamG = gameElement.getChildText("teamG", ns);
		int goalsH = Integer.parseInt(gameElement.getChildText("goalsH", ns));
		int goalsG = Integer.parseInt(gameElement.getChildText("goalsG", ns));
		boolean isPlayed = Boolean.parseBoolean(gameElement.getChildText("isPlayed", ns));

		/*
		 * Try to find the teams which are supposed
		 * to be playing in the teams vector. 
		 */
		Team team1 = null,
				team2 = null;
		for(int i = 0; i < possiblePlayers.size(); i++) {
			if(possiblePlayers.get(i).getStrName().equals(teamH)) 
				team1 = possiblePlayers.get(i);
			if(possiblePlayers.get(i).getStrName().equals(teamG))
				team2 = possiblePlayers.get(i);
		}

		/*
		 * If we couldn't find them...
		 * -- In the ko phase, assume that the team names
		 * are place holders because the game has not been played yet.
		 * -- In the group phase, that's an error, and we throw an exception.
		 */
		if(team1 == null) {
			if(isFinalsGame)
				team1 = new Team(teamH, 0, 0, 0, 0, 0, 0, 0);
			else
				throw new IllegalArgumentException("Team could not be found in the group!");
		}
		if(team2 == null) {
			if(isFinalsGame)
				team2 = new Team(teamG, 0, 0, 0, 0, 0, 0, 0);
			else
				throw new IllegalArgumentException("Team could not be found in the group!");
		}

		// Return the new game object.
		return new Game(gameId, date, time, location, team1, team2, goalsH, goalsG, isPlayed);
	}

	/**
	 * Creates a team object from an XML element.
	 * We just need to parse the subelements.
	 * 
	 * @param teamElement - Element
	 * @return new Team
	 */
	private static Team createTeam(Element teamElement) {
		String teamName = teamElement.getChildText("strName", ns);
		int played = Integer.parseInt(teamElement.getChildText("played", ns));
		int won = Integer.parseInt(teamElement.getChildText("won", ns));
		int lost = Integer.parseInt(teamElement.getChildText("lost", ns));
		int draw = Integer.parseInt(teamElement.getChildText("drawn", ns));
		int gf = Integer.parseInt(teamElement.getChildText("gf", ns));
		int ga = Integer.parseInt(teamElement.getChildText("ga", ns));
		int points = Integer.parseInt(teamElement.getChildText("points", ns));
		return new Team(teamName, points, gf, ga, played, won, lost, draw);
	}

	/**
	 * Creates a XML element from a Game object.
	 * This method works by:
	 * -- splitting the string representation (just as for CSV files)
	 * -- add the subelements with the element names from teamFields,
	 * -- and add the id attribute.
	 * 
	 * @param game - Game
	 * @param rootName - String
	 * Depending on its position in the file, the game element can have different names.
	 * @return new Element
	 */
	private static Element gameToElement(Game game, String rootName) {
		Element gameRoot = new Element(rootName, ns);
		String [] attributes = game.toString().split(",");
		for(int i = 1; i < attributes.length; i++){
			String element = attributes[i];
			String elementName = gameFields[i-1];
			gameRoot.addContent(new Element(elementName, ns).setText(element));
		}
		gameRoot.setAttribute(new Attribute("id", String.valueOf(game.getIntId())));
		return gameRoot;
	}

	/**
	 * Creates a XML element from a Team object.
	 * This method works similarly to {@link XMLFiles#gameToElement(Game, String)}.
	 *  
	 * @param team Team
	 * @return new Element
	 */
	private static Element teamToElement(Team team) {
		Element teamRoot = new Element("teams", ns);
		String [] attributes = team.toString().split(",");
		for(int i = 0; i < attributes.length; i++){
			String element = attributes[i];
			String elementName = teamFields[i];
			teamRoot.addContent(new Element(elementName, ns).setText(element));
		}
		return teamRoot;
	}
}