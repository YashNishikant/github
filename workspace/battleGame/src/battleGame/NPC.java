package battleGame;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class NPC {

	int npcY;
	int npcX;
	int npcHealth;
	double speed;
	int wHitBox;
	int hHitBox;
	int healthbarbox;
	int healthnpc;
	int healthcount;
	int speedY;
	int speedaddition;
	int turnaround;
	int knockbackstr;
	
	boolean knockback = false;
	boolean alive = true;
	boolean dropLock = true;
	
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
		speedaddition = 0;
		knockbackstr = 6;
			
	}

	public void npcBehavior() {

		turnaround = (int)(Math.random() * 405) + 1;
		
		if (knockback) {
			npcX += knockbackstr;
			healthbarbox += knockbackstr;
			healthnpc += knockbackstr;
		}

		if (healthcount <= 0) {
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
		npcX = (int)(npcX + speed + speedaddition);
		npcY = npcY + speedY;
		healthbarbox = (int)(healthbarbox + speed + speedaddition);
		healthnpc = (int)(healthnpc + speed + speedaddition);
	}

	public Rectangle bounds() {

		return (new Rectangle(npcX - 9, npcY + 10, wHitBox, hHitBox));

	}

}
