package battleGame;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class buildings {
	
	int movingsurrounding1 = 100;
	int bY = 250;
	int bW = 200;
	int bH = 700;
	double speed = 0;
	
	int clear;
	
	public buildings(int x) {	
		movingsurrounding1 = x;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(movingsurrounding1, bY, bW, bH);
	}

	public void move() {
		movingsurrounding1 = (int)(movingsurrounding1 + speed);
	}
	
}