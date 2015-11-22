package dbWeitereBeispiele;

/**
 *Legen Sie einige Städte sowie einige Hotels in diesen Städten mit 
 * unterschiedlichen Kategorien an. 
 * Lesen Sie dann
 * alle Hotels der Kategorie 5, sowie alle Hotels der Kategorien 3 und 4 und zuletzt alle Hotels aus
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StaedteReisen {

	public static void main(String[] args) {
		CreateTableStaedte();
		CreateTableHotels();
		RetrieveAll();
		RetrieveAllKat5();
		RetrieveAllKat3und4();
	}

	@SuppressWarnings("unused")
	private static void CreateTableStaedte() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspaceprojekt\\testdb;create=true")) {
			try(Statement stmt = conn.createStatement()) {
//				try {
//					stmt.executeUpdate("DROP TABLE staedte");
//					System.out.println("Table dropped");
//				}
//				catch(Exception e) { }
				String s1 = "CREATE TABLE staedte (" +
						"id        INTEGER NOT NULL," +
						"Name      VARCHAR(50)," +
						"PRIMARY KEY(id))";
				stmt.executeUpdate(s1);
				System.out.println("Table created");
				conn.setAutoCommit(false);
				stmt.addBatch("INSERT INTO staedte VALUES(1, 'Wien')");
				stmt.addBatch("INSERT INTO staedte VALUES(2, 'Paris')");
				stmt.addBatch("INSERT INTO staedte VALUES(3, 'Helsinki')");
				int[] updateCounts = stmt.executeBatch();
				System.out.println("Rows inserted");
				conn.commit();
				System.out.println("Updates now committed");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void CreateTableHotels() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspaceprojekt\\testdb;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				try {
					stmt.executeUpdate("DROP TABLE hotels");
					System.out.println("Table dropped");
				}
				catch(Exception e) { }
				String s1 = "CREATE TABLE hotels (" +
						"id          INTEGER NOT NULL," +
						"city_id     INTEGER NOT NULL," +
						"NameHotel   VARCHAR(100)," +
						"Kategorie 	 INTEGER NOT NULL,"+
						"PRIMARY KEY(id))";
				stmt.executeUpdate(s1);
				System.out.println("Hotels created");
				conn.setAutoCommit(false);
				stmt.addBatch("INSERT INTO hotels VALUES(11, 1, 'Imperial',5)");
				stmt.addBatch("INSERT INTO hotels VALUES(12, 1, 'Holiday inn',4)");
				stmt.addBatch("INSERT INTO hotels VALUES(13, 1,'Zur alten Donau',3)");
				stmt.addBatch("INSERT INTO hotels VALUES(21, 2, 'Ambassador',5)");
				stmt.addBatch("INSERT INTO hotels VALUES(22, 2,'Motel One',4)");
				stmt.addBatch("INSERT INTO hotels VALUES(23, 2,'Ota Munki',3)");
				stmt.addBatch("INSERT INTO hotels VALUES(31,3, 'Grand Hotel de Paris',5)");
				stmt.addBatch("INSERT INTO hotels VALUES(32, 3,'Paris Hilton',5)");
				stmt.addBatch("INSERT INTO hotels VALUES(33, 3, 'Auberge Espagniol',3)");

				int[] updateCounts = stmt.executeBatch();
				System.out.println("Rows inserted");
				conn.commit();
				System.out.println("Updates now committed");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void RetrieveAll() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\hotels;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM hotels");
				while(rs.next())
					System.out.println("id = " + rs.getInt("id") + " Stadt = " + rs.getString("city_id") + 
							" Name = " + rs.getString("NameHotel") + " Kategorie = " + rs.getInt("Kategorie"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void RetrieveAllKat5() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\hotels;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM hotels WHERE Kategorie = 5 ");
				System.out.println(rs);
				while(rs.next())
					System.out.println("id = " + rs.getInt("id") + " Stadt = " + rs.getString("city_id") + 
							" Name = " + rs.getString("NameHotel") + " Kategorie = " + rs.getInt("Kategorie"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void RetrieveAllKat3und4() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\hotels;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM hotels WHERE Kategorie = 3  OR Kategorie = 4");
				System.out.println(rs);
				while(rs.next())
					System.out.println("id = " + rs.getInt("id") + " Stadt = " + rs.getString("city_id") + 
							" Name = " + rs.getString("NameHotel") + " Kategorie = " + rs.getInt("Kategorie"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



}
