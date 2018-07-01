package erp.ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import erp.database.LegoDB;

/**
 * database connection testing class
 * 
 */
public class TestDB {

	/**
	 * main database testing method, reads all content from the TeileTypen relation
	 * and prints it so the console
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		Connection con = new LegoDB().openConnection();

		Statement st;
		ResultSet rs;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM TEILETYPEN");
			while (rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print("          ");
				System.out.print(rs.getString(2) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
