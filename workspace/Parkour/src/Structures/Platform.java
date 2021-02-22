package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Platform extends engine {

	public double width;
	public double height;



	public Platform(int x, int y) {
		width = 400;
		height = 500;

		this.x = 500 + x;
		this.y = y;

	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) (width), (int) (height));

	}

	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, (int) (width), (int) (height)));
	}

}
