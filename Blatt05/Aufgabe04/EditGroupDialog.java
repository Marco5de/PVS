package swc.gui;

import java.util.ArrayList;

import javax.swing.*;

public class EditGroupDialog {	
	public static String getNewName(String name) {
		JLabel info = new JLabel("Titel");
		JTextField text = new JTextField(name);
		JComponent [] inputs = {info, text};
		Object [] options = {"Apply changes", "Cancel"};
		ArrayList<JComponent> list = new ArrayList<>();
		list.add(info);
		list.add(text);

		int x = -2;
		while(x == -2) {
			x = JOptionPane.showOptionDialog(null, list.toArray(), "Edit Title", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if(x == 0) {
				ImageIcon c = swc.ctrl.CtrlGroup.getFlagIcon(text.getText());
			}
			else
				return "no title set";
		}
		return text.getText();
	}
}