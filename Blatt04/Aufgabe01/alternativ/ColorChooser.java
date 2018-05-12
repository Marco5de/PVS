package uebung_4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Solution for exercise 1.
 * This abstract JFrame class is used by the actual implementations,
 * who add a JComboBox/ButtonGroup, and an implementation of
 * {@link ActionListener#actionPerformed(ActionEvent)}.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public abstract class ColorChooser extends JFrame implements ActionListener{
	protected static final long serialVersionUID = 1L;

	/**
	 * The TreeMap assigns colors to (input) strings.
	 */
	protected TreeMap<String, Color> map = new TreeMap<>();
	/**
	 * The names of the input strings.
	 */
	protected String [] colors = {"red", "green", "blue"};

	/**
	 * Set up the basic window.
	 */
	public ColorChooser() {
		// Fill the tree map.
		map.put("red", Color.RED);
		map.put("green", Color.GREEN);
		map.put("blue", Color.BLUE);

		/* Set size and layout of the window.
		 * Instead of a WindowAdapter, setDefaultCloseOperation
		 * is used to end the program when the window ist closed. */
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
	}
}