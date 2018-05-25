package swc.gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import swc.ctrl.CtrlGroup;
import swc.data.*;

/**
 * EditGameDialog contains a single static method
 * which opens a JDialog to set how many goals have
 * been shot in a football game.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class EditGameDialog {
	/**
	 * Set the number of goals that have been shot in a game.
	 * 
	 * @param g - Game
	 * <ul> Game data is required to show the dialog properly.
	 * @return newGoals - int[2]
	 * <ul> An array of two integers.
	 * <p>
	 * The method can also return null.
	 * This tells us that the user has closed the dialog.
	 */
	public static int [] getNewGoalCount(Game g) {
		/*
		 * The names of the home and guest teams are
		 * required, as well as how many goals they
		 * have shot so far.
		 */
		String home = g.getTeamH().getStrName(),
				guest = g.getTeamG().getStrName();
		int goalsHome = g.getGoalsH(),
				goalsGuest = g.getGoalsG();
		int [] newGoals = {goalsHome, goalsGuest};

		/*
		 * Set up two JPanels which will hold
		 * the dialog content.
		 */
		JPanel p1 = new JPanel();
		FlowLayout f = (FlowLayout) p1.getLayout();
		f.setAlignment(FlowLayout.LEFT);
		JPanel p2 = new JPanel();
		f = (FlowLayout) p2.getLayout();
		f.setAlignment(FlowLayout.LEFT);

		/*
		 * Set up two JTextAreas in which
		 * the number of goals shot can be
		 * typed in.
		 */
		JTextArea text1 = new JTextArea();
		text1.setPreferredSize(new Dimension(50, 20));
		text1.setBorder(new EtchedBorder());
		JTextArea text2 = new JTextArea();
		text2.setPreferredSize(new Dimension(50, 20));
		text2.setBorder(new EtchedBorder());

		/*
		 * Set up the JPanels that they show
		 * the icon and name of one team,
		 * and a JTextArea.
		 * Do this for the user and guest team.
		 */
		JLabel l1 = new JLabel(home);
		l1.setPreferredSize(new Dimension(200, 20));
		p1.add(new JLabel(CtrlGroup.getFlagIcon(home)));
		p1.add(l1);
		p1.add(text1);
		p1.add(new JLabel("Goal(s)"));
		JLabel l2 = new JLabel(guest);
		l2.setPreferredSize(new Dimension(200, 20));
		p2.add(new JLabel(CtrlGroup.getFlagIcon(guest)));
		p2.add(l2);
		p2.add(text2);
		p2.add(new JLabel("Goal(s)"));

		/*
		 * Hold the JPanels in a array.
		 * Create a array for options which
		 * the user can choose from.
		 */
		Object [] options = {"Apply changes", "Cancel"};
		ArrayList<JComponent> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);

		while(true) {
			/*
			 * As long as necessary, fill in the old goal
			 * numbers, then show the dialog.
			 */
			text1.setText(Integer.toString(goalsHome));
			text2.setText(Integer.toString(goalsGuest));
			int response = JOptionPane.showOptionDialog(null, list.toArray(), "Edit Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			// If the user closes the dialog, don't return anything.
			if(response != 0)
				return null;

			/*
			 * Attempt to convert the inputs into integers,
			 * then verify that they are positive numbers.
			 * If this fails, report those errors, then
			 * show the dialog again.
			 */
			try {
				newGoals[0] = Integer.parseInt(text1.getText());
				newGoals[1] = Integer.parseInt(text2.getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Enter valid numbers!", "Error on setting goals", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			if(newGoals[0] < 0 || newGoals[1] < 0) {
				JOptionPane.showMessageDialog(null, "Enter positive numbers!", "Error on setting goals", JOptionPane.ERROR_MESSAGE);
				continue;
			}

			// Return the new numbers.
			break;
		}
		return newGoals;
	}
}