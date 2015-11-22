package preparedStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class DBPrepared {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\autoddb;create=true");
			System.out.println("Connection established");
			updatePrice(conn);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM dealers, cars WHERE dealers.DEALER_ID = cars.DEALER_ID";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("All cars: ");
			while (rs.next()) 
				System.out.println("     " + rs.getString("DEALER_NAME") + ": " + rs.getString("CAR_NAME") + " mit " + rs.getInt("CAR_HP") + " PS kostet " + rs.getInt("CAR_PRICE") + " €");
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

	private static void updatePrice(Connection conn) throws SQLException {
		HashMap<Integer, Integer> prices = new HashMap<Integer, Integer>();
		prices.put(11, 39900); //  Ford Focus RS
		prices.put(12, 25050); //  Ford Focus eco
		prices.put(13, 28450); //  Ford Focus TDCi
		prices.put(21, 183260); //  Mercedes SLS AMG
		prices.put(22, 33580); //  Mercedes C180
		prices.put(23, 78400); //  Mercedes S250
		prices.put(31, 32830); //  Audi A3
		prices.put(32, 46990); //  Audi Q5
		prices.put(33, 152630); //  Audi R8
		prices.put(41, 25990); //  Alfa Romeo Giulietta
		prices.put(42, 55400); //  Alfa Romeo Brera
		prices.put(43, 194380); //  Alfa Romeo 8C
		prices.put(51, 16450); //  Citroen DS3
		prices.put(61, 27580); //  Renault Megane
		prices.put(62, 26550); //  Renault Clio Gordini RS
		prices.put(63, 8730); //  Renault Twingo
		String update = "UPDATE cars SET CAR_PRICE = ? WHERE CAR_ID = ?";
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement(update);
		for(Integer id : prices.keySet()) {
			stmt.setInt(1, prices.get(id));
			stmt.setInt(2, id);
			stmt.executeUpdate();
		}
		if (stmt != null)
			stmt.close();
	}

}
