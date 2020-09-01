package weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Grenade {

	public int grenadeX = 0;
	public double grenadeY = 0;
	public int yoffset = 24;
	public int grenadeSpeed = 2;
	public int grenadeSpeedAdditional = 2;
	public int width = 10;
	public int height = 5;
	public int explosiveDamage = 100;
	public double grenadeFallingSpeed;
	public double setBackExplosionINC = -4;
	public double setBackExplosionDEC = -1;
	public double increaseExplosion = 5;

	public boolean explode;
	public boolean lock = true;
	public boolean bulletFire;
	public boolean letdestroy;
	public boolean heavyDestruction;
	public boolean explosionOver;
	public boolean drawExplosiveForRobotDestruction = true;

	public int explosionWidthHeight = 50;
	public int explodeHeight = 1105;

	public Grenade(int x, double y) {
		grenadeX = x;
		grenadeY = y;
	}

	public void fire() {

		if (bulletFire && heavyDestruction && !letdestroy) {
			grenadeY += grenadeFallingSpeed;
			grenadeFallingSpeed += 0.05;

			if (grenadeY >= 950) {
				grenadeY = -900;
				explode = true;
				letdestroy = true;
			}
		}
	}

	public void destruction(Graphics g) {

		if (explode && lock) {

			g.setColor(Color.BLUE);
			g.fillOval(grenadeX - 50, explodeHeight, explosionWidthHeight, explosionWidthHeight);

			explosionWidthHeight += increaseExplosion;
			explodeHeight -= increaseExplosion;
			grenadeX += setBackExplosionINC;

			if (explosionWidthHeight >= 450) {
				explode = false;
				lock = false;
			}
		}

		if (explosionWidthHeight >= 0 && !explode && !lock) {
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX - 50, explodeHeight, explosionWidthHeight, explosionWidthHeight);

			if (explosionWidthHeight > 0) {
				explosionWidthHeight -= increaseExplosion;
				explodeHeight += increaseExplosion;
				grenadeX += setBackExplosionDEC;

			} else {
				explosionOver = true;
			}
		}
	}

	public void move() {
		grenadeX = grenadeX + grenadeSpeed + grenadeSpeedAdditional;
	}
	
	public void destroy() {

		if (letdestroy && explosionOver) {

			grenadeSpeed = 0;
			grenadeSpeedAdditional = 0;
			grenadeFallingSpeed = 0;
			grenadeX = 9000000;
			grenadeY = -400000;
		}
	}

	public Rectangle boundsExplosive() {
		if (explode) {
			return (new Rectangle(grenadeX - 50, explodeHeight + 80, explosionWidthHeight, explosionWidthHeight));
		} else {
			return (new Rectangle(grenadeX, (int) grenadeY, 10, 10));
		}
	}

}
