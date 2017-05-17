package com.oksbwn.main;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import com.oksbwn.popUp.PopMe;
import com.oksbwn.server_activity.SendData;
import com.oksbwn.system_interactions.OsLoginTime;

/*
* /*
* Author :Bikash Narayan Panda
* Date: 08/Jul/2015
* *
* API: http:192.168.0.1/smart_home/API/desktopApp/saveLoginTime.php
* */
public class ClassCalledWhenClosingApp  {
	public static void main(String[] args) throws Exception{
		new ClassCalledWhenClosingApp();
		}
	public ClassCalledWhenClosingApp() {
		Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
		if (runnable != null)
			runnable.run();
		int v=JOptionPane.showConfirmDialog(null, "Are you Sure ?", "Exit ALBERTO", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
		if (v==0){
			try {
					double x = OsLoginTime.getSystemUptime();
					new SendData().sendPost("http://192.168.0.1/smart_home/API/serverApp/saveLoginTime.php",
							new String[]{"TIME"}, new String[]{""+x/(3600*1000)});
					System.exit(0);
				}catch (Exception e) {
					e.printStackTrace();
				}	
		}
		else if(v==1)
		{
			new PopMe("Nice to get you back.", "Thank  you.", "ok",false);
		}			
	}
}