package battleGame;

public class buildings {
	
	int movingsurrounding1 = 100;
	int bY = 250;
	int bW = 200;
	int bH = 700;
	boolean enter;
	double speed = 0;
	
	public buildings(int x) {	
		movingsurrounding1 = x;
	}

	public void move() {
		movingsurrounding1 = (int)(movingsurrounding1 + speed);
	}
}