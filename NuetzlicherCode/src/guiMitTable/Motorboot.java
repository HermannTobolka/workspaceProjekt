package guiMitTable;

import javax.swing.Icon;

public class Motorboot extends Boot {
	private int motorLeistung;

	public Motorboot(String modell, int plaetze, int preis, Icon bild, int ml) {
		super(modell, plaetze, preis, bild);
		setMotorLeistung(ml);
	}

	public int getMotorLeistung() {
		return motorLeistung;
	}

	public void setMotorLeistung(int motorLeistung) {
		this.motorLeistung = motorLeistung;
	}

}
