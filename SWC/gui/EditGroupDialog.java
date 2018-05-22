package swc.gui;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import swc.ctrl.CtrlGroup;

public class EditGroupDialog {	
	public static String getNewName(String name) {
		JLabel info = new JLabel("Titel");
		JTextField text = new JTextField();
		JComponent [] inputs = {info, text};
		Object [] options = {"Apply changes", "Cancel"};
		ArrayList<JComponent> list = new ArrayList<>();
		list.add(info);
		list.add(text);

		int x = -2;
		while(x == -2) {
			text.setText(name);
			x = JOptionPane.showOptionDialog(null, list.toArray(), "Edit Title", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if(x == 0) {
				URL url = CtrlGroup.class.getResource("/data/icon/" + text.getText() + ".gif");
				// Wichtig: man kann eine Bilddatei mit diesem Namen aufrufen
				// Ausserdem beginnt der Name nicht mit lowercase (fuer spezielle Dateien reserviert)
				if(text.getText().equals("")){
					x = -2;
					JOptionPane.showMessageDialog(null, "Empty name set!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if(url == null || Character.isLowerCase(text.getText().charAt(0))) {
					x = -2;
					JOptionPane.showMessageDialog(null, "Invalid name set!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
				return "no new name set";
		}
		return text.getText();
	}
}