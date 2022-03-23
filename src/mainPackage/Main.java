package mainPackage;

import javax.swing.JFrame;

public class Main {

	
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Looper");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ThePanel panel = new ThePanel(frame);
		frame.add(panel);
		
		frame.setVisible(true);
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		panel.init();
		
	}
	
	static public JFrame setSize(JFrame frame, int x, int y){
		
			frame.setSize(x,y);
			
		return frame;
		
	}
	
}
