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

	int healthbarbox;
	int health;
	int healthcount;

	boolean jump = false;
	boolean rise = true;
	boolean insideSuit = false;
	boolean nobattery = false;
	boolean onground = false;
	boolean hitgrass = false;
	boolean onetimeJump = true;
	boolean jumping = false;
	boolean death = false;

	public human() {

		speedY = 0;
		personX = 810;
		personY = 870;
		maxheight = personY - 100;
		healthcount = 40;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
		hitgrass = false;
	}

	public void move() {
		if (!death) {
			if (personY >= 871) {
				speedY = 0;
				personY = 870;
			}

			personY = personY + speedY;
		} else {
			if (personY <= 915)
				speedY += 3;
		}
	}

	public void shutdown() {

		if (insideSuit && nobattery) {

			if (personY >= 869) {
				speedY = 0;
			} else {
				speedY += 0.05;
			}
		}
	}

	public void jump() {

		if (jump && !death) {
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

	public void drawHealth(Graphics g, int xFrame, int xRed) {
		if (!death) {
			// healthbar
			g.setColor(Color.black);
			g.fillRect(xFrame - 5, (int) (personY + 1), 42, 6);
			g.setColor(Color.red);
			g.fillRect(xRed - 4, (int) (personY + 3), healthcount, 2);
		}
	}

	public Rectangle bounds() {

		return (new Rectangle((int) (personX - 10), (int) (personY + 10), wHitBox, hHitBox));

	}

}
