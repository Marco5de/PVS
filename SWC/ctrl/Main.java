package swc.ctrl;

import swc.gui.*;
import swc.data.*;

public class Main {
	
	public static void main(String[] args) {			
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		CreateDialog cD = new CreateDialog(null, new SoccerWC());
		cD.setVisible(true);
	}
}