package playerNpc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import battleGame.Textures;

public class human extends Textures {

	int replaceFace = 12;

	public double speedY;
	public double personX;
	public double personY;
	public double maxheight;
	public int minheight;
	public int hHitBox;
	public int wHitBox;
	public boolean allowJ = true;

	public int healthbarbox;
	public int health;
	public int healthcount;

	public boolean jump;
	public boolean rise = true;
	public boolean insideSuit;
	public boolean nobattery;
	public boolean onground;
	public boolean hitgrass;
	public boolean onetimeJump = true;
	public boolean jumping;
	public boolean death;
	public boolean holdingWeapon;

	String assetsPath;

	public int animationSpeed = 30;
	public int animationLimit;
	public int animationDelayR;
	public int animationDelayL;
	public boolean animateLeft;
	public boolean animateRight;
	public boolean turnRight = true;
	public boolean turnLeft;

	public int animationNumberR = 1;
	public int animationNumberL = 1;

	public human() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";

		speedY = 0;
		personX = 810;
		personY = 870;
		maxheight = personY - 100;
		healthcount = 40;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
	}

	public void move() {
		if (!death) {
			if (personY >= 871) {
				speedY = 0;
				personY = 870;
			}

			personY = personY + speedY;
		} else {
			if (personY <= 915) {
				speedY = 3;
			} else {
				speedY = 0;
			}
		}

	}

	public void shutdown() {

		if (insideSuit && nobattery) {

			if (personY >= 869) {
				speedY = 0;
			} else {
				speedY += 0.05;
			}
		}
	}

	public void jump() {
		if (allowJ) {
			if (jump && !death) {
				if (onetimeJump) {
					speedY = -2.5;
					onetimeJump = false;
					jumping = true;
				}

				personY += speedY;
				if (speedY < 0) {
					speedY += 0.07;
				} else {
					jump = false;
					onetimeJump = true;
					jumping = false;
				}
			}

			if (!jump) {
				personY += speedY;

				jumping = false;

				if (speedY < 3) {
					speedY += 0.07;
				}
			}
		}
	}

	public void drawHealth(Graphics g, double personX2, double personX3, int x) {
		// healthbar
		g.setColor(Color.black);
		g.fillRect((int) (personX2 - (x)), (int) (personY + 1), 42, 6);
		g.setColor(Color.red);
		g.fillRect((int) (personX3 - (x - 1)), (int) (personY + 3), healthcount, 2);
	}

	public void draw(Graphics g) {
		if (!death) {

			if (turnRight) {
				if (!holdingWeapon) {
					addImage(g, "//Player//PlayerRightARMS.png", (int) (personX - 3), (int) personY + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) personY + replaceFace);
				} else {
					addImage(g, "//Player//PlayerRightARMSWeapon.png", (int) (personX - 3), (int) personY + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) personY + replaceFace);
				}
			} else {
				addImage(g, "//Player//PlayerLeftARMS.png", (int) (personX - 6), (int) personY + 30);
				addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) personY + replaceFace);
			}

			if (turnRight && !animateRight) {
				addImage(g, "//Player//PlayerRight.png", personX, (int) personY + 12);
				addImage(g, "//Player//PlayerFaceRight.png", (personX + 2), (int) personY + replaceFace);
			}

			if (turnLeft && !animateLeft) {
				addImage(g, "//Player//PlayerLeft.png", (int) personX, (int) personY + 12);
				addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) personY + replaceFace);
			}

			// ANIMATION
			animationLimit = 6;

			animationNumberR = animation(g, animationDelayR, animationNumberR, animateRight, "PlayerRight", personX,
					(int) personY + 12, animationLimit, animationSpeed);

			animationDelayR++;
			animationNumberL = animation(g, animationDelayL, animationNumberL, animateLeft, "PlayerLeft", personX,
					(int) personY + 12, animationLimit, animationSpeed);
			animationDelayL++;
			// ANIMATION

		} else {
			addImage(g, "//Icons//skull.png", (int) personX, (int) (personY + 10));
		}

	}

	public Rectangle bounds() {

		return (new Rectangle((int) (personX - 10), (int) (personY + 10), wHitBox, hHitBox));

	}

}
