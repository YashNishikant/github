package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class NPC {

	int npcY = 870;
	int npcX = 600;
	int npcHealth = 100;
	int speed = 0;
	int wHitBox = 38;
	int hHitBox = 70;
	int healthbarbox = npcX - 1;
	int healthnpc = npcX;
	int healthcount = 40;
	int speedY = 0;
	int speedaddition = 0;
	int turnaround = 0;
	
	boolean knockback = false;
	boolean alive = true;

	public NPC(int x, int speed1) {

		npcY = 870;
		npcX = x;
		npcHealth = 100;
		speed = 0;
		wHitBox = 38;
		hHitBox = 70;
		healthbarbox = npcX - 1;
		healthnpc = npcX;
		healthcount = 40;

		speedaddition = speed1;

	}

	public void npcBehavior() {

		turnaround = (int)(Math.random() * 105) + 1;
		
		if (knockback) {
			npcX += 1;
			healthbarbox += 1;
			healthnpc += 1;
		}

		if (healthcount == 0) {
			alive = false;
		} else {
			alive = true;
		}

		if (alive) {

			if(turnaround == 50) {
				speedaddition = -1;
			}
		
			if(turnaround == 60) {
				speedaddition = 1;
			}
			
		}
		else {
			speedaddition = 0;
		}
	}

	public void drawNPC(Graphics g) {
		if (alive) {
			// healthbar
			g.setColor(Color.black);
			g.fillRect(healthbarbox - 10, npcY + 1, 42, 6);
			g.setColor(Color.green);
			g.fillRect(healthnpc - 10, npcY + 3, healthcount, 2);

			// User
			g.setColor(Color.GREEN);
			g.fillRect(npcX, npcY + 30, 20, 30);

			// legs
			g.setColor(Color.blue);
			g.fillRect(npcX + 12, npcY + 61, 7, 19);
			g.fillRect(npcX + 1, npcY + 61, 7, 19);

			// arms
			g.setColor(Color.blue);
			g.fillRect(npcX + 21, npcY + 30, 7, 25);
			g.fillRect(npcX - 8, npcY + 30, 7, 25);
		}
	}

	public void move() {
		npcX = npcX + speed + speedaddition;
		npcY = npcY + speedY;
		healthbarbox = healthbarbox + speed + speedaddition;
		healthnpc = healthnpc + speed + speedaddition;
	}

	public Rectangle bounds() {

		return (new Rectangle(npcX - 9, npcY + 10, wHitBox, hHitBox));

	}

}
