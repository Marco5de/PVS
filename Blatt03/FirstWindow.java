package uebung_3;

import java.awt.event.*;
import javax.swing.*;

/**
 * Solution for exercise 1.
 * Extends a JFrame to display a stanza of a poem.
 * Uses a WindowHandler when the JFrame is
 * opened or closed.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class FirstWindow extends JFrame{
	/**
	 * The poem (excerpt from Goethe's "Erlkönig").
	 * Formatted using HTML.
	 */
	private static String goethe = "<html><p>Wer reitet so spät durch Nacht und Wind?</p>"
									+"<p>Es ist der Vater mit seinem Kind.</p>"
									+"<p>Er hat den Knaben wohl in dem Arm.</p>"
									+"<p>Er faßt ihn sicher, er hält ihn warm.</p></html>";

	/**
	 * Shows the window with the poem by:
	 * -- Setting the title.
	 * -- Setting the default size.
	 * -- Add a JLabel with the poem on it.
	 * -- Add the window listener WindowHandler.
	 * -- Show on screen.
	 */
	public FirstWindow() {
		setTitle("Erlkönig");
		setSize(400, 300);
		JLabel gedicht = new JLabel(goethe);
		getContentPane().add(gedicht);
		addWindowListener(new WindowHandler());
		setVisible(true);
	}

	/**
	 * The main program creates a new window.
	 * @param args
	 */
	public static void main(String [] args) {
		new FirstWindow();
	}
}

/**
 * Implementation of the WindowListener used by FirstWindow.
 */
final class WindowHandler extends WindowAdapter{
	/**
	 * When the window is opened, print a welcoming message on the console.
	 * @see java.awt.event.WindowListener#windowOpened(WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("Sie lesen nun einen Auszug aus dem Erlkönig von Goethe.");
	}

	/**
	 * When the window is being closed,
	 * print a goodbye message on the console, then end the program.
	 * @see java.awt.event.WindowListener#windowClosing(WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Weitere Gedichte finden Sie im Internet. Auf Wiedersehen!");
		System.exit(0);
	}
}