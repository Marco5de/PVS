package uebung_4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class ColorChooser1 extends ColorChooser{
	private JComboBox<String> chooser = new JComboBox<>(colors);

	public ColorChooser1() {
		super();
		setTitle("Übung 1a");
		getContentPane().add(chooser, FlowLayout.LEFT);
		chooser.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenColor = (String) chooser.getSelectedItem();
		getContentPane().setBackground(map.get(chosenColor));
	}

	public static void main(String [] args) {
		new ColorChooser1();
	}
}