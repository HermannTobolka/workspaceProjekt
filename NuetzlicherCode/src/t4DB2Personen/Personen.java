package t4DB2Personen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Legen Sie eine Tabelle für Personen mit folgenden Spalten an:
 SVNummer (String, primary key)
 Name (String)
 Adresse (String)
 Telnummer (String)
 Email (String)
Erzeugen Sie einige Personen in der Tabelle und lesen Sie diese wieder aus.
 * 
 * @author tobolkah
 *
 */

public class Personen {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspaceProjekt\\testdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();
			try {
				stmt.executeUpdate("DROP TABLE personen");
				System.out.println("Table dropped");
			}
			catch(Exception e) { }
			
			// Tabelle erstellen
			String s1 = "CREATE TABLE Personen (" +
					"SVNummer 		VARCHAR(10),"+
					"Name 		VARCHAR(94),"+
					"Adresse        VARCHAR(146)," +
					"Telnummer  VARCHAR(20)," +
					"Email     VARCHAR(145)," +
					"PRIMARY KEY(SVNummer))";
			stmt.executeUpdate(s1);
			System.out.println("Table created");
			
			//Tabelle befüllen

			String s2 = "INSERT INTO Personen VALUES (" +
					"'1145090478'" + ","+ "'Hermann Tobolka'"+ ","+ "'Max Winter Platz 11/4, 1020 Wien'" +
					"," + "'06646633748'"+","+ "'Hermann.Tobolka@gmail.com'"  +")";
			System.out.println(s2);
			stmt.executeUpdate(s2);

			String s3 = "INSERT INTO Personen VALUES (" +
					"'9465050345'" + ","+ "'Gustav Gans'"+ ","+ "'Entemn Platz 11/4, 1020 Entenhausen'" +
					"," + "'0221546464'"+","+ "'gustav.Gans@gmail.com'"  +")";
			System.out.println(s3);
			stmt.executeUpdate(s3);



			String s4 = "INSERT INTO Personen VALUES (" +
					"'7894515158'" + ","+ "'Donald Duck'"+ ","+ "'Schnorregasse 48, 1020 Entenhausen'" +
					"," + "'5464654654'"+","+ "'ddduckd@gmail.com'"  +")";
			System.out.println(s4);
			stmt.executeUpdate(s4);

			String s5 = "INSERT INTO Personen VALUES (" +
					"'4521784931'" + ","+ "'Snoopy'"+ ","+ "'Hundehütte, 1020 Wien'" +
					"," + "'0'"+","+ "'Snoopy@gmail.com'"  +")";
			System.out.println(s5);
			stmt.executeUpdate(s5);

			String s6 = "INSERT INTO Personen VALUES (" +
					"'4561784512'" + ","+ "'Daisy Duck'"+ ","+ "'Schnorrgasse 44, 1020 Entenhausen'" +
					"," + "'06508646674'"+","+ "'daisydee@gmail.com'"  +")";
			System.out.println(s6);
			stmt.executeUpdate(s6);

			System.out.println("Table populated");
			
			// Tabelle Abfragen
			
			System.out.println("Querying Personen");
			rs = stmt.executeQuery("SELECT * FROM personen");
			while(rs.next()) {
				System.out.println("SVNummer= " + rs.getString("SVNummer") + ","+ " Name = " + rs.getString("Name") + ","+" Adresse= " + rs.getString("Adresse")+","+ " Telefonnummer = "+rs.getString("Telnummer")+ ","+" Email = "+ rs.getString("Email"));
			}

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

}
