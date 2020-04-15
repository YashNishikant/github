package atari_breakout;

import java.awt.Rectangle;

public class block {

	boolean hitBlock = false;
	int xBlock;
	int yBlock;
	
	public block(int x, int y) {
		xBlock = x;
		yBlock = y;
		
		hitBlock = false;
	}
	
	public void disappear()
	{
		xBlock = -100;
	}
	
	public void hitdetect() {
		
	if(hitBlock) {
		
		xBlock = -100;	
		
	}	
		
	}
	
	//borders for squares
	public Rectangle bounds() {

		return (new Rectangle(xBlock, yBlock, 60, 60));

	}

	
}