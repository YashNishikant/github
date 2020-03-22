import java.awt.Color;
import java.awt.Rectangle;

public class ball {

	int xCoordBall = 900;
	int yCoordBall = 480;

	int randomStartup_down = (int)(Math.random() *2 ) + 1;
	int randomStartleft_right = (int)(Math.random() *2 ) + 1;
	
	boolean down = false;
	boolean right = false;
	
	int speedBall = 1;
	
	public ball() {
		
		xCoordBall = 900;
		yCoordBall = 480;	
		
	}
	
	public void starting() {
			
		//randomStart
		if(randomStartup_down == 1) {		
			down = true;
			randomStartup_down = 0;
		}	
		if(randomStartup_down == 2) {		
			down = false;	
			randomStartup_down = 0;
		}
		//left or right	
		if(randomStartleft_right == 1) {		
			right = true;
			randomStartup_down = 0;
		}	
		if(randomStartleft_right == 2) {		
			right = false;
			randomStartup_down = 0;
		}	
		
	}
	
	public void move() {
		
			// down left going down right (SIDEWALL)
			if (xCoordBall <= -14) {

				xCoordBall -= speedBall;
				yCoordBall += speedBall;
				
			}
	//_____________________________________________			
			// down right going up right (FLOOR)
			if (yCoordBall >= 910 && right == true && down == true) {
				down = false;
				right = true;
			}

			// down left going up left (FLOOR)
			if (yCoordBall >= 910 && right == false && down == true) {
				down = false;
				right = false;
			}
	//_____________________________________________		
			// (SIDEWALL)
			if (xCoordBall >= 1890) {

				xCoordBall -= speedBall;
				yCoordBall += speedBall;

			}

	//_____________________________________________
			// up right going down right (TOP)
			if (yCoordBall <= 20 && right == true && down == false) {
				down = true;
				right = true;
			}

			// up left going down left (TOP)
			if (yCoordBall <= 20 && right == false && down == false) {
				down = true;
				right = false;
			}
	//_____________________________________________	

			if (down == true) {
				yCoordBall += speedBall;
			} else {
				yCoordBall -= speedBall;
			}

			if (right == true) {
				xCoordBall += speedBall;
			} else {
				xCoordBall -= speedBall;
			}
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(xCoordBall, yCoordBall, 20, 20));
		
	}
	
}
