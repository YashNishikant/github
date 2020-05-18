package battleGame;

public class target {

	int targetx = 1900;
	int targety = (int) (Math.random() * 800);

	public target(int x, int y) {
		targetx = x;
		targety = y;
	}
	
	public void move() {	
		targetx -= 3;	
	}
	
}
