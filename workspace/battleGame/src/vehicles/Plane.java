package vehicles;

import java.awt.Graphics;
import java.awt.Rectangle;
import battleGame.Textures;

public class Plane extends Textures {

	public double x;
	public double y;
	public double topSpeed;
	public double ySpeed;
	public double riseSpeed;
	public boolean planePos = true;
	public double illusionspeed;
	public double planeSpeed = 0;
	public boolean canExit;
	public boolean canEnter;
	public boolean enter;
	public boolean decelerate;
	public boolean accelerate;
	public boolean wheels = true;

	public Plane() {

		x = 0;
		y = 755;
		riseSpeed = -90;
		topSpeed = -100;
		ySpeed = 0;

	}

	public void move() {
		if (!enter) {
			x += illusionspeed;
		}

		y += ySpeed;

		if (accelerate) {
			if (planeSpeed > topSpeed) {
				planeSpeed -= 0.1;
			}
			
			if ((int) planeSpeed <= (int) riseSpeed) {
				if (y > 400) {
					ySpeed = -0.1;
				} else {
					ySpeed = 0;
				}
			}
		}
	}

	public void draw(Graphics g) {
		addImage(g, "//Vehicles//plane.png", x, (int) y);

		if (wheels) {
			addImage(g, "//Vehicles//planeWheels.png", x, (int) y);
		}
	}

	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, 450, 200));
	}

}
