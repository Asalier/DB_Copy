package de.nrw.lichtenau.ian.db_copy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB_Copy {
	
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		List<DBProp> conf=ConfUtil.getdbconf();
		if(conf.size()>0)  {
			DBProp dbProp=conf.get(0);
			Class.forName(dbProp.getDriver());
			
			try(Connection conn = DriverManager.getConnection(dbProp.getUrl(), dbProp.getUser(), dbProp.getPass())) {
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
}
