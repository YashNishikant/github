import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class human {

	int speedY;
	int personX;
	int personY;
	int maxheight;
	int minheight;
	int hHitBox;
	int wHitBox;

	boolean jump = false;
	boolean rise = true;
	boolean insideSuit = false;
	boolean nobattery = false;
	boolean onground = false;
	boolean hitgrass = false;
	boolean fall = false;
	boolean alreadyjumped = false;
	
	public human() {

		speedY = 0;
		personX = 810;
		personY = 600;
		maxheight = 600;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
		hitgrass = false;
	}

	public void move() {

		personY = personY + speedY;
	}

	public void jump() {
		
		if (jump == true) {
			if (personY >= maxheight-10 && rise == true) {
				System.out.println("1");
				speedY = -3;
				if (personY <= maxheight) {
					rise = false;
					fall = true;
					speedY = 3;
					alreadyjumped = true;
				}
				
				if(speedY == 0) {
					jump = false;
					alreadyjumped = false;
				}
			}
		}
	}
	public void draw(Graphics g) {

		// User
		g.setColor(Color.red);
		g.fillRect(personX, personY + 30, 20, 30);

		// legs
		g.setColor(Color.DARK_GRAY);
		g.fillRect(personX + 12, personY + 61, 7, 19);
		g.fillRect(personX + 1, personY + 61, 7, 19);

		// arms
		g.setColor(Color.GRAY);
		g.fillRect(personX + 21, personY + 30, 7, 25);
		g.fillRect(personX - 8, personY + 30, 7, 25);

	}

	public Rectangle bounds() {

		return (new Rectangle(personX - 10, personY + 10, wHitBox, hHitBox));

	}

}
