package de.nrw.lichtenau.ian.db_copy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO mars: Conf_Util -> ConfUtil DONE
//TODO mars: static erzwingen
//TODO mars: /home/ian -> ermitteln DONE
public class ConfUtil {
	public static List<DBProp> getdbconf() throws IOException {
		try(BufferedReader r = new BufferedReader(new FileReader(System.getProperty("user.home") + "/quell.ini"))){
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
}
