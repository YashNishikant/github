package battleGame;

public class human {

	int speedX = 0;
	int speedY = 0;
	int personX = 210;
	int personY = 870;
	int maxheight = 770;
	int minheight = 870;

	boolean jump = false;
	boolean rise = true;

	public human() {

		speedX = 0;
		speedY = 0;
		personX = 210;
		personY = 870;
		maxheight = 770;
		minheight = 870;

	}

	public void move() {

		if (personY >= 871) {
			speedY = 0;
			personY = 870;
		}
		personX += speedX;
		personY += speedY;
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
