package uebung_5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Solution for exercise 3.
 * Implemnents a MouseListener and MouseWheelListener.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class MouseEventDemo extends JFrame implements MouseListener, MouseWheelListener{
	/**
	 * Part a): a JTextArea.
	 */
	private JTextArea textArea = new JTextArea("Willkommen!");
	/**
	 * For part c): the lower JButton.
	 */
	private JButton leftButton;
	/**
	 * For part d): the JLabel to move.
	 */
	private JLabel label = new JLabel("Hearing the mouse wheel...");

	/**
	 * Set up the window.
	 */
	public MouseEventDemo() {
		// Set general window constraints.
		setTitle("Mouse Events");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Use the GridBagLayout.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Insert the JTextArea.
		textArea.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		getContentPane().add(textArea, c);


		// Insert the first JButton for part b).
		JButton button = new JButton("Listening...") {
			public String toString() {
				return "right button";
			}
		};
		button.addMouseListener(this);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		c.gridwidth = 1;
		getContentPane().add(button, c);

		// Insert the second JButton for part c).
		leftButton = new JButton("Knopf"){
			public String toString() {
				return "lower button";
			}
		};
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		leftButton.addMouseListener(this);
		getContentPane().add(leftButton, c);

		// Add the JPanel with the JLabel.
		JPanel panel = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 3;
		panel.add(label);
		getContentPane().add(panel);
		getContentPane().addMouseWheelListener(this);

		// Ready to show!
		pack();
		setSize(450, 150);
		setVisible(true);
	}

	/**
	 * Create a MouseEventDemo.
	 * @param args String []
	 */
	public static void main(String [] args) {
		new MouseEventDemo();
	}

	/**
	 * Report that the first button has been clicked.
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().equals("right button")) 
			textArea.setText("Right Button clicked!");
	}

	/**
	 * Report that the first button has been pressed.
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().toString().equals("right button"))
			textArea.setText("Right Button pressed!");
	}

	/**
	 * Report that the first button has been released.
	 * @param e MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().toString().equals("right button"))
			textArea.setText("Right Button released!");
	}

	/**
	 * Act when the mouse enters a JButton area.
	 * @param e {@link MouseEvent}
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// if we touch the right button...
		if(e.getSource().toString().equals("right button")) {
			textArea.setText("Mouse entered the area of the right button!");
		}
		// Otherwise, print the current mouse position on the lower button.
		else {
			int x = e.getX(),
					y = e.getY();
			String pos = "Pos "+x+"; "+y;
			leftButton.setText(pos);
		}
	}

	/**
	 * Act when the mouse exits a JButton area.
	 * @param e {@link MouseEvent}
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// if we touch the right button...
		if(e.getSource().toString().equals("right button")) {
			textArea.setText("Mouse exited the area of the right button!");
		}
		// Otherwise, reset the other button.
		else {
			leftButton.setText("Knopf");
		}
	}

	/**
	 * When the mouse wheel is moved, the Y-Position of the JLabel label
	 * is changed by 5*(1, if moved down; or -1, if moved up)
	 * @param e {@link MouseWheelEvent}
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotation = 5*e.getWheelRotation();
		label.setLocation(label.getLocation().x, label.getLocation().y+rotation);
		System.out.println(rotation);
	}
}