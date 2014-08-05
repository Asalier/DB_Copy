package de.nrw.lichtenau.ian.db_copy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO mars: Conf_Util -> ConfUtil
//TODO mars: static erzwingen
//TODO mars: /home/ian -> ermitteln
public class Conf_Util {
	public static List<DB_Prop> getdbconf() throws IOException {
		try(BufferedReader r = new BufferedReader(new FileReader("/home/ian/test.txt"))){
			List<DB_Prop> rc = new ArrayList<>();
			String line = "";
			DB_Prop con = null;
			while (true) {
				line = r.readLine();
				if (line == null){
					break;
				}
				if (line.startsWith("[DB_")){
					con = new DB_Prop();	
					rc.add(con);
				}else{
					String[] kv=line.split("=");
					switch(kv[0]){
					case "URL" : 
						con.setUrl(kv[1]);
						break;
					case "JDBC_Driver" : 
						con.setDriver(kv[1]);
						break;
					case "USER" : 
						con.setUser(kv[1]);
						break;
					case "PASS" : 
						con.setPass(kv[1]);
						break;
					}
				}
			}
			return rc;
		} catch (FileNotFoundException e) {
			return new ArrayList<>();

		}
	}
}
