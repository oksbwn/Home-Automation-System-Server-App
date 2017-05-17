/*
 * Author :Bikash Narayan Panda
 * Date: 08/Jul/2015
 * Gets tiem duration from last Switched on
 * */
package com.oksbwn.system_interactions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
public class OsLoginTime {
	public static long getSystemUptime() throws Exception {
		long uptime = -1;
		
		    Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
		    BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
		    String line;
		    while ((line = in.readLine()) != null) {
		        if (line.startsWith("Statistics since")) {
		            SimpleDateFormat format = new SimpleDateFormat("'Statistics since' MM/dd/yyyy hh:mm:ss a");
		            Date boottime = format.parse(line);
		            uptime = System.currentTimeMillis() - boottime.getTime();
		            break;
		       }
		}
		return uptime;//in millisecond
		}
}
