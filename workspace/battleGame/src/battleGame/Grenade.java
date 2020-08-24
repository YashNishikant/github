package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Grenade {

	int grenadeX = 0;
	double grenadeY = 0;
	int yoffset = 24;
	int grenadeSpeed = 2;
	int grenadeSpeedAdditional = 2;
	int width = 10;
	int height = 5;
	int explosiveDamage = 100;
	double grenadeFallingSpeed;
	double setBackExplosionINC = -4;
	double setBackExplosionDEC = -1;
	double increaseExplosion = 5;

	boolean explode;
	boolean lock = true;
	boolean bulletFire;
	boolean letdestroy;
	boolean heavyDestruction;
	boolean explosionOver;
	boolean drawExplosiveForRobotDestruction = true;

	int explosionWidthHeight = 50;
	int explodeHeight = 1105;

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
