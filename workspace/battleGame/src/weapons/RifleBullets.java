package weapons;

import java.awt.Color;
import java.awt.Graphics;

public class RifleBullets extends bullet{
	
		double bulletTrail;
	
	public RifleBullets(double x, double y) {
		super(x, y);
		bulletSpeed = 25;
		damage = 15;
		bulletTrail = 5;
	}

	public void drawBullet(Graphics g) {
		if (bulletFire) {
			addImage(g, "//Bullet//riflebullet.png", bulletX, (int) bulletY + yoffset);
		
			g.setColor(Color.YELLOW);
			g.fillRect((int)bulletX, (int) bulletY + 30, (int) bulletTrail, 2);
			if(bulletTrail > -240) {
				bulletTrail-=8;
			}
		}	
	}
	
}
