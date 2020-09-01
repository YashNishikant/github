package structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class cityBounds {

	int buildingPos;
	public int speed;
	
	public cityBounds(int x) {
		buildingPos = x;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(buildingPos, 30, 100, 1000);
	}
	
	public void move() {
		buildingPos += speed;
	}
	
	public Rectangle bounds() {
		return (new Rectangle(buildingPos, 30, 100, 1000));
	}
	
}
