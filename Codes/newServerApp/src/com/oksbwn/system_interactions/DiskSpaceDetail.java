package com.oksbwn.system_interactions;
import java.io.File;
public class DiskSpaceDetail
{
	public String FreeSpaceInDrive()
    {	
		String space="";
    	File file = new File("C:");
    	long totalSpace = file.getTotalSpace(); //total disk space in bytes.
    	long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
     	space=space+"C:";
    	space="T:  "+ totalSpace /1024 /1024 + " MB  F: "+ usableSpace /1024 /1024+" MB";
    	return space;
    	}
	}