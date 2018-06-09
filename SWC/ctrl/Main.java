package swc.ctrl;

import swc.gui.*;

import java.io.IOException;

import swc.data.*;

/**
 * The starting point of SWC.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class Main {
	/**
	 * Create and show the main GUI.
	 * @param args - String[]
	 */
	public static void main(String[] args) {			
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Mainframe m = new Mainframe(new SoccerWC());
	}
}