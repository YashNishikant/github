package structures;

public class clouds {

	public int cloud1start = -50;
	public int cloud2start = -800;
	public int cloud3start = -1000;
	public int cloud4start = -1200;
	public int cloud1Y, cloud2Y, cloud3Y, cloud4Y;
	
	public clouds() {

		cloud1start = -50;
		cloud2start = -800;
		cloud3start = -1600;
		cloud4start = -2100;
		
		cloud1Y = 60;
		cloud2Y = 120;
		cloud3Y = 80;
		cloud4Y = 150;

	}

	public void move() {

		cloud1start += 2;
		cloud2start += 2;
		cloud3start += 2;
		cloud4start += 2;

		if (cloud1start == 2500) {
			cloud1start = -100;
		}

		if (cloud2start == 2500) {
			cloud2start = -100;
		}

		if (cloud3start == 2500) {
			cloud3start = -100;
		}

		if (cloud4start == 2500) {
			cloud4start = -100;
		}
		
	}	
}
