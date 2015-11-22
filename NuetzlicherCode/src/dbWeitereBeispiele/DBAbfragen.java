package dbWeitereBeispiele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBAbfragen {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspace\\workdb;create=true");
			stmt = conn.createStatement();
			try {
				System.out.println("Querying table1");
				rs = stmt.executeQuery("SELECT * FROM table1");
				while(rs.next()) 
					System.out.println("one = " + rs.getString("one") + " two = " + rs.getInt("two"));
			}
			catch(Exception e) { 
				System.out.println(e.getMessage());
			}
			System.out.println("Querying dbtest");
			rs = stmt.executeQuery("SELECT * FROM dbtest");
			while(rs.next()) {
				System.out.println("fld_string = " + rs.getString("fld_string") + " fld_int = " + rs.getInt("fld_int") + " fld_double = " + rs.getDouble("fld_double"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
