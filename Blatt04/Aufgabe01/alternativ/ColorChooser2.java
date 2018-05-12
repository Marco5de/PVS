package uebung_4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class ColorChooser2 extends ColorChooser implements ActionListener{
	private ButtonGroup buttons = new ButtonGroup();

	public ColorChooser2() {
		super();
		setTitle("Übung 1b");
		for(String color: colors) {
			JRadioButton button = new JRadioButton(color);
			getContentPane().add(button);
			button.addActionListener(this);
			buttons.add(button);
		}

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenColor = e.getActionCommand();
		getContentPane().setBackground(map.get(chosenColor));
	}

	public static void main(String [] args) {
		new ColorChooser2();
	}
}