package windows;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

import mainPackage.ThePanel;

public class WindowManager {

	LinkedList<WindowInterface> windows;
	int numWindows;
	int currWindow;
	ThePanel panel;
	
	int currKey;
	
	private int bpm;
	
	public WindowManager(ThePanel panel) {
		currKey = 0;
		this.panel = panel;
		bpm = 130;
		windows = new LinkedList<WindowInterface>();
		windows.add(new Menu(panel, this));
		windows.add(new Project(panel, this, bpm));
		numWindows = 2;
		currWindow = 0;
		
	}
	
	
	
	public void init() {
	
			windows.get(currWindow).init();
		
	}
	
	public void switchWindow(int i) {
		
		currWindow = i;
		windows.get(i).init();
		
	}
	
	public void update() {windows.get(currWindow).update();}
	public void paint(Graphics g) {windows.get(currWindow).paint(g);}
	public void GUI(JPanel j) {
		windows.get(currWindow).GUI(j);
	}
	
	public void keyP(int k) {
    
        
if(currKey == 1 && k != KeyEvent.VK_CONTROL)                        
panel.reSize(k - 48);                                           

if(k == KeyEvent.VK_CONTROL)                                        
currKey = 1;                                     
else
currKey = 0;    
       
		windows.get(currWindow).keyP(k);}
	public void keyR(int k) {
		if(k == KeyEvent.VK_CONTROL)     
			currKey = 0;                
		
		windows.get(currWindow).KeyR(k);}
	public void keyT(int k) {windows.get(currWindow).keyT(k);}
	
	public void mouseC(MouseEvent m) {windows.get(currWindow).mouseC(m);}
	public void mouseEn(MouseEvent m) {windows.get(currWindow).mouseEn(m);}
	public void mouseEx(MouseEvent m) {windows.get(currWindow).mouseEx(m);}
	public void mouseP(MouseEvent m) {windows.get(currWindow).mouseP(m);}
	public void mouseR(MouseEvent m) {windows.get(currWindow).mouseR(m);}
	public void mouseD(MouseEvent m) {windows.get(currWindow).mouseD(m);}
	public void mouseM(MouseEvent m) {windows.get(currWindow).mouseM(m);}
	
	public void setBpm(int bpm) {this.bpm = bpm;}
	
}
