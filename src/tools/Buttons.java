package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mainPackage.ThePanel;
import windows.WindowInterface;

public class Buttons implements WindowInterface{
	ThePanel panel;
	double starterW;
	double starterH;
	MouseEvent m;
	
	boolean clicked;
	boolean mouseIn;
	
	int x,y,w,h;
	int X,Y,W,H;
	String name, font;
	Color background1, background2, currBackground, writing;
	double fontSize;
	int option;
	int click;
	
	public Buttons(ThePanel panel, int x, int y, int w, int h, String name, Color background1, Color background2, Color writing, String font, int option, int click, double fontSize) {
		this.fontSize = fontSize * 0.7;
		this.option = option;
		this.panel = panel;
		this.click = click;
		update();
		m = null;
		mouseIn = false;
		clicked = false;
		this.X = x;
		this.Y = y;
		this.W = w;
		this.H = h;
		this.name = name;
		this.background1 = background1;
		this.currBackground = background1;
		this.background2 = background2;
		this.writing = writing;
		this.font = font;
		
		starterW = panel.width();
		starterH = panel.height();
		
	}

		
	
	
	
	public void update() {
		
		x = (int)(panel.width()/(starterW/X));
		y = (int)(panel.height()/(starterH/Y));
		w = (int)(panel.width()/(starterW/W));
		h = (int)(panel.height()/(starterH/H));

	}
	
	public void paint(Graphics g) {
	
		
		if(option == 0) {
		g.setColor(currBackground);
		g.fillRect(x,y,w,h);
		}
		else if(option == 1)
		{
			g.setColor(currBackground);
			g.drawRect(x,y,w,h);
		}
		
		if(click == 0)
		g.setColor(writing);
		g.setFont(new Font(font, Font.BOLD,(int) (h/fontSize)));
		g.drawString(name, x+ w/38, y + g.getFont().getSize());
		
	}
	public String getName() {return name;}
	
	public void mouseC(MouseEvent m) {              
		// TODO Auto-generated method stub          
		
	}                                               
                                                    
                                    
	public void mouseEn(MouseEvent m) {             
		// TODO Auto-generated method stub        
		update();

	}                                               
                                                    
	                                 
	public void mouseEx(MouseEvent m) {             
		// TODO Auto-generated method stub          
		                                            
	}                                               
                                                                                    
	public void mouseP(MouseEvent m) {              
		// TODO Auto-generated method stub          
		if(click==1) {
			if(mouseIn) {
				if(!clicked)
				clicked = true;
				else
					clicked = false;
				if(currBackground != background2)
				currBackground = background2;
				else
					currBackground = background1;
			}
		}                 
	}
                                                    
	                                 
	public void mouseR(MouseEvent m) {              
		// TODO Auto-generated method stub          
		 
		if(click == 0) {
		if(mouseIn)
			clicked = true;
		}
	}





	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
	public void mouseD(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseM(MouseEvent m) {
		// TODO Auto-generated method stub

		
		if(m.getX() > x && m.getX() < x + w) {
			if(m.getY() > y && m.getY() < y + h) {
				if(click == 0)
				currBackground = background2;
				
					mouseIn = true;
					
				
			}else { if(click==0)currBackground = background1;mouseIn = false;};
		}else { if(click == 0)currBackground = background1; mouseIn = false;}
				
		
	}                                               
	
	public boolean clicked() {
		if(clicked) {
			if(click == 0)
		clicked = false;
			return true;
		
		}
		return false;
		}
	public void setClicked(boolean bool) {clicked = true;currBackground = background2;}
	public void changeWriting(String s) {this.name = s;}
	
}
