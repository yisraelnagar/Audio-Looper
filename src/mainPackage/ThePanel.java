package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import windows.WindowManager;

public class ThePanel extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener{

	Thread thread;
	WindowManager windowM;
	//Dimensions
	int width,height;
	int scale;
	JFrame frame;
	
	public ThePanel(JFrame frame) {
		super();
		this.frame = frame;
		scale = 4;
		width = 240;
		height = 135;
		this.setBackground(new Color(1f,1f,1f));
		this.setPreferredSize(new Dimension(width*(scale),height*(scale)));
		this.setFocusable(true);
		this.requestFocus();
		windowM  = new WindowManager(this);
		reSize(3);
		
	}
	
	public void init() {
		
		windowM.init();
		GUI();
		if(thread == null) {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		thread = new Thread(this);
		thread.start();
		}
	}
	
	public void reSize(int scale) {
		if(this.scale != scale+1 && scale > 0 && scale < 8) {
			this.scale = scale+1;
		this.setPreferredSize(new Dimension((int) (width* this.scale),(int) (height*this.scale)));
		this.frame.pack();
		
		}
	}
	
	public void update() {
		windowM.update();
		
	}
	
	public void paintComponent(java.awt.Graphics g) {
	super.paintComponent(g);
	windowM.paint(g);
		
	}
	
	
	
	public void GUI() {
		this.setLayout(null);
		windowM.GUI(this);
	
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub


	while(true) {
		update();
		repaint();
		
	}
		
	}
	
	public void keyPressed(KeyEvent k) {
		windowM.keyP(k.getKeyCode());
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	public void keyReleased(KeyEvent k) {
		windowM.keyR(k.getKeyCode());
	}
	public void keyTyped(KeyEvent k) {
		windowM.keyT(k.getKeyCode());
	}
	
	
	
	

	
	public int width() {return width*(scale);}
	public int height() {return height*(scale);}

	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseC(m);
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseEn(m);
	}

	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseEx(m);
	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseP(m);
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseR(m);
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseD(m);
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		windowM.mouseM(m);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

