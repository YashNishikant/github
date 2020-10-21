package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Player extends engine{

	public double x;
	
	public double width;
	public double height;
	public double speedx = 0;
	public double speedX = 4;
	public double speedy = 0;
	public double speedY = 4;
	
	public Player(double x1, double y1) {
		x = 900;
		y = 500;
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x, (int)y, (int)(width), (int)(height));
	}
	
	
	public Rectangle bounds() {
	
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
		
	}
}
