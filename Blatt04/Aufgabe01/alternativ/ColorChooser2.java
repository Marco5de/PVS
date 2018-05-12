package uebung_4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Solution for exercise 1b). Extends the basic ColorChooser.
 * A ButtonGroup containing RadioButtons is used to choose the background color.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class ColorChooser2 extends ColorChooser implements ActionListener{
	/**
	 * The button group.
	 */
	private ButtonGroup buttons = new ButtonGroup();

	/**
	 * Create the color chooser.
	 */
	public ColorChooser2() {
		super();
		// Set the window title.
		setTitle("Übung 1b");

		/* For each color:
		 * -- Create a JRadioButton.
		 * -- Add it on the window.
		 * -- Register the window as listener.
		 * -- Add it to the button group.
		 */
		for(String color: colors) {
			JRadioButton button = new JRadioButton(color);
			getContentPane().add(button);
			button.addActionListener(this);
			buttons.add(button);
		}

		setVisible(true);
	}

	/**
	 * When a RadioButton is selected, take its name,
	 * look up the color, and set the background of the content
	 * pane to it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenColor = e.getActionCommand();
		getContentPane().setBackground(map.get(chosenColor));
	}

	/**
	 * Create and show a color chooser with JRadioButtons.
	 * @param args
	 */
	public static void main(String [] args) {
		new ColorChooser2();
	}
}