package atari_breakout;

import java.awt.Rectangle;

public class block2 {

	boolean hitBlock = false;
	int xBlock = 500;
	int yBlock = 200;
	
	public block2() {
		
		xBlock = 500;
		yBlock = 200;
		hitBlock = false;
	}
	
	
	//borders for squares
	public Rectangle bounds1() {

		return (new Rectangle(397, 200, 1, 60));

	}
	public Rectangle bounds2() {

		return (new Rectangle(462, 200, 1, 60));

	}
	public Rectangle bounds3() {

		return (new Rectangle(400, 197, 60, 1));

	}
	public Rectangle bounds4() {

		return (new Rectangle(400, 262, 60, 1));

	}
	
}