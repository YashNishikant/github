package weapons;

import java.awt.Color;
import java.awt.Graphics;

public class RifleBullets extends bullet{
	
	double bulletTrail;
	
	public RifleBullets(double x, double y) {
		super(x, y);
		bulletSpeed = 40;
		damage = 15;
		bulletTrail = 5;
	}

	public void drawBullet(Graphics g) {
		if (bulletFire) {
			addImage(g, "//Bullet//riflebullet.png", bulletX, (int) bulletY + yoffset);
		
			g.setColor(Color.YELLOW);
			g.fillRect((int)bulletX + 1, (int) bulletY + 29, (int) bulletTrail, 2);
			if(bulletTrail > -540) {
				bulletTrail-=30;
			}
		}	
	}
	
	public void trackbullet(double x) {
		
		if(bulletX > x + 2000) {
	
			bulletX = -200;
			bulletY = -500;
			bulletTrail = 0;
			bulletSpeed = 0;
			
		}
	}
	
}
