package erp.database;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database connectivity class, handles all database read/write access for the
 * program
 * 
 */
public class LegoDB {
	private Connection conn;
	private PreparedStatement qAuftraege;
	private PreparedStatement qAuftragpos;
	private PreparedStatement qKunde;
	private  PreparedStatement checkTeil;
	private PreparedStatement insertAuftrag;
	private PreparedStatement insertAuftragpos;
	private PreparedStatement getAuftrNr;


	/**
	 * Constructor to prepare Prepared Statement
	 */
	public LegoDB() throws SQLException {
		conn = this.openConnection();
		this.qAuftraege = conn.prepareStatement(
				"SELECT *" +
				"FROM auftragsds");
		this.qAuftragpos = conn.prepareStatement(
				"SELECT *" +
				"FROM auftragsposds");
		this.qKunde = conn.prepareStatement(
				"SELECT *" +
				"FROM kunden");
		this.checkTeil = conn.prepareStatement(
				"SELECT TeileID, Farbe" +
				"FROM Teile" +
				"WHERE TeileID = ? AND FABRE = ?");
		this.insertAuftrag = conn.prepareStatement(
					"INSERT INTO auftragds(AuftrNr, KdNr, KdAuftrNr, KdAuftrDatum, Erfassungsdatum)" +
							"VALUES(?, ?, ?, ?, ?)");
		this.insertAuftragpos = conn.prepareStatement(
				"INSERT INTO auftragsposds(AuftrNr, AuftrPos, TeileID, Farbe, AnzVonKundeBestellt)" +
						"VALUES(?, ?, ?, ?, ?)");
		this.getAuftrNr = conn.prepareStatement(
				"SELECT AuftrNr" +
						"FROM auftragsds" +
						"ORDER BY AuftrNr ASC");
	}

	/**
	 * connection method, called once on program initialization
	 */
	public Connection openConnection() {
		// MARIADB / MYSQL
		String url = "jdbc:mariadb://localhost:3306/legotrailer_db?user=root";
		String driver = "org.mariadb.jdbc.Driver";

		conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * reads all Auftrag elements from the database and returns them
	 * 
	 * @return a List of the model type Auftrag
	 */
	public List<Auftrag> readAuftraege() {
		ArrayList<Auftrag> list = new ArrayList<Auftrag>();
		try {
			ResultSet feedback = qAuftraege.executeQuery();
			while(feedback.next()){
				int auftrNr = feedback.getInt("AuftrNr");
				int kdNr = feedback.getInt("KdNr");
				String kdAuftrNr = feedback.getString("KdAuftrNr");
				java.sql.Date kdAuftrDatum = feedback.getDate("KdAuftrDatum");
				java.sql.Date erfassungsDatum = feedback.getDate("ErfassungsDatum");
				int lgLieferung = feedback.getInt("LgLieferung");
				int auftrStatus = feedback.getInt("AuftrStatus");
				java.sql.Date auftrAnfertigung = feedback.getDate("AuftrAnFertigung");
				int auftrPosInArbeit = feedback.getInt("AuftrPosInArbeit");
				boolean lgLiefBool = false;
				if(lgLieferung==1)
					lgLiefBool=true;

				java.util.Date kdAuftrDatumUtil = new java.util.Date(kdAuftrDatum.getTime());
				java.util.Date erfassungsDatumUtil = new java.util.Date(erfassungsDatum.getTime());

				Auftrag temp = new Auftrag(auftrNr,kdNr,kdAuftrNr,kdAuftrDatumUtil,erfassungsDatumUtil);
				temp.setAuftrStatus(auftrStatus);
				temp.setLgLieferung(lgLiefBool);
				list.add(temp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * method to find the sequentially next auftragsnummer for use with a new
	 * Auftrag
	 * 
	 * @return the next unused auftragsnummer
	 */
	public int readNextAuftragsnummer() {
		try {
			ResultSet feedback = getAuftrNr.executeQuery();
			if(feedback.next())
				return feedback.getInt("AuftrNr");
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * method for saving an Auftrag and all its Auftragspositionen(String[] teileID, int[] farbe, int[] amount) to the database
	 * IMPORTANT: the java.util.Date parameter "kdAuftrDatum" must be converted to a java.sql.Date before using it in a preparedstatement
	 */
	public void createAuftrag(int kdNr, String kdAuftrNr, java.util.Date kdAuftrDatum, String[] teileID, int[] farbe, int[] amount) {
		int nextAuftragsnummer = readNextAuftragsnummer(); // get the AuftragsNummer for the new auftrag before it is inserted into the database
		//auto commit does not have to be set false, bc PreparedStatement has to be comitted by ".execute" or ".executeUpdate()
		try {
			java.sql.Date auftrDateSQL = new java.sql.Date(kdAuftrDatum.getTime());
			insertAuftrag.setInt(1,nextAuftragsnummer);
			insertAuftrag.setInt(2,kdNr);
			insertAuftrag.setString(3,kdAuftrNr);
			insertAuftrag.setDate(4,auftrDateSQL);
			insertAuftrag.setDate(5,auftrDateSQL);
			insertAuftrag.executeUpdate();

			for(int i=0; i<teileID.length;i++){
				insertAuftragpos.setInt(1,nextAuftragsnummer);
				//Count auftragspos starting at 1
				insertAuftragpos.setInt(2,i+1);
				insertAuftragpos.setString(3,teileID[i]);
				insertAuftragpos.setInt(4,farbe[i]);
				insertAuftragpos.setInt(5,amount[i]);
				insertAuftragpos.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads all Auftragsposition elements for a given auftragsnummer from the
	 * database and returns them
	 * 
	 * @param auftragsnummer
	 *            the auftragsnummer of the Auftrag you want the Auftragspositionen
	 *            of
	 * @return a List of the model type Auftragsposition
	 */
	public List<Auftragsposition> readAuftragspositionen(int auftragsnummer) {
		ArrayList<Auftragsposition> list = new ArrayList<Auftragsposition>();
		try {
			ResultSet feedback = qAuftragpos.executeQuery();
			while(feedback.next()){
				int auftrNr = feedback.getInt("AuftrNr");
				int auftrPos = feedback.getInt("AuftrPos");
				String teileID = feedback.getString("TeileID");
				int farbe = feedback.getInt("Farbe");
				float vkPreis = feedback.getFloat("VkPreis");
				int anzVonKundeBestellt = feedback.getInt("AnzVonKundeBestellt");
				int anzFuerKundeReserviert = feedback.getInt("AnzFuerKundeReserviert");
				int anzNochZuFertigen = feedback.getInt("AnzNochZuFertigen");
				java.sql.Date fertigungBeendet = feedback.getDate("FertigungBeendet");
				int fertigungsStatus = feedback.getInt("FertigungsStatus");
				if(fertigungBeendet==null)
					fertigungBeendet = new java.sql.Date(0);
				java.util.Date fertigungBeendetUtil = new java.util.Date(fertigungBeendet.getTime());

				Auftragsposition temp = new Auftragsposition();
				temp.setAnzahlBestellt(anzVonKundeBestellt);
				temp.setAuftragsnummer(auftrNr);
				temp.setAuftragspositionsnummer(auftrPos);
				temp.setFarbe(farbe);
				temp.setTeileID(teileID);
				temp.setAnzahlNochZuFertigen(anzNochZuFertigen);
				temp.setFertigungBeendet(fertigungBeendetUtil);
				temp.setFertigungsstatus(fertigungsStatus);
				temp.setVerkaufspreis(vkPreis);
				temp.setAnzahlReserviert(anzFuerKundeReserviert);

				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * checks a given teileID/farbe combination for validity by checking the TEILE
	 * relation in the database, only combinations listed there are accepted
	 * 
	 * @param teileID
	 *            ID number of the part in question
	 * @param farbe
	 *            color code of the part
	 * @return true or false, depending if the part exists in this color
	 */
	public boolean checkValidTeil(String teileID, int farbe) {
		try {
			checkTeil.setString(1, teileID);
			checkTeil.setInt(2,farbe);
			ResultSet feedback = checkTeil.executeQuery();
			boolean out = feedback.next();
			feedback.close();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * method for reading all kunden from the database
	 * 
	 * @return an ArrayList with all kunde objects in the database (sorted by their KdNr)
	 */
	public List<Kunde> readKunden() {
		ArrayList<Kunde> list = new ArrayList<Kunde>();
		try {
			ResultSet feedback = qKunde.executeQuery();
			while (feedback.next()){
				int kdNr = feedback.getInt("KdNr");
				String kdName = feedback.getString("KdName");
				String kdStadt = feedback.getString("KdStadt");
				int bonitaet = feedback.getInt("Bonitaet");

				list.add(new Kunde(kdNr,kdName,kdStadt,bonitaet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
