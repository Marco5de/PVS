package uebung_4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

/**
 * Solution for exercise 1a). Extends the basic ColorChooser.
 * A JComboBox is used to choose the background color.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class ColorChooser1 extends ColorChooser{
	/**
	 * The JComboBox takes the color strings as options.
	 */
	private JComboBox<String> chooser = new JComboBox<>(colors);

	/**
	 * Create the color chooser.
	 */
	public ColorChooser1() {
		super();
		
		// Set the window title.
		setTitle("Übung 1a");
		// Add the ComboBox and listen to it.
		getContentPane().add(chooser, FlowLayout.LEFT);
		chooser.addActionListener(this);
		// Show the window.
		setVisible(true);
	}

	/**
	 * When a color is selected in the box, take its name,
	 * look up the Color object in map, and set it as
	 * background color of the content pane.
	 * @param e ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenColor = (String) chooser.getSelectedItem();
		getContentPane().setBackground(map.get(chosenColor));
	}

	/**
	 * Create and show the color chooser with the JComboBox.
	 * @param args
	 */
	public static void main(String [] args) {
		new ColorChooser1();
	}
}