package com.oksbwn.system_interactions;
import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class IpAdress{
 
  //public static void main(String[] args){
public static InetAddress getIP(){
	InetAddress ip;
	try {ip = InetAddress.getLocalHost();
		return ip;
 	} catch (UnknownHostException e) {e.printStackTrace();}
	return null;
 
	}
 
   }
