package erp.ctrl;

import erp.database.LegoDB;
import erp.gui.AuftragForm;

/**
 * Main class, used to initialize the GUI and database connection
 * 
 */
public class LegoERPMain {
	/**
	 * entry method
	 */
	public static void main(String[] args) {
		AuftragForm view = new AuftragForm(new LegoDB());
		view.setVisible(true);

	}

}
