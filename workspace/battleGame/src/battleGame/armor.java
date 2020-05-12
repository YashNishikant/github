package battleGame;

public class armor {
	
	boolean fire = false;
	boolean track = false;
	boolean canfly = false;
	boolean ignore = false;
	
	int armorPosX = 10;
	int armorPosY = 875;
	
	public armor() {
		
		fire = false;
		track = false;
		canfly = false;
		ignore = false;
		
		armorPosX = 10;
		armorPosY = 875;	
	
	}
	
	public void tracking() {
		
		if(armorPosY < 865) {	
			fire = true;	
		}
		else {
		fire = false;	
		
		}
	}
}
