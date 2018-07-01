package erp.database;

/**
 * class representing an order of a certain Teil
 * 
 */
public class Teil {
	String teileID;
	int farbe;

	public String getTeileID() {
		return teileID;
	}

	public int getFarbe() {
		return farbe;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	int anzahl;

	public Teil(String teileID, int farbe, int anzahl) {
		this.teileID = teileID;
		this.farbe = farbe;
		this.anzahl = anzahl;
	}
}
