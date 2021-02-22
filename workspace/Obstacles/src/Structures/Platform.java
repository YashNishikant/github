package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Platform extends engine {

	public double width;
	public double height;
	private boolean changeTexture;
	private boolean solid = true;
	private boolean stopCollisionTop;
	private boolean stopCollisionLeft;
	private boolean stopCollisionRight;
	private boolean renderTop = false;
	private boolean renderLeft = false;
	private boolean renderRight = false;

	public Platform(double x, double y) {
		width = 80;
		height = 80;

		this.x = x;
		this.y = y;

	}

	public void showColliders(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) (width), (int) (height));
		if (renderTop) {
			g.fillRect((int) x, (int) y - 5, (int) (width), (int) (10));
		}
		if (renderLeft) {
			g.fillRect((int) x - 5, (int) y, (int) (10), (int) (height));
		}
		if (renderRight) {
			g.fillRect((int) (x + width-5), (int) y, (int) (10), (int) (height));
		}
		g.fillRect((int) x, (int) (y + height), (int) (width), (int) (5));
	}
	
	public void draw(Graphics g) {

		addImage(g, "Platform.png", x, (int) y);

		if (!changeTexture) {
			addImage(g, "Platform.png", x, (int) y);
		} else {
			addImage(g, "PlatformDirt.png", x, (int) y);
		}
	}

	public void setDirtTexture(boolean x) {
		changeTexture = x;
	}

	public boolean getSolid() {
		return solid;
	}

	public void stopCollisionTop(boolean x) {
		stopCollisionTop = x;
	}
	public void stopCollisionRight(boolean x) {
		stopCollisionRight = x;
	}
	public void stopCollisionLeft(boolean x) {
		stopCollisionLeft = x;
	}
	
	public boolean getCRight() {
		return stopCollisionTop;
	}
	public boolean getCLeft() {
		return stopCollisionRight;
	}
	public boolean getCTop() {
		return stopCollisionLeft;
	}
	
	
	public Rectangle bounds1() {
		if (!stopCollisionTop) {
			renderTop = true;
			return (new Rectangle((int) x + 10, (int) y - 1, (int) (width) - 20, (int) (height / 30)));
		} else {
			renderTop = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds2() {
		if (!stopCollisionLeft) {
			renderLeft = true;
			return (new Rectangle((int) x - 1, (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			renderLeft = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds3() {
		if (!stopCollisionRight) {
			renderRight = true;
			return (new Rectangle((int) (x + width), (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			renderRight = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds4() {
		return (new Rectangle((int) x + 10, (int) (y + height), (int) (width) - 20, (int) (height / 30)));
	}
}
