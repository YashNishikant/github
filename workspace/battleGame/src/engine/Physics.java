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
	
	public double GRAVITY = 1;
	
	public Physics() {
		fallingFactor = 0;
		GRAVITY /= 10;
	}

	public double gravity() {
		if (gravityActivate) {
			y += fallingFactor;
			fallingFactor += GRAVITY;
		}
		return y;
	}

	public double applyForceVertical(double ForceReplace) {
		if (forceUp) {
			if(setForce) {
				force = -ForceReplace;
				setForce = false;
			}
			
			y += force;
			force += GRAVITY;
			gravityActivate = false;
		}
		
		if(force > 0) {
			forceUp = false;
			gravityActivate = true;
		}
		
		return y;
	}
}
