package dbWeitereBeispiele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBAnlegen {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspace\\workdb;create=true");
			System.out.println("Connection established");
			stmt = conn.createStatement();
			try {
				stmt.executeUpdate("DROP TABLE dbtest");
				System.out.println("Table dropped");
			}
			catch(Exception e) { }
			String s1 = "CREATE TABLE dbtest (" +
					"pkey        integer NOT NULL," +
					"fld_string  VARCHAR(20)," +
					"fld_int     integer," +
					"fld_double  numeric(4,2)," +
					"PRIMARY KEY(pkey))";
			stmt.executeUpdate(s1);
			System.out.println("Table created");
			for (int i = 1; i <= 5; i++) {
				double d = i + i/10.0;
				String s2 = "INSERT INTO dbtest VALUES (" +
				i + "," +
				"'fld"+i + "'," +
				i + "," +
				d + ")";
				stmt.executeUpdate(s2);
				System.out.println("Table populated");
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
