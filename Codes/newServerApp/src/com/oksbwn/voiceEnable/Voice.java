package com.oksbwn.voiceEnable;
import com.sun.speech.freetts.VoiceManager;
/*
 * Author :Bikash Narayan Panda
 * Date: 08/Jul/2015
 * */
public class Voice extends Thread{
	String audioString;
	public static void main(String[] args) {
		   new Voice("Hi");
	}
	public Voice(String thingsToSay){
		this.audioString=thingsToSay;
		start();
	}
	public void run(){
		try{
			com.sun.speech.freetts.Voice voiceManager;
			voiceManager = VoiceManager.getInstance().getVoice("kevin16");
			voiceManager.allocate();
			voiceManager.speak(audioString);
		}catch(Exception e){
			
		}
	}
}
