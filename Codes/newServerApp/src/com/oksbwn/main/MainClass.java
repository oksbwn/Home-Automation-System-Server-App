package com.oksbwn.main;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.plaf.metal.MetalIconFactory;

import com.oksbwn.alram_manager.alramManager;
import com.oksbwn.main_gui.maingui;
import com.oksbwn.voiceEnable.Voice;

public class MainClass {
	static TrayIcon icon = new TrayIcon(getImage(),"OKSBWN My Assistant",createPopupMenu());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new pingMobileIPToCheckIfAtHome();
		
		new alramManager();
	
		try{
			icon.addActionListener(new ActionListener() 
		      {        
			    public void actionPerformed(ActionEvent e) 
		           { 
			    	try {
						new maingui().show();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	 }});  
			SystemTray.getSystemTray().add(icon);
			Thread.sleep(3000);
		icon.displayMessage("Alberto","Running",TrayIcon.MessageType.INFO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static Image getImage() throws HeadlessException 
	  {      
		Icon defaultIcon = MetalIconFactory.getFileChooserHomeFolderIcon(); 
	   Image img = new BufferedImage(defaultIcon.getIconWidth(),defaultIcon.getIconHeight(),BufferedImage.TYPE_4BYTE_ABGR);
		
		defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);     
	     return img;
	  }  
	private static PopupMenu createPopupMenu() throws HeadlessException 
	  { 
	  PopupMenu menu = new PopupMenu(); 
	  MenuItem exit = new MenuItem("Exit");  
	  
	  exit.addActionListener(new ActionListener() 
	  {         
	   public void actionPerformed(ActionEvent e) 
	     {    
		   new ClassCalledWhenClosingApp();
		   new Voice("Sir its a pleasure to work with you.");
	       System.exit(0);
	      }});
	  
	  	menu.add(exit);
	  	return menu;
		}
}
