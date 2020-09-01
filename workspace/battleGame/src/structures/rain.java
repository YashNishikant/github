package structures;

import java.awt.Color;
import java.awt.Graphics;

public class rain {

	public int raindropX;
	public double raindropY;
	double rainSpeed;
	int rainSpeedX;

	public boolean beginRain = false;

	public rain() {
		raindropX = (int) (Math.random() * 2000) + -10;
		raindropY = (int) (Math.random() * -1200) + -10;
		rainSpeed = 0;
		rainSpeedX = 0;
	}

	public void rainFall() {
		if (beginRain) {
			if (raindropY <= 940) {
				raindropY = raindropY + rainSpeed;
				if (rainSpeed < 9) {
					rainSpeed += 0.09;
				}
			} else {
				raindropY = (int) (Math.random() * -1200) + 0;
			}

			raindropX = raindropX + rainSpeedX;
		} else {

		}
	}

	public void draw(Graphics g) {
		if (beginRain) {
			if (raindropY <= 940) {
				g.setColor(Color.BLUE);
				g.fillRect(raindropX, (int) raindropY, 5, 10);
			}
		} else {
		}
	}
}
