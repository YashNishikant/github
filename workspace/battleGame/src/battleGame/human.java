package battleGame;

public class human {

	int speedY = 0;
	int personX = 410;
	int personY = 870;
	int maxheight = 770;
	int minheight = 870;

	boolean jump = false;
	boolean rise = true;
	boolean insideSuit = false;
	boolean nobattery = false;
	
	public human() {

		speedY = 0;
		personX = 810;
		personY = 870;
		maxheight = 770;
		minheight = 870;

	}

	public void move() {

		if (personY >= 871) {
			speedY = 0;
			personY = 870;
		}
		personY += speedY;
	}

	public void shutdown() {
	
		if(insideSuit && nobattery) {
		
			if (personY >= 869) {
				speedY = 0;
			}
			else {
				speedY = 3;
			}
		}
	}
	public void jump() {

		if (jump == true) {

			if (personY >= maxheight && rise == true) {
				personY -= 2;
			}

			if (personY == maxheight) {
				rise = false;
			}

			if (personY >= maxheight && rise == false) {
				rise = false;
				personY += 2;

				if (personY >= minheight) {
					rise = true;
					jump = false;

				}

			}

		}
	}
}
