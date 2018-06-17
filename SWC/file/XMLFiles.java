package swc.file;

import java.util.Vector;

import org.jdom2.*;
import swc.data.*;

public class XMLFiles {
	public static SoccerWC createFromXMLFile(String filepath) {
		return null;
	}

	public static void writeToXMLFile(String destination, SoccerWC worldCup) {}

	private static Game createGame(Element gameElement,
			boolean isFinalsGame,
			Vector<Team> possiblePlayers) {
		return null;
	}

	private static Team createTeam(Element teamElement) {
		return null;
	}

	private static Element gameToElement(Game game) {
		return null;
	}

	private static Element teamToElement(Team team) {
		return null;
	}
}