package engine;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Textures extends Physics{

	String assetsPath;

	public Textures() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void addText(Graphics g, String text, int x ,int y, int size) {

		g.setFont(new Font("default", Font.BOLD, size));
		g.drawString(text, x, y);
		
	}
	
	public void addImage(Graphics g, String s, double x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, (int) x, (int) y);

	}

	public int animation(Graphics g, int animationDelay, int animationNumber, boolean animateDirection, String PlayerDirection, double x, int y, int animationLimit, int animationSpeed) {
		
		animationDelay++;
		if (animateDirection) {
			addImage(g, ("//Player//" + PlayerDirection + animationNumber + ".png"), (int) x, (int) y);
			
			if (animationDelay % animationSpeed == 0) {
				animationNumber++;
				
			}
			if (animationNumber == animationLimit) {	
				animationNumber = 1;
			}
		}		
		return animationNumber;
	}
}

