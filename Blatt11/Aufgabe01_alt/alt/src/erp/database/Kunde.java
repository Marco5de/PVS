package erp.database;

/**
 * model class for the Kunde
 * 
 */
public class Kunde {
	int kdNr;
	String kdName;
	String kdStadt;
	int bonitaet;

	public int getKdNr() {
		return kdNr;
	}

	public String getKdName() {
		return kdName;
	}

	public String getKdStadt() {
		return kdStadt;
	}

	public int getBonitaet() {
		return bonitaet;
	}

	public Kunde(int kdNr, String kdName, String kdStadt, int bonitaet) {
		this.kdNr = kdNr;
		this.kdName = kdName;
		this.kdStadt = kdStadt;
		this.bonitaet = bonitaet;
	}
}
