package Structures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Platform extends engine{

	public double y;
	public double width;
	public double height;
	
	public Platform(double x1, double y1, double spacing) {
		x = 550 + spacing;
		y = (int)(Math.random()*101)+800;
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, (int)(width), (int)(height));
		
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, (int)(width), (int)(height/20));
		
		g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int)y+10, (int)(width/100), (int)(height));
		
		g.setColor(Color.ORANGE);
		g.fillRect((int)(x + width-5), (int)y+10, (int)(width/100), (int)(height));
		
		
	}
	
	public void keepSize(double x, double y) {
		width = x;
		height = y;
		
	}
	
	public Rectangle boundsTop() {
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height/20)));
	}
	
	public Rectangle boundsLeft() {
		
		return(new Rectangle((int)x, (int)y+10, (int)(width/100), (int)(height)));
		
	}
	public Rectangle boundsRight() {
		
		return(new Rectangle((int)(x + width-5), (int)y+10, (int)(width/100), (int)(height)));
		
	}
	
}
