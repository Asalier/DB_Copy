package de.nrw.lichtenau.ian.db_copy;

import java.io.IOException;
import java.io.PrintWriter;

public class Speichern {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//			BufferedReader r = new BufferedReader(new FileReader("/home/ian/git/DB_Copy/src/de/nrw/lichtenau/ian/db_copy/DB_Copy.java"));
//			String line = "";
//			while(line != null){
//				line = r.readLine();
//				System.out.println(line);
//			}
			
	try (PrintWriter w = new PrintWriter(System.getProperty("user.home") + "con.ini", "UTF-8")){
		
	}
	}
	

}
