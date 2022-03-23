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

public class BPM extends Thread{

	int bpm;
	Record record;
	int i = 0;
	
	public BPM(int bpm) {
		this.bpm = bpm;
		record = new Record();
	}
	
	public void setBPM(int bpm){this.bpm = bpm;
	this.interrupt();
	}
	public int getBPM() {return this.bpm;}
	
	public void run() {
	File path = new File(System.getProperty("user.dir") + "/Sounds/bpm.wav");
	AudioInputStream input = null;
	DataLine.Info info = null;
	AudioFormat format;
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
	
	clip.setFramePosition(0);
	System.out.println("Beep At " + System.currentTimeMillis());
	
	clip.start();
	if(i == 1)
		record.start();
	if( i == 9) {
		record.finish();
		record.playFile((6000/bpm)-31);
	
	}
	try {
	this.sleep(60000/bpm);
	
	}catch(Exception e) {e.printStackTrace();}
	i++;
}
}	
}
