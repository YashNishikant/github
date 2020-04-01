package atari_breakout;

import java.awt.Rectangle;

public class ballBreakout {

	boolean right = true;
	boolean down = false;

	int ballX = 0;
	int ballY = 0;

	int ballSpeed = 4;

	public void ballBreakout() {

		right = true;
		down = false;

		ballX = 600;
		ballY = 800;

		ballSpeed = 3;

	}

	public void move() {
		// top
		if (ballY <= 0) {
			down = true;
		}
		// leftside
		if (ballX <= 0) {
			right = true;
		}
		// rightside
		if (ballX >= 1880) {
			right = false;
		}
		// bottom
		if (ballY >= 1000) {
			ballSpeed = 0;
		}

		if (down == true) {
			ballY += ballSpeed;
		} else {
			ballY -= ballSpeed;
		}

		if (right == true) {
			ballX += ballSpeed;
		} else {
			ballX -= ballSpeed;
		}

	}

	public Rectangle bounds() {

		return (new Rectangle(ballX, ballY, 30, 30));

	}

}
