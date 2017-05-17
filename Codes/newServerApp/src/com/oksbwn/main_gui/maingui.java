package com.oksbwn.main_gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oksbwn.popUp.PopMe;
import com.oksbwn.server_activity.SendData;
/*
 * Main Always on GUI
 * Date 14/Jul/2015
 * 
 * */
public class maingui {
	JFrame frame=new CustomMyFrame().getFrame();
	JPanel panel=new JPanel();
	JEditorPane[] pane;
	int i;
	URL url ;
	private String filePath;
	public static void main(String[] args) throws InterruptedException{
		try {
			new maingui().show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void show() throws Exception{
		panel.setBounds(0,frame.getHeight()/2,frame.getWidth(),frame.getHeight()/2);
		
		final JSONParser parser = new JSONParser();
		final SendData sd=new SendData();
		Object obj = parser.parse(sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/getLoads.php",new String[]{}, new String[]{}));
		final JSONArray json=(JSONArray)obj;
		
		pane=new JEditorPane[json.size()];
		for(i=0;i<json.size();i++){
			final JSONObject job=(JSONObject)json.get(i);
			pane[i]=new JEditorPane();
			pane[i].setContentType("text/html");
			pane[i].setEditable(false);
			pane[i].setSize(frame.getWidth()/10,frame.getHeight()/3);
			if(job.get("status").toString().contains("O"))
				filePath="C:/Alberto/desktopApp/loads/"+job.get("image")+"_on.png";
			else
				filePath="C:/Alberto/desktopApp/loads/"+job.get("image")+"_off.png";

			url =new File(filePath).toURI().toURL();
			pane[i].setText("<html><img src="+url+" width=\""+frame.getWidth()/11+"\" height=\""+((frame.getHeight()/7)-25)+"\"></img><br><b><i>"+job.get("name")+"</i></b></html>");
			
			panel.add(pane[i]);
			pane[i].setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, panel.getBackground()));
			pane[i].addMouseListener(new MouseListener() {
				int x=i;
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
					pane[x].setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, panel.getBackground()));
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					pane[x].setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.black));
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					new PopMe("Smart Home Manager.", "Load Status Changed.","ok",false);
					try {
						sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/change_load_status.php",new String[]{"NO"}, new String[]{""+job.get("id")});
						Object obj = parser.parse(sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/getLoads.php",new String[]{}, new String[]{}));
						final JSONArray json_one=(JSONArray)obj;
							JSONObject newJob=(JSONObject)json_one.get(x);
							if(newJob.get("status").toString().contains("O"))
								filePath="C:/Alberto/desktopApp/loads/"+newJob.get("image")+"_on.png";
							else
								filePath="C:/Alberto/desktopApp/loads/"+newJob.get("image")+"_off.png";

							//System.out.println(job.get("id")+"  "+newJob.toString());
							url =new File(filePath).toURI().toURL();
							pane[x].setText("<html><img src="+url+" width=\""+frame.getWidth()/11+"\" height=\""+((frame.getHeight()/7)-25)+"\"></img><br><b><i>"+newJob.get("name")+"</i></b></html>");
							frame.revalidate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
				try {
					Object obj = parser.parse(sd.sendPost("http://192.168.0.1/smart_home/API/serverApp/getLoads.php",new String[]{}, new String[]{}));
					final JSONArray json=(JSONArray)obj;
					for(i=0;i<json.size();i++){
						JSONObject newJob=(JSONObject)json.get(i);
						if(newJob.get("status").toString().contains("O"))
							filePath="C:/Alberto/desktopApp/loads/"+newJob.get("image")+"_on.png";
						else
							filePath="C:/Alberto/desktopApp/loads/"+newJob.get("image")+"_off.png";
						url =new File(filePath).toURI().toURL();
						pane[i].setText("<html><img src="+url+" width=\""+frame.getWidth()/11+"\" height=\""+((frame.getHeight()/7)-25)+"\"></img><br><b><i>"+newJob.get("name")+"</i></b></html>");
						
					}
					frame.revalidate();
					frame.repaint();
					Thread.sleep(300000);
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		}).start();
		panel.setBackground(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(),Color.BLACK.getBlue(),1));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}	
}
