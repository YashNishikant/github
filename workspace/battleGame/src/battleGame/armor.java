package battleGame;

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
	
	int armorPosX = 10;
	int armorPosY = 877;
	int armorspeed = 0;
	
	public armor() {

		fire = false;
		track = false;
		canfly = false;
		ignore = false;

		armorPosX = 10;
		armorPosY = 877;

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
			fire = true;
			canfly = true;
		}
	}

	public void move() {

		armorPosX = armorPosX + armorspeed;
		
	}	
}