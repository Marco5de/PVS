package swc.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.Game;
import swc.data.Group;

/**
 * GroupPanel shows the teams of a group and 
 * the games they've played in two JTables.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class GroupPanel extends JPanel{
	/**
	 * serialVersionUID for a javax.swing class.
	 */
	private static final long serialVersionUID = -4158074916617279581L;
	/**
	 * The group form which we get data.
	 */
	private Group group;

	/**
	 * Create a new GroupPanel.
	 * @param group - Group
	 */
	public GroupPanel(Group group) {
		// Use the BoxLayout.
		this.group = group;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		CtrlGroup.calculateGroupTable(group);

		// Show a JLabel for the teams.
		Font f = new Font(null, Font.BOLD, 14);
		JLabel labelTeams = new JLabel("Table for "+group.getStrGroupName()+" - Top two teams will advance.");
		labelTeams.setFont(f);
		add(labelTeams);
		// Show the JTable.
		TeamModel teams = new TeamModel(group.getTeams());
		JTable table1 = new JTable(teams);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		add(scrollPane1);

		// Show a JLabel for the games.
		JLabel labelGames = new JLabel("Matches for "+group.getStrGroupName());
		labelGames.setFont(f);
		add(labelGames);
		// Show the JTable.
		GameModel games = new GameModel(group.getGames());
		JTable table2 = new JTable(games);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.addMouseListener(new GameEventHandler(games, table2, group));
		JScrollPane scrollPane2 = new JScrollPane(table2);
		add(scrollPane2);
	}
}


/**
 * EventHandler is used to handle the
 * clicking of tables using the GameModel.
 * With it, we can set the goals shot in a game.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
abstract class EventHandler extends MouseAdapter{
	/**
	 * The games to observe.
	 */
	protected GameModel model;
	/**
	 * The table the user clicks on.
	 */
	protected JTable table;

	/**
	 * Create the handler.
	 * @param model - GameModel
	 */
	public EventHandler(GameModel model, JTable table) {
		this.model = model;
		this.table = table;
	}
}

/**
 * GameEventHandler is used by GroupPanel and resorts
 * the group results.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
final class GameEventHandler extends EventHandler{
	/**
	 * A group variable used for resorting.
	 */
	private Group group;
	public GameEventHandler(GameModel model, JTable table, Group group) {
		super(model, table);
		this.group = group;
	}	

	/**
	 * When double clicked, open a EditGameDialog to
	 * type in the goals shot in a game.
	 * @param e MouseEvent
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() < 2)
			return;

		int selectedRow = table.getSelectedRow();
		Game game = model.getGames().get(selectedRow);

		// Only change the values in the Game class.
		int [] newGoalValues = EditGameDialog.getNewGoalCount(game);
		if(newGoalValues == null)
			return;

		game.setGoalsH(newGoalValues[0]);
		game.setGoalsG(newGoalValues[1]);
		game.setPlayed(true);
		// Resort the games.
		CtrlGroup.calculateGroupTable(group);
	}
}