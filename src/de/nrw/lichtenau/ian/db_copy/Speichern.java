package de.nrw.lichtenau.ian.db_copy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Date;

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
			
	try (PrintWriter w = new PrintWriter("/home/ian/test.txt", "UTF-8")){
	w.println("Hallo Ian");
	w.println("Hallo Markus");
	w.println(new Date());
	w.println(1);
	w.println(1.5);
	}
	}
	

}
