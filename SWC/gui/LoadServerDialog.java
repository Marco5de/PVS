package swc.gui;

import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.*;

public class LoadServerDialog {
	public static String getOption(Frame owner){
		JTextField filePath = new JTextField();
		JLabel info = new JLabel("URL for file:");
		ArrayList<JComponent> list = new ArrayList<>();
		list.add(info);
		list.add(filePath);
		Object [] options = {"Load file", "Cancel"};

		int response = 0;
		while(true){
			filePath.setText("");
			response = JOptionPane.showOptionDialog(owner, 
					list.toArray(), 
					"Load File from Server", 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.PLAIN_MESSAGE, 
					null,
					options, 
					options[0]);

			if(response != 0)
				return null;

			if(filePath.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Do not enter an empty path!", "Error on loading a new file", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			break;
		}
		
		return filePath.getText();
	}
}