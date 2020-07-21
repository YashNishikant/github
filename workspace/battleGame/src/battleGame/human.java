package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class human {

	double speedY;
	int personX;
	double personY;
	double maxheight;
	int minheight;
	int hHitBox;
	int wHitBox;

	boolean jump = false;
	boolean rise = true;
	boolean insideSuit = false;
	boolean nobattery = false;
	boolean onground = false;
	boolean hitgrass = false;
	boolean onetimeJump = true;
	boolean jumping = false;

	public human() {

		speedY = 0;
		personX = 810;
		personY = 870;
		maxheight = personY - 100;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
		hitgrass = false;
	}

	public void move() {
		if (personY >= 871) {
			speedY = 0;
			personY = 870;
		}

		personY = personY + speedY;

	}

	public void shutdown() {

		if (insideSuit && nobattery) {

			if (personY >= 869) {
				speedY = 0;
			} else {
				speedY = 3;
			}
		}
	}

	public void jump() {

		if (jump) {
			if (onetimeJump) {
				speedY = -2.5;
				onetimeJump = false;
				jumping = true;
			}

			personY += speedY;
			if (speedY < 0) {
				speedY += 0.07;
			} else {
				jump = false;
				onetimeJump = true;
				jumping = false;
			}
		}

		if (!jump) {
			personY += speedY;

			jumping = false;

			if (speedY < 3) {
				speedY += 0.07;
			}
		}

	}

	public void draw(Graphics g) {

		// User
		g.setColor(Color.green);
		g.fillRect(personX, (int) (personY + 30), 20, 30);

		// legs
		g.setColor(Color.blue);
		g.fillRect(personX + 12, (int) (personY + 61), 7, 19);
		g.fillRect(personX + 1, (int) (personY + 61), 7, 19);

		// arms
		g.setColor(Color.blue);
		g.fillRect(personX + 21, (int) (personY + 30), 7, 25);
		g.fillRect(personX - 8, (int) (personY + 30), 7, 25);

	}

	public Rectangle bounds() {

		return (new Rectangle((int) (personX - 10), (int) (personY + 10), wHitBox, hHitBox));

	}

}
