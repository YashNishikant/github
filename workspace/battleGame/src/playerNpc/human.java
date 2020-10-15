package playerNpc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Textures;

public class human extends Textures {

	int replaceFace = 12;

	public boolean isMoving;

	public double speedY;
	public double personX;
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

	public boolean animateLeft;
	public boolean animateRight;
	public boolean turnRight = true;
	public boolean turnLeft;

	public human() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		personX = 810;
		y = 470;
		maxheight = y - 100;
		healthcount = 40;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
	}

	public void move() {
		if (death) {
			if (y <= 915) {
				speedY = 3;
			} else {
				speedY = 0;
			}
		}

	}

	public void drawHealth(Graphics g, double personX2, double personX3, int x) {
		// healthbar
		g.setColor(Color.black);
		g.fillRect((int) (personX2 - (x)), (int) (y + 1), 42, 6);
		g.setColor(Color.red);
		g.fillRect((int) (personX3 - (x - 1)), (int) (y + 3), healthcount, 2);
	}

	public void draw(Graphics g) {
		if (!death) {

			if (turnRight) {
				if (!holdingWeapon) {
					addImage(g, "//Player//PlayerRightARMS.png", (int) (personX - 3), (int) y + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) y + replaceFace);
				} else {
					addImage(g, "//Player//PlayerRightARMSWeapon.png", (int) (personX - 3), (int) y + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) y + replaceFace);
				}
			} else {
				addImage(g, "//Player//PlayerLeftARMS.png", (int) (personX - 6), (int) y + 30);
				addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) y + replaceFace);
			}

			if (turnRight && !animateRight) {
				addImage(g, "//Player//PlayerRight.png", personX, (int) y + 12);
				addImage(g, "//Player//PlayerFaceRight.png", (personX + 2), (int) y + replaceFace);
			}

			if (turnLeft && !animateLeft) {
				addImage(g, "//Player//PlayerLeft.png", (int) personX, (int) y + 12);
				addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) y + replaceFace);
			}

			// ANIMATION
			if (isMoving) {
				animationLimit = 5;

				animationNumberR = animation(g, animationDelayR, animationNumberR, animateRight, "PlayerRight", personX,
						(int) y + 12, animationLimit, animationSpeed);

				animationDelayR++;
				animationNumberL = animation(g, animationDelayL, animationNumberL, animateLeft, "PlayerLeft", personX,
						(int) y + 12, animationLimit, animationSpeed);
				animationDelayL++;
			}

			else {
				if (turnRight) {
					addImage(g, "//Player//PlayerRight.png", personX, (int) y + 12);
					addImage(g, "//Player//PlayerFaceRight.png", (personX + 2), (int) y + replaceFace);
				}

				if (turnLeft) {
					addImage(g, "//Player//PlayerLeft.png", (int) personX, (int) y + 12);
					addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) y + replaceFace);
				}
			}

			// ANIMATION

		} else {
			addImage(g, "//Icons//skull.png", (int) personX, (int) (y + 10));
		}

	}

	public Rectangle bounds() {

		return (new Rectangle((int) (personX - 10), (int) (y + 10), wHitBox, hHitBox));

	}

}
