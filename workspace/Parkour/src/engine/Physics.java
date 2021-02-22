package engine;

import javax.swing.JPanel;

public class Physics extends JPanel {

	public double fallingFactor;
	boolean gravityActivate = true;
	boolean gravityInverseActivate = true;
	public boolean forceUp;
	public boolean hitground;
	public double force;
	public boolean setForce = true;
	public double y;
	public double x;
	public double speedObject = 0;
	int random;
	public boolean forceHorizontalL;
	public boolean forceHorizontalR;

	public double DOWNWARD_FORCE = 6;

	public Physics() {
		fallingFactor = 0;
		DOWNWARD_FORCE /= 10;
	}

	public double gravity(double gravity) {
		if (gravityActivate) {
			y += fallingFactor;
			fallingFactor += gravity;
		}
		return y;
	}

	public double gravityInverse(double gravity) {
		if (gravityInverseActivate) {
			y -= fallingFactor;
			fallingFactor -= gravity;
		}
		return y;
	}

	public int randomInt(int a, int b) {
		random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;
	}

	public double applyForceVerticalDOWN(double ForceReplace) {

		if (forceUp) {
			if (setForce) {
				fallingFactor = 0;
				force = ForceReplace;
				setForce = false;
			}
			y += force;
			force -= DOWNWARD_FORCE;
			gravityInverseActivate = false;
		}
		if (force <= 0) {
			forceUp = false;
			gravityInverseActivate = true;
		}
		return y;
	}

	public double applyForceHorizontal(double speed) {
		if (forceHorizontalL) {
			speedObject = speed;
			x += speedObject;
		}

		if (forceHorizontalR) {
			speedObject = speed;
			x -= speedObject;
			
		}
		
		return x;
	}
}
