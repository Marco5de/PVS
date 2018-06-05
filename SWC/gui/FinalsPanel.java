package swc.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import swc.ctrl.*;
import swc.data.*;

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
	 * The SoccerWC object from which we get data.
	 */
	private SoccerWC worldCup;
	/**
	 * A JPanel containing information about the winner.
	 */
	private JPanel winner = new JPanel();

	/**
	 * Create a FinalsPanel.
	 * @param worldCup - SoccerWC
	 */
	public FinalsPanel(SoccerWC worldCup) {
		// Prepare this panel. Use the BoxLayout.
		this.worldCup = worldCup;
		Final finals = worldCup.getFinals();
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
		table1.addMouseListener(new FinalsEventHandler(model, table1, this));
		JScrollPane scrollPane = new JScrollPane(table1);
		add(scrollPane);

		// JLabel and JTable for the quarterfinals.
		labelPlaceHolder = new JLabel("Quarterfinals:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getQuarterFinals());
		JTable table2 = new JTable(model);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.addMouseListener(new FinalsEventHandler(model, table2, this));
		scrollPane = new JScrollPane(table2);
		add(scrollPane);

		// JLabel and JTable for the semifinals.
		labelPlaceHolder = new JLabel("Semifinals:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getSemiFinals());
		JTable table3 = new JTable(model);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table3.addMouseListener(new FinalsEventHandler(model, table3, this));
		scrollPane = new JScrollPane(table3);
		add(scrollPane);

		// JLabel and JTable for the match for third place.
		labelPlaceHolder = new JLabel("Match for third place:");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getThirdGame());
		JTable table4 = new JTable(model);
		table4.addMouseListener(new FinalsEventHandler(model, table4, this));
		scrollPane = new JScrollPane(table4);
		add(scrollPane);

		// JLabel and JTable for the final game.
		labelPlaceHolder = new JLabel("Final");
		labelPlaceHolder.setFont(font);
		add(labelPlaceHolder);
		model = new GameModel(finals.getFinalGame());
		JTable table5 = new JTable(model);
		table5.addMouseListener(new FinalsEventHandler(model, table5, this));
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
	void setWinner() {
		String winnerName = worldCup.getFinals().getWinner();
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

	/**
	 * Return the world cup object to display.
	 * @return worldCup
	 */
	public SoccerWC getWorldCup() {
		return worldCup;
	}
}

/**
 * FinalsEventHandler can also recalculate
 * which teams get into the final games.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
class FinalsEventHandler extends EventHandler {
	/**
	 * A FinalsPanel to operate on.
	 */
	private FinalsPanel hostPanel;

	/**
	 * Create a new FinalsEventHandler.
	 * @param model GameModel
	 * @param table JTable
	 * @param hostPanel FinalsPanel
	 */
	public FinalsEventHandler(GameModel model, JTable table, FinalsPanel hostPanel) {
		super(model, table);
		this.hostPanel = hostPanel;
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
		if(newGoalValues[0] == newGoalValues[1]) {
			JOptionPane.showMessageDialog(null, "No draw is possible in a finals match", "Input error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		game.setGoalsH(newGoalValues[0]);
		game.setGoalsG(newGoalValues[1]);
		game.setPlayed(true);
		// Calculate the finals.
		CtrlFinals.calculateFinals(hostPanel.getWorldCup());
		// Determine the winner.
		hostPanel.setWinner();
	}
}