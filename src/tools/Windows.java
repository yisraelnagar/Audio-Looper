package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import mainPackage.ThePanel;
import windows.WindowInterface;

public class Windows implements WindowInterface{

	int x, y, w, h;
	Color color;
	int numButtons;
	Buttons[] buttons;
	double starterW;
	double starterH;
	ThePanel panel;
	
	public Windows(ThePanel panel, int x, int y, int w, int h, int numButtons, Buttons[] buttons, Color color) {
		this.panel = panel;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h= h;
		this.numButtons = numButtons;
		this.buttons = buttons;
		starterW = panel.width();
		starterH = panel.height();
		this.color = color;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(color);
		g.fillRect(x, y, w, h);
		for(int i = 0; i < numButtons; i++) {
			buttons[i].paint(g);
			buttons[i].update();
			
	}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//if(buttons[0].clicked())
			System.out.println(panel.height()/(starterH/y));
		
		x = (int)(panel.width()/(starterW/x));
		y = (int)(panel.height()/(starterH/y));
		w = (int)(panel.width()/(starterW/w));
		h = (int)(panel.height()/(starterH/h));
		
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
		
	}

	@Override
	public void mouseD(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseM(MouseEvent m) {
		// TODO Auto-generated method stub
		for(int i = 0; i < numButtons; i++) {
			
			buttons[i].mouseM(m);
			
		}
	}
	
	
}
