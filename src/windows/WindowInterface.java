package windows;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public interface WindowInterface {

	public void init();
	public void update();
	public void paint(Graphics g);
	public void GUI(JPanel j);
	public void keyP(int k);
	public void KeyR(int k);
	public void keyT(int k);
	public void mouseC(MouseEvent m);
	public void mouseEn(MouseEvent m);
	public void mouseEx(MouseEvent m);
	public void mouseP(MouseEvent m);
	public void mouseR(MouseEvent m);
	public void mouseD(MouseEvent m);
	public void mouseM(MouseEvent m);

	
}
