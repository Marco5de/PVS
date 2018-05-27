package swc.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		table2.addMouseListener(new GameEventHandler(games, table2));
		JScrollPane scrollPane2 = new JScrollPane(table2);
		add(scrollPane2);
	}
}