package battleGame;

import java.awt.Rectangle;

public class target {

	int targetx = 1900;
	int targety = (int) (Math.random() * 800);
	int speed = 2;
	boolean letDestroy = false;

	public target(int x, int y) {
		targetx = x;
		targety = y;
	}

	public void move() {
		targetx -= speed;
	}

	public void destroy() {

		if (letDestroy) {
			targetx = 2900;
			speed = 0;
		}

	}

	public Rectangle bounds() {

		return (new Rectangle(targetx, targety, 30, 30));

	}
}
