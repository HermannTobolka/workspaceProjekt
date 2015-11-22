package guiMitTable;

import javax.swing.Icon;

public abstract class Boot {
	private String modell;
	private int plaetze;
	private int preis;
	private Icon bild;
	
	public Boot(String modell, int plaetze, int preis, Icon bild) {
		super();
		this.modell = modell;
		this.plaetze = plaetze;
		this.preis = preis;
		this.bild = bild;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public int getPlaetze() {
		return plaetze;
	}

	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public Icon getBild() {
		return bild;
	}

	public void setBild(Icon bild) {
		this.bild = bild;
	}
	
	

}
