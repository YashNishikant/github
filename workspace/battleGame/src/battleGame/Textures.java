package battleGame;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Textures extends JPanel{

	String assetsPath;

	public Textures() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
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

