package battleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class NPC extends JPanel{

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
	int animationSpeed = 30;
	int animationLimit = 5;
	int animationNumberR = 1;
	int animationDelayR;

	int animationNumberL = 1;
	int animationDelayL;

	boolean animateLeft;
	boolean animateRight;
	
	String assetsPath;
	
	boolean knockback = false;
	boolean alive = true;
	boolean dropLock = true;
	boolean turnleft;
	boolean turnright;
	
	int randomDirection;
	
	public NPC(int x, int speed1) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
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

		turnaround = (int) (Math.random() * 405) + 1;

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

			if (turnaround == 50) {
				speedaddition = -1;
				turnleft = true;
				turnright = false;
			}

			if (turnaround == 60) {
				speedaddition = 1;
				turnright = true;
				turnleft = false;
			}

		} else {
			speedaddition = 0;
		}
	}

	public void drawNPC(Graphics g) {
		
		randomDirection = (int)(Math.random() * 1);
		
		if (alive) {
			
			if(!turnright && !turnleft) {
				if(randomDirection == 1) {
					addImage(g, "PlayerRight.png", npcX, (int)npcY + 12);
				}
				if(randomDirection == 0) {
					addImage(g, "PlayerLeft.png", npcX, (int)npcY + 12);
				}
			}
			
			if(turnright) {
				animateRight = true;
				animateLeft = false;
				
				animationNumberR = animation(g, animationDelayR, animationNumberR, animateRight, "PlayerRight", npcX,
						(int) npcY + 12, animationLimit, animationSpeed);
				animationDelayR++;
			}
			
			if(turnleft) {
				animateRight = false;
				animateLeft = true;
				
				addImage(g, "PlayerLeft.png", npcX, (int)npcY + 12);
				
				animationNumberL = animation(g, animationDelayL, animationNumberL, animateLeft, "PlayerLeft", npcX,
						(int) npcY + 12 , animationLimit, animationSpeed);
				animationDelayL++;
			}
		}
	}
	
	public void addImage(Graphics g, String s, int x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, x, (int) y);
	}
	
	public int animation(Graphics g, int animationDelay, int animationNumber, boolean animateDirection, String PlayerDirection, int x, int y, int animationLimit, int animationSpeed) {
		
		animationDelay++;
		
		if (animateDirection) {
			addImage(g, (PlayerDirection + animationNumber + ".png"), (int) x, (int) y);
			
			if (animationDelay % animationSpeed == 0) {
				animationNumber++;
				
			}
			if (animationNumber == animationLimit) {
				
				animationNumber = 1;
			}
		}		
		return animationNumber;
	}
	
	public void drawNPCHealth(Graphics g) {
		if (alive) {
			// healthbar
			g.setColor(Color.black);
			g.fillRect(healthbarbox - 5, npcY + 1, 42, 6);
			g.setColor(Color.green);
			g.fillRect(healthnpc - 5, npcY + 3, healthcount, 2);
		}
	}
	
	public void move() {
		npcX = (int) (npcX + speed + speedaddition);
		npcY = npcY + speedY;
		healthbarbox = (int) (healthbarbox + speed + speedaddition);
		healthnpc = (int) (healthnpc + speed + speedaddition);
	}

	public Rectangle bounds() {

		return (new Rectangle(npcX - 9, npcY + 10, wHitBox, hHitBox));

	}

}
