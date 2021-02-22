package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Player extends engine{

	public double x;
	
	public double width;
	public double height;
	public double speedY;
	public double speed = 10;
	
	public int yCoord = 400;
	
	public Player(double x1, double y1) {
		x = 700;
		y = yCoord;
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, (int)(width), (int)(height));
	}
	
	
	public Rectangle bounds() {
	
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
		
	}
}
