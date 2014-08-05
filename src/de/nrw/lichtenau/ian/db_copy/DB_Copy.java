package de.nrw.lichtenau.ian.db_copy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Copy {
	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/adressen";
	static final String USER = "ian";
	static final String PASS = "test";
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_Driver);
		
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
			DatabaseMetaData meta = conn.getMetaData();
			try(ResultSet res = meta.getTables(null, null, "%", null)) {
				while (res.next()){
					String tabellenname = res.getString("table_name");
//					System.out.println("Id : "+res.getInt("id"));
					System.out.println("Name : " + tabellenname);
//					System.out.println("Vorname : "+ res.getString("vorname"));
					
					try(ResultSet res2 = meta.getColumns(null, null, tabellenname, "%")) {
						while (res2.next()){
//							System.out.println("Id : "+res.getInt("id"));
							System.out.println("Spaltenname : " +res2.getString("column_name"));
//							System.out.println("Vorname : "+ res.getString("vorname"));
							//System.out.println();
						}
					}
					System.out.println();
				}
			}
		}
	}
}
