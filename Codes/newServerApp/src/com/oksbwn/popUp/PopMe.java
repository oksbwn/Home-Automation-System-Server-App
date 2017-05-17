package com.oksbwn.popUp;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/*
 * Author :Bikash Narayan Panda
 * Date: 08/Jul/2015
 * */
public class PopMe extends Thread{
	String header;
	String message;
	String icon;
	boolean isMail;
	public static void main(String[] args) throws Exception{
		new PopMe("hi hghags gdfgk kakjdkj adg kjus", "from Bikash kdghkfhjg djfkjdhgfkj jhjkdfk jkhdfhjdh kjdfjkhdkf h kjhdfjhfd", "ok",false);
	}
	public PopMe(String header, String message, String image,boolean isMail){
		this.header=header;
		this.message=message;
		this.icon=image;
		this.isMail=isMail;
		start();
	}
	public void run(){
		final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		if (runnable != null)
		   runnable.run();
		final JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setType(javax.swing.JFrame.Type.UTILITY);
    	frame.setBackground(new Color(Color.black.getRed(), Color.black.getGreen(),Color.black.getBlue(),0));
		((JComponent)frame.getContentPane()).setBorder(    
		        BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.black ) );
		frame.setSize(600,80);
		frame.setLayout(null);

		JLabel head = new JLabel(header);
		head.setOpaque(false);
		head.setForeground(Color.red);
		head.setBounds(90,2,380,20);
		frame.getContentPane().add(head);
		
		JLabel headingLabel = new JLabel();
		ImageIcon headingIcon = new ImageIcon("C:/Alberto/desktopApp/icons/back.jpg");
		headingLabel .setIcon(headingIcon); // --- use image icon you want to be as heading image.
		headingLabel.setOpaque(false);
		headingLabel.setBounds(5,5,80,80-10);
		frame.getContentPane().add(headingLabel);
		
		JLabel messageLabel = new JLabel("<HtMl>"+message);
		messageLabel.setForeground(Color.blue);
		messageLabel.setBounds(90, 20, 380,30);
		frame.getContentPane().add(messageLabel);
		if(isMail){
			JButton showMail = new JButton("show"); 
			showMail.setFocusable(false);
			showMail.setBounds(500, 20,80,30);
			showMail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//new ReadMailFromGMAIl(10);
					frame.dispose();
					
				}
			});
			frame.getContentPane().add(showMail);
			
		}
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen  
        frame.setLocation((int) ((scrSize.getWidth()/2)-frame.getWidth()/2),0); 
                new Thread(){  
           @Override 
            public void run() {  
                  try {  
                	  Thread.sleep(5*1000); // time after which pop up will be disappeared. 
                	  if(isMail)
                		  Thread.sleep(5*1000); // time after which pop up will be disappeared.  
                      frame.dispose();  
                      }
                  catch (Exception e)
                  {
                	  
                  } };}.start(); 
		
	}
}