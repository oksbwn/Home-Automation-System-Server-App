package com.oksbwn.main_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.oksbwn.main.ClassCalledWhenClosingApp;
import com.oksbwn.voiceEnable.Voice;

public class CustomMyFrame {
	JFrame frame=new JFrame();
	JLabel close= new JLabel("X");
	static Dimension gh=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	static int x=gh.width;
	static int y=gh.height; 
	Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
	JFrame getFrame(){

		frame.setUndecorated(true);
		frame.setAlwaysOnTop(false);
		frame.setBackground(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(),Color.BLACK.getBlue(),0));
		frame.setType(javax.swing.JFrame.Type.UTILITY); 
		frame.setBounds(toolHeight.left,0,x-toolHeight.left,y);
		frame.getContentPane().setLayout(null);
		
		close.setBounds(frame.getWidth()-20,5,15,15);
		close.setForeground(Color.GREEN);
		frame.getContentPane().add(close);
		close.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				close.setForeground(Color.GREEN);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				close.setForeground(Color.RED);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			   new ClassCalledWhenClosingApp();
			   new Voice("Sir its a pleasure to work with you.");
			}
		});
		return frame;
	}
}
