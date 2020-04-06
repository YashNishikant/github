package atari_breakout;

import java.awt.Rectangle;

public class ballBreakout {

	boolean right = true;
	boolean down = false;
	boolean rightangled = false;
	boolean leftangled = false;
	
	int ballX = 500;
	int ballY = 900;

	int ballSpeedX = 4;
	int ballSpeedY = 4;
	
	public void ballBreakout() {

		right = true;
		down = false;

		ballX = 500;
		ballY = 900;

		ballSpeedX = 4;
		ballSpeedY = 4;
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
			ballSpeedY = 0;
			ballSpeedX = 0;
		}

		if (down == true) {			
			ballY += ballSpeedY;
		} else {
			ballY -= ballSpeedY;
		}

		if (right == true) {
			
			ballX += ballSpeedX;
		} else {
			ballX -= ballSpeedX;
		}

		//angled
		if (rightangled == true) {
			ballSpeedY = 2;
			
			ballX += ballSpeedX;
			ballY -= ballSpeedY;
		
			rightangled = false;
			
		} 
		
		//angled
		if (leftangled == true) {			
			ballSpeedY = 2;
			
			ballX -= ballSpeedX;
			ballY -= ballSpeedY;
			
			leftangled = false;
		} 
		
	}

	public Rectangle bounds() {

		return (new Rectangle(ballX, ballY, 30, 30));

	}

}
