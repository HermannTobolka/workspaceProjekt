package dbWeitereBeispiele;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;


public class DBBatch {

	public static void main(String[] args) {
		CreateTable();
		RetrieveAll();
		RetrieveAndUpdate();
		RetrieveAll();
		GetMetaData();
	}
	
	@SuppressWarnings("unused")
	private static void CreateTable() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:C:\\users\\Hermann\\workspace\\workdb;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				try {
					stmt.executeUpdate("DROP TABLE dbBoote");
					System.out.println("Table dropped");
				}
				catch(Exception e) { }
				String s1 = "CREATE TABLE dbBoote (" +
						"id          INTEGER NOT NULL," +
						"modell      VARCHAR(200)," +
						"baujahr     INTEGER," +
						"preis       FLOAT," +
						"PRIMARY KEY(id))";
				stmt.executeUpdate(s1);
				System.out.println("Table created");
				conn.setAutoCommit(false);
				stmt.addBatch("INSERT INTO dbBoote VALUES(1, 'FishHunter', 1999, 120.50)");
				stmt.addBatch("INSERT INTO dbBoote VALUES(2, 'Windjammer', 1985, 12000)");
				stmt.addBatch("INSERT INTO dbBoote VALUES(3, 'SpeedyRacer', 2010, 23000)");
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
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\bootdb;create=true")) {
			try(Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM dbBoote");
				while(rs.next())
					System.out.println("id = " + rs.getInt("id") + " modell = " + rs.getString("modell") + 
							" baujahr = " + rs.getInt("baujahr") + " preis = " + rs.getDouble("preis"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void RetrieveAndUpdate() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\bootdb;create=true")) {
			try(Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM dbBoote");
				int id = 0;
				while(rs.next()) {
					id = rs.getInt("id");
					rs.updateDouble("Preis", rs.getDouble("preis") * 2);
					rs.updateRow();
				}
				rs.moveToInsertRow();
				id++;
				rs.updateInt("id", id);
				rs.updateString("modell", "Brandneu");
				rs.updateInt("baujahr", 2014);
				rs.updateDouble("preis", 1234);
				rs.insertRow();
				rs.beforeFirst();
				if (rs.next())
					rs.deleteRow();
				rs.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void GetMetaData() {
		try(Connection conn = DriverManager.getConnection("jdbc:derby:c:\\users\\hermann\\workspace\\bootdb;create=true")) {
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println("Productname: " + metaData.getDatabaseProductName());
			System.out.println("Productversion: " + metaData.getDatabaseProductVersion());
			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String[] types = null;
			ResultSet rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(rs.next())
				System.out.println("Tablename = " + rs.getString("TABLE_NAME") + " Tabletype = " + rs.getString("TABLE_TYPE"));
			tableNamePattern = "DBBOOTE";
			String columnNamePattern = null;
			rs = metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
			while(rs.next()) {
				int dt = rs.getInt("DATA_TYPE");
				String dts = dt == Types.INTEGER ? "integer" : dt == Types.DOUBLE ? "float" :
					dt == Types.VARCHAR ? "varchar(" + rs.getInt("COLUMN_SIZE") + ")" : Integer.toString(dt);
				System.out.println("Columnname = " + rs.getString("COLUMN_NAME") + " Columntype = " + dts);
			}
			rs = metaData.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);
			while(rs.next()) {
				System.out.println("Primary Key Columnname = " + rs.getString("COLUMN_NAME"));
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
