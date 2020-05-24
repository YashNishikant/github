package battleGame;

import java.awt.Color;
import java.awt.Graphics;

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

	public void draw(Graphics g) {
		
		// User
		g.setColor(Color.red);
		g.fillRect(personX, personY + 30, 20, 30);

		// legs
		g.setColor(Color.blue);
		g.fillRect(personX + 12, personY + 61, 7, 19);
		g.fillRect(personX + 1, personY + 61, 7, 19);

		// arms
		g.setColor(Color.blue);
		g.fillRect(personX + 21, personY + 30, 7, 25);
		g.fillRect(personX - 8, personY + 30, 7, 25);	
		
	}
	
}
