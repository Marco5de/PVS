package swc.gui;

import java.awt.*;

import javax.swing.*;

import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.Final;
import swc.data.Game;
import swc.data.Team;

/**
 * GroupPanel shows all games after the group
 * games in multiple JTables.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class FinalsPanel extends JPanel {
	/**
	 * serialVersionUID for a javax.swing class.
	 */
	private static final long serialVersionUID = -7127416300548189567L;
	/**
	 * The Final object from which we get data.
	 */
	private Final finals;
	/**
	 * A JPanel containing information about the winner.
	 */
	private JPanel winner = new JPanel();

	/**
	 * Create a FinalsPanel.
	 * @param finals - Final
	 */
	public FinalsPanel(Final finals) {
		// Prepare this panel. Use the BoxLayout.
		this.finals = finals;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Font font = new Font(null, Font.BOLD, 14);

		// Create a JLabel and add it to this panel.		 
		JLabel labelPlaceHolder = new JLabel("Round of 16:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		/*
		 * Prepare a JTable by creating a model, then the table,
		 * then a scroll pane into which it goes. Then add it
		 * to the panel. This table is for the round of 16.
		 */
		GameModel model = new GameModel(finals.getRoundOf16());
		JTable table1 = new JTable(model);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.addMouseListener(new GameEventHandler(model, table1));
		JScrollPane scrollPane = new JScrollPane(table1);
		add(scrollPane);

		// JLabel and JTable for the quarterfinals.
		labelPlaceHolder = new JLabel("Quarterfinals:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getQuarterFinals());
		JTable table2 = new JTable(model);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.addMouseListener(new GameEventHandler(model, table2));
		scrollPane = new JScrollPane(table2);
		add(scrollPane);

		// JLabel and JTable for the semifinals.
		labelPlaceHolder = new JLabel("Semifinals:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getSemiFinals());
		JTable table3 = new JTable(model);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table3.addMouseListener(new GameEventHandler(model, table3));
		scrollPane = new JScrollPane(table3);
		add(scrollPane);

		// JLabel and JTable for the match for third place.
		labelPlaceHolder = new JLabel("Match for third place:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getThirdGame());
		JTable table4 = new JTable(model);
		table4.addMouseListener(new GameEventHandler(model, table4));
		scrollPane = new JScrollPane(table4);
		add(scrollPane);

		// JLabel and JTable for the final game.
		labelPlaceHolder = new JLabel("Final");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getFinalGame());
		JTable table5 = new JTable(model);
		table5.addMouseListener(new GameEventHandler(model, table5));
		scrollPane = new JScrollPane(table5);
		add(scrollPane);

		// Add a new panel to show the winner.
		JPanel winnerContainer = new JPanel(),
			winnerPanel = new JPanel();
		winnerPanel.setLayout(new BorderLayout());
		// Deco cups.
		ImageIcon cupIcon = CtrlGroup.getFlagIcon("cup");
		JLabel cupLabel = new JLabel(cupIcon),
				cupLabel2 = new JLabel(cupIcon);
		winnerPanel.add(cupLabel, BorderLayout.WEST);
		winnerPanel.add(cupLabel2, BorderLayout.EAST);
		// Text label for the "winner":
		JLabel text = new JLabel("Winner:");
		JPanel textPanel = new JPanel();
		textPanel.add(text);
		text.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		setWinner();
		winnerPanel.add(winner);
		winnerPanel.add(textPanel, BorderLayout.NORTH);
		winnerContainer.add(winnerPanel);
		add(winnerContainer);
	}

	/**
	 * Update the panel which shows the name and flag
	 * of the world cup winner.
	 */
	private void setWinner() {
		// Get the finals winner and his icon.
		Game finalGame = finals.getFinalGame();
		Team finalsWinner = finalGame.getTeamH();
		if(finalGame.getGoalsG() > finalGame.getGoalsH())
			finalsWinner = finalGame.getTeamG();
		String winnerName = finalsWinner.getStrName();
		ImageIcon winnerIcon = CtrlGroup.getFlagIcon(winnerName);

		// Set up the panel.
		winner.setPreferredSize(new Dimension(300, 50));
		winner.removeAll();
		Font font = new Font(Font.DIALOG, Font.BOLD, 12);
		JLabel w1 = new JLabel(winnerName);
		w1.setFont(font);
		JLabel w2 = new JLabel(winnerIcon);
		winner.add(w1);
		winner.add(w2);
	}
}