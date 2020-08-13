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

	bullet[] b = new bullet[100];
	buildings[] towers = new buildings[700];
	NPC[] player = new NPC[100];
	rain[] raindrop = new rain[200];
	Grenade[] explosive = new Grenade[20];

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();
	Timer time = new Timer(5, this);
	controls gui = new controls();
	battery power = new battery();

	public sandbox() {

		for (int i = 0; i < raindrop.length; i++) {
			rain raindrop1 = new rain();
			raindrop[i] = raindrop1;
		}

		for (int i = 0; i < player.length; i++) {
			NPC player1 = new NPC((int) (Math.random() * 20000) + 200, 1);
			player[i] = player1;
		}

		for (int i = 0; i < b.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			b[i] = b1;
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
			if (explosive[i].bulletFire) {
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

		// bullet
		g.setColor(Color.BLACK);
		for (int i = 0; i < b.length; i++) {
			if (!iron.flyIMG && !iron.turbo && !iron.ableToTurbo_LEFT && !iron.flyIMG_LEFT) {
				b[i].fire();
				if (b[i].bulletFire) {
					ImageIcon i12 = new ImageIcon("C:\\Users\\yash0\\Pictures\\bullet.png");
					i12.paintIcon(this, g, b[i].bulletX, (int) b[i].bulletY + b[i].yoffset);
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

		if (iron.flyIMG) {
			ImageIcon i9 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanFly.png");
			i9.paintIcon(this, g, iron.armorPosX, iron.armorPosY);

		}

		if (iron.turbo) {
			ImageIcon i10 = new ImageIcon("C:\\Users\\yash0\\Pictures\\TURBO.png");
			i10.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}

		if (iron.flyIMG_LEFT) {
			ImageIcon i11 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanFlyLEFT.png");
			i11.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
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
		
			if(raindrop[i].beginRain) {
			
			ImageIcon i13 = new ImageIcon("C:\\Users\\yash0\\Pictures\\rain.png");
			i13.paintIcon(this, g, raindrop[i].raindropX, (int) raindrop[i].raindropY);
			}
		}
	}

	public void contain() {
		for (int i = 0; i < b.length; i++) {
			if (!b[i].bulletFire) {
				b[i].bulletX = iron.armorPosX + 19;
				b[i].bulletY = iron.armorPosY - 5;
			}
		}

		for (int i = 0; i < explosive.length; i++) {
			if (!explosive[i].bulletFire) {
				explosive[i].grenadeX = iron.armorPosX + 19;
				explosive[i].grenadeY = iron.armorPosY + 20;
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

	public void movePlayer(int x) {

		for (int j = 0; j < player.length; j++) {
			player[j].speed = x;
		}
		for (int j = 0; j < towers.length; j++) {
			towers[j].speed = x;
		}
		for (int j = 0; j < explosive.length; j++) {
			explosive[j].grenadeSpeedForPlayer = x;
		}
	}

	public void actionPerformed(ActionEvent e) {
		beginRain();
		for (int i = 0; i < raindrop.length; i++) {
			raindrop[i].rainFall();
		}
		weather();
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
		iron.tracking();
		appropriateImage();
		trackSystem();
		power.batteryfunction();
		cloud.move();
		Collision();
		user.shutdown();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_X) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.grenadeAmmo > 0) {
					gui.grenadeAmmo--;
					explosive[gui.grenadeAmmo].bulletFire = true;
					explosive[gui.grenadeAmmo].heavyDestruction = true;
				}
			}
		}

		if (i == KeyEvent.VK_R) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.ammo > 0) {
					gui.ammo--;
					b[gui.ammo].bulletFire = true;
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

			iron.ableToTurbo_LEFT = true;

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

			iron.ableToTurbo = true;

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

		for (int j = 0; j < explosive.length; j++) {
			Rectangle BRecExplosive = explosive[j].boundsExplosive();

			for (int i = 0; i < b.length; i++) {
				Rectangle BRec = b[i].bounds();

				for (int k = 0; k < player.length; k++) {
					Rectangle npc = player[k].bounds();

					if (BRec.intersects(npc) && b[i].bulletFire) {
						player[k].knockback = true;
						b[i].bulletFire = false;
						b[i].letdestroy = true;
						if (player[k].healthcount > 0) {
							player[k].healthcount -= b[i].damage;
						}
					}

					if (BRecExplosive.intersects(npc) && explosive[j].bulletFire) {
						if (player[k].healthcount > 0) {
							player[k].healthcount -= explosive[j].explosiveDamage;
						}
					}
				}
			}
		}
		if (suit.intersects(human)) {
			iron.track = true;
			user.insideSuit = true;
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
