package Environment;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class land extends JPanel{

	public double X;
	public double XGround;
	public int Y;
	public double backgroundspeed;

	String assetsPath;
	
	public land(int x1) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		X = x1;
		Y = 400;
		backgroundspeed = 0;
	}

	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Background.png", (int) X, Y);
		X += backgroundspeed;
	}

	public void addImage(Graphics g, String s, int x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, x, (int) y);
	}
	
}
