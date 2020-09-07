package Environment;

import java.awt.Graphics;

public class Ground extends land {
	
	public double floorSpeed;
	public int YGround;
	
	public Ground(int x1) {
		super(x1);
		XGround = x1;
		YGround = 465;
		floorSpeed = 0;
	}

	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Ground.png", (int) XGround, YGround);
		XGround += floorSpeed;
	}
}
