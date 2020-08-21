package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class bullet {

	int bulletX = 0;
	double bulletY = 0;
	double damageForBoss = 0.5;
	int yoffset = 24;
	int bulletSpeed = 10;
	int width = 10;
	int height = 5;
	int damage = 10;
	boolean bulletFire = false;
	boolean letdestroy = false;

	public bullet(int x, double y) {
		bulletX = x;
		bulletY = y;
	}

	public void fire() {
		bulletX = bulletX + bulletSpeed;
	}

	public Rectangle bounds() {
		return (new Rectangle(bulletX, (int) bulletY + yoffset, width, height));
	}

	public void destroy() {

		if (letdestroy) {
			bulletSpeed = 0;
			bulletX = 9000;
			bulletY = -40;
		}
	}

}
