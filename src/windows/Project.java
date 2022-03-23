package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import functions.BPP;
import functions.Tape;
import mainPackage.ThePanel;
import tools.Buttons;

public class Project implements WindowInterface{

	private final int numBpm;
	BPP bpm;
	private final int bpmTimeMilli;
	
	double starterW, starterH;
	double currW, currH;
	
	ThePanel panel;
	WindowManager wm;
	
	boolean recording;
	
	private int currKey;
	
	LinkedList<Tape> tapes;
	boolean checkedBeat;
	int beat;
	
	Color backgroundColor;
	boolean Recording;
	Buttons[] button;
	boolean[] buttonClick;
	
	public Project(ThePanel panel, WindowManager wm, int numBpm) {
		beat = 0;
		checkedBeat = false;
		this.panel = panel;
		this.wm = wm;
		this.numBpm = numBpm;
		bpmTimeMilli = 60000/numBpm;
		bpm = new BPP(this.numBpm);
		this.starterW = this.panel.width();
		this.starterH = this.panel.height();
		
		tapes = new LinkedList<Tape>();
		
		this.currW = starterW;
		this.currH = starterH;
		currKey = 0;
		recording = false;
		
		backgroundColor = new Color(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat());
		button = new Buttons[9];
		for(int i = 0; i < 9; i ++)
		 button[i] = new Buttons(panel, (int)(starterW-150), (int)(i*(starterH/9)), 150, (int)(starterH/9)-1, "Add Tape",	Color.GRAY, Color.BLACK, Color.BLACK, "Agency FB", 1, 1, 3);
		buttonClick = new boolean[9];
	}   	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		bpm.init();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	
		updateDimensions();
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		//Paint The Background
		g.setColor(backgroundColor);
		g.fillRect(0, 0, panel.width(), panel.height());
		
		
		//Draw The Bpm
		g.setFont(new Font("Agency FB", Font.BOLD, (int)(currW/(starterW/25))));
		g.setColor(Color.BLACK);
		//if(bpm.getBeat() != 0)
		g.drawString(Integer.toString(bpm.getBeat()), (int)(currW/(starterW/5)),(int) (currH/(starterH/22)));
		
		//Paint The Tapes Backgrounds
		
		
		//Painting the tapes
		for(int i = 0; i < button.length; i++){
			
			button[i].update();
			button[i].paint(g);
			if(!buttonClick[i]) {
				if(button[i].clicked()) {
					newTape(tapes.size(), i, 4);
				buttonClick[i] = true;
				button[i].changeWriting("");
				}
			}
						if(i < tapes.size()) {               
						tapes.get(i).update();               
						tapes.get(i).paint(g);               
						}                                    
			}
	}

	@Override
	public void GUI(JPanel j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyP(int k) {
		// TODO Auto-generated method stub
		if(!recording) {
		if(currKey == 1 && k >= 48 && k <=57 ) {
			
			//tapes.add(new Tape(k - 48, 8, this)); 
			int s = tapes.size();
			for(int i = 0; i < s+1; i++) {
			if(i == tapes.size()) {
				
			
			System.out.println(recording);
			newTape(i, k- 49, 8);
			button[k-49].setClicked(true);
			buttonClick[k-49] = true;
			button[k-49].changeWriting("");
			i = s+1;
			}
			else if(tapes.get(i).getTapeNumber() == k-48) {
				tapes.get(i).stopTape();
				tapes.get(i).setBeated(true);
			
				changeTape(i, k-49, 8);
				i = s+1;
			}
			
			}
			
		
		}
		}
		if(k == KeyEvent.VK_SPACE)
			currKey = 1;
		else
			currKey = 0;
		
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
		for(int i = 0; i < tapes.size(); i++)
			tapes.get(i).mouseP(m);
		if(!recording)
		for(int i = 0; i < button.length; i++)
			button[i].mouseP(m);
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
		for(int i = 0; i < tapes.size(); i++)
	   	tapes.get(i).mouseM(m);          
		if(!recording)
			for(int i = 0; i < button.length; i++)  
				button[i].mouseM(m);                
	}                                         
	public int getBpm() {return numBpm;}
	
	public void updateDimensions(){currW = this.panel.width();currH = this.panel.height();}
	
	public void setRecording(boolean bool) {recording = bool;}

	public boolean getRecording(){return recording;}
	
	public void newTape(int k, int num,int loops) {
		
		recording = true;
		tapes.add(k, new Tape(num + 1, loops, this, bpm,panel, starterW, starterH));
        tapes.get(k).setBeated(true);                                       

	}
	public void changeTape(int k, int num, int loops) {
			recording = true;                                                   
			tapes.set(k, new Tape(num + 1, loops, this, bpm,panel, starterW, starterH));       
		    tapes.get(k).setBeated(true);                                                      		                                                                                       

	}
	}
