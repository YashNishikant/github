package battleGame;


import java.awt.Rectangle;

public class armor {

	boolean fire = false;
	boolean track = false;
	boolean canfly = false;
	boolean ignore = false;
	boolean blast = false;
	boolean normal = true;
	boolean activatefire = true;
	boolean fireonground = false;
	boolean confirmgroundfire = false;
	boolean flyIMG = false;
	boolean turbo = false;
	boolean ableToTurbo = false;
	boolean flyIMG_LEFT = false;
	boolean turbo_LEFT = false;
	boolean ableToTurbo_LEFT = false;
	
	boolean driftleft;
	boolean driftright;
	
	int armorPosX;
	int armorPosY;
	int armorspeed;
	int wHitBox;
	int hHitBox;
	
	public armor() {

		fire = false;
		track = false;
		canfly = false;
		ignore = false;
		
		wHitBox = 38;
		hHitBox = 75;
		
		armorPosX = 10;
		armorPosY = 877;

		armorspeed = 0;
		flyIMG = false;
		turbo = false;
		ableToTurbo = false;
		
	}

	public void tracking() {
		
		if (armorPosY >= 870 && fireonground == false) {
			fire = false;
			activatefire = true;
			canfly = false;
			normal = true;
			blast = false;
			confirmgroundfire = false;
		}
		
		if (armorPosY >= 870 && fireonground) {
			confirmgroundfire = true;
		}

		if (armorPosY < 865 && activatefire) {
			if(!turbo && !turbo_LEFT && !flyIMG && !flyIMG_LEFT)
				fire = true;
				canfly = true;
		}
	}

	public void move() {
		armorPosX = armorPosX + armorspeed;
	}	
	
	public Rectangle bounds() {

		return (new Rectangle(armorPosX+4, armorPosY, wHitBox, hHitBox));

	}
	
}