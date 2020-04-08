package atari_breakout;

import java.awt.Rectangle;

public class block {

	boolean hitBlock = false;
	int xBlock = 200;
	int yBlock = 200;
	
	public block() {
		
		xBlock = 200;
		yBlock = 200;
		hitBlock = false;
	}

	
	//borders for squares
	public Rectangle bounds1() {

		return (new Rectangle(197, 200, 1, 60));

	}
	public Rectangle bounds2() {

		return (new Rectangle(262, 200, 1, 60));

	}
	public Rectangle bounds3() {

		return (new Rectangle(200, 197, 60, 1));

	}
	public Rectangle bounds4() {

		return (new Rectangle(200, 262, 60, 1));

	}
	
}