package guiMitTable;

import javax.swing.Icon;

public class Segelboot extends Boot {
	private int segelflaeche;

	public Segelboot(String modell, int plaetze, int preis, Icon bild, int segelflaeche) {
		super(modell, plaetze, preis, bild);
		this.setSegelflaeche(segelflaeche);
	}

	public int getSegelflaeche() {
		return segelflaeche;
	}

	public void setSegelflaeche(int segelflaeche) {
		this.segelflaeche = segelflaeche;
	}

}
