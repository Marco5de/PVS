package uebung3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Solution for exercise 2.
 * Extend a JFrame to show the GUI of a basic
 * calculator, using GridLayout and FlowLayout (variant 2).
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class Calculator2 extends JFrame{
	/**
	 * A 2D-array of strings; the labels
	 * of the calculator buttons.
	 */
	private static String [][] buttons = {
			{"+", "1", "2", "3"},
			{"-", "4", "5", "6"},
			{"*", "7", "8", "9"},
			{":", "0", "=", "C"}
	};

	/**
	 * Set up the calc UI.
	 */
	public Calculator2() {
		/* Set tile and default size.
		 * A GridLayout will be use to arrange the "display" and
		 * "keyboard". Also, add the WindowEnder window listener. */
		setTitle("Calculator");
		setSize(300, 300);
		setLayout(new GridLayout(5, 1));
		addWindowListener(new WindowEnder());

		/*
		 * The "display" for caluclations is a JTextField
		 * with right horizontal alignment. Add it first
		 * to the window, so that it is added on top.
		 */
		JTextField display = new JTextField("0");
		display.setForeground(Color.BLACK);
		display.setHorizontalAlignment(JTextField.RIGHT);
		getContentPane().add(display);

		/*
		 * A FlowLayout will place components in a row from
		 * left to right, and then in the row below etc.
		 * Create 4 JPanels using a FlowLayout
		 * to arrange buttons on the separate rows of the "keyboard",
		 * then add those rows to the screen.
		 */
		for(String [] line: buttons) {
			JPanel row = new JPanel(new FlowLayout());
			for(String button: line) {
				JButton key = new JButton(button);
				key.setSize(new Dimension(60, 50));
				row.add(key);
			}
			getContentPane().add(row);
		}

		// Show it to the user.
		setVisible(true);
	}

	/**
	 * Just create the calculator UI.
	 * @param args
	 */
	public static void main(String [] args) {
		new Calculator2();
	}
}
