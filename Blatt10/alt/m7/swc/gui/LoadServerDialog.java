package swc.gui;

import java.awt.*;
import javax.swing.*;

public class LoadServerDialog {
	public static String openDialog(Frame window) {
		JLabel lbl = new JLabel("Url for file:");
		JTextField tf = new JTextField();
		//Needed to create JOptionPane
		Object[] objArr = {"Load File","Cancel"};
		JComponent[] jcompArr = {lbl,tf};
		
		boolean check = true;
		boolean input = false;
		int strIn = 0;
		while (check) {
			tf.setText("");
			strIn = JOptionPane.showOptionDialog(window,jcompArr, "Load File From Server",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, objArr, "Load File");
			
			if(strIn != 0)
				break;
			for(char c: tf.getText().toCharArray()) {
				if(!((int)c == 32))
					input = true;
			}
			if(!input) {
				JOptionPane.showMessageDialog(null, "Empty Input is not allowed","Error",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			break;
		}
		return tf.getText();
	}

}
