package battleGame;

public class armor {

	boolean fire = false;
	boolean track = false;
	boolean canfly = false;
	boolean ignore = false;
	boolean blast = false;
	boolean normal = true;
	boolean activatefire = true;
	
	int armorPosX = 10;
	int armorPosY = 875;

	public armor() {

		fire = false;
		track = false;
		canfly = false;
		ignore = false;

		armorPosX = 10;
		armorPosY = 877;

	}

	public void tracking() {
		
		if (armorPosY >= 870) {
			fire = false;
			normal = true;
			blast = false;
			activatefire = true;
			canfly = false;
		}
		
		if (armorPosY < 865 && activatefire) {
			fire = true;
			canfly = true;
		}
	}
}
