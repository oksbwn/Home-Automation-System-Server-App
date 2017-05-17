package com.oksbwn.alram_manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oksbwn.server_activity.SendData;
import com.oksbwn.voiceEnable.Voice;

public class alramManager extends Thread{
	boolean DEBUG_ENABLED=true;
	public alramManager(){
			start();
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new alramManager();
	}
	public void run(){

		JSONParser parser = new JSONParser();
		SendData sd=new SendData();
		JSONObject obj;
		SimpleDateFormat dateSimple= new SimpleDateFormat("E");
		SimpleDateFormat datelongSimple= new SimpleDateFormat("d/MMM/y");
		SimpleDateFormat timeSimple= new SimpleDateFormat("HH.mm");
		while(true){
			Date dat= new Date();
			try {
				obj = (JSONObject) parser.parse(sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/getAlramTime.php",new String[]{"DAY"}, new String[]{dateSimple.format(dat).toUpperCase()}));
				if(obj.get("daystatus").toString().equals("NOTSET") && obj.get("date").toString().equals(datelongSimple.format(dat).toString())){
					sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/saveTodayAlramStatus.php",new String[]{"STATUS"}, new String[]{"ON"});
					obj = (JSONObject) parser.parse(sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/getAlramTime.php",new String[]{"DAY"}, new String[]{dateSimple.format(dat).toUpperCase()}));
						
				}
				if(obj.get("daystatus").toString().equals("OFF")){
					if(DEBUG_ENABLED)
						System.out.println("Alram is OFF");
					Thread.sleep((long) (-Double.parseDouble(obj.get("alramtime").toString())+Double.parseDouble(timeSimple.format(dat))*6000));
				}
				else if(obj.get("daystatus").toString().equals("ON") && obj.get("alramstatus").toString().equals("ON") &&(Double.parseDouble(obj.get("alramtime").toString())<Double.parseDouble(timeSimple.format(dat)))){

					if(DEBUG_ENABLED)
						System.out.println("Alram is ON");
					//changeAudioStatus.php
					sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/changeAudioStatus.php",new String[]{"STATUS"}, new String[]{"1"});
					sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/doMorningRoutine.php",new String[]{}, new String[]{});

					Thread.sleep(2000);
					new Voice("Good Morning Sir....you should wake up....");
					Thread.sleep(30000);
					sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/Alram/changeAudioStatus.php",new String[]{"STATUS"}, new String[]{"0"});
				}
			 Thread.sleep(300000);
			} catch (Exception e) {
				if(DEBUG_ENABLED)
					e.printStackTrace();
			}
		}
	}
}
