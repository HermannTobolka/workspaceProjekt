package rezeptSammlung;

import java.util.ArrayList;

/*
 * Hilfsklasse f�r die Hilfsklasse
 * 
 * hier schaut man sich einfach nur Strings auf der Console an. 
 */

public class StringBetrachter {

	public static void main(String[] args) {
		
		int rezeptId = 0; // RezeptId vom neu angelegten Rezept - wird mit zweiter INsert Aktion in rezeptzutat verwendet
		String rName = ""; // Kommt sp�ter als Parameter vom GUI
		boolean lc = false; // kommen entweder als Parameter aus der GUI oder werden in der Rezept Anlegen Methode von String in Boolean "�bersetzt" mit if abfrage
		boolean kh = false;
		boolean veg = false;
		String kochAnweisung = "";
		rName = "Palatschinken";
		kochAnweisung ="Das Mehl mit der Milch zu einem glatten Teig versprudeln, salzen, und die Eier einr�hren. 1/2 Stunde reasten lassen. In einer Pfanne ein wenig Fett"
				+ "hei� werden lassen, mit einem mittleren Sch�pfl�ffel Teig hinein gie�en und diesen durch Drehen der Pfanne so verlaufen lassen, dass er die ganze Pfanne mit"
				+ "einer d�nnen, gleichm��igen Schichte bedeckt. Am Rand noch etwas Fett zuflie�en lassen und die Palatschinke bei guter Hitze auf beiden Seiten hellbraun"
				+ "backen. Mit Marillen oder Himbeermarmelade f�llen, mit Zucker bestreuen und sofort zum Tisch geben, damit sie nichts von ihrer Knusprigkeit verlieren.";


		String s1= "INSERT INTO Rezepte (Rezepte.Rezeptname, Rezepte.lowcarb, rezepte.khreich, Rezepte.vegetarisch, rezepte.Kochanweisung) "
				 				+ "Values ("+ "'" + rName +"'" +", "+ lc +" , "+ kh +" ,  "+veg +" , "+"'"+ kochAnweisung +"'"+" )";
		System.out.println(s1);
		
			 

	

	}

}
