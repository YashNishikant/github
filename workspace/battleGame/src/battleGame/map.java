package battleGame;

import java.awt.Color;
import java.awt.Graphics;

public class map {
	
	int movingsurrounding1 = 100;
	int bY = 250;
	int bW = 200;
	int bH = 700;
	int speed = 0;
	
	public map() {	
		movingsurrounding1 = 100;	
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(movingsurrounding1, bY, bW, bH);
		
		g.setColor(Color.RED);
		g.fillRect(movingsurrounding1 + 900, bY+300, bW, bH);

	}

	public void move() {
		movingsurrounding1 = movingsurrounding1 + speed;
		
	}
	
}
