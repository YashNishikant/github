package battleGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class sandbox extends JPanel implements ActionListener, KeyListener {

	int buildspacing = 0;
	int knockbackRNG = 0;
	int landspacing = 0;
	int blockoffset = 0;
	int limitXleft = 1900;
	int limitXright = -10;
	boolean allowJ = true;
	boolean darkenSky;
	boolean destroyerFireLock = true;
	boolean destroyerKnockBack;

	int fireBulletTick;

	bullet[] bullets = new bullet[100];
	buildings[] towers = new buildings[700];
	NPC[] player = new NPC[100];
	rain[] raindrop = new rain[200];
	Grenade[] explosive = new Grenade[20];
	DestroyerBullets[] enemyBulletLeft = new DestroyerBullets[100];
	DestroyerBullets[] enemyBulletRight = new DestroyerBullets[100];

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();
	Timer time = new Timer(5, this);
	controls gui = new controls();
	battery power = new battery();
	BattleBoss destroyer = new BattleBoss(1500, 500);
	Shield shield = new Shield(iron.armorPosX + 50, iron.armorPosY);

	public sandbox() {

		for (int i = 0; i < raindrop.length; i++) {
			rain raindrop1 = new rain();
			raindrop[i] = raindrop1;
		}

		for (int i = 0; i < player.length; i++) {
			NPC player1 = new NPC((int) (Math.random() * 20000) + 200, 1);
			player[i] = player1;
		}

		for (int i = 0; i < bullets.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			bullets[i] = b1;
		}

		for (int i = 0; i < towers.length; i++) {
			buildings building = new buildings(buildspacing);
			buildspacing += 600;
			towers[i] = building;
		}

		for (int i = 0; i < explosive.length; i++) {
			Grenade explosive1 = new Grenade(iron.armorPosX + 19, iron.armorPosY - 5);
			explosive[i] = explosive1;
		}

		for (int i = 0; i < enemyBulletLeft.length; i++) {
			DestroyerBullets enemyBullet1 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
			enemyBulletLeft[i] = enemyBullet1;
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			DestroyerBullets enemyBullet2 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
			enemyBulletRight[i] = enemyBullet2;
		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		gui.naturaldrawings(g);

		for (int i = 0; i < towers.length; i++) {
			if (towers[i].movingsurrounding1 > limitXright - 180 && towers[i].movingsurrounding1 < limitXleft + 180) {
				towers[i].draw(g);
				ImageIcon i5 = new ImageIcon("C:\\Users\\yash0\\Pictures\\buildingIMG.png");
				i5.paintIcon(this, g, towers[i].movingsurrounding1, towers[i].bY);
			}
		}
		for (int i = 0; i < player.length; i++) {
			if (player[i].alive && player[i].npcX > limitXright && player[i].npcX < limitXleft) {
				player[i].drawNPC(g);
				ImageIcon i6 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
				i6.paintIcon(this, g, player[i].npcX, player[i].npcY + 10);
			} else {
				if (player[i].npcX > limitXright && player[i].npcX < limitXleft) {
					ImageIcon i8 = new ImageIcon("C:\\Users\\yash0\\Pictures\\skull.png");
					i8.paintIcon(this, g, player[i].npcX, player[i].npcY + 10);
				}
			}
		}
		// explosive
		for (int i = 0; i < explosive.length; i++) {
			if (explosive[i].bulletFire && explosive[i].drawExplosiveForRobotDestruction) {
				ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\Grenade.png");
				i2.paintIcon(this, g, explosive[i].grenadeX, (int) explosive[i].grenadeY);
			}
			explosive[i].fire();
			explosive[i].destruction(g);
		}

		// Ground
		g.setColor(Color.gray);
		g.fillRect(0, 950, 2000, 2000);

		cloud.draw(g);

		user.drawHealth(g);
		if (!iron.track) {

			user.draw(g);

			iron.normal = true;
			iron.fire = false;

			gui.draw(g);

			ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
			i2.paintIcon(this, g, user.personX, (int) (user.personY + 10));

			if (power.powerlength <= 0) {
				g.setColor(Color.RED);
				g.setFont(new Font("default", Font.BOLD, 25));
				g.drawString("!", iron.armorPosX, iron.armorPosY);
			}
		}

		// shield
		if (shield.activateShield) {
			ImageIcon shield = new ImageIcon("C:\\Users\\yash0\\Pictures\\shield.png");
			shield.paintIcon(this, g, iron.armorPosX + 50, iron.armorPosY);
		}

		// destroyer
		if (destroyer.alive) {
			ImageIcon destroyerimg = new ImageIcon("C:\\Users\\yash0\\Pictures\\Destroyer.png");
			destroyerimg.paintIcon(this, g, destroyer.X - 12, (int) (destroyer.Y + 10));
			destroyer.draw(g);
		} else {
			ImageIcon destroyerdead = new ImageIcon("C:\\Users\\yash0\\Pictures\\DestroyerDead.png");
			destroyerdead.paintIcon(this, g, destroyer.X - 12, (int) (destroyer.Y + 10));
		}

		// destroyerBullets
		for (int i = 0; i < enemyBulletLeft.length; i++) {
			enemyBulletLeft[i].fire();
			enemyBulletLeft[i].bulletSpeed = -10;
			if (enemyBulletLeft[i].bulletFire) {
				ImageIcon i12 = new ImageIcon("C:\\Users\\yash0\\Pictures\\DestroyerBulletsLEFT.png");
				i12.paintIcon(this, g, enemyBulletLeft[i].bulletX,
						(int) enemyBulletLeft[i].bulletY + enemyBulletLeft[i].yoffset);
			}
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			enemyBulletRight[i].fire();
			enemyBulletRight[i].bulletSpeed = 10;
			if (enemyBulletRight[i].bulletFire) {
				ImageIcon i12 = new ImageIcon("C:\\Users\\yash0\\Pictures\\DestroyerBulletsRIGHT.png");
				i12.paintIcon(this, g, enemyBulletRight[i].bulletX,
						(int) enemyBulletRight[i].bulletY + enemyBulletRight[i].yoffset);
			}
		}

		// bullet
		for (int i = 0; i < bullets.length; i++) {
			if (!iron.flyIMG && !iron.turbo && !iron.ableToTurbo_LEFT && !iron.flyIMG_LEFT) {
				bullets[i].fire();
				if (bullets[i].bulletFire) {
					ImageIcon i12 = new ImageIcon("C:\\Users\\yash0\\Pictures\\bullet.png");
					i12.paintIcon(this, g, bullets[i].bulletX, (int) bullets[i].bulletY + bullets[i].yoffset);
				}
			}
		}
		if (iron.normal) {
			ImageIcon i = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanNOFire.png");
			i.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}

		if (iron.fire) {
			ImageIcon i3 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanSuitJavaCanvasIMG.png");
			i3.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}

		if (iron.blast) {
			ImageIcon i4 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanBlast.png");
			i4.paintIcon(this, g, iron.armorPosX, iron.armorPosY);

		}

		if (iron.confirmgroundfire) {
			ImageIcon i4 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanBlastOnGround.png");
			i4.paintIcon(this, g, iron.armorPosX, iron.armorPosY);

		}
		// fly
		if (iron.flyIMG && !shield.activateShield) {
			ImageIcon i9 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanFly.png");
			i9.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		} else if (iron.flyIMG && shield.activateShield) {
			ImageIcon i9 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanSuitJavaCanvasIMG.png");
			i9.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}

		if (iron.flyIMG_LEFT && !shield.activateShield) {
			ImageIcon i11 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanFlyLEFT.png");
			i11.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		} else if (iron.flyIMG_LEFT && shield.activateShield) {
			ImageIcon i11 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanSuitJavaCanvasIMG.png");
			i11.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}
		// fly
		if (iron.turbo) {
			ImageIcon i10 = new ImageIcon("C:\\Users\\yash0\\Pictures\\TURBO.png");
			i10.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}
		if (iron.turbo_LEFT) {
			ImageIcon i12 = new ImageIcon("C:\\Users\\yash0\\Pictures\\TURBO_LEFT.png");
			i12.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}

		if (iron.track) {

			gui.drawArmor(g);

			if (power.powerlength <= 0) {

				g.setColor(Color.RED);
				g.setFont(new Font("default", Font.BOLD, 25));
				g.drawString("!          !          !", 1660, 45);
				user.nobattery = true;

				g.setColor(Color.RED);
				g.setFont(new Font("default", Font.BOLD, 25));
				g.drawString("!", iron.armorPosX, iron.armorPosY);

			}
			power.draw(g);

		}
		for (int i = 0; i < raindrop.length; i++) {

			if (raindrop[i].beginRain) {

				ImageIcon i13 = new ImageIcon("C:\\Users\\yash0\\Pictures\\rain.png");
				i13.paintIcon(this, g, raindrop[i].raindropX, (int) raindrop[i].raindropY);
			}
		}
	}

	public void contain() {
		for (int i = 0; i < bullets.length; i++) {
			if (!bullets[i].bulletFire) {
				bullets[i].bulletX = iron.armorPosX + 19;
				bullets[i].bulletY = iron.armorPosY - 5;
			}

			shield.x = iron.armorPosX + 50;
			shield.y = iron.armorPosY;
		}

		for (int i = 0; i < explosive.length; i++) {
			if (!explosive[i].bulletFire) {
				explosive[i].grenadeX = iron.armorPosX + 19;
				explosive[i].grenadeY = iron.armorPosY + 20;
			}
		}

		for (int i = 0; i < enemyBulletLeft.length; i++) {
			if (!enemyBulletLeft[i].bulletFire) {
				enemyBulletLeft[i].bulletX = destroyer.X;
				enemyBulletLeft[i].bulletY = destroyer.Y + 10;
			}
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			if (!enemyBulletRight[i].bulletFire) {
				enemyBulletRight[i].bulletX = destroyer.X;
				enemyBulletRight[i].bulletY = destroyer.Y + 10;
			}
		}

	}

	public void trackSystem() {

		if (user.personY >= 870) {
			user.speedY = 0;
		}

		if (iron.track) {
			iron.armorPosX = user.personX - 12;
			iron.armorPosY = (int) (user.personY + 8);
		}
	}

	public void batterydecrease() {
		if (user.insideSuit && iron.armorPosY <= 869) {
			power.isflyingforbattery = true;
		} else {
			power.isflyingforbattery = false;
		}
	}

	public void knockbackRNG() {

		knockbackRNG++;
		for (int i = 0; i < player.length; i++) {
			if (knockbackRNG % 10 == 0) {
				player[i].knockback = false;
			}
		}

		if (knockbackRNG % 10 == 0) {
			destroyer.knockback = false;
		}
	}

	public void deadnpc() {
		for (int i = 0; i < player.length; i++) {
			if (!player[i].alive) {
				if (player[i].npcY <= 915) {
					player[i].speedY = 2;
					player[i].speedaddition = 0;
					player[i].speed = 0;
				} else {
					player[i].speedY = 0;

				}
			}
		}

		if (!destroyer.alive) {
			if (destroyer.Y <= 870) {
				destroyer.speedY += 0.1;

			} else {
				destroyer.speedY = 0;
			}
		}
	}

	public void beginRain() {
		int randomRain;
		randomRain = (int) (Math.random() * 20000);

		if (randomRain == 10) {
			darkenSky = true;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = true;
			}
		}
		if (randomRain == 20) {
			darkenSky = false;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = false;
			}
		}
	}

	void appropriateImage() {
		if (user.personY >= 870) {
			iron.flyIMG = false;
			iron.flyIMG_LEFT = false;
			iron.turbo = false;
			iron.turbo_LEFT = false;

		}
	}

	void weather() {
		if (gui.greenblue > 150 && darkenSky)
			gui.greenblue--;

		if (!darkenSky && gui.greenblue < 255) {
			gui.greenblue++;
		}
	}

	void movePlayer(int x) {

		for (int j = 0; j < player.length; j++) {
			player[j].speed = x;
		}
		for (int j = 0; j < towers.length; j++) {
			towers[j].speed = x;
		}
		for (int j = 0; j < explosive.length; j++) {
			explosive[j].grenadeSpeed = x;
		}
		if (!destroyerKnockBack) {
			destroyer.speed = x;
		}
	}

	void moveTowardsPlayer() {
		if (destroyer.alive && !destroyerKnockBack) {
			if (destroyer.attackLeft) {
				if (destroyer.X > user.personX + 200) {
					destroyer.speedaddition = -1;
				} else {
					destroyer.speedaddition = 0;
				}
				if (destroyer.goUp) {
					if (destroyer.Y != user.personY) {
						destroyer.speedY = -1;
					}
				}
				if (destroyer.Y == user.personY) {
					destroyer.speedY = 0;
					if (gui.enemyAmmoL > 0 && destroyerFireLock) {
						enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
						gui.enemyAmmoL--;
						destroyerFireLock = false;
					}
				}

				if (destroyer.goDown) {
					if (destroyer.Y != user.personY) {
						destroyer.speedY = 1;
					}

					if (destroyer.Y == user.personY) {
						destroyer.speedY = 0;
						if (gui.enemyAmmoL > 0 && destroyerFireLock) {
							enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
							gui.enemyAmmoL--;
							destroyerFireLock = false;
						}
					}
				}
			}

			if (destroyer.attackRight) {
				if (destroyer.X < user.personX - 200) {
					destroyer.speedaddition = 1;
				} else {
					destroyer.speedaddition = 0;
				}
				if (destroyer.goUp) {
					if (destroyer.Y != user.personY) {
						destroyer.speedY = -1;
					}

					else {
						destroyer.speedY = 0;
						if (gui.enemyAmmoR > 0 && destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyerFireLock = false;
						}
					}

				}

				if (destroyer.goDown) {
					if (destroyer.Y != user.personY) {
						destroyer.speedY = 1;
					}

					else {
						destroyer.speedY = 0;
						if (gui.enemyAmmoR > 0 && destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyerFireLock = false;
						}
					}
				}
			}
		}
		if (!destroyer.goDown && !destroyer.goUp) {
			destroyer.speedY = 0;
		}
	}

	void fireTick() {
		fireBulletTick++;
		if (fireBulletTick % 50 == 0) {
			destroyerFireLock = true;
		} else {
			destroyerFireLock = false;
		}
	}

	public void actionPerformed(ActionEvent e) {
		beginRain();
		moveTowardsPlayer();
		for (int i = 0; i < raindrop.length; i++) {
			raindrop[i].rainFall();
		}
		weather();
		for (int j = 0; j < enemyBulletLeft.length; j++) {
			enemyBulletLeft[j].destroy();
		}
		for (int j = 0; j < enemyBulletRight.length; j++) {
			enemyBulletRight[j].destroy();
		}
		user.move();
		for (int i = 0; i < player.length; i++) {
			player[i].npcBehavior();
		}
		knockbackRNG();
		deadnpc();
		for (int i = 0; i < towers.length; i++) {
			towers[i].move();
		}
		for (int i = 0; i < player.length; i++) {
			player[i].move();
		}
		iron.move();
		batterydecrease();
		contain();
		if (allowJ) {
			user.jump();
		}
		for (int i = 0; i < explosive.length; i++) {
			explosive[i].destroy();
		}
		destroyer.move();
		destroyer.Behavior();
		iron.tracking();
		appropriateImage();
		trackSystem();
		power.batteryfunction();
		cloud.move();
		Collision();
		user.shutdown();
		fireTick();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_B) {
			shield.activateShield = true;
		}
		if (i == KeyEvent.VK_Z) {
			shield.activateShield = false;
		}

		if (i == KeyEvent.VK_X && !shield.activateShield) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.grenadeAmmo > 0) {
					gui.grenadeAmmo--;
					explosive[gui.grenadeAmmo].bulletFire = true;
					explosive[gui.grenadeAmmo].heavyDestruction = true;
				}
			}
		}

		if (i == KeyEvent.VK_R && !shield.activateShield) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.ammo > 0) {
					gui.ammo--;
					bullets[gui.ammo].bulletFire = true;
				}
			}
		}

		if (i == KeyEvent.VK_F && !iron.ableToTurbo && !iron.ableToTurbo_LEFT) {

			if (iron.track) {

				iron.blast = true;
				iron.normal = false;
				iron.fire = false;
				iron.activatefire = false;
			}
			if (iron.track && iron.armorPosY >= 870) {

				iron.blast = false;
				iron.normal = false;
				iron.fire = false;
				iron.activatefire = false;
				iron.fireonground = true;
				iron.confirmgroundfire = true;
			}
		}

		if (i == KeyEvent.VK_G) {

			iron.blast = false;
			iron.fire = true;
			iron.normal = false;
			iron.confirmgroundfire = false;
			iron.fireonground = false;
		}

		if (i == KeyEvent.VK_V) {

			if (iron.armorPosX != user.personX - 12) {
				if (iron.armorPosX >= user.personX) {
					iron.armorspeed = -10;
				}

				if (iron.armorPosX <= user.personX) {
					iron.armorspeed = 10;
				}
			}
			power.track = true;
		}

		if (i == KeyEvent.VK_C) {
			if (user.personY >= 870) {
				iron.track = false;
				power.track = false;
				iron.armorPosX = user.personX - 70;
				allowJ = true;
			}
			user.insideSuit = false;
		}
		if (i == KeyEvent.VK_W) {
			if (iron.track == true && iron.confirmgroundfire == false && iron.fireonground == false) {
				user.speedY = -5;
				allowJ = false;

				if (user.personY >= 870) {
					power.isflyingforbattery = false;
				}
			}
			if (!iron.track && user.personY >= 870) {
				user.jump = true;
			}
		}

		if (i == KeyEvent.VK_S && iron.track == true && !(iron.armorPosY >= 870)) {
			allowJ = false;
			user.speedY = 5;

		}

		if (i == KeyEvent.VK_A) {

			destroyerKnockBack = false;
			
			if (!shield.activateShield) {
				iron.ableToTurbo_LEFT = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870) {
				movePlayer(6);
			}
			if (!iron.track) {
				movePlayer(2);
				iron.armorspeed = 2;
			}

			// air
			if (iron.track == true && iron.armorPosY < 870) {

				iron.flyIMG = false;
				iron.flyIMG_LEFT = true;
				iron.fire = false;
				iron.canfly = false;
				iron.ignore = false;
				iron.blast = false;
				iron.normal = false;
				iron.fireonground = false;
				iron.confirmgroundfire = false;

				movePlayer(12);
			} else {
				movePlayer(4);
				iron.armorspeed = 4;
			}
		}

		if (i == KeyEvent.VK_D) {
			if (!shield.activateShield) {
				iron.ableToTurbo = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870) {
				movePlayer(-6);
			}
			if (!iron.track) {
				movePlayer(-2);
				iron.armorspeed = -2;
			}
			// air
			if (iron.track == true && iron.armorPosY < 870) {

				iron.flyIMG = true;
				iron.flyIMG_LEFT = false;
				iron.fire = false;
				iron.canfly = false;
				iron.ignore = false;
				iron.blast = false;
				iron.normal = false;
				iron.fireonground = false;
				iron.confirmgroundfire = false;

				movePlayer(-12);

			} else {
				movePlayer(-4);
				iron.armorspeed = -4;
			}

		}

		if (i == KeyEvent.VK_T && iron.ableToTurbo) {

			iron.turbo = true;
			iron.flyIMG = false;

			power.turboReducePowerMore = true;

			movePlayer(-82);
		}

		if (i == KeyEvent.VK_Q && iron.ableToTurbo_LEFT) {
			iron.turbo_LEFT = true;
			iron.flyIMG_LEFT = false;

			power.turboReducePowerMore = true;

			movePlayer(82);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();

		iron.turbo = false;
		iron.turbo_LEFT = false;

		movePlayer(0);

		if (i == KeyEvent.VK_Q && iron.ableToTurbo_LEFT) {
			iron.flyIMG_LEFT = true;
			power.turboReducePowerMore = false;
		}

		if (i == KeyEvent.VK_T && iron.ableToTurbo) {
			iron.flyIMG = true;
			power.turboReducePowerMore = false;
		}
		if (i == KeyEvent.VK_D) {
			iron.flyIMG = false;
			iron.ableToTurbo = false;
		}

		if (i == KeyEvent.VK_A) {
			iron.flyIMG_LEFT = false;
			iron.ableToTurbo_LEFT = false;
		}

		if (i == KeyEvent.VK_D && iron.armorPosY < 865) {
			iron.driftright = true;
			iron.fire = true;
		}

		if (i == KeyEvent.VK_A && iron.armorPosY < 865) {
			iron.driftleft = true;
			iron.fire = true;
		}

		if (i == KeyEvent.VK_D && iron.armorPosY >= 870) {

			movePlayer(0);
		}

		if (i == KeyEvent.VK_A && iron.armorPosY >= 870) {

			movePlayer(0);
		}

		if (iron.track) {
			user.speedY = 0;
		}

		iron.armorspeed = 0;
	}

	public void Collision() {

		Rectangle suit = iron.bounds();
		Rectangle human = user.bounds();
		Rectangle boss = destroyer.bounds();
		Rectangle bossDetection = destroyer.detection();
		Rectangle ShieldRec = shield.bounds();

		for (int j = 0; j < explosive.length; j++) {
			Rectangle BRecExplosive = explosive[j].boundsExplosive();

			for (int i = 0; i < bullets.length; i++) {
				Rectangle BRec = bullets[i].bounds();

				if (BRec.intersects(boss) && bullets[i].bulletFire) {
					bullets[i].bulletFire = false;
					bullets[i].letdestroy = true;
					destroyer.knockback = true;
					if (destroyer.healthcount > 0) {
						destroyer.healthcount -= bullets[i].damageForBoss;
					}
				}
				for (int k = 0; k < player.length; k++) {
					Rectangle npc = player[k].bounds();

					if (BRec.intersects(npc) && bullets[i].bulletFire) {
						player[k].knockback = true;
						bullets[i].bulletFire = false;
						bullets[i].letdestroy = true;
						if (player[k].healthcount > 0) {
							player[k].healthcount -= bullets[i].damage;
						}
					}

					if (BRecExplosive.intersects(npc) && explosive[j].bulletFire) {
						if (player[k].healthcount > 0) {
							player[k].healthcount -= explosive[j].explosiveDamage;
						}
					}

					if (BRecExplosive.intersects(boss) && explosive[j].bulletFire) {
						if (destroyer.healthcount > 0) {
							destroyer.healthcount -= explosive[j].explosiveDamage;
							explosive[j].explode = true;
							explosive[j].letdestroy = true;
							explosive[j].drawExplosiveForRobotDestruction = false;
						}
					}
				}
			}
		}

		for (int j = 0; j < enemyBulletLeft.length; j++) {
			Rectangle destroyerBulletLeft = enemyBulletLeft[j].bounds();
			if (destroyerBulletLeft.intersects(human) && enemyBulletLeft[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcount--;
				}
				enemyBulletLeft[j].letdestroy = true;
			}

			if (destroyerBulletLeft.intersects(ShieldRec) && enemyBulletLeft[j].bulletFire) {
				enemyBulletLeft[j].letdestroy = true;
			}

		}

		for (int j = 0; j < enemyBulletRight.length; j++) {
			Rectangle destroyerBulletRight = enemyBulletRight[j].bounds();
			if (destroyerBulletRight.intersects(human) && enemyBulletRight[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcount--;
				}
				enemyBulletRight[j].letdestroy = true;
			}
			if (destroyerBulletRight.intersects(ShieldRec) && enemyBulletLeft[j].bulletFire) {
				enemyBulletRight[j].letdestroy = true;
			}
		}

		if (suit.intersects(human)) {
			iron.track = true;
			user.insideSuit = true;
			power.track = true;
		}

		if (bossDetection.intersects(human)) {
			destroyer.attackMode = true;

			if (user.personY > destroyer.Y) {
				destroyer.goDown = true;
				destroyer.goUp = false;
			}

			if (user.personY < destroyer.Y) {
				destroyer.goDown = false;
				destroyer.goUp = true;
			}

			if (user.personX >= destroyer.X) {
				destroyer.attackRight = true;
				destroyer.attackLeft = false;
			}
			if (user.personX <= destroyer.X) {
				destroyer.attackRight = false;
				destroyer.attackLeft = true;
			}
		} else {
			destroyer.attackMode = false;
			destroyer.attackLeft = false;
			destroyer.attackRight = false;
			destroyer.goDown = false;
			destroyer.goUp = false;
		}

		if (ShieldRec.intersects(boss)) {
			destroyer.speed = 0;
			destroyer.speedaddition = 0;
			destroyerKnockBack = true;
		}
		else {
			destroyerKnockBack = false;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		sandbox sPanel = new sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
