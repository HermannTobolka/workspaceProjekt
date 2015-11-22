package rezeptSammlung;

import java.awt.image.RescaleOp;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@SuppressWarnings("unused")
public class Model {

	//Instanzvariablen MODEL

	private Controller controller;

	private int zutatenZaehler = 0; 
	private String[]fArray;
	private ArrayList<String>rListe;
	private String[]rArray;
	private String[]rArray2;
	private ArrayList<String>zListe;
	private String[]zArray;
	private String s1;
	private ArrayList<Filter> filterListe = new ArrayList<>();
	private Filter f;

	/**
	 * Initialize Model
	 */

	public void initialize() {
	}

	/**
	 * Keep reference to the controller
	 * @param c
	 */
	public void registerController(Controller c) { 
		controller = c; 
	}

	// Spezifische Methoden Rezeptsammlung

	/**
	 *  ZUTAT ANLEGEN
	 *  und in Zutaten DB eintragen
	 * zuerst Zutaten in der DB Zählen (zutatenZaehler) damit ZutatenId möglich zu vergeben
	 * dann neues Objekt anlegen 
	 * @param zName = Name der Zutat, vom User so eingetragen über GUI
	 */
	// TODO: Fehlermeldung für leerer String --> nächste Version? 
	// TODO: Bestätigungsfenster - Zutat xy angelegt
	// TODO: für spätere Version refactoring zutaten Tabelle mit automatisch generierter id


	public void zutatAnlegen(String zName){

		Connection conn = null;
		Statement stmt = null; 
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			// Zutaten Objekte zählen in Datenbank, Ergebnis in int zutatenZaehler festhalten  
			String s1 = "SELECT 	COUNT (ZutatID) From ZUTATEN";
			ResultSet rs =  stmt.executeQuery(s1);
			while (rs.next()){
				System.out.println(rs.getInt(1)+1);
				zutatenZaehler = rs.getInt(1);}
			rs.close();

			// neues Zutat Objekt anlegen
			Zutat z = new Zutat (zutatenZaehler +1,zName); 

			// neue Zutat in DB ablegen
			// Testausgabe SQL Befehls-String auf Console
			//System.out.println(z.getZutatID() + "," + "'" +  z.getZutatName() + "'"  );

			String s2 = "INSERT INTO zutaten VALUES ("+ z.getZutatID() + "," + "'" +  z.getZutatName() + "'"  +")";
			stmt.executeUpdate(s2);
			System.out.println("Zutat " + '"' + z.getZutatName() + '"' + " in Tabelle Zutaten eingetragen");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
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
				return;
			}
		}
	}



	/**
	 * Filter anlegen
	 * speichert Suchkriterien, die als Paramter vom GUI mitgegeben werden 
	 * und Filtername, der im GUI abgefragt und mitgegeben wird.  
	 * legt Filter in ArrayList Filter, speichert diese filterListe in externer Datei ab. 
	 * @param fName
	 * @param rezeptKategorie
	 * @param zutatenJa
	 * @param zutatenNein
	 */

	public void filterAnlegen(String fName, ArrayList<String> rezeptKategorie , ArrayList<String> zutatenJa , ArrayList<String> zutatenNein){
		// Filter Objekt anlegen und in ArrayList Filter speichern
		f = new Filter (fName, rezeptKategorie, zutatenJa,zutatenNein);
		filterListe.add(f);
		//System.out.println(filterListe);

		// FilterListe in Datei speichern
		try { 
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Hermann\\Workspaceprojekt\\Filterspeicher.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(filterListe);
			oos.close(); 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return; 
		}
		catch (IOException e) {
			e.printStackTrace(); 
			return; 
		}
	}

	/**
	 * Filter löschen
	 * 
	 *
	 * 
	 * @param fName kommt vom Gui, aus der Checkbox Filterliste 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void filterLoeschen(String fName){

		// Filterliste wird aus Datei Filterspeicher.ser geladen (geholt und deserialisiert)
		filterListe = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Hermann\\Workspaceprojekt\\Filterspeicher.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			filterListe = (ArrayList<Filter>)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return  ;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return  ;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return  ;
		}

		/*
		 *  Durch Filterliste wird mittels Iterator iteriert, da es zu einer ConcurrentModificationException kommt
		 * wenn man durch eine Arraylist mit for schleife durchgeht und dabei ein Item entfernt
		 */

		Iterator iter=filterListe.iterator();
		while(iter.hasNext()){
			Filter f=(Filter) iter.next();
			if(f.getNameFilter().equals (fName )){
				iter.remove();
			}
		}


		// FilterListe ohne gelöschten Filter wird wieder in die Speicherdatei geschrieben
		try { 
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Hermann\\Workspaceprojekt\\Filterspeicher.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(filterListe);
			oos.close(); 
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return; 
		}
		catch (IOException e) {
			e.printStackTrace(); 
			return; 
		}

	}

	/**
	 * Filter Anwenden
	 * Der gewählte Filter wird aus der Speicherdatei geholt.
	 * Es wird eine komplexe Rezeptsuche mit den im Filterobjekt 
	 * gespeicherten Infos zu Kategorie und Zutaten durchgeführt
	 * Heraus kommt eine Liste mit Rezeptname die via Liste gefundener Rezepte angezeigt werden kann
	 * 
	 * @param fName
	 * @return String [] mit Namen der passenden Rezepte für die Liste gefundener Rezepte
	 */
	@SuppressWarnings("unchecked")
	public String [] filterAnwenden(String fName){
		// Filterliste wird aus Datei Filterspeicher.ser geladen (geholt und deserialisiert)
		filterListe = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Hermann\\Workspaceprojekt\\Filterspeicher.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			filterListe = (ArrayList<Filter>)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return  rArray;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return rArray ;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return  rArray;
		}

		//nachdem die Liste der Filter nicht geändert wird bei dieser Methode sollte es genügen mit for Schleife durchzugehen 
		for (Filter f : filterListe)
			if (f.getNameFilter().equals(fName)) // passendes Filterobjekt aus Filterliste holen
				// der rezeptSucheFilter Methode die String Arrays als Parameter mitgeben, die im Filterobjekt als Instanzvariablen gespeichert sind
				rArray =	rezeptSucheFilter(f.getRezeptKategorie(), f.getZutatenJa(), f.getZutatenNein());
		return rArray;

	}

	/**
	 * Suchstring für RezeptSuche mit Namen bauen
	 * @param rName (Vom User eingegebner Rezeptname bei Direktsuche)
	 * @return String s1
	 */
	public String rezeptSuchenName(String rName){
		return s1 = "SELECT * From Rezepte Where upper (Rezeptname) like upper ("+ "'" + rName + "%'" +  ")   ";


	}

	/**
	 * Rezepte Auflisten liefert ein Stringarray mit RezeptNamen. 
	 * @param String s1 wird entweder über die Methode rezeptSucheNamen oder RezeptSuche Filter geliefert
	 * @return rArray
	 */


	public String[] rezepteAuflisten (String s1){

		rListe = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null; 
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			// Suche in DB mit rezeptName = Usereingabe (String rName) - LIKE Abfrage damit man Rindsgulasch mit der Eingabe Rind oder Rinds auch findet

			ResultSet rs =  stmt.executeQuery(s1);
			while (rs.next()){
				// Testausgabe 
				System.out.println("=======Testausgabe ob DB Abfrage Inahlte bringt");
				System.out.println("Name gefundenes Rezept aus db: " + rs.getString(2) + " Rezept-ID: " + rs.getInt(1) + " Low Carb: " + rs.getBoolean(3));
				System.out.println("======= ENDE Testausgabe ob DB Abfrage Inahlte bringt");
				Rezept r = new Rezept(rs.getInt(1),rs.getString(2));
				//System.out.println(r.getRezeptName());
				rListe.add(r.getRezeptName());
			}
			rs.close();
			rArray = rListe.toArray(new String[rListe.size()]);


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return rArray;
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
				return rArray;
			}
		}	
		return rArray;
	}

	/**
	 * Komplexe Rezeptsuche mit Filter-Kriterien 
	 * (Rezeptkategorie und Inklusion/Exklusion von je bis zu 2 Zutaten)
	 * Dafür sind mehrere SQL Abfragen nötig, siehe jeweils Kommentar dazu
	 * Geliefert wird ein Array mit Rezept-Namen, dass in einem
	 *  "Liste gefundener Rezepte" Dialog aufgelistet werden kann
	 *  
	 * @param rezeptKategorie
	 * @param zutatenJa
	 * @param zutatenNein
	 * @return String[] rArray2
	 */

	public  String[]  rezeptSucheFilter(ArrayList<String> rezeptKategorie, ArrayList<String> zutatenJa , ArrayList<String> zutatenNein ){

		rListe = new ArrayList<String>();   // Sammeln der Rezeptnamen zunächst als AL
		ArrayList<Integer> rezeptIdExklusion = new ArrayList<>(); // Sammeln von Rezept-Ids zur Zutaten Exklusion

		// gewählte Rezept-Kategorien werden in Teil-String für die spärtere SQL-Abfrage verwandelt
		String kategorieSuche = new String( ""); 

		if (rezeptKategorie.size() ==1) // Je nachdem ob 1 oder 2 Kategorien übergeben werden
			kategorieSuche= ("rezepte." + rezeptKategorie.get(0) +"= true"); // wird der String länger oder kürzer
		else if (rezeptKategorie.size()==2)
			kategorieSuche = ("rezepte." + rezeptKategorie.get(0) +"= true And rezepte."+rezeptKategorie.get(1)+"=true");
		// wird keine kategorie angegeben suchen wir nach rezepten, die entweder vegetarisch sind oder nicht vegetarisch sind
		else kategorieSuche = ("rezepte.vegetarisch = true or rezepte.vegetarisch= false"); //Das geht wahrscheinlich auch eleganter


		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			/*
			 * Zutaten Exklusion ("kein Knoblauch") funktioniert in mehreren Schritten a) Rezepte mit Knoblauch auflisten b) diese dann später ausschliessen bei der SQL Abfrage
			 */

			// Je nachdem, ob 0,1, oder 2 Zutaten exkludiert werden sollen sieht der String anders aus
			String exklusion = new String("rezepte.id != 0");

			if (zutatenNein.size()!= 0 && zutatenNein.size()<3) {  // wenn mindestens 1 Zutat(en) übergeben werden,  müssen rezeptids zum exkludieren gesammelt werden
				if (zutatenNein.size()==1){
					String s1 = " Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (zutaten.zutatname =  "+"'" + zutatenNein.get(0) +"'" +") "
							+ "and rezepte.id = rezeptzutat.rezeptid and rezeptzutat.zutatid = zutaten.zutatid ";

					ResultSet rs =  stmt.executeQuery(s1);  
					while (rs.next()){
						//System.out.println(rs.getInt(1));
						rezeptIdExklusion.add(rs.getInt(1)); // Rezept Id in Arraylist ablegen 
						exklusion =   ("rezepte.id != " + rezeptIdExklusion.get(0) +"");  // eine Id wird exkludiert

					}	// bringt die Abfrage ein leeres Resultset werden keine Rezepte exkludiert
					 
					rs.close();
				}


				// wenn 2 Zutaten exkludiert werden sollen, Abfrage nach Zutat 2, die nicht dabei sein soll
				else if (zutatenNein.size() == 2 ){
					String s1 = " Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (zutaten.zutatname =  "+"'" + zutatenNein.get(0) +"'" +") "
							+ "and rezepte.id = rezeptzutat.rezeptid and rezeptzutat.zutatid = zutaten.zutatid ";

					ResultSet rs =  stmt.executeQuery(s1);  
					while (rs.next()){
						//System.out.println(rs.getInt(1));
						rezeptIdExklusion.add(rs.getInt(1));
					}

					String s2 = " Select distinct rezepte.id   from rezeptzutat, rezepte, zutaten where  (zutaten.zutatname =  "+"'" + zutatenNein.get(1) +"'" +") "
							+ "and rezepte.id = rezeptzutat.rezeptid and rezeptzutat.zutatid = zutaten.zutatid ";
					//System.out.println(s2);	
					ResultSet rs1 =  stmt.executeQuery(s2);
					while (rs1.next()){
						System.out.println(rs1.getInt(1));
						rezeptIdExklusion.add(rs1.getInt(1));// zweite Rezeptid sammeln 
						exklusion =   ("rezepte.id != " + rezeptIdExklusion.get(0) +" and rezepte.id !=" + rezeptIdExklusion.get(1) + ""); // 2 ids werden exkludiert 
					}
					// bringt die Abfrage ein leeres Resultset werden keine Rezepte exkludiert, dann wird exklusion nicht geändert und bleibt auf rezepteid!= ß
					 
					rs1.close();
				}
			}
			//werden keine Zutaten zur Exklusion mitgegeben 
			else exklusion = ("rezepte.id != 0");  // nur Rezepte mit id 0 werden exkludiert (also keines). Auch wenn mehr als 2 Zutaten zur Exklusion gewählt werden passiert das

			/*
			 * Zutaten Inklsuion: funktioniert momentan mit einer Abfrage, es werden Rezepte gefunden, die Zutat 1 und oder Zutat 2 enthalten
			 * Kategorie und Exklusion fliessen in diese Abfrage mit ein
			 */

			String inklusion = new String ("");


			if (zutatenJa.size()!=0 && zutatenJa.size()<3) {  // wird mindestens eine Zutat zur Inklusion mitgegeben
				if (zutatenJa.size()==1){
					inklusion = ("zutaten.zutatname = "+ "'"+zutatenJa.get(0) + "'" +"");
				} 
				else if (zutatenJa.size()==2){
					inklusion = ("zutaten.zutatname = "+ "'"+zutatenJa.get(0) + "'" +" or zutaten.zutatname = "+ "'"+zutatenJa.get(1) + "'" +"");
				}

			}// werden keine Zutaetn zur Inklusion mitgegeben, alle Rezepte anzeigen
			else inklusion = ("rezepte.id != 0");

			System.out.println("Select distinct rezepte.rezeptname  from rezeptzutat, rezepte, zutaten where ("+  kategorieSuche + ") "
					+ "and (" + inklusion + ") "
					+ "and (" + exklusion + ")"
					+ " and rezeptzutat.zutatid = zutaten.zutatid"
					+ " and rezepte.id = rezeptzutat.rezeptid");

			ResultSet rs2 = stmt.executeQuery( "Select distinct rezepte.rezeptname  from rezeptzutat, rezepte, zutaten where ("+  kategorieSuche + ") "
					+ "and (" + inklusion + ") "
					+ "and (" + exklusion + ")"
					+ " and rezeptzutat.zutatid = zutaten.zutatid"
					+ " and rezepte.id = rezeptzutat.rezeptid") ;
			while (rs2.next()){
				System.out.println(rs2.getString(1));
				rListe.add(rs2.getString(1)); // gefundene Rezepte Namen in ArrayList sammeln
			}

			rs2.close();
			rArray2 = rListe.toArray(new String[rListe.size()]);  // Liste in Array umwandeln, das im "Liste gefundener Rezepte Dialog" angezeigt werden kann"
			// Testausgabe
			System.out.println("Testausgabe, ob Array befüllt ist nach RezeptSucheFilter");
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
	 * REZEPT ANZEIGE: liefert ein Rezept-Objekt, das im GUI angezeigt werden kann
	 *  2 fache DB Abfrage  
	 * @param rName = aus Suchergebnis gewähltes Rezept (name davon)
	 * @return Rezept r;
	 */

	public  Rezept rezeptAnzeigen(String rName){

		int rezeptId =0;
		Rezept r = new Rezept();
		ArrayList<String> mengeZutatList = new ArrayList<>();


		Connection conn = null;
		Statement stmt = null; 

		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();
			// Abfrage 1: Rezept-Id zum Rezeptnamen finden
			String s1 = "Select * From rezepte where rezeptname =  ("+ "'" + rName + "'" +  ")";
			System.out.println(s1);

			ResultSet rs =  stmt.executeQuery(s1);  
			while (rs.next()){
				// Testausgabe 
				//System.out.println(rs.getInt(1) + " " + rs.getString(2) + rs.getString(6));
				//Rezept anlegen und initialisieren; 
				r = new Rezept(rs.getInt(1),rs.getString(2));
				// Rezeptid als Methoden-interner Parameter, der an die zweite Abfrage übergeben wird 
				rezeptId = rs.getInt(1);
				r.setKochAnweisung(rs.getString(6));
			}

			/*
			 *  zweite Abfrage, das Statement wird erst hier gebildet, 
			 *  da man dazu die Rezeptid aus Abfrage 1 braucht.
			 *  Zutaten und Mengen werden aus der rezeptzutat und zutaten Tabelle geholt, 
			 *  in eine Arraylist gelegt, die dann mittels Setter dem Rezept Objekt mitgegeben wird. 
			 *  
			 */
			ResultSet rs2 = stmt.executeQuery( "SELECT rezeptzutat.menge, zutaten.zutatname From rezeptzutat, zutaten where rezeptzutat.rezeptid =" + rezeptId + "and rezeptzutat.zutatid = zutaten.zutatid");
			while (rs2.next()){
				//Testausgabe
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

	/**
	 * REZEPT ANLEGEN - Schreibt Rezept + Zutaten und Mengen in Tabellen Rezepte und Rezeptzutat
	 * @param rName
	 * @param lc
	 * @param kh
	 * @param veg
	 * @param kochAnweisung
	 * @param zutatenNamenList
	 * @param mengenList
	 */
	public void rezeptAnlegen (String rName, boolean lc, boolean kh, boolean veg, String kochAnweisung, ArrayList<String> zutatenNamenList, ArrayList<String> mengenList){
		// Interne Variablen
		int rezeptId = 0; // RezeptId vom neu angelegten Rezept - wird mit zweiter INsert Aktion in rezeptzutat verwendet
		ArrayList<Integer> zutatenIds = new ArrayList<Integer>(); // Zutaten Ids werden nicht mitgeliefert, erst unten mit SQL Abfrage geholt

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

	/*
	 * Alte Methode - vor Umstellung DB und vor Umstellung Rezept Klasse
	 */

	//	public Rezept rezeptAnzeigen(String rName){
	//		 Rezept r = new Rezept();
	//		int rezeptId = 0;
	//		Connection conn = null;
	//		Statement stmt = null; 
	//		try {
	//			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
	//			System.out.println("Connection established");
	//			stmt = conn.createStatement();
	//
	//			//Aufruf selektiertes Rezept aus der Datenbank. 
	//			// mit like für rezeptnamen
	//			String s1 = "SELECT * From Rezepte Where upper (Rezeptname) like upper ("+ "'" + rName + "%'" +  ")   ";
	//			
	//			
	//			
	//			ResultSet rs =  stmt.executeQuery(s1);
	//			while (rs.next()){
	//				rezeptId = rs.getInt(1);	

	// Testausgabe / alte Methode mit Rezeptobjekt 
	//				System.out.println("=======Testausgabe ob DB Abfrage REZEPTANZEIGEN bringt");
	//				System.out.println("Name gefundenes Rezept aus db: " + rs.getString(2) + " Rezept-ID: " + rs.getInt(1) + " Low Carb: " + rs.getBoolean(3));
	//				r = new Rezept(rs.getInt(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4),rs.getBoolean(5),rs.getString("Kochanweisung"),rs.getString("zutat1Menge"),rs.getString("zutat2Menge"),rs.getString("zutat3Menge"),rs.getString("zutat4Menge"),rs.getString("zutat5Menge"),rs.getString("zutat6Menge"),rs.getString("zutat7Menge"),rs.getString("zutat8Menge"),rs.getString("zutat9Menge"),rs.getString("zutat10Menge"),rs.getString("zutat11Menge"),rs.getString("zutat12Menge"),rs.getString("zutat13Menge"),rs.getString("zutat14Menge"),rs.getString("zutat15Menge"),rs.getString("zutat16Menge"),rs.getString("zutat17Menge"),rs.getString("zutat18Menge"),rs.getString("zutat19Menge"),rs.getString("zutat20Menge"),
	//						rs.getString("zutat1Name"),rs.getString("zutat2Name"),rs.getString("zutat3Name"),rs.getString("zutat4Name"),rs.getString("zutat5Name"),
	//						rs.getString("zutat6Name"),rs.getString("zutat7Name"),rs.getString("zutat8Name"),rs.getString("zutat9Name"),rs.getString("zutat10Name"),
	//								rs.getString("zutat11Name"),rs.getString("zutat12Name"),rs.getString("zutat13Name"),rs.getString("zutat14Name"),rs.getString("zutat15Name"),
	//								rs.getString("zutat16Name"),rs.getString("zutat17Name"),rs.getString("zutat18Name"),rs.getString("zutat19Name"),rs.getString("zutat20Name"));
	//				System.out.println("ToString von angelegtem RezeptObjekt: " + r.toString());
	//				System.out.println("======= ENDE Testausgabe ob DB Abfrage Inahlte bringt");
	//			}
	//			rs.close();
	//			ResultSet rs2 = stmt.executeQuery( "SELECT rezepte.rezeptname, rezeptzutat.menge, zutaten.zutatname, rezepte.kochanweisung From rezeptzutat, zutaten, rezepte where rezeptzutat.rezeptid =" + rezeptId + "and rezeptzutat.zutatid = zutaten.zutatid");
	//			while (rs2.next()){
	//				System.out.println(rs2.getString(1)+ rs2.getString(2) + rs2.getString(3)+ rs2.getString(4));
	//			}
	//
	//		} catch (SQLException e) {
	//			System.out.println(e.getMessage());
	//			e.printStackTrace();
	//			 
	//		}
	//		finally {
	//			try {
	//				if (stmt != null)
	//					stmt.close();
	//				if (conn != null)
	//					conn.close();
	//			} catch (SQLException e) {
	//				System.out.println(e.getMessage());
	//				e.printStackTrace();
	//				 
	//			}
	//		}
	//		return r;	
	//		 
	//	}

	/**
	 * FILTER Auflisten liefert String Array mit Namen aller Filter 
	 * @return: fArray
	 */
	@SuppressWarnings("unchecked")
	public String[] filterAuflisten() {


		filterListe = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\Hermann\\Workspaceprojekt\\Filterspeicher.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			filterListe = (ArrayList<Filter>)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return fArray;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return fArray;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return fArray;
		}
		System.out.println("\nDeserialisiertes Auto Objekt");
		System.out.println(filterListe);


		ArrayList<String> hilfsListeFilter = new ArrayList<String>();
		for (int i =0; i < filterListe.size(); i++){
			hilfsListeFilter.add(filterListe.get(i).getNameFilter());
			System.out.println(filterListe.get(i).getNameFilter());
		}

		fArray =   hilfsListeFilter.toArray(new String[hilfsListeFilter.size()]);
		for (String s : fArray)
			System.out.println(s);
		return fArray;
	}

	/**
	 * Zutaten Auflisten liefert String Array mit Namen aller Zutaten 
	 * @return: fArra
	 */
	public String[] zutatenAuflisten() {
		zListe = new ArrayList<String>();


		Connection conn = null;
		Statement stmt = null; 
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM zutaten");
			while(rs.next()){
				// TEstausgabe
				System.out.println("id = " + rs.getInt("zutatid") + " Name = " + rs.getString("zutatName") );
				Zutat z = new Zutat (rs.getInt(1),rs.getString(2));
				//									System.out.println("Hier kommt das zutat objekt");
				//									System.out.println(z.toString());
				zListe.add(z.getZutatName());

			}
			rs.close();
			Collections.sort(zListe);
			System.out.println(zListe);
			zArray =   zListe.toArray(new String[zListe.size()]);
			//Testausgabe 
			//				System.out.println("Testausgabe FilterArray");
			//				for (int i =0 ; i< fArray.length; i++){
			//					System.out.println(fArray[i]);
			//				}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return zArray;
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
				return zArray;
			}
		}
		return zArray;	
	}





	/**
	 * CONSTRUCTOR Model - Tabellen hier ursprünglich angelegt, jetzt auskommentiert
	 * 
	 */

	public Model( ) {

		//Tabellen anlegen:
		//				Connection conn = null;
		//				Statement stmt = null; 

		/**
		 * Table Rezepte anlegen
		 * 
		 */

		// Tabelle Rezepte
		//		try {
		//			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
		//			System.out.println("Connection established");
		//			stmt = conn.createStatement();
		//			try {
		//				stmt.executeUpdate("DROP TABLE rezepte");
		//				System.out.println("Table Rezepte dropped");
		//			}
		//			catch(Exception e) { }
		//			String s1 = "CREATE TABLE rezepte (" +
		//					"rezeptID        integer NOT NULL," +
		//					"rezeptName  VARCHAR(70)," +
		//					"lowCarb     BOOLEAN," +
		//					"khReich  BOOLEAN," +
		//					"vegetarisch  BOOLEAN," +
		//					"zutat1ID   integer NOT NULL," +
		//					"zutat2ID   integer NOT NULL," +
		//					"zutat3ID   integer NOT NULL," +
		//					"zutat4ID   integer NOT NULL," +
		//					"zutat5ID   integer NOT NULL," +
		//					"zutat6ID   integer NOT NULL," +
		//					"zutat7ID   integer NOT NULL," +
		//					"zutat8ID   integer NOT NULL," +
		//					"zutat9ID   integer NOT NULL," +
		//					"zutat10ID   integer NOT NULL," +
		//					"zutat11ID   integer NOT NULL," +
		//					"zutat12ID   integer NOT NULL," +
		//					"zutat13ID   integer NOT NULL," +
		//					"zutat14ID   integer NOT NULL," +
		//					"zutat15ID   integer NOT NULL," +
		//					"zutat16ID   integer NOT NULL," +
		//					"zutat17ID   integer NOT NULL," +
		//					"zutat18ID   integer NOT NULL," +
		//					"zutat19ID   integer NOT NULL," +
		//					"zutat20ID   integer NOT NULL," +
		//					"kochAnweisung  	VARCHAR(990)," +
		//					"PRIMARY KEY(rezeptID))";
		//
		//		
		//		} catch (SQLException e) {
		//			System.out.println(e.getMessage());
		//			e.printStackTrace();
		//			return;
		//		}
		//		finally {
		//			try {
		//				if (stmt != null)
		//					stmt.close();
		//				if (conn != null)
		//					conn.close();
		//			} catch (SQLException e) {
		//				System.out.println(e.getMessage());
		//				e.printStackTrace();
		//				return;
		//			}
		//		}

		/**
		 * Table Zutaten anlegen
		 * 
		 */

		//		try {
		//			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
		//			System.out.println("Connection established");
		//			stmt = conn.createStatement();
		//			try {
		//				stmt.executeUpdate("DROP TABLE zutaten");
		//				System.out.println("Table Zutaten dropped");
		//			}
		//			catch(Exception e) { }
		//			String s1 = "CREATE TABLE zutaten (" +
		//					"zutatID        integer NOT NULL," +
		//					"zutatName  VARCHAR(70)," +
		//					"PRIMARY KEY(zutatID))";
		//
		//			stmt.executeUpdate(s1);
		//			
		//		} catch (SQLException e) {
		//			System.out.println(e.getMessage());
		//			e.printStackTrace();
		//			return;
		//		}
		//		finally {
		//			try {
		//				if (stmt != null)
		//					stmt.close();
		//				if (conn != null)
		//					conn.close();
		//			} catch (SQLException e) {
		//				System.out.println(e.getMessage());
		//				e.printStackTrace();
		//				return;
		//			}
		//		}


		/**
		 * Table Filter Anlegen
		 */

		//		try {
		//			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspaceProjekt\\workdb;create=true");
		//			System.out.println("Connection established");
		//			stmt = conn.createStatement();
		//			try {
		//				stmt.executeUpdate("DROP TABLE filter");
		//				System.out.println("Table Filter dropped");
		//			}
		//			catch(Exception e) { }
		//			String s1 = "CREATE TABLE filter (" +
		//					"filterID        integer NOT NULL," +
		//					"filterName  VARCHAR(70)," +
		//					"lowCarb     BOOLEAN," +
		//					"khReich  BOOLEAN," +
		//					"vegetarisch  BOOLEAN," +
		//					"zutat1PlusID   integer NOT NULL," +
		//					"zutat2PlusID   integer NOT NULL," +
		//					"zutat3PlusID   integer NOT NULL," +
		//					"zutat4PlusID   integer NOT NULL," +
		//					"zutat5PlusID   integer NOT NULL," +
		//					"zutat1MinusID   integer NOT NULL," +
		//					"zutat2MinusID   integer NOT NULL," +
		//					"zutat3MinusID   integer NOT NULL," +
		//					"zutat4MinusID   integer NOT NULL," +
		//					"zutat5MinusID   integer NOT NULL," +
		//										"PRIMARY KEY(filterID))";
		//
		//			stmt.executeUpdate(s1);
		//			System.out.println("Table Filter created");



		//		} catch (SQLException e) {
		//			System.out.println(e.getMessage());
		//			e.printStackTrace();
		//			return;
		//		}
		//		finally {
		//			try {
		//				if (stmt != null)
		//					stmt.close();
		//				if (conn != null)
		//					conn.close();
		//			} catch (SQLException e) {
		//				System.out.println(e.getMessage());
		//				e.printStackTrace();
		//				return;
		//			}
		//		}
		//	
	}

	// Ende Model
}

