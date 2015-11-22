package dbWeitereBeispiele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBJoin {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspace\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
		try {
			dropTables(stmt);
			createPopulateDealers(stmt);
			createPopulateCars(stmt);
			String query = "SELECT * FROM dealers, cars WHERE dealers.DEALER_ID = cars.DEALER_ID AND cars.CAR_HP > 300";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Sports cars: ");
			while (rs.next()) 
				System.out.println("     " + rs.getString("DEALER_NAME") + ": " + rs.getString("CAR_NAME") + " mit " + rs.getInt("CAR_HP") + " PS");
			query = "SELECT * FROM dealers, cars WHERE dealers.DEALER_ID = cars.DEALER_ID";
			rs = stmt.executeQuery(query);
			System.out.println("All cars: ");
			while (rs.next()) 
				System.out.println("     " + rs.getString("DEALER_NAME") + ": " + rs.getString("CAR_NAME") + " mit " + rs.getInt("CAR_HP") + " PS");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;		}
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

	private static void dropTables(Statement stmt) {
		try {
			stmt.executeUpdate("DROP TABLE cars");
			System.out.println("cars Table dropped");
			stmt.executeUpdate("DROP TABLE dealers");
			System.out.println("dealers Table dropped");
		}
		catch(Exception e) { 
			System.out.println("DROP failed");
		}
	}

	private static void createPopulateDealers(Statement stmt) throws SQLException {
		String s1 = "CREATE TABLE dealers (" +
		"DEALER_ID integer NOT NULL," +
		"DEALER_NAME VARCHAR(40) NOT NULL," +
		"DEALER_ADDRESS VARCHAR(200) NOT NULL," +
		"PRIMARY KEY(DEALER_ID))";
		stmt.executeUpdate(s1);
		System.out.println("dealers Table created");
		stmt.executeUpdate("INSERT INTO dealers VALUES (1, 'Ford Peschek', 'Oeynhausen, Triesterstr')");
		stmt.executeUpdate("INSERT INTO dealers VALUES (2, 'Mercesdes Brammen', 'Baden, Vöslauerstr')");
		stmt.executeUpdate("INSERT INTO dealers VALUES (3, 'Audi Berger', 'Baden, Grundauerweg')");
		stmt.executeUpdate("INSERT INTO dealers VALUES (4, 'Weintritt', 'Baden, Haidhofstr')");
		stmt.executeUpdate("INSERT INTO dealers VALUES (5, 'Citroen Gruber', 'Baden, Braitnerstr')");
		stmt.executeUpdate("INSERT INTO dealers VALUES (6, 'Renault Hoffmann', 'Baden, Oberwaltersdorferstr')");
		System.out.println("dealers Table populated");
	}

	private static void createPopulateCars(Statement stmt) throws SQLException {
		String s1 = "CREATE TABLE cars (" +
		"CAR_ID integer NOT NULL," +
		"DEALER_ID integer NOT NULL," +
		"CAR_NAME VARCHAR(40) NOT NULL," +
		"CAR_HP integer NOT NULL," +
		"CAR_PRICE integer NOT NULL," +
		"PRIMARY KEY(CAR_ID)," +
		"FOREIGN KEY(DEALER_ID) REFERENCES dealers(DEALER_ID))";
		stmt.executeUpdate(s1);
		System.out.println("cars Table created");
		stmt.executeUpdate("INSERT INTO cars VALUES (11, 1, 'Ford Focus RS', 305, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (12, 1, 'Ford Focus eco', 150, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (13, 1, 'Ford Focus TDCi', 163, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (21, 2, 'SLS AMG', 571, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (22, 2, 'C180', 120, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (23, 2, 'S250', 204, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (31, 3, 'Audi A3', 200, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (32, 3, 'Audi Q5', 211, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (33, 3, 'Audi R8', 430, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (41, 4, 'Giulietta', 170, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (42, 4, 'Brera', 260, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (43, 4, '8C', 450, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (51, 5, 'DS3', 150, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (61, 6, 'Megane', 130, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (62, 6, 'Clio Gordini RS', 201, 0)");
		stmt.executeUpdate("INSERT INTO cars VALUES (63, 6, 'Twingo', 75, 0)");
		System.out.println("cars Table populated");
	}

}
