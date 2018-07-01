package erp.database;

import java.util.Date;

/**
 * model class for the Auftragsposition
 * 
 */
public class Auftragsposition {
	public int getAuftragsnummer() {
		return auftragsnummer;
	}

	public void setAuftragsnummer(int auftragsnummer) {
		this.auftragsnummer = auftragsnummer;
	}

	public int getAuftragspositionsnummer() {
		return auftragspositionsnummer;
	}

	public void setAuftragspositionsnummer(int auftragspositionsnummer) {
		this.auftragspositionsnummer = auftragspositionsnummer;
	}

	public String getTeileID() {
		return teileID;
	}

	public void setTeileID(String teileID) {
		this.teileID = teileID;
	}

	public int getFarbe() {
		return farbe;
	}

	public void setFarbe(int farbe) {
		this.farbe = farbe;
	}

	public float getVerkaufspreis() {
		return verkaufspreis;
	}

	public void setVerkaufspreis(float verkaufspreis) {
		this.verkaufspreis = verkaufspreis;
	}

	public int getAnzahlBestellt() {
		return anzahlBestellt;
	}

	public void setAnzahlBestellt(int anzahlBestellt) {
		this.anzahlBestellt = anzahlBestellt;
	}

	public int getAnzahlReserviert() {
		return anzahlReserviert;
	}

	public void setAnzahlReserviert(int anzahlReserviert) {
		this.anzahlReserviert = anzahlReserviert;
	}

	public int getAnzahlNochZuFertigen() {
		return anzahlNochZuFertigen;
	}

	public void setAnzahlNochZuFertigen(int anzahlNochZuFertigen) {
		this.anzahlNochZuFertigen = anzahlNochZuFertigen;
	}

	public Date getFertigungBeendet() {
		return fertigungBeendet;
	}

	public void setFertigungBeendet(Date fertigungBeendet) {
		this.fertigungBeendet = fertigungBeendet;
	}

	public int getFertigungsstatus() {
		return fertigungsstatus;
	}

	public void setFertigungsstatus(int fertigungsstatus) {
		this.fertigungsstatus = fertigungsstatus;
	}

	private int auftragsnummer;
	private int auftragspositionsnummer;
	private String teileID;
	private int farbe;
	private float verkaufspreis;
	private int anzahlBestellt;
	private int anzahlReserviert;
	private int anzahlNochZuFertigen;
	private Date fertigungBeendet;
	private int fertigungsstatus;

}
