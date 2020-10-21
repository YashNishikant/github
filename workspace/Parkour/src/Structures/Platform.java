package Structures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Platform extends engine{

	public double y;
	public double width;
	public double height;
	
	public Platform() {
		width = 500;
		height = 100;
		
		x = 500;
		y = 800;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, (int)(width), (int)(height));
		
	}
	
	public Rectangle boundsTop() {
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height/20)));
	}
	public Rectangle boundsLeft() {
		return(new Rectangle((int)x, (int)y, (int)(width/20), (int)(height)));
	}
	public Rectangle boundsRight() {
		return(new Rectangle((int)(x+width), (int)y, (int)(width/20), (int)(height)));
	}
	
}
