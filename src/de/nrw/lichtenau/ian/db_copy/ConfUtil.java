package de.nrw.lichtenau.ian.db_copy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO mars: Conf_Util -> ConfUtil DONE
//TODO mars: static erzwingen
//TODO mars: /home/ian -> ermitteln DONE
public class ConfUtil {
	public static List<DBProp> getdbconf() throws IOException {		
		try(BufferedReader r = new BufferedReader(new FileReader(System.getProperty("user.home") + "/con.ini"))){
			List<DBProp> rc = new ArrayList<>();
			String line = "";
			DBProp con = null;
			while (true) {
				line = r.readLine();
				if (line == null){
					break;
				}
				if (line.startsWith("[DB_")){
					con = new DBProp();
					con.setName(line.substring(4, line.length()-1));
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
	public static void setdbconf(List<DBProp> verb) throws FileNotFoundException, UnsupportedEncodingException {
		try (PrintWriter w = new PrintWriter(System.getProperty("user.home") + "/con.ini" , "UTF-8")){
			for(DBProp ver: verb) {
				w.println("[DB_" + ver.getName() + "]");
				w.println("URL=" + ver.getUrl());
				w.println("JDBC_Driver=" + ver.getDriver());
				w.println("USER=" + ver.getUser());
				w.println("PASS=" + ver.getPass());
			}
		}
	}
}
	
