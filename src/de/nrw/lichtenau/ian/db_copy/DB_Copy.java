package de.nrw.lichtenau.ian.db_copy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.nrw.lichtenau.ian.db_copy.gui.Window;

public class DB_Copy {
	
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
////		
//		Object sconn;
//		sconn = Window.SourceDBcomboBox.getSelectedItem();
////		if(ConfUtil.verb.size()>0)  {
////			DBProp dbProp=ConfUtil.verb.get(0);
//			try {
//				Class.forName(((DBProp) sconn).getDriver());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			
//			try(Connection scon = DriverManager.getConnection(((DBProp) sconn).getUrl(), ((DBProp) sconn).getUser(), ((DBProp) sconn).getPass())) {
//				DatabaseMetaData meta = scon.getMetaData();
//				try(ResultSet res = meta.getTables(null, null, "%", null)) {
//					while (res.next()){
//						String tabellenname = res.getString("table_name");
//						JOptionPane.showMessageDialog(null, "Id : "+res.getInt("id"));
////						System.out.println("Id : "+res.getInt("id"));
////						System.out.println("Name : " + tabellenname);
////	//					System.out.println("Vorname : "+ res.getString("vorname"));
////						
////						try(ResultSet res2 = meta.getColumns(null, null, tabellenname, "%")) {
////							while (res2.next()){
////	//							System.out.println("Id : "+res.getInt("id"));
////								System.out.println("Spaltenname : " +res2.getString("column_name"));
////	//							System.out.println("Vorname : "+ res.getString("vorname"));
////								//System.out.println();
////							}
////						}
////						System.out.println();
//					}
////				}
////			}
////		}
	}
////
		}
