package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Grenade {
	
	int grenadeX = 0;
	double grenadeY = 0;
	int yoffset = 24;
	int grenadeSpeed = 2;
	int grenadeSpeedForPlayer;
	int width = 10;
	int height = 5;
	int explosiveDamage = 100;
	double grenadeFallingSpeed;
	int explodeX;
	
	double setBackExplosion = 5;
	double increaseExplosion = 5;
	
	boolean explode;
	boolean lock = true;
	boolean bulletFire = false;
	boolean letdestroy = false;
	boolean heavyDestruction;
	
	int explosionWidthHeight = 50;
	int explodeHeight = 1105;
	
	public Grenade(int x, double y) {
		grenadeX = x;
		grenadeY = y;
	}
	
	public void fire() {
		
		if (bulletFire && heavyDestruction && !letdestroy) {

			explodeX = grenadeX;

			grenadeX = grenadeX + grenadeSpeed + grenadeSpeedForPlayer;
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
			g.fillOval(grenadeX, explodeHeight, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.WHITE);
			g.fillOval(grenadeX, explodeHeight + 20, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX, explodeHeight + 40, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.WHITE);
			g.fillOval(grenadeX, explodeHeight + 60, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX, explodeHeight + 80, explosionWidthHeight, explosionWidthHeight);
			
			explosionWidthHeight+= increaseExplosion;
			explodeHeight -= increaseExplosion;
			grenadeX -= setBackExplosion;
			
			if(explosionWidthHeight >= 450) {
				explode = false;
				lock = false;
			}
		}

		
		grenadeX = (int)(grenadeX+ grenadeSpeed + grenadeSpeedForPlayer);
		
		if(explosionWidthHeight >= 0 && !explode && !lock) {

			
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX, explodeHeight, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.WHITE);
			g.fillOval(grenadeX, explodeHeight + 20, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX, explodeHeight + 40, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.WHITE);
			g.fillOval(grenadeX, explodeHeight + 60, explosionWidthHeight, explosionWidthHeight);
			
			g.setColor(Color.BLUE);
			g.fillOval(grenadeX, explodeHeight + 80, explosionWidthHeight, explosionWidthHeight);
			
			if(explosionWidthHeight > 0) {
			explosionWidthHeight -= increaseExplosion;
			explodeHeight += increaseExplosion;
			//grenadeX += setBackExplosion;
			
			}		
		}	
	}
	
	public void destroy() {

		if (letdestroy) {
			grenadeSpeed = 0;
			grenadeFallingSpeed = 0;
			grenadeX = 9000;
			grenadeY = -40;
		}
	}
	
	public Rectangle boundsExplosive() {
		if (explode) {
			return (new Rectangle(grenadeX, explodeHeight, explosionWidthHeight, explosionWidthHeight));
		}
		else{
			return(new Rectangle(0,0,0,0));
		}
	}
	
}
