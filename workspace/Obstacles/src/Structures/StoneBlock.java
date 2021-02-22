package Structures;
import java.awt.Graphics;

public class StoneBlock extends Platform{

	public double width;
	public double height;
	
	public StoneBlock(double x, double y) {
		super(x, y);
	}

	public void draw(Graphics g) {	

		addImage(g, "StoneBrick.png", x, (int) y);
	}
	
}
