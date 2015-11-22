package rezeptSammlung;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Ein Filter ist nichts anderes als eine Menge bestimmter Suchkriterien, die 
 * der Rezeptsuche übergeben werden. Die Instanzvariablen des Filters decken sich also
 * mit den Parametern, die man zur komplexen Rezeptsuche braucht, daher auch "rezeptSucheFilter"
 * Die Filter werden nicht in einer DB Tabelle gespeichert. Bei einer Webversion würde sie
 * ja auch lokal gespeichert. und zwar in einer Datei per serialisierung 
 * 
 */


@SuppressWarnings("serial")
public class Filter implements Serializable{
	
	private String nameFilter;
	private ArrayList<String> rezeptKategorie;
	private ArrayList<String> zutatenJa;
	private ArrayList<String> zutatenNein;
		
	public Filter(){
		
	}

	public Filter(String nameFilter, ArrayList<String> rezeptKategorie,
			ArrayList<String> zutatenJa, ArrayList<String> zutatenNein) {
		super();
		this.nameFilter = nameFilter;
		this.rezeptKategorie = rezeptKategorie;
		this.zutatenJa = zutatenJa;
		this.zutatenNein = zutatenNein;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public ArrayList<String> getRezeptKategorie() {
		return rezeptKategorie;
	}

	public void setRezeptKategorie(ArrayList<String> rezeptKategorie) {
		this.rezeptKategorie = rezeptKategorie;
	}

	public ArrayList<String> getZutatenJa() {
		return zutatenJa;
	}

	public void setZutatenJa(ArrayList<String> zutatenJa) {
		this.zutatenJa = zutatenJa;
	}

	public ArrayList<String> getZutatenNein() {
		return zutatenNein;
	}

	public void setZutatenNein(ArrayList<String> zutatenNein) {
		this.zutatenNein = zutatenNein;
	}

	@Override
	public String toString() {
		return "Filter [nameFilter=" + nameFilter + ", rezeptKategorie="
				+ rezeptKategorie + ", zutatenJa=" + zutatenJa
				+ ", zutatenNein=" + zutatenNein + "]";
	}

	
	
}