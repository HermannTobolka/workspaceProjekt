package dbWeitereBeispiele;
/**
 * Verwenden Sie ein prepared statement, um Bücher in die Tabelle zu laden. 
 * Verwenden Sie ein weiteres prepared statement, um Buecher zu bestimmten 
 * Themen aus der Tabelle auszulesen.
 * 
 *  Verwenden Sie ein modifizierbares ResultSet beim Lesen der Bücher zu
 *   einem ausgewählten Thema  und erhöhen Sie die Preise der Bücher
 *    um einen bestimmten Prozentsatz.  
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class BuchDB {

	public static void main(String[] args) {

		ArrayList<Buch> buchliste = new ArrayList<Buch>();
		buchliste.add(new Buch("873516546", "Abenteuer", "Winnetou1", "Karl May", 24));
		buchliste.add(new Buch("873qwsa16546", "Abenteuer", "Winnetou3", "Karl May", 24));
		buchliste.add(new Buch("87s3516546", "Abenteuer", "Winnetou2", "Karl May", 24));
		buchliste.add(new Buch("87aqw3516546", "Abenteuer", "Winnetou4", "Karl May", 24));
		buchliste.add(new Buch("666", "Okkultes", "Die Bibel des Satans", "Anonymous", 66));
		buchliste.add(new Buch("4687687", "Okkultes","Die Freimaurer erobern die WELT",  "Mozart", 24));
		buchliste.add(new Buch("135465", "Erotik", "Kamasutra", "VJ Gupta", 66));
		buchliste.add(new Buch("7765464", "Erotik", "KamasutraII", "VJ Gupta", 99));

		Buch b7 = new Buch ("98097u08","Abentuer","king solomons mines", "h.ryder haggard",19);
		Buch b8 = new Buch ("982eug","Abentuer", "der Schatz im silbersee", "Karl May", 13);
		Buch b9 = new Buch ("987ziuh","Okkultes", "Vodoo im 21 Jahrhundert", "ngustaav",979);
		
	
		Buch[] buchArray = buchliste.toArray(new Buch[buchliste.size()]);

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\buchdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();

			//Tabelle anlegen  - auskommentiert weil sonst Fehlermeldung, dass Tabelle schon da ist. 

			//			String s1 = "CREATE TABLE buecher (" +
			//					"ISBN VARCHAR(46)," +
			//					"THEMA VARCHAR(564)," +
			//					"TITEL VARCHAR(99)," +
			//					"AUTOR VARCHAR(56)," +
			//					"PREIS integer," +
			//					"PRIMARY KEY(ISBN))";
			//					stmt.executeUpdate(s1);
			//					System.out.println("buecher Table created");

			conn.setAutoCommit(false);
			stmt.addBatch("INSERT INTO buecher VALUES('98097u08', 'Abentuer', 'King Solomons mines' , 'h.ryder haggard', 120.50)");
			stmt.addBatch("INSERT INTO buecher VALUES('982eug', 'Abenteuer', 'der Schatz im silbersee', ' Karl May', 12000)");
			stmt.addBatch("INSERT INTO buecher VALUES('987ziuh', 'Okkultes', 'Vodoo im 21 Jahrhundert', 'ngustaav',23000)");
			int[] updateCounts = stmt.executeBatch();
			System.out.println("Rows inserted");
			conn.commit();
			System.out.println("Updates now committed");
//			String befuellen = "INSERT into buecher VALUES (DEFAULT, ?,?,?,?,? )";
//			System.out.println(befuellen);
//			PreparedStatement stmt1 = null;
//			stmt1 = conn.prepareStatement(befuellen);
////						for(Buch b : buchliste) {
////							stmt1.setString(1, b.getIsbn());
////							stmt1.setString(2, b.getThema());
////							stmt1.setString(3, b.getTitel());
////							stmt1.setString(4, b.getAuthor());
////							stmt1.setInt(5, b.getPreis());
////							stmt1.executeUpdate();
////						}
//			stmt1.setString(1, b7.getIsbn());
//			stmt1.setString(2, b7.getThema());
//			stmt1.setString(3, b7.getTitel());
//			stmt1.setString(4, b7.getAuthor());
//			stmt1.setInt(5, b7.getPreis());
//			System.out.println(stmt1);
//			stmt1.executeUpdate();
			
			// Fehlermeldung The number of values assigned is not the same as the number of specified or implied columns.
			
			// test mit array, ohne iterieren durch arralylist
		//			stmt1.setString(1, buchArray[0].getIsbn());
		//			stmt1.setString(2, buchArray[0].getThema());
		//			stmt1.setString(3, buchArray[0].getTitel());
		//			stmt1.setString(4, buchArray[0].getAuthor());
		//			stmt1.setInt(5, buchArray[0].getPreis());
		//			stmt1.executeUpdate();
		//			
		//			Ebenfalls Fehlermeldung 
		//			The number of values assigned is not the same as the number of specified or implied columns.
		//		



			
			// PREPARED STATEMENTS FÜR ABFRAGEN
			
			String abfragenAbenteuer = "SELECT * FROM buecher WHERE THEMA = Abenteuer";
			
			String abfrageKarlMay = "SELECT * FROM buecher WHERE AUTOR = Karl May";
			
			
			
			
			
			
			if (stmt != null)
				stmt.close();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		}

	}

}
