package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Wand extends JPanel {

	int X;
	int Y;
	int spellWidth = 255;
	boolean drawWand;
	boolean spell;
	String assetsPath;
	int animationSpeed = 10;
	int animationLimit = 3;
	int animationDelay;
	int animationDelay2;
	boolean turnRight = true;
	boolean turnLeft;

	int_by_ref animationNumber = new int_by_ref(1);
	int_by_ref animationNumber2 = new int_by_ref(1);

	public Wand(int x, int y) {
		X = x;
		Y = y;
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void draw(Graphics g) {
		
		if (drawWand) {
			addImage(g, "//Weapons//wand.png", X, Y);
		}
		if (spell) {
			animation(g, animationDelay, animationNumber, "Spell", X, Y, animationLimit, animationSpeed);
			animationDelay++;
			animation(g, animationDelay2, animationNumber2, "Spell", X + spellWidth , Y, animationLimit, animationSpeed);
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
		return (new Rectangle(X + 45, Y, 500, 5));
	}
	
	
}
