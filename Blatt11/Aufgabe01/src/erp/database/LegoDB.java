package erp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Database connectivity class, handles all database read/write access for the
 * program
 * 
 */
public class LegoDB {
	private Connection con;
	private PreparedStatement queryAuftraege,
							queryAuftragsPos,
							queryAuftragNr,
							queryKunden,
							queryTeileSuche,
							insertAuftrag,
							insertAuftragsPos;

	public LegoDB(){
		try {
			con = openConnection();
			queryAuftraege = con.prepareStatement("SELECT * FROM AuftragsDS");
			queryAuftragsPos = con.prepareStatement("SELECT * FROM AuftragsPosDS WHERE AuftrNr = ?");
			queryAuftragNr = con.prepareStatement("SELECT AuftrNr FROM AuftragsDS ORDER BY AuftrNr DESC");
			queryKunden = con.prepareStatement("SELECT * FROM Kunden");
			queryTeileSuche = con.prepareStatement("SELECT TeileID, Farbe FROM Teile WHERE TeileID = ? AND Farbe = ?");
			insertAuftrag = con.prepareStatement("INSERT INTO AuftragsDS(AuftrNr, KdNr, KdAuftrNr, KdAuftrDatum, Erfassungsdatum) VALUES (?, ?, ?, ?, ?)");
			insertAuftragsPos = con.prepareStatement("INSERT INTO AuftragsPosDS(AuftrNr, AuftrPos, TeileID, Farbe, AnzVonKundeBestellt) VALUES (?, ?, ?, ?, ?)");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * connection method, called once on program initialization
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection openConnection() throws ClassNotFoundException, SQLException {
		// MARIADB / MYSQL
		String url = "jdbc:mariadb://localhost:3306/legotrailer_db?user=root";
		String driver = "org.mariadb.jdbc.Driver";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		return con;
	}

	/**
	 * reads all Auftrag elements from the database and returns them
	 * 
	 * @return a List of the model type Auftrag
	 * @throws SQLException 
	 */
	public List<Auftrag> readAuftraege(){
		Vector<Auftrag> auftraege = new Vector<>();
		try {
			ResultSet results = queryAuftraege.executeQuery();
			while(results.next()) {
				int auftrNr = results.getInt("AuftrNr");
				int kdNr = results.getInt("KdNr");
				int lgLieferung = results.getInt("LgLieferung");
				boolean lagernd = false;
				if(!results.wasNull() && lgLieferung == 1)
					lagernd = true;
				String kdAuftrNr = results.getString("KdAuftrNr");
				java.sql.Date auftrDatum = results.getDate("KdAuftrDatum");
				java.util.Date datumAuftrag = new Date(auftrDatum.getTime());
				java.sql.Date erfassungsDatum = results.getDate("ErfassungsDatum");
				java.util.Date datumErfassung = new Date(erfassungsDatum.getTime());
				int status = results.getInt("AuftrStatus");

				Auftrag auftrag = new Auftrag(auftrNr, kdNr, kdAuftrNr, datumAuftrag, datumErfassung);
				auftrag.setLgLieferung(lagernd);
				auftrag.setAuftrStatus(status);
				auftraege.add(auftrag);
			}
			results.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return auftraege;
	}

	/**
	 * method to find the sequentially next auftragsnummer for use with a new
	 * Auftrag
	 * 
	 * @return the next unused auftragsnummer
	 * @throws SQLException 
	 */
	public int readNextAuftragsnummer() {
		try {
			ResultSet results = queryAuftragNr.executeQuery();
			if(!results.next())
				return 0;
			else
				return results.getInt("AuftrNr")+1;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}

	/**
	 * method for saving an Auftrag and all its Auftragspositionen(String[] teileID, int[] farbe, int[] amount) to the database
	 * IMPORTANT: the java.util.Date parameter "kdAuftrDatum" must be converted to a java.sql.Date before using it in a preparedstatement
	 */
	public void createAuftrag(int kdNr, String kdAuftrNr, java.util.Date kdAuftrDatum, String[] teileID, int[] farbe, int[] amount) {
		int nextAuftragsnummer = readNextAuftragsnummer(); // get the AuftragsNummer for the new auftrag before it is inserted into the database
		//TODO
		try {
			insertAuftrag.setInt(1, nextAuftragsnummer);
			insertAuftrag.setInt(2, kdNr);
			insertAuftrag.setString(3, kdAuftrNr);
			java.sql.Date auftrDatum = new java.sql.Date(kdAuftrDatum.getTime());
			insertAuftrag.setDate(4, auftrDatum);
			insertAuftrag.setDate(5, auftrDatum);
			insertAuftrag.executeUpdate();

			for(int i = 0; i < teileID.length; i++) {
				insertAuftragsPos.setInt(1, nextAuftragsnummer);
				insertAuftragsPos.setInt(2, i+1);
				insertAuftragsPos.setString(3, teileID[i]);
				insertAuftragsPos.setInt(4, farbe[i]);
				insertAuftragsPos.setInt(5, amount[i]);
				insertAuftragsPos.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
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
		Vector<Auftragsposition> positionen = new Vector<>();
		try {
			queryAuftragsPos.setInt(1, auftragsnummer);
			ResultSet results = queryAuftragsPos.executeQuery();
			while(results.next()) {
				Auftragsposition pos = new Auftragsposition();
				int auftrNr = results.getInt("AuftrNr");
				int auftrPosNr = results.getInt("AuftrPos");
				String teileID = results.getString("TeileID");
				int farbe = results.getInt("Farbe");
				float vkPreis = results.getFloat("VkPreis");
				int anzahlBestellt = results.getInt("AnzVonKundeBestellt");
				int anzahlReserviert = results.getInt("AnzVonKundeBestellt");
				int anzahlZuFertigen = results.getInt("AnzNochZuFertigen");
				java.sql.Date fertigungsende = results.getDate("FertigungBeendet");
				if(fertigungsende == null) {
					fertigungsende = new java.sql.Date(0);
				}
				java.util.Date fertigungBeendet = new Date(fertigungsende.getTime());
				int fertigungsstatus = results.getInt("Fertigungsstatus");

				pos.setAuftragsnummer(auftrNr);
				pos.setAuftragspositionsnummer(auftrPosNr);
				pos.setTeileID(teileID);
				pos.setFarbe(farbe);
				pos.setVerkaufspreis(vkPreis);
				pos.setAnzahlBestellt(anzahlBestellt);
				pos.setAnzahlNochZuFertigen(anzahlZuFertigen);
				pos.setAnzahlReserviert(anzahlReserviert);
				pos.setFertigungBeendet(fertigungBeendet);
				pos.setFertigungsstatus(fertigungsstatus);
				positionen.add(pos);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return positionen;
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
			queryTeileSuche.setString(1, teileID);
			queryTeileSuche.setInt(2, farbe);
			ResultSet result = queryTeileSuche.executeQuery();
			boolean isValid = result.next();
			result.close();
			return isValid;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	/**
	 * method for reading all kunden from the database
	 * 
	 * @return an ArrayList with all kunde objects in the database (sorted by their KdNr)
	 */
	public List<Kunde> readKunden() {
		Vector<Kunde> kunden = new Vector<>();
		try {
			ResultSet results = queryKunden.executeQuery();
			while(results.next()) {
				int kdNr = results.getInt("KdNr");
				String kdName = results.getString("KdName");
				String kdStadt = results.getString("KdStadt");
				int bonitaet = results.getInt("Bonitaet");
				Kunde kunde = new Kunde(kdNr, kdName, kdStadt, bonitaet);
				kunden.add(kunde);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return kunden;
	}
}
