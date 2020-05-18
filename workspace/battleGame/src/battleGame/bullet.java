package battleGame;

public class bullet {

	int bulletX = 0;
	int bulletY = 0;
	int bulletSpeed = 6;
	boolean bulletFire = false;

	public bullet(int x, int y) {

		bulletX = x;
		bulletY = y;
	}

	public void fire() {
		if (bulletFire) {
			bulletX = bulletX + bulletSpeed;
		}
	}
}
