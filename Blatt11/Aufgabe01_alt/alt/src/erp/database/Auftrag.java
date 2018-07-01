package erp.database;

import java.util.Date;

/**
 * model class for an Auftrag
 * 
 */
public class Auftrag {
	public Date getKdAuftrDatum() {
		return kdAuftrDatum;
	}

	public String getKdAuftrNr() {
		return kdAuftrNr;
	}

	public int getAuftrNr() {
		return auftrNr;
	}

	public int getKdNr() {
		return kdNr;
	}

	public Date getErfassungsDatum() {
		return erfassungsDatum;
	}

	public boolean isLgLieferung() {
		return lgLieferung;
	}

	public void setLgLieferung(boolean lagerware) {
		this.lgLieferung = lagerware;
	}

	public int getAuftrStatus() {
		return auftrStatus;
	}

	public void setAuftrStatus(int auftragsstatus) {
		this.auftrStatus = auftragsstatus;
	}

	@Override
	public String toString() {
		return auftrNr + "";
	}

	private int auftrNr;
	private int kdNr;
	private String kdAuftrNr;
	private Date kdAuftrDatum;
	private Date erfassungsDatum;
	private boolean lgLieferung;
	private int auftrStatus;

	public Auftrag(int auftrNr, int kdNr, String kdAuftrNr, Date kdAuftrDatum, Date erfassungsDatum) {
		this.auftrNr = auftrNr;
		this.kdNr = kdNr;
		this.kdAuftrNr = kdAuftrNr;
		this.kdAuftrDatum = kdAuftrDatum;
		this.erfassungsDatum = erfassungsDatum;
	}

}
