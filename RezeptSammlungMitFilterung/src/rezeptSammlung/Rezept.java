package rezeptSammlung;

import java.util.ArrayList;

/*
 * Zutaten werden separat erfasst und können auch einzeln neu angelegt werden. Ein Rezept kann aus mehreren Zutaten neu erstellt werden, 
 * daher gibt es viele Instanzvariablen für die Zutaten. Angenommen wird, dass man mit 20 Zutaten auskommt.
 * Die Zutaten können über eine eindeutige Zutaten-ID angesprochen werden, mit der sie in der Zutaten-Tabelle zu finden sind
 * 
 * 
 */


public class Rezept {
	private int rezeptID;
	private String rezeptName;
	private boolean lowCarb;
	private boolean khReich;
	private boolean vegetarisch;
	
	private String kochAnweisung;
	private ArrayList<String> zutatenRezept;
	 

	/**
	 * mehrere Contstrutors, um Bedarf für alle Methoden zu decken, 
	 *es kann und soll nicht immer ein Rezept-Objekt mit allen 40 oder so Instanzvariablen gehandelt werden
	 */
	public Rezept(){

	}
	
	 
	public Rezept(int rezeptID, String rezeptName, boolean lowCarb,
			boolean khReich, boolean vegetarisch, String kochAnweisung,
			ArrayList<String> zutatenRezept) {
		super();
		this.rezeptID = rezeptID;
		this.rezeptName = rezeptName;
		this.lowCarb = lowCarb;
		this.khReich = khReich;
		this.vegetarisch = vegetarisch;
		this.kochAnweisung = kochAnweisung;
		this.zutatenRezept = zutatenRezept;
	}



	// Für viele Methoden genügt ein Rezept-Objekt mit Id und Name, der Rest wird dann wieder aus der DB abgefragt wenn Bedarf ist

	public Rezept(int rezeptID, String rezeptName) {
		super();
		this.rezeptID = rezeptID;
		this.rezeptName = rezeptName;
	}




	/**
	 * To STring zur Testausgabe auf der Konsole beim Methodenschreiben
	 */
 

	@Override
	public String toString() {
		return "Rezept [rezeptID=" + rezeptID + ", rezeptName=" + rezeptName
				+ ", lowCarb=" + lowCarb + ", khReich=" + khReich
				+ ", vegetarisch=" + vegetarisch + ", kochAnweisung="
				+ kochAnweisung + ", zutatenRezept=" + zutatenRezept + "]";
	}





	/**
	 * Getter und Setter für alle Instanzvariablen - viele werden nicht gebraucht, aber besser alle anlegen
	 * als nachher wundern, warum Code nicht funktioniert...
	 * @return
	 */

	public int getRezeptID() {
		return rezeptID;
	}

	public void setRezeptID(int rezeptID) {
		this.rezeptID = rezeptID;
	}

	public String getRezeptName() {
		return rezeptName;
	}

	public void setRezeptName(String rezeptName) {
		this.rezeptName = rezeptName;
	}

	public boolean isLowCarb() {
		return lowCarb;
	}
	public void setLowCarb(boolean lowCarb) {
		this.lowCarb = lowCarb;
	}
	public boolean isKhReich() {
		return khReich;
	}
	public void setKhReich(boolean khReich) {
		this.khReich = khReich;
	}
	public boolean isVegetarisch() {
		return vegetarisch;
	}
	public void setVegetarisch(boolean vegetarisch) {
		this.vegetarisch = vegetarisch;
	}

	
	public String getKochAnweisung() {
		return kochAnweisung;
	}
	public void setKochAnweisung(String kochAnweisung) {
		this.kochAnweisung = kochAnweisung;
	}


	public ArrayList<String> getZutatenRezept() {
		return zutatenRezept;
	}


	public void setZutatenRezept(ArrayList<String> zutatenRezept) {
		this.zutatenRezept = zutatenRezept;
	}






}
