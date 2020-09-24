package weapons;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import playerNpc.int_by_ref;

public class Wand extends JPanel {

	public double X;
	public int Y;
	public int spellWidth = 255;
	public boolean drawWand;
	public boolean spell;
	public String assetsPath;
	public int animationSpeed = 10;
	public int animationLimit = 3;
	public int animationDelay;
	public int animationDelay2;
	public boolean turnRight = true;
	public boolean turnLeft;

	int_by_ref animationNumber = new int_by_ref(1);
	int_by_ref animationNumber2 = new int_by_ref(1);

	public Wand(double x, int y) {
		X = x;
		Y = y;
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void draw(Graphics g) {
		
		if (drawWand) {
			addImage(g, "//Weapons//wand.png", (int) X, Y);
		}
		if (spell) {
			animation(g, animationDelay, animationNumber, "Spell", (int) X, Y, animationLimit, animationSpeed);
			animationDelay++;
			animation(g, animationDelay2, animationNumber2, "Spell", (int) (X + spellWidth) , Y, animationLimit, animationSpeed);
			animationDelay2++;
		}
	}

	public void animation(Graphics g, int animationDelay, int_by_ref animationNumberByRef, String image, int x, int y,
			int animationLimit, int animationSpeed) {
		
		addImage(g, ("//Weapons//" + image + animationNumberByRef.int_ref + ".png"), (int) x, (int) y);
		if (animationDelay % animationSpeed == 0) {
			animationNumberByRef.int_ref++;
		}

		if (animationNumberByRef.int_ref == animationLimit) {
			animationNumberByRef.int_ref = 1;
		}
	}

	public void addImage(Graphics g, String s, int x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, x, (int) y);
	}
	public Rectangle bounds() {
		return (new Rectangle((int)(X + 45), Y, 500, 5));
	}
	
	
}
