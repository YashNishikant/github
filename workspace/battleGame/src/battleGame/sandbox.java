package battleGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import Environment.Ground;
import Environment.clouds;
import Environment.land;
import Environment.rain;
import playerNpc.BattleBoss;
import playerNpc.NPC;
import playerNpc.human;
import scenes.BuildingScene;
import structures.Airport;
import structures.buildings;
import structures.cityBounds;
import vehicles.Plane;
import vehicles.car;
import weapons.DestroyerBullets;
import weapons.Grenade;
import weapons.Shield;
import weapons.Wand;
import weapons.armor;
import weapons.battery;
import weapons.bullet;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class sandbox extends Textures implements ActionListener, KeyListener {
	int buildspacing = (int) (Math.random() * -100000) + 100000;
	int landSpacing = -1500;
	int landSpacingFloor = -100;
	int limitXleft = 1900;
	int limitXright = -10;

	// Buildings
	buildings[] towers = new buildings[10];
	cityBounds cityboundary1 = new cityBounds(buildspacing);
	cityBounds cityboundary2 = new cityBounds(buildspacing + 6000);
	Airport airport = new Airport();
	// Environment
	rain[] raindrop = new rain[200];
	land[] landscape = new land[5];
	Ground[] floor = new Ground[1];
	clouds cloud = new clouds();
	// NPC and User
	BattleBoss destroyer = new BattleBoss(1500, -5000);
	NPC[] player = new NPC[20];
	human user = new human();
	// Weapons
	DestroyerBullets[] enemyBulletLeft = new DestroyerBullets[100];
	DestroyerBullets[] enemyBulletRight = new DestroyerBullets[100];
	bullet[] bullets = new bullet[100];
	armor iron = new armor();
	Wand wand = new Wand(user.personX + 40, (int) (user.personY + 30));
	Shield shield = new Shield(iron.armorPosX + 50, iron.armorPosY);
	// Vehicles
	car car = new car();
	Plane plane = new Plane();
	// game controls and scenes
	controls gui = new controls(0);
	battery power = new battery();
	BuildingScene buildScene = new BuildingScene();
	// Timer
	Timer time = new Timer(5, this);
	// Assets
	String assetsPath;

	public sandbox() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";

		for (int i = 0; i < floor.length; i++) {
			Ground floor1 = new Ground(landSpacingFloor);
			landSpacingFloor += 4200;
			floor[i] = floor1;
		}

		for (int i = 0; i < landscape.length; i++) {
			land land1 = new land(landSpacing);
			landSpacingFloor += 0;
			landSpacing += 800;
			landscape[i] = land1;
		}

		for (int i = 0; i < raindrop.length; i++) {
			rain raindrop1 = new rain();
			raindrop[i] = raindrop1;
		}

		for (int i = 0; i < player.length; i++) {
			NPC player1 = new NPC((int) (Math.random() * 4000) + 200, 1);
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

	public void actionPerformed(ActionEvent e) {
		beginRain();
		airport.move();
		car.move();
		plane.move();
		iron.uncrafted();
		movingObjects();
		refreshBullets();
		cityboundary1.move();
		cityboundary2.move();
		moveTowardsPlayer();
		weather();
		user.move();
		knockbackRNG();
		deadnpc();
		keepCarMoving();
		iron.move();
		batterydecrease();
		contain();
		user.jump();
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

	// PAINT BEGIN
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		gui.naturaldrawings(g);
		// clouds
		cloud.draw(g);
		enterBuilding(g);
		for (int i = 0; i < landscape.length; i++) {
			landscape[i].draw(g);
		}

		for (int i = 0; i < floor.length; i++) {
			floor[i].draw(g);
		}

		for (int i = 0; i < towers.length; i++) {
			if (towers[i].bX > limitXright - 180 && towers[i].bX < limitXleft + 180) {
				towers[i].draw(g);
			}
		}

		airport.draw(g);

		for (int i = 0; i < player.length; i++) {
			if (player[i].alive && player[i].npcX > limitXright && player[i].npcX < limitXleft) {
				player[i].drawNPC(g);
				player[i].drawNPCHealth(g);
			} else {
				if (player[i].npcX > limitXright && player[i].npcX < limitXleft) {
					player[i].dead(g);
				}
			}
		}

		// wand
		wand.draw(g);

		if (!iron.track) {
			iron.normal = true;
			iron.fire = false;
		}

		// shield
		if (shield.activateShield) {
			shield.draw(g);
		}

		// Destroyer

		destroyer.draw(g);
		destroyer.drawHealth(g);

		for (int i = 0; i < enemyBulletLeft.length; i++) {
			enemyBulletLeft[i].fire();
			enemyBulletLeft[i].bulletSpeed = -10;

			if (enemyBulletLeft[i].bulletFire) {

				addImage(g, "//Bullet//DestroyerBulletsLEFT.png", enemyBulletLeft[i].bulletX,
						(int) enemyBulletLeft[i].bulletY + enemyBulletLeft[i].yoffset);
			}
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			enemyBulletRight[i].fire();
			enemyBulletRight[i].bulletSpeed = 10;
			if (enemyBulletRight[i].bulletFire) {

				addImage(g, "//Bullet//DestroyerBulletsRIGHT.png", enemyBulletRight[i].bulletX,
						(int) enemyBulletRight[i].bulletY + enemyBulletRight[i].yoffset);
			}
		}

		// bullet
		for (int i = 0; i < bullets.length; i++) {
			if (!iron.flyIMG && !iron.turbo && !iron.ableToTurbo_LEFT && !iron.flyIMG_LEFT) {
				bullets[i].fire();
				bullets[i].draw(g);
			}
		}

		if (iron.track) {

			gui.drawArmor(g);

			if (power.powerlength <= 0) {
				user.nobattery = true;

				iron.batteryDrained(g);

			}
			power.draw(g);
		}

		plane.draw(g);
		car.draw(g);

		// Armor
		flyWithShield(g);
		iron.Images(g);

		if (!iron.track && !car.enter && !plane.enter) {
			user.drawHealth(g, user.personX, user.personX, 5);
		} else if (!car.enter && !plane.enter) {
			user.drawHealth(g, user.personX, user.personX, 10);
		}

		if (!iron.track && !car.enter && !plane.enter) {
			user.draw(g);
		}

		for (int i = 0; i < raindrop.length; i++) {
			if (raindrop[i].beginRain) {
				raindrop[i].draw(g);
			}
		}
		buildScene.draw(g);
	}

	// PAINT END
	// PAINT END
	// PAINT END

	void flyWithShield(Graphics g) {

		if (iron.flyIMG && !shield.activateShield) {

			addImage(g, "//Armor//TankFlyRight.png", iron.armorPosX, iron.armorPosY);

		} else if (user.personY <= 865 && shield.activateShield) {
			addImage(g, "//Armor//TankArmor.png", iron.armorPosX, iron.armorPosY);
		}

		if (iron.flyIMG_LEFT && !shield.activateShield) {

			addImage(g, "//Armor//TankFlyLeft.png", iron.armorPosX, iron.armorPosY);

		} else if (user.personY <= 865 && shield.activateShield) {

			addImage(g, "//Armor//TankArmor.png", iron.armorPosX, iron.armorPosY);

		}

	}

	void keepCarMoving() {
		if (car.enter) {
			movePlayer(car.carSpeed);
		}
	}

	public void enterBuilding(Graphics g) {
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].enter) {
				addImage(g, "Icons//E_Icon.png", 10, 10);
			}
		}
	}

	public void contain() {

		if (plane.planePos) {
			plane.x = airport.X + 1000;
			plane.planePos = false;
		}

		cityboundary1.buildingPos = towers[0].bX;
		cityboundary2.buildingPos = towers[9].bX;

		for (int i = 0; i < floor.length; i++) {

			if (floor[i].XGround < -1000) {
				floor[i].XGround = 2200;
			}

			if (floor[i].XGround > -10) {
				floor[i].XGround = -400;
			}

		}

		for (int i = 0; i < landscape.length; i++) {

			if (landscape[i].X < -1000) {
				landscape[i].X = 1800;
			}

			if (landscape[i].X > 1920) {
				landscape[i].X = -850;
			}

		}

		for (int i = 0; i < player.length; i++) {
			if (player[i].playerPos) {
				player[i].npcX = (int) (towers[4].bX);
				player[i].playerPos = false;
			}
		}

		if (iron.track) {
			user.holdingWeapon = false;
			wand.drawWand = false;
		}

		wand.Y = (int) user.personY + 30;

		for (int i = 0; i < bullets.length; i++) {
			if (!bullets[i].bulletFire) {
				bullets[i].bulletX = iron.armorPosX + 19;
				bullets[i].bulletY = iron.armorPosY - 5;
			}

			shield.x = iron.armorPosX + 50;
			shield.y = iron.armorPosY;
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
			iron.armorPosX = (int) (user.personX - 12);
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

		gui.knockbackRNG++;
		for (int i = 0; i < player.length; i++) {
			if (gui.knockbackRNG % 10 == 0) {
				player[i].knockback = false;
			}
		}

		if (gui.knockbackRNG % 10 == 0) {
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
				destroyer.speedY = 4;
			} else {
				destroyer.speedY = 0;
			}
		}

		if (user.death && iron.armorPosY <= 870) {
			user.speedY += 0.5;
		}

	}

	public void beginRain() {
		int randomRain;
		randomRain = (int) (Math.random() * 200000);

		if (randomRain == 10) {
			gui.darkenSky = true;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = true;
				gui.beginRain = true;
			}
		}
		if (randomRain == 20) {
			gui.darkenSky = false;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = false;
				gui.beginRain = false;
			}
		}

		for (int i = 0; i < raindrop.length; i++) {
			raindrop[i].rainFall();
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
		if (gui.greenblue > 150 && gui.darkenSky)
			gui.greenblue--;

		if (!gui.darkenSky && gui.greenblue < 255) {
			gui.greenblue++;
		}
	}

	void refreshBullets() {

		for (int j = 0; j < enemyBulletLeft.length; j++) {
			enemyBulletLeft[j].destroy();
		}
		for (int j = 0; j < enemyBulletRight.length; j++) {
			enemyBulletRight[j].destroy();
		}

		if (gui.shieldHP <= 0) {
			shield.activateShield = false;
		}

		if (gui.enemyAmmoR < 2) {
			gui.enemyAmmoR = 99;
			for (int i = 0; i < enemyBulletRight.length; i++) {
				DestroyerBullets enemyBullet2 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
				enemyBulletRight[i] = enemyBullet2;
			}
		}

		if (gui.enemyAmmoL < 2) {
			gui.enemyAmmoL = 99;
			for (int i = 0; i < enemyBulletLeft.length; i++) {
				DestroyerBullets enemyBullet1 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
				enemyBulletLeft[i] = enemyBullet1;
			}
		}
	}

	void movePlayer(double x) {

		if (!user.death) {

			for (int j = 0; j < floor.length; j++) {
				floor[j].floorSpeed = x;
			}
			for (int j = 0; j < landscape.length; j++) {
				landscape[j].backgroundspeed = x / 2;
			}
			for (int j = 0; j < player.length; j++) {
				player[j].speed = x;
			}
			for (int j = 0; j < towers.length; j++) {
				towers[j].speed = x;
			}

			if (!car.enter) {
				car.illusionspeed = x;
			}

			plane.illusionspeed = x;
			cityboundary1.speed = x;
			cityboundary2.speed = x;
			airport.illusionSpeed = x;
			destroyer.speed = x;
			iron.armorspeed = x;
		}
	}

	void movingObjects() {
		for (int j = 0; j < player.length; j++) {
			player[j].npcBehavior();
			player[j].move();
		}
		for (int j = 0; j < towers.length; j++) {
			towers[j].move();
		}

	}

	void moveTowardsPlayer() {
		if (destroyer.alive && !user.death) {
			if (destroyer.attackLeft) {
				if (destroyer.X > (int) user.personX + 200) {
					destroyer.speedaddition = -1;
				} else {
					destroyer.speedaddition = 0;
				}
				if (destroyer.goUp) {
					if (destroyer.Y != (int) user.personY) {
						destroyer.speedY = -1;
					}
				}
				if (destroyer.Y == (int) user.personY) {
					destroyer.speedY = 0;
					if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
						enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
						gui.enemyAmmoL--;
						destroyer.destroyerFireLock = false;
					}
				}

				if (destroyer.goDown) {
					if (destroyer.Y != (int) user.personY) {
						destroyer.speedY = 1;
					}

					if (destroyer.Y == (int) user.personY) {
						destroyer.speedY = 0;
						if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
							enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
							gui.enemyAmmoL--;
							destroyer.destroyerFireLock = false;
						}
					}
				}
			}

			if (destroyer.attackRight) {
				if (destroyer.X < (int) user.personX - 200) {
					destroyer.speedaddition = 1;
				} else {
					destroyer.speedaddition = 0;
				}
				if (destroyer.goUp) {
					if (destroyer.Y != (int) user.personY) {
						destroyer.speedY = -1;
					}

					else {
						destroyer.speedY = 0;
						if (gui.enemyAmmoR > 0 && destroyer.destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyer.destroyerFireLock = false;
						}
					}

				}

				if (destroyer.goDown) {
					if (destroyer.Y != (int) user.personY) {
						destroyer.speedY = 1;
					}

					else {
						destroyer.speedY = 0;
						if (gui.enemyAmmoR > 0 && destroyer.destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyer.destroyerFireLock = false;
						}
					}
				}
			}
		}
		if (!destroyer.goDown && !destroyer.goUp) {
			destroyer.speedY = 0;
		}

		if (user.healthcount <= 0) {
			user.death = true;
		}

	}

	void fireTick() {
		destroyer.fireBulletTick++;
		if (destroyer.fireBulletTick % 50 == 0) {
			destroyer.destroyerFireLock = true;
		} else {
			destroyer.destroyerFireLock = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		for (int j = 0; j < towers.length; j++) {
			if (i == KeyEvent.VK_E && towers[j].enter) {
				buildScene.entered = true;
			}
			if (i == KeyEvent.VK_3 && car.canEnter) {
				car.enter = true;
			}
			if (i == KeyEvent.VK_3 && plane.canEnter) {
				plane.enter = true;
			}

			if (i == KeyEvent.VK_4) {
				car.enter = false;
				car.carSpeed = 0;
			}
			if (i == KeyEvent.VK_Q) {
				buildScene.entered = false;
			}

		}

		if (i == KeyEvent.VK_1 && !iron.track && user.turnRight) {
			wand.drawWand = true;
			user.holdingWeapon = true;
		}

		if (i == KeyEvent.VK_SPACE && user.holdingWeapon) {
			wand.spell = true;
		}

		if (i == KeyEvent.VK_2 && !iron.track) {
			wand.drawWand = false;
			user.holdingWeapon = false;
			wand.spell = false;
		}

		if (i == KeyEvent.VK_B && gui.shieldHP > 0) {
			shield.activateShield = true;
			iron.blast = false;
		}
		if (i == KeyEvent.VK_Z) {
			shield.activateShield = false;
		}

		if (i == KeyEvent.VK_R && !shield.activateShield) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.ammo > 0) {
					gui.ammo--;
					bullets[gui.ammo].bulletFire = true;
				}
			}
		}

		if (i == KeyEvent.VK_F && !iron.ableToTurbo && !iron.ableToTurbo_LEFT && !shield.activateShield) {

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

		if (i == KeyEvent.VK_G && !iron.flyIMG && !iron.flyIMG_LEFT) {

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

		if (i == KeyEvent.VK_C && iron.track) {
			if (user.personY >= 870) {
				iron.track = false;
				power.track = false;
				iron.armorPosX = user.personX - 70;
				user.allowJ = true;
			}
			user.insideSuit = false;
		}
		if (i == KeyEvent.VK_W && !user.nobattery) {
			if (iron.track == true && iron.confirmgroundfire == false && iron.fireonground == false) {
				user.speedY = -5;
				user.allowJ = false;

				if (user.personY >= 870) {
					power.isflyingforbattery = false;
				}
			}
			if (!iron.track && user.personY >= 870) {
				user.jump = true;
			}
		}

		if (i == KeyEvent.VK_S && iron.track == true && !(iron.armorPosY >= 870)) {
			user.allowJ = false;
			user.speedY = 5;

		}

		if (i == KeyEvent.VK_A && !user.death) {

			if (car.enter) {
				movePlayer(car.carSpeed);
				car.carLeft = true;
				car.carRight = false;
				car.accelerate = true;
				car.decelerate = false;
			}

			if (user.holdingWeapon) {
				user.animateLeft = false;
				user.turnRight = true;
				user.animateRight = true;
				user.turnLeft = false;
			} else {
				user.turnLeft = true;
				user.turnRight = false;
				user.animateLeft = true;
				user.animateRight = false;
			}

			iron.blast = false;

			if (!shield.activateShield) {
				iron.ableToTurbo_LEFT = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870 && !car.enter && !plane.enter) {
				movePlayer(6);
			}
			if (!iron.track && !car.enter && !plane.enter) {
				movePlayer(4);
				iron.armorspeed = 2;
			}

			// air
			if (iron.track == true && iron.armorPosY < 870 && !car.enter && !plane.enter) {

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
			} else if (!car.enter && !plane.enter) {
				movePlayer(4);
			}
		}

		if (i == KeyEvent.VK_D && !user.death) {
			if (car.enter) {
				movePlayer(car.carSpeed);
				car.carLeft = false;
				car.carRight = true;
				car.accelerate = true;
				car.decelerate = false;
			}

			if (plane.enter) {
				movePlayer(plane.planeSpeed);
				plane.accelerate = true;
				plane.decelerate = false;
			}

			user.turnLeft = false;
			user.turnRight = true;
			user.animateRight = true;
			user.animateLeft = false;
			iron.blast = false;

			if (!shield.activateShield) {
				iron.ableToTurbo = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870 && !car.enter && !plane.enter) {
				movePlayer(-6);
			}
			if (!iron.track && !car.enter && !plane.enter) {
				movePlayer(-4);
			}
			// air
			if (iron.track == true && iron.armorPosY < 870 && !car.enter && !plane.enter) {

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

			} else if (!car.enter && !plane.enter) {
				movePlayer(-4);
			}

		}

		if (i == KeyEvent.VK_T && iron.ableToTurbo && iron.track) {

			iron.turbo = true;
			iron.flyIMG = false;

			power.turboReducePowerMore = true;

			movePlayer(-82);
		}

		if (i == KeyEvent.VK_Q && iron.ableToTurbo_LEFT && iron.track) {
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

		if (i == KeyEvent.VK_A && user.holdingWeapon) {
			user.animateRight = false;
		}

		if (i == KeyEvent.VK_SPACE && user.holdingWeapon) {
			wand.spell = false;
		}

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
			user.animateRight = false;

			if (user.personY <= 865) {
				iron.fire = true;
			}

			if (car.enter) {
				car.decelerate = true;
				car.accelerate = false;
			}

			if (plane.enter) {
				plane.decelerate = true;
				plane.accelerate = false;
			}

		}

		if (i == KeyEvent.VK_A) {

			iron.flyIMG_LEFT = false;
			iron.ableToTurbo_LEFT = false;
			user.animateLeft = false;

			if (user.personY <= 865) {
				iron.fire = true;
			}

			if (car.enter) {
				car.decelerate = true;
				car.accelerate = false;
			}
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
		Rectangle spell = wand.bounds();
		Rectangle firstBuilding = cityboundary1.bounds();
		Rectangle lastBuilding = cityboundary2.bounds();
		Rectangle Car = car.bounds();
		Rectangle aircraft = plane.bounds();

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

				if (npc.intersects(firstBuilding)) {
					player[k].turnright = true;
					player[k].turnleft = false;
					player[k].speedaddition = 1;
				}

				if (npc.intersects(lastBuilding)) {
					player[k].turnright = false;
					player[k].turnleft = true;
					player[k].speedaddition = -1;
				}

				if (BRec.intersects(npc) && bullets[i].bulletFire) {
					player[k].knockback = true;
					bullets[i].bulletFire = false;
					bullets[i].letdestroy = true;
					if (player[k].healthcount > 0) {
						player[k].healthcount -= bullets[i].damage;
					}
				}

				if (spell.intersects(npc) && wand.spell) {
					player[k].knockback = true;
					if (player[k].healthcount > 0) {
						player[k].healthcount--;
					}
					player[k].oneTimeJump = true;
				}
			}
		}

		for (int j = 0; j < enemyBulletLeft.length; j++) {
			Rectangle destroyerBulletLeft = enemyBulletLeft[j].bounds();
			if (destroyerBulletLeft.intersects(human) && enemyBulletLeft[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcount -= enemyBulletLeft[j].damage;
				}
				enemyBulletLeft[j].letdestroy = true;
			}

			if (destroyerBulletLeft.intersects(ShieldRec) && enemyBulletLeft[j].bulletFire) {
				enemyBulletLeft[j].letdestroy = true;
				if (gui.shieldHP > 0)
					gui.shieldHP -= 2;
			}

		}

		for (int j = 0; j < enemyBulletRight.length; j++) {
			Rectangle destroyerBulletRight = enemyBulletRight[j].bounds();
			if (destroyerBulletRight.intersects(human) && enemyBulletRight[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcount -= enemyBulletRight[j].damage;
				}
				enemyBulletRight[j].letdestroy = true;
			}
			if (destroyerBulletRight.intersects(ShieldRec) && enemyBulletRight[j].bulletFire) {
				enemyBulletRight[j].letdestroy = true;
				if (gui.shieldHP > 0)
					gui.shieldHP -= 2;
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

		if (spell.intersects(boss) && wand.spell) {
			destroyer.knockback = true;
			destroyer.healthcount--;
		}

		for (int i = 0; i < towers.length; i++) {
			Rectangle tower = towers[i].bounds();
			towers[i].enter = false;
			if (human.intersects(tower)) {
				towers[i].enter = true;
			}
		}

		if (human.intersects(Car)) {
			car.canEnter = true;
		} else {
			car.canEnter = false;
		}

		if (human.intersects(aircraft)) {
			plane.canEnter = true;
		} else {
			plane.canEnter = false;
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
