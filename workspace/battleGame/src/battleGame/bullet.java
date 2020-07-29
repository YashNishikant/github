package battleGame;


import java.awt.Graphics;
import java.awt.Rectangle;

public class bullet {

	int bulletX = 0;
	int bulletY = 0;
	int yoffset = 24;
	int bulletSpeed = 15;
	int width = 10;
	int height = 5;
	int damage = 40;
	boolean bulletFire = false;
	boolean letdestroy = false;

	public bullet(int x, int y) {

		bulletX = x;
		bulletY = y;
	}

	public void fire() {
		if (bulletFire) {
			bulletX = bulletX + bulletSpeed;
		}
	}

	public Rectangle bounds() {

		return (new Rectangle(bulletX, bulletY + yoffset, width, height));

	}

	public void destroy() {

		if (letdestroy) {
			bulletSpeed = 0;
			bulletX = 9000;
			bulletY = -40;
		}
	}

}