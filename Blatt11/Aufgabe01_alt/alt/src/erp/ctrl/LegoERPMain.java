package erp.ctrl;

import erp.database.LegoDB;
import erp.gui.AuftragForm;

import java.sql.SQLException;

/**
 * Main class, used to initialize the GUI and database connection
 * 
 */
public class LegoERPMain {
	/**
	 * entry method
	 */
	public static void main(String[] args) {
		AuftragForm view = null;
		try {
			view = new AuftragForm(new LegoDB());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		view.setVisible(true);

	}

}
