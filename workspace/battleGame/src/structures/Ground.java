package structures;

import java.awt.Graphics;

public class Ground extends land {
	
	public int floorSpeed;
	
	public Ground(int x1) {
		super(x1);
		XGround = x1;
		floorSpeed = 0;
	}

	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Ground.png", XGround, 417);
		XGround += floorSpeed;
	}
}
