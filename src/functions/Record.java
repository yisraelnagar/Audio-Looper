package functions;

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

public class Record extends Thread{

	 // record duration, in milliseconds
    final long RECORD_TIME;
 
    // path of the wav file
    File wavFile;
 
    // format of audio file
    AudioFileFormat.Type fileType;
 
    // the line from which audio data is captured
    TargetDataLine line;

    
    
    
    
    

	File path1;
	AudioInputStream input1;
	DataLine.Info info1;
	AudioFormat format1;
	Clip clip1;
	
    
    
    
    
    
    
    
    
    
    
	
	public Record() {
	 // record duration, in milliseconds
    RECORD_TIME = 8000;  // 1 minute
 
    // path of the wav file
     wavFile = new File(System.getProperty("user.dir") + "/Sounds/Recording.wav");
 
    // format of audio file
    fileType = AudioFileFormat.Type.WAVE;
 
    path1 = new File(System.getProperty("user.dir") + "/Sounds/Recording.wav");
	input1 = null;
	info1 = null;
	clip1 = null;
    
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
	
	
	 public void finish() {
	        line.stop();
	        line.close();
	        System.out.println("Finished");
	        
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
		try {
			this.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	clip1.start();	
	System.out.println("Played At" + " " + System.currentTimeMillis());	
	}

	
	
	
	
	
	
	
	
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
	
	
	
	
	
	
}

























