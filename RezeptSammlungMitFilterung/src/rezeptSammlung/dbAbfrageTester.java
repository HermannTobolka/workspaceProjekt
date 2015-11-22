package rezeptSammlung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/*
 * in dieser Klasse kann  man SQL Abfragen basteln und ausprobieren
 * mit der Methode String testen
 * funktionieren sie kann man hier eine neue Methode fürs MOdel zusammenbauen,
 * ohne dort ständig am Code herumdoktern zu müssen
 */



public class dbAbfrageTester {

	// Für rezeptSuche mit Filter
	static  ArrayList<String> rListe;
	static String[]rArray2;

	/**
	 * Main Methode zum Starten vom Stringtester oder der neu erstellten Methode
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 *  für Aufruf Rezept Suche mit Filter
		 *  wird im Original dem Model über den Controler vom View mitgegeben
		 */

		ArrayList<String> zutatenJa = new ArrayList<String>();
		ArrayList<String>zutatenNein= new ArrayList<String>();
		ArrayList<String> rezeptKategorie = new ArrayList<String>();
		zutatenJa.add("Rindfleisch");
		zutatenJa.add("Zwiebel");
		zutatenNein.add("Knoblauch");
		rezeptKategorie.add("khreich");

		// Aufruf rezepteSucheFilter
		rezeptSucheFilter(rezeptKategorie, zutatenJa, zutatenNein);

		//Aufruf rezeptAnlegen
		// rezeptAnlegen();

		// Aufruf Stringtester - für neue SQL Abfragen
		//	stringTesten();

	}


	/**
	 * Methode mit der man verschiedene Strings einfach kurz testen kann 
	 * 
	 */
	public static void stringTesten () {  

		int rezeptId = 0;
		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			/*
			 * String Anlegen
			 */

			String s1 = "Select * from rezepte where id !=0";

			//	Testausgabe, String auf Console betrachten		
			System.out.println(s1);

			// String abschicken Ergebnis in RS aufheben. 
			ResultSet rs =  stmt.executeQuery(s1);

			//			// Werte in DB schreiben bei Insert 
			//			stmt.executeUpdate(s1);


			//Abfrage-Ergebnis holen, ggf. getString und getInt vertauschen für die verschiedenen Spaltenindizes 
			//holen von neuer Rezept-ID die wird Update Rezeptzutat dann übergeben
			while (rs.next()){
				System.out.println(rs.getInt(1)); // + " " + rs.getString(2) + rs.getString(6));
				rezeptId = rs.getInt(1);
			}




		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
		}

	}


	public static void rezeptAnlegen (){

		// Interne Variablen
		int rezeptId = 0; // RezeptId vom neu angelegten Rezept - wird mit zweiter INsert Aktion in rezeptzutat verwendet
		
		// Parameter 
		String rName = ""; // Kommt später als Parameter vom GUI
		boolean lc = false; // kommen entweder als Parameter aus der GUI oder werden in der Rezept Anlegen Methode von String in Boolean "übersetzt" mit if abfrage
		boolean kh = false;
		boolean veg = false;
		String kochAnweisung = "";
//		rName = "Palatschinken";
//		kochAnweisung ="Das Mehl mit der Milch zu einem glatten Teig versprudeln, salzen, und die Eier einrühren. 1/2 Stunde reasten lassen. In einer Pfanne ein wenig Fett"
//				+ "heiß werden lassen, mit einem mittleren Schöpflöffel Teig hinein gießen und diesen durch Drehen der Pfanne so verlaufen lassen, dass er die ganze Pfanne mit"
//				+ "einer dünnen, gleichmäßigen Schichte bedeckt. Am Rand noch etwas Fett zufließen lassen und die Palatschinke bei guter Hitze auf beiden Seiten hellbraun"
//				+ "backen. Mit Marillen oder Himbeermarmelade füllen, mit Zucker bestreuen und sofort zum Tisch geben, damit sie nichts von ihrer Knusprigkeit verlieren.";

		// String [] zutatenNamen = {"Mehl", "Eier", "Milch", "Salz","Zucker","Marmelade"};
		ArrayList<Integer> zutatenIds = new ArrayList<Integer>();
		ArrayList<String> mengenList = new ArrayList<String>();
		mengenList.add("20 dkg");
		mengenList.add("2");
		mengenList.add("1/2 l");
		mengenList.add("1 PRise");
		mengenList.add("reichlich");
		mengenList.add("reichlich");
		ArrayList<String>zutatenNamenList = new ArrayList<String>();
		zutatenNamenList.add("Mehl");
		zutatenNamenList.add("Eier");
		zutatenNamenList.add("Milch");
		zutatenNamenList.add("Salz");
		zutatenNamenList.add("Zucker");
		zutatenNamenList.add("Marmelade");
		
	
		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			/*
			 * Rezept Anlegen Mit Name, Kategorie und Kochanweisung
			 */
			
			String s1= "INSERT INTO Rezepte (Rezepte.Rezeptname, Rezepte.lowcarb, rezepte.khreich, Rezepte.vegetarisch, rezepte.Kochanweisung) "
	 				+ "Values ("+ "'" + rName +"'" +", "+ lc +" , "+ kh +" ,  "+veg +" , "+"'"+ kochAnweisung +"'"+" )";
			
						//Testausgabe, wie String ausschaut
						System.out.println(s1);
						// Werte in DB schreiben
						stmt.executeUpdate(s1);


			/*
			 * Rezept id vom neuen Rezept abfragen, die dann in RezeptZutat mit iNsert eingetragen wirdn 
			 */

			String s1a="SELECT Rezepte.id FROM Rezepte  WHERE REzepte.Rezeptname = "+"'" + rName +"'" +" ";
			ResultSet rs =  stmt.executeQuery(s1a);  
			while (rs.next()){
				System.out.println(rs.getInt(1)); // + " " + rs.getString(2) + rs.getString(6));
				rezeptId = rs.getInt(1);
			}

			/*
			 * Zutaten Ids besorgen - geliefert werden ja nur die Namen, in Rezeptzutat brauchen wir ids. 
			 */
		 
// Eine Zutat ist mindestens dabei
			String zutatIDAbfrage = "zutaten.zutatname =  " +"'" + zutatenNamenList.get(0)+"'" + " ";
// Sind es mehr verlängert sich der Zutaten-Abfrage Teil- String 
			for (int i =1 ; i< zutatenNamenList.size(); i++){
				zutatIDAbfrage = zutatIDAbfrage.concat(" or zutaten.zutatname =  " +"'" + zutatenNamenList.get(i)+"'" + " ");		 
			}
			// kompletten String für SQL Abfrage zusammensetzen 			
			String s2a = "Select zutaten.zutatid From Zutaten Where   "+   zutatIDAbfrage + "  ";
			System.out.println(s2a);
			ResultSet rs2a =  stmt.executeQuery(s2a);  
			while (rs2a.next()){
				//System.out.println(rs2a.getInt(1)); // + " " + rs.getString(2) + rs.getString(6));
				zutatenIds.add(rs2a.getInt(1)); 
			}
			System.out.println("Testausgabe ob alle Zutaten-Ids richtig befüllt sind");
			System.out.println(zutatenIds);

			/*
			 * Tabelle RezeptZutat mit Zutaten und Mengen befüllen für die aktuelle Rezept-ID , zuerst Teilstring bastlen aus übergebenen Zutaten und Mengen
			 * 
			 */
			
			// Eine Zutat + Menge ist fix dabei
			String zutatMengeEintragen = " ( "+ rezeptId +" , "+zutatenIds.get(0) +" , " +"'" +mengenList.get(0) +"'" +" )  ";
			System.out.println(zutatMengeEintragen);

			// für die zweite bis "nte" Zutat verlängert sich der Teilstring ggf. 
			for (int i =1 ; i< mengenList.size(); i++){
				zutatMengeEintragen = zutatMengeEintragen.concat(" , ( "+ rezeptId +" , "+zutatenIds.get(i) +" , " +"'" +mengenList.get(i) +"'" +" )  ");		 
			}
	 
			 // kompletter String für Befüllung Rezept-Zutat 			
			String s2 = "Insert INTO REZEPTZUTAT (Rezeptid,Zutatid, Menge) VALUES  " + zutatMengeEintragen +" " ;
			System.out.println(s2);
			stmt.executeUpdate(s2);


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
		}

	}



	/**
	 * zweifach Abfrage, hier für komplexere Suchanfrage 
	 * zuerst suchen wir nach rezeptids, wo die zu exkludierende zutat  (zb Knoblauch) enthalten ist 
	 * in der zweiten Abfrage suchen wir rezept(namen) die zur gewünschten Kategorie gehören und die zu inkludierenden Zutaten enthalten
	 *  und nicht die rezeptid der rezepte mit Knoblauch enthalten
	 * @param rezeptKategorie
	 * @param zutatenJa
	 * @param zutatenNein
	 * @return
	 */

	public static String[]  rezeptSucheFilter(ArrayList<String> rezeptKategorie, ArrayList<String> zutatenJa , ArrayList<String> zutatenNein ){

		rListe = new ArrayList<String>();
		ArrayList<Integer> rezeptIdExklusion = new ArrayList<>();
		String kategorieSuche = new String( "");


		if (rezeptKategorie.size() ==1)
			kategorieSuche= ("rezepte." + rezeptKategorie.get(0) +"= true"); 
		else kategorieSuche = ("rezepte." + rezeptKategorie.get(0) +"= true And rezepte."+rezeptKategorie.get(1)+"=true");



		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();


			String s1 = " Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (zutaten.zutatname =  "+"'" + zutatenNein.get(0) +"'" +") "
					+ "and rezepte.id = rezeptzutat.rezeptid and rezeptzutat.zutatid = zutaten.zutatid ";


			ResultSet rs =  stmt.executeQuery(s1);  
			while (rs.next()){
				System.out.println(rs.getInt(1));
				rezeptIdExklusion.add(rs.getInt(1));
			}

			if (zutatenNein.size() > 1 ){
				String s2 = " Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (zutaten.zutatname =  "+"'" + zutatenNein.get(1) +"'" +") "
						+ "and rezepte.id = rezeptzutat.rezeptid and rezeptzutat.zutatid = zutaten.zutatid ";
				System.out.println(s2);	
				ResultSet rs1 =  stmt.executeQuery(s2);
				while (rs1.next()){
					System.out.println(rs1.getInt(1));
					rezeptIdExklusion.add(rs1.getInt(1));
				}
				rs1.close();

			}
			//TESTAUSGABE 
			//			System.out.println( "Select distinct rezepte.rezeptname  from rezeptzutat, rezepte, zutaten where ("+ "'" + kategorieSuche + "'" + ") "
			//	 				+ "and  (zutaten.zutatname = "+ "'"+zutatenJa.get(0) + "'" +" or zutaten.zutatname = "+ "'"+zutatenJa.get(1) + "'" +") and (rezepte.id != ("+"'" + rezeptIdExklusion +"'" +")) and rezeptzutat.zutatid = zutaten.zutatid"
			//	 				+ " and rezepte.id = rezeptzutat.rezeptid");

			ResultSet rs2 = stmt.executeQuery(  "Select distinct rezepte.rezeptname  from rezeptzutat, rezepte, zutaten where ("+  kategorieSuche + ") "
					+ "and  (zutaten.zutatname = "+ "'"+zutatenJa.get(0) + "'" +" or zutaten.zutatname = "+ "'"+zutatenJa.get(1) + "'" +") "
					+ "and (rezepte.id != "+ rezeptIdExklusion.get(0)   +") and rezeptzutat.zutatid = zutaten.zutatid"
					+ " and rezepte.id = rezeptzutat.rezeptid") ;
			while (rs2.next()){
				System.out.println(rs2.getString(1));
				rListe.add(rs2.getString(1));
			}
			rs.close();
			rs2.close();
			rArray2 = rListe.toArray(new String[rListe.size()]);
			for (String s : rArray2)
				System.out.println(s);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return rArray2;

		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return rArray2;
			}
		}
		return rArray2;



	}


	/**
	 * Zutat Inkludieren - zwischenschritt als Hilfe für Design der komplexen Suchanfrage
	 */
	public static void zutatenInkludieren () {  // name MethoderezeptSucheFilter ( String kategorie, String inkludieren, String exkludieren  ){
		//lokale Variablen für Methode 



		// vorher if abfrage: if exklusions Infos aus gui != leer, dann abfrage für exklusionsstring. abfragestring DAFÜR = infoobjekt aus gui [1]...[n]
		//	" Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (rezeptzutat.zutatid = infobjekt aus gui [^1]....  or rezeptzutat.rezeptid = infoobjekt aus gui[n]) and rezepte.id = rezeptzutat.rezeptid";
		// liefert rezept ids , exklusionsids - in arraylist aufheben oder auch nicht
		// inklusions string + and (rezeptid exklusionsid[1]... and rezeptid != exklusionsid[n])

		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			// AbfrageString zum Testen

			String s1 = "Select distinct rezepte.rezeptname  from rezeptzutat, rezepte, zutaten where rezepte.lowcarb = false "
					+ "and  (zutaten.zutatname = 'Rindfleisch' or zutaten.zutatname = 'Erdäpfel') and (rezepte.id !=1) and rezeptzutat.zutatid = zutaten.zutatid"
					+ " and rezepte.id = rezeptzutat.rezeptid";


			//Testausgabe, wie String ausschaut
			System.out.println(s1);

			//Abfrage-Ergebnis holen, ggf. getString und getInt vertauschen für die verschiedenen Spaltenindizes 
			ResultSet rs =  stmt.executeQuery(s1);  
			while (rs.next()){
				System.out.println(rs.getString(1)); // + " " + rs.getString(2) + rs.getString(6));


			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
		}

	}



	/**
	 * zweifach Abfrage, hier für Rezeptanzeige. 
	 * zuerst rezept id holen aus Rezepte
	 * dann rezept Objekt anlegen mit daten aus Tabelle Rezepte und aus tabelle rezeptzutat mit der zweiten Abfrage

	 * @param rName
	 * @return
	 */

	public static Rezept rezeptAnzeigen(String rName){

		int rezeptId =0;
		Rezept r = new Rezept();
		ArrayList<String> mengeZutatList = new ArrayList<>();


		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			String s1 = "Select * From rezepte where rezeptname =  ("+ "'" + rName + "'" +  ")";
			System.out.println(s1);

			ResultSet rs =  stmt.executeQuery(s1);  
			while (rs.next()){
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + rs.getString(6));

				rezeptId = rs.getInt(1);	
				r.setRezeptID(rezeptId); r.setKochAnweisung(rs.getString(6));
			}

			ResultSet rs2 = stmt.executeQuery( "SELECT rezeptzutat.menge, zutaten.zutatname From rezeptzutat, zutaten where  rezeptzutat.rezeptid =" + rezeptId + "and rezeptzutat.zutatid = zutaten.zutatid");
			while (rs2.next()){
				//System.out.println(rs2.getString(1)+ rs2.getString(2));
				mengeZutatList.add ((rs2.getString(1) + " " ).concat(rs2.getString(2)));
				r.setZutatenRezept(mengeZutatList);

			}
			System.out.println(r.toString());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return r;
		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return r;
			}
		}


		return r;	
	}





	// ENDE DBABFRAGENTESTER 
}
