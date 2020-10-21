package freeworld;

import java.awt.Color;
import java.awt.Graphics;

public class Miscellaneous {

	int width;
	int height;
	
	public Miscellaneous() {
		width = 2000;
		height = 2000;
	}
	
	public void drawBackground(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
	}
	
}
