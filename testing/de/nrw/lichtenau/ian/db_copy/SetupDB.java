package de.nrw.lichtenau.ian.db_copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDB {
	private static final int MAX_ROWS=10000;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con2=DriverManager.getConnection("jdbc:mysql://localhost/adressen2", "ian", "test");
		Statement truncate2=con2.createStatement();
		truncate2.executeUpdate("truncate table adressen");

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/adressen", "ian", "test");
		Statement truncate=con.createStatement();
		truncate.executeUpdate("truncate table adressen");
		PreparedStatement insert=con.prepareStatement("insert into adressen (id, str, haus_nr, plz) values (?,?,?,?)");
		for(int i=0; i<=MAX_ROWS; i++) {
			insert.setInt(1, i);
			insert.setString(2, "Straße_"+i);
			insert.setString(3, "Hausnr_"+i);
			insert.setString(4, "PLZ_"+i);
			insert.executeUpdate();
			if(i % 1000==0) {
				System.out.println(""+i+" Datensätze geschrieben");
			}
		}
	}

}
