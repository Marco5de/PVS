import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Solution for exercise 2.
 * Extend a JFrame to show the GUI of a basic
 * calculator, using GridLayout (variant 1).
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class Calculator1 extends JFrame{
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
	public Calculator1() {
		/* Set tile and default size.
		 * A GridLayout will be use to arrange the "display" and
		 * "keyboard". Also, add the WindowEnder window listener. */
		setTitle("Calculator");
		setSize(300, 300);
		setLayout(new GridLayout(2, 1));
		addWindowListener(new WindowEnder());

		/*
		 * The "display" for caluclations is a JTextField
		 * with right horizontal alignment. Add it first
		 * to the window, so that it is added on top.
		 */
		JTextField display = new JTextField("0");
		display.setForeground(Color.BLACK);
		display.setHorizontalAlignment(JTextField.RIGHT);
		add(display);

		/*
		 * A GridLayout will place components in a row from
		 * left to right, and then in the row below etc.
		 * Create a JPanel with a 4x4 GridLayout
		 * then add JButtons with the labels set above;
		 * thus creating the "keyboard". Then, place it
		 * at the bottom of the window.
		 */
		JPanel keyboard = new JPanel(new GridLayout(4, 4));
		for(String [] line: buttons) {
			for(String button: line) {
				keyboard.add(new JButton(button));
			}
		}
		add(keyboard);

		// Show it to the user.
		setVisible(true);
	}

	/**
	 * Just create the calculator UI.
	 * @param args
	 */
	public static void main(String [] args) {
		new Calculator1();
	}
}

/**
 * A window adapter which ends the program
 * when the window is closed.
 */
final class WindowEnder extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
