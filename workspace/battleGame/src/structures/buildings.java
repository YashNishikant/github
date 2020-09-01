package structures;

import java.awt.Rectangle;

public class buildings {
	
	public int bX;
	public int bY = 250;
	int bW = 200;
	int bH = 700;
	public boolean enter;
	public double speed = 0;
	
	public buildings(int x) {	
		bX = x;
	}

	public void move() {
		bX = (int)(bX + speed);
	}

	public Rectangle bounds() {
		return (new Rectangle(bX, bY, bW, bH));
	}
	
}