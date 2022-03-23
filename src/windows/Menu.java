package windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

import mainPackage.ThePanel;
import tools.Buttons;
import tools.Windows;

public class Menu implements WindowInterface{

	ThePanel panel;
	
	//Tools
	int currKey;
	int numButtons;
	LinkedList<Buttons> buttons;
	MouseEvent m;
	WindowManager wm;
	boolean newProject;
	Windows newWindow;
	
	public Menu(ThePanel panel, WindowManager wm) {
		this.panel = panel;
		this.wm = wm;
		newProject = false;
		m = null;
		buttons = new LinkedList<Buttons>();
		buttons.add(new Buttons(panel, 380, 100, 300, 35, "New Project",  new Color(0,0,0,0), new Color(.1f,.1f,.1f,.4f),new Color(0,0,0), "Agency FB", 0, 0, 2));
		buttons.add(new Buttons(panel,380, 135, 300, 35, "Load Project", new Color (0,0,0,0), new Color(.1f,.1f,.1f,.4f), new Color(0,0,0), "Agency FB", 0, 0, 2));
		currKey = 0;
		numButtons = 2;
		newWindow = new Windows(panel, 680, 50, 300, 400, 0, null, new Color(.2f,.4f,.6f,.8f));
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	if(newProject)
		newWindow.update();
	
		//New Project                        
		if(buttons.get(0).clicked()) {         
			newProject = true;               
			this.wm.switchWindow(1);
		}
	 
                 
	
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		
		for(int i = 0; i < numButtons; i++) {
		buttons.get(i).paint(g);
		buttons.get(i).update();			
		}
	
	
	
	
		if(newProject)          
			newWindow.paint(g); 
		                        	
	}

	@Override
	public void GUI(JPanel j) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyP(int k) {
		// TODO Auto-generated method stub
	
	
	
	
	
	
	
	

			
	}

	@Override
	public void KeyR(int k) {
		// TODO Auto-generated method stub


	}

	@Override
	public void keyT(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseC(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEn(MouseEvent m) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEx(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseP(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseR(MouseEvent m) {
		// TODO Auto-generated method stub
		for(int i = 0; i < numButtons; i++) { 
			buttons.get(i).mouseR(m);
		}
	}

	@Override
	public void mouseD(MouseEvent m) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void mouseM(MouseEvent m) {
		// TODO Auto-generated method stub
		for(int i = 0; i < numButtons; i++)
		buttons.get(i).mouseM(m);
	

	if(newProject)
		newWindow.mouseM(m);
}
}