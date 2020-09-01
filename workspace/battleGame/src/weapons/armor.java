package weapons;

import java.awt.Rectangle;

public class armor {

	public boolean fire = false;
	public boolean track = false;
	public boolean canfly = false;
	public boolean ignore = false;
	public boolean blast = false;
	public boolean normal = false;
	public boolean activatefire = true;
	public boolean fireonground = false;
	public boolean confirmgroundfire = false;
	public boolean flyIMG = false;
	public boolean turbo = false;
	public boolean ableToTurbo = false;
	public boolean flyIMG_LEFT = false;
	public boolean turbo_LEFT = false;
	public boolean ableToTurbo_LEFT = false;
	public boolean driftleft;
	public boolean driftright;
	public boolean crafted;

	public int armorPosX;
	public int armorPosY;
	public int armorspeed;
	public int wHitBox;
	public int hHitBox;

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

	public void uncrafted() {
		if (!crafted) {
			armorPosX = 0;
			armorPosY = -100;
		}
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
			if (!turbo && !turbo_LEFT && !flyIMG && !flyIMG_LEFT)
				fire = true;
			canfly = true;
		}
	}

	public void move() {
		armorPosX = armorPosX + armorspeed;
	}

	public Rectangle bounds() {

		return (new Rectangle(armorPosX + 4, armorPosY, wHitBox, hHitBox));

	}

}