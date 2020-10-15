package engine;

import javax.swing.JPanel;

public class Physics extends JPanel {

	public double fallingFactor;
	boolean gravityActivate = true;
	public boolean forceUp;
	public boolean hitground;
	public double force;
	public boolean setForce = true;
	public double y;
	public double x;
	public double speedObject = 0;
	int random;
	public boolean forceHorizontal;

	public double DOWNWARD_FORCE = 1;

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

	public int randomInt(int a, int b) {

		random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;

	}

	public double applyForceVertical(double ForceReplace) {
		if (forceUp) {
			if (setForce) {
				fallingFactor = 0;
				force = -ForceReplace;
				setForce = false;
			}

			y += force;
			force += DOWNWARD_FORCE;
			gravityActivate = false;
		}

		if (force > 0) {
			forceUp = false;
			gravityActivate = true;
		}

		return y;
	}

	public double applyForceHorizontal(double speed) {
		if (forceHorizontal) {

			if(speedObject < speed) {
				speedObject += 0.05;
			}
			if(speedObject > speed) {
				speedObject -= 0.05;
			}
		
			x += speedObject;
			
		} else {
			if ((int) speedObject != 0) {

				x += speedObject;

				if (speed > 0) {
					speedObject -= 0.1;
				}
				if (speed < 0) {
					speedObject += 0.1;
				}
			}
		}
		return x;
	}

}
