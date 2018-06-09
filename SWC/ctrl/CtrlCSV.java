package swc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import swc.data.Final;
import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

/**
 * CtrlCSV handles the import and export of world cup
 * states from and to CSV files.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class CtrlCSV {
	/**
	 * Writes a world cup to a CSV file.
	 * 
	 * @param worldCup - SoccerWC
	 * @param filename - String
	 * @throws FileNotFoundException
	 * Occurs if the file under filename can't be opened.
	 * @throws IOException
	 * Occurs if the file can't be written properly.
	 */
	private static void writeToCSV(SoccerWC worldCup, String filename) throws FileNotFoundException, IOException {
		/*
		 * Strings which are written multiple times into the file:
		 * the world cup name, and the data fields of games and teams.
		 */
		String teamHeader = "Position, Team, Played, Won, Draw, Loss, GF, GA, Points";
		String gameHeader = "Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed";
		String worldCupName = worldCup.getName();

		// Create the writer instance.
		PrintWriter outputWriter = null;
		outputWriter = new PrintWriter(new FileOutputStream(filename));

		/*
		 * Write the group information into the file:
		 * First the teams, then the games.
		 * Use toString() as line information.
		 */
		for(Group group: worldCup.getGroups()) {
			outputWriter.println(worldCupName+":\n"+group.getStrGroupName()+":");

			outputWriter.println("Teams:\n"+teamHeader);
			int counter = 0;
			for(Team team: group.getTeams()) {
				outputWriter.println(counter+","+team.toString());
				counter++;
			}

			outputWriter.println("Games:\n"+gameHeader);
			for(Game game: group.getGames())
				outputWriter.println(game.toString());
		}

		/*
		 * Write information about the finals:
		 * Round of 16, quarter finals, semifinals,
		 * match for third place, and final.
		 */
		Final finals = worldCup.getFinals();
		outputWriter.println("Finals:\nRound of 16\n"+gameHeader);
		for(Game roundGame: finals.getRoundOf16())
			outputWriter.println(roundGame.toString());

		outputWriter.println("Quarterfinals:\n"+gameHeader);
		for(Game quarterfinal: finals.getQuarterFinals())
			outputWriter.println(quarterfinal.toString());

		outputWriter.println("Semifinals:\n"+gameHeader);
		for(Game semifinal: finals.getSemiFinals())
			outputWriter.println(semifinal.toString());

		outputWriter.println("Match for third place:\n"+gameHeader);
		outputWriter.println(finals.getThirdGame().toString());
		outputWriter.println("Final:\n"+gameHeader);
		outputWriter.println(finals.getFinalGame().toString());

		// Flush the writer (force a write) and close it then.
		outputWriter.flush();
		outputWriter.close();
	}

	/**
	 * Creates a new world cup from a CSV file.
	 * 
	 * @param filename - String
	 * @return SoccerWC
	 *
	 * @throws FileNotFoundException
	 * Thrown when the source file cannot be opened.
	 * @throws IOException
	 * Thrown when the file can't be closed.
	 * @throws NullPointerException
	 * Thrown when the file is too small.
	 * @throws NumberFormatException
	 * Thrown when a (integer) number can't be read.
	 * @throws IllegalArgumentException
	 * Thrown when a team cannot be assigned to a game.
	 */
	public static SoccerWC createFromFile(String filename) throws FileNotFoundException, IOException,
															NumberFormatException, IllegalArgumentException, NullPointerException {
		// Create the new world cup.
		SoccerWC worldCup = new SoccerWC();
		Vector<Team> allTeams = new Vector<>();

		// Set up the file reader.
		worldCup.setFilename(filename);
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		/*
		 * Create the teams:
		 * -- Read in 16 lines.
		 * -- Determine the group name.
		 * -- Create the teams.
		 * -- Create the games, using the previously created teams.
		 */
		for(int i = 0; i < 8; i++) {
			String [] lines = new String[16];
			worldCup.setName(lines[0]);
			for(int j = 0; j < 16; j++) {
				lines[j] = reader.readLine();
				if(lines[j] == null)
					throw new NullPointerException("Reached end of file before world cup could be created!");
			}

			Group group = new Group(lines[1].replaceAll(":", ""));
			for(int t = 0; t < 4; t++) {
				Team team = createTeam(lines[t+4]);
				group.getTeams().addElement(team);
				allTeams.add(team);
			}
			for(int g = 0; g < 6; g++) {
				group.getGames().addElement(createGame(lines[g+10],
						group.getTeams(),
						false));
			}
		}
		/*
		 * Read in the last 27 lines which
		 * contain information about the final games.
		 */
		String [] finalLines = new String[27];
		for(int i = 0; i < 27; i++) {
			finalLines[i] = reader.readLine();
			if(finalLines[i] == null)
				throw new NullPointerException("Reached end of file before world cup could be created!");
		}

		/*
		 * Create the games for the round of 16.
		 * One already knows by then which teams take
		 * part in the tournament. 
		 */
		Vector<Game> roundOfSixteen = worldCup.getFinals().getRoundOf16();
		for(int i = 0; i < 8; i++)
			roundOfSixteen.addElement(createGame(finalLines[i+3],
					allTeams,
					true));

		// The same for the quarter finals...
		Vector<Game> quarterFinals = worldCup.getFinals().getQuarterFinals();
		for(int q = 0; q < 4; q++)
			quarterFinals.addElement(createGame(finalLines[q+13],
					allTeams,
					true));

		// ... and the semifinals...
		Vector<Game> semiFinals = worldCup.getFinals().getSemiFinals();
		for(int s = 0; s < 2; s++)
			semiFinals.addElement(createGame(finalLines[s+19],
					allTeams,
					true));

		// ... and the two final games.
		worldCup.getFinals().setThirdGame(createGame(finalLines[23], allTeams, true));
		worldCup.getFinals().setFinalGame(createGame(finalLines[26], allTeams, true));

		// Close the reader and return the world cup.
		reader.close();
		return worldCup;
	}

	/**
	 * Creates a new team.
	 * @param line - String
	 * @return {@link Team}
	 * @throws NumberFormatException
	 */
	private static Team createTeam(String line) throws NumberFormatException{
		/*
		 * Split the text line. Get the team name,
		 * and the numerical values.
		 */
		String [] info = line.split(",");
		String teamName = info[1];
		int [] teamNumbers = new int[7];
		for(int i = 0; i < 7; i++) {
			teamNumbers[i] = Integer.parseInt(info[i+2]);
		}

		// Return the new team.
		return new Team(teamName,
				teamNumbers[0],	// played games
				teamNumbers[1], // won games
				teamNumbers[2], // drawn games
				teamNumbers[3], // lost games
				teamNumbers[4], // goals for
				teamNumbers[5], // goals against
				teamNumbers[6]  // points in the group phase
		);
	}

	/**
	 * Creates a new game.
	 * @param line - String
	 * @param teams - Vector<Game>
	 * @param koPhase boolean
	 * @return new Game
	 * @throws IllegalArgumentException
	 * Thrown when a team can not be assigned.
	 * @throws NumberFormatException
	 * Thrown when a integer number cannot be parsed.
	 */
	private static Game createGame(String line, Vector<Team> teams, boolean koPhase) throws IllegalArgumentException, NumberFormatException{
		// Split the line and get the game id.
		String [] info = line.split(",");
		int gameId = Integer.parseInt(info[0]);

		/*
		 * Try to find the teams which are supposed
		 * to be playing in the teams vector. 
		 */
		Team team1 = null,
				team2 = null;
		for(int i = 0; i < 4; i++) {
			if(teams.get(i).getStrName().equals(info[4])) 
				team1 = teams.get(i);
			if(teams.get(i).getStrName().equals(info[5]))
				team2 = teams.get(i);
		}
		/*
		 * If we couldn't find them...
		 * -- In the ko phase, assume that the team names
		 * are place holders because the game has not been played yet.
		 * -- In the group phase, that's an error, and we throw an exception.
		 */
		if(team1 == null) {
			if(koPhase)
				team1 = new Team(info[4], 0, 0, 0, 0, 0, 0, 0);
			else
				throw new IllegalArgumentException("Team could not be found in the group!");
		}
		if(team2 == null) {
			if(koPhase)
				team2 = new Team(info[5], 0, 0, 0, 0, 0, 0, 0);
			else
				throw new IllegalArgumentException("Team could not be found in the group!");
		}

		// Create a new game saving...
		Game game = new Game(gameId, 
				info[1], // the date
				info[2], // the time
				info[3], // the location
				team1, // the home team
				team2, // the guest team
				Integer.parseInt(info[6]), // the goals shot by the host
				Integer.parseInt(info[7]), // the goals shot by the guest
				Boolean.parseBoolean(info[8]) // whether the game has already been played
		);
		// A game must not have negative goals.
		if(game.getGoalsG() < 0 || game.getGoalsH() < 0)
			throw new IllegalArgumentException("Negative goals were found in a game!");
		return game;
	}
}