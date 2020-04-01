package atari_breakout;

import java.awt.Rectangle;

public class paddleClass {

	int paddleX;
	int speedPaddle;
	
	public paddleClass(){
		
	paddleX = 900;
	speedPaddle = 0;	
		
	}
	
	public void move() {
		
		if(paddleX < 0) {
			
			paddleX = 0;	
			
		}
		
		if(paddleX > 1570) {
			
			paddleX = 1570;	
			
		}
		paddleX = paddleX + speedPaddle;	
		
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(paddleX, 970, 300, 20));
		
	}
	
}
