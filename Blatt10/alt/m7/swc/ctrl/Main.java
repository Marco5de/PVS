package swc.ctrl;

import swc.data.SoccerWC;
import swc.gui.Mainframe;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String [] args){
		Mainframe mainFrame = new Mainframe(new SoccerWC());
		mainFrame.setVisible(true);	
	}

}
