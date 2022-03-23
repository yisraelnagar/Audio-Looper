package functions;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mainPackage.ThePanel;
import windows.WindowManager;

public class BPP extends Thread{

	private int bpm;
	private int beat;
	long startTime;
	
	
	
	
	
	

	public BPP(int bpm) {
		
		this.bpm = bpm;
		beat = 0;
		
		
	}
	
	public void init(){
		
		startTime = System.currentTimeMillis();
		this.start();
		
	}
	
	public void run(){
		File path = new File(System.getProperty("user.dir") + "/Sounds/bpm.wav");;
		AudioInputStream input = null;
		DataLine.Info info = null;
		AudioFormat format = null;
		Clip clip = null;
		
		
		try {
			input = AudioSystem.getAudioInputStream(path);
			format = input.getFormat();
			info = new DataLine.Info(Clip.class, format);
		
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip = (Clip) AudioSystem.getLine(info);
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(input);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
		
		clip.stop();
		clip.setFramePosition(0);
		if(beat == 0 || beat == 10)
		clip.setFramePosition(10000);
		clip.start();
		
		try {
			this.sleep(60000/bpm);
			}catch(Exception e) {e.printStackTrace();}
		beat++;		
		checkBeat();
		
		System.out.println(beat);
		}
		
		
		}

	
	public void checkBeat() {
		
		if(beat >= 5)
			beat = 1;
	}
	
	
	public int getBeat() {return beat;}
}
