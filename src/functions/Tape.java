package functions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import mainPackage.ThePanel;
import tools.Buttons;
import windows.Project;

public class Tape extends Thread{

	private final int tapeNumber;



	 // record duration, in milliseconds
   final long RECORD_TIME;
   final int RECORD_BEATS;
   int recordLoop;

   boolean beated = false;
   
   // path of the wav file
   File wavFile;

   // format of audio file
   AudioFileFormat.Type fileType;

   // the line from which audio data is captured
   TargetDataLine line;

   
   boolean finished;
   boolean recording;
   
   int bpm;

	File path1;
	AudioInputStream input1;
	DataLine.Info info1;
	AudioFormat format1;
	Clip clip1;
	
   BPP beat;
   ThePanel panel;
 
   Project proj;
   Buttons button;
   
   
   
   
   
	
	public Tape(int tapeNumber, int recordTime, Project proj, BPP beat, ThePanel panel, double starterW, double starterH) {
		this.panel = panel;
		this.beat = beat;
		this.proj = proj;
		this.tapeNumber = tapeNumber;
		this.bpm = proj.getBpm();
		// record duration, in milliseconds
   RECORD_BEATS = recordTime;
   RECORD_TIME = (60000/bpm)*recordTime + 5000;  // 1 minute
   
   recordLoop = 0;

   // path of the wav file
    wavFile = new File(System.getProperty("user.dir") + "/Sounds/Recording.wav");

   // format of audio file
   fileType = AudioFileFormat.Type.WAVE;

   path1 = new File(System.getProperty("user.dir") + "/Sounds/Recording.wav");
	input1 = null;
	info1 = null;
	clip1 = null;
   finished = false;
   recording = false;
   beated = false;
   button = new Buttons(panel, (int)(starterW-150), (int)((tapeNumber-1)*(starterH/9)), 150, (int)(starterH/9)-1, "Waiting", 	Color.BLACK, Color.RED, Color.BLACK, "Agency FB", 1, 1, 3);
	System.out.println(tapeNumber + 10);
	}
	
	
	public void run() {
		
		
	    try {
           AudioFormat format = getAudioFormat();
           DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

           // checks if system supports the data line
           if (!AudioSystem.isLineSupported(info)) {
               System.out.println("Line not supported");
               System.exit(0);
           }
           line = (TargetDataLine) AudioSystem.getLine(info);
           line.open(format);     
           line.start();   // start capturing

           System.out.println("Start capturing...");

           AudioInputStream ais = new AudioInputStream(line);


           System.out.println("Start recording...");

           // start recording
           AudioSystem.write(ais, fileType, wavFile);
       } catch (LineUnavailableException ex) {
           ex.printStackTrace();
       } catch (IOException ioe) {
           ioe.printStackTrace();
       }
	    
	    
	}
	
	public void startRecord(int wait) throws InterruptedException {
		this.sleep(wait);
		recording = true;
		this.start();
		
	}
	
	 public void finishRecord(int wait) throws InterruptedException {
		 	this.sleep(wait);
	        line.stop();
	        line.close();
	        System.out.println("Finished");
	        finished = true;
	        loadFile();
		 
	    }
	
	AudioFormat getAudioFormat() {
       float sampleRate = 16000;
       int sampleSizeInBits = 8;
       int channels = 2;
       boolean signed = true;
       boolean bigEndian = true;
       AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                            channels, signed, bigEndian);
       return format;
   }
	
	public void playFile(int num) {
		
		if(beatCheck()) {
	clip1.stop();
	clip1.setFramePosition(0);	
	try {
			this.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if(button.clicked())
	clip1.start();	
	System.out.println("Played At" + " " + System.currentTimeMillis());	
	}
		setRecordLoop(4);
	}
	
	
	public int getTapeNumber() {return tapeNumber;}
	
	
	public void loadFile() {
		
		try {
			input1 = AudioSystem.getAudioInputStream(path1);
			format1 = input1.getFormat();
			info1 = new DataLine.Info(Clip.class, format1);
		
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip1 = (Clip) AudioSystem.getLine(info1);
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip1.open(input1);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean getFinished() {return finished;}
	
	public boolean getRecording() {return recording;}
	
	public int getRecordLoop() {return recordLoop;}
	public void setRecordLoop(int i) {recordLoop+=i; if(recordLoop > RECORD_BEATS) recordLoop = 4;}
	public int getRecordBeats() {return RECORD_BEATS;}
	public boolean beatCheck() {return recordLoop >= RECORD_BEATS;}

	
	
	
	
	//Window Aspects
	public void update() {
		button.update();
		
		if(beat.getBeat() <= 1) {
		
			if(!beated) {
			beated = true;
			
				
				if(!getFinished()) {

					if(getRecording()) {
						setRecordLoop(4);
						if(beatCheck())
							try {
								finishRecord(300);
							proj.setRecording(false);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				
					}
					else {
						try {
							button.changeWriting("Recording");
							startRecord(0);
							recording = true;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				else playFile(0);
			
		}
			
			}
		else
			beated = false;
		
		if(getFinished())
		if(!button.clicked())
			clip1.stop();
		
	}
	
	public void paint(Graphics g) {
		
		if(getFinished()) {
			g.setColor(Color.black);
			button.changeWriting("Tape " + Integer.toString(tapeNumber));
		}
		g.setColor(Color.black);
		//g.drawRect((int)(panel.width()/(starterW/(starterW - 150))), 0 + (int)((tapeNumber-1)*panel.height()/(starterH/(starterH/9))), (int)(panel.width()/(starterW/150)), (int)(panel.height()/(starterH/(starterH/9))));
		button.update();
		button.paint(g);
		
	}
	
	public void mouseP(MouseEvent m) {if(getFinished())button.mouseP(m);}
	public void mouseM(MouseEvent m) {if(getFinished())button.mouseM(m);}
	
	public void setBeated(boolean bool) {
		beated = bool;
	}
	
	public void stopTape() {clip1.stop();}
	
	
}
