package battleGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

public class fight extends JPanel implements ActionListener, KeyListener {

	int ammo = 100;
	int powercount = 0;
	int powerlength = 190;
	int hitcount = 0;

	boolean fire = false;
	boolean create = false;

	bullet[] b = new bullet[100];
	target[] t = new target[100];

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();
	Timer time = new Timer(5, this);
	map building = new map();
	
	public fight() {

		for (int i = 0; i < b.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			b[i] = b1;
		}

		for (int i = 0; i < t.length; i++) {
			target t1 = new target((int) (Math.random() * 4000) + 2900, (int) (Math.random() * 800) );
			t[i] = t1;
		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Sky
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 2000, 2000);
		
		building.draw(g);
		
		// Ground
		g.setColor(Color.gray);
		g.fillRect(0, 950, 2000, 2000);

		// Clouds
		g.setColor(Color.white);
		g.fillRect(cloud.cloud1start, 60, 200, 40);

		g.setColor(Color.white);
		g.fillRect(cloud.cloud2start, 120, 200, 40);

		g.setColor(Color.white);
		g.fillRect(cloud.cloud3start, 80, 200, 40);

		if (!iron.track) {
			
			iron.normal = true;
			iron.fire = false;
			
			//CONTROL
			g.setColor(Color.BLACK);
			g.fillRect(6, 22, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(8, 24, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("V Equip Armor", 10, 40);
			
			g.setColor(Color.BLACK);
			g.fillRect(6, 72, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(8, 74, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("W Jump", 7, 90);

			// mechIMG
			g.setColor(Color.black);
			g.fillRect(user.personX, user.personY + 30, 20, 30);

			// legs
			g.setColor(Color.blue);
			g.fillRect(user.personX + 12, user.personY + 61, 7, 19);
			g.fillRect(user.personX + 1, user.personY + 61, 7, 19);

			// arms
			g.setColor(Color.blue);
			g.fillRect(user.personX + 21, user.personY + 30, 7, 25);
			g.fillRect(user.personX - 8, user.personY + 30, 7, 25);

			ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
			i2.paintIcon(this, g, user.personX, user.personY + 10);

			if(powerlength <= 0) {
			g.setColor(Color.RED);
			g.setFont(new Font("default", Font.BOLD, 25));
			g.drawString("!", iron.armorPosX, iron.armorPosY);	
			}
		}

		// bullet
		g.setColor(Color.BLACK);
		for (int i = 0; i < b.length; i++) {
			b[i].fire();
			b[i].draw(g);
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

		if (iron.track) {
			
			//CONTROLS
			g.setColor(Color.BLACK);
			g.fillRect(20, 182, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(22, 184, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("F Ready Blaster", 24, 200);
			
			g.setColor(Color.BLACK);
			g.fillRect(20, 232, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(22, 234, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("R Fire Bullets", 24, 250);
			
			g.setColor(Color.BLACK);
			g.fillRect(20, 282, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(22, 284, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("C Exit Suit", 24, 300);
			
			g.setColor(Color.BLACK);
			g.fillRect(20, 332, 22, 22);
			g.setColor(Color.CYAN);
			g.fillRect(22, 334, 18, 18);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("G Unready Blaster", 24, 350);

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 25));
			g.drawString("Ammo: " + ammo, 20, 50);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 25));
			g.drawString("Target Hits: " + hitcount, 20, 100);
			
			// batteryDisplay
			g.setColor(Color.black);
			g.fillRect(1650, 20, 200, 30);
			
			// cap
			g.setColor(Color.BLACK);
			g.fillRect(1660, 25, 200, 20);
			
			if(powerlength <= 0) {
		
				g.setColor(Color.RED);
				g.setFont(new Font("default", Font.BOLD, 25));
				g.drawString("!          !          !", 1660, 45);	
				user.nobattery = true;
			
				g.setColor(Color.RED);
				g.setFont(new Font("default", Font.BOLD, 25));
				g.drawString("!", iron.armorPosX, iron.armorPosY);	
				
			}
		
			// actualbattery
			g.setColor(Color.white);

			if (powerlength <= 38) {
				g.setColor(Color.red);
			}
			g.fillRect(1655, 25, powerlength, 20);
		}

		// target
		g.setColor(Color.red);

		for (int i = 0; i < t.length; i++) {
			g.fillRect(t[i].targetx, t[i].targety, 30, 30);
		}

	}

	public void targetmove() {

		for (int i = 0; i < t.length; i++) {
			t[i].move();
		}

	}

	public void contain() {
		for (int i = 0; i < b.length; i++) {
			if (!b[i].bulletFire) {
				b[i].bulletX = iron.armorPosX + 19;
				b[i].bulletY = iron.armorPosY - 5;
			}
		}
	}

	public void trackSystem() {

		if (iron.track) {
			iron.armorPosX = user.personX - 12;
			iron.armorPosY = user.personY + 8;
		}
	}

	public void batterysystem() {

		if (iron.track) {
			powercount++;
		}
		if ((powercount % 100 == 0) && powerlength > 0 && iron.canfly) {
			powerlength -= 1;
		}

	}

	public void actionPerformed(ActionEvent e) {
		building.move();
		iron.move();
		targetmove();
		contain();
		user.move();
		user.jump();
		iron.tracking();
		trackSystem();
		batterysystem();
		cloud.move();
		Collision();
		for (int i = 0; i < t.length; i++) {
			t[i].destroy();
		}
		for (int i = 0; i < t.length; i++) {
			b[i].destroy();
		}
		user.shutdown();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_P) {
			
			for(int j = 0; j < t.length; j++)
			t[j].move = true;
		}
		
		if (i == KeyEvent.VK_R) {
			if (iron.blast) {
				if (ammo > 0) {
					ammo--;
					fire = true;
					b[ammo].bulletFire = true;
				}
			}
		}

		if (i == KeyEvent.VK_F) {

			if (iron.track) {
				
				iron.blast = true;
				iron.normal = false;
				iron.fire = false;
				iron.activatefire = false;
			}
		}

		if (i == KeyEvent.VK_G) {

			iron.blast = false;
			iron.fire = true;
			iron.normal = false;
		}

		if (i == KeyEvent.VK_V) {

			iron.armorPosX = user.personX;
			iron.track = true;
			user.insideSuit = true;
		}

		if (i == KeyEvent.VK_C) {
			if (user.personY >= 870) {
				iron.track = false;
			}
			user.insideSuit = false;
		}
		if (i == KeyEvent.VK_W) {

			if (iron.track == true) {
				user.speedY = -4;
			}

			if (!iron.track) {
				user.jump = true;

			}
		}

		if (i == KeyEvent.VK_S && iron.track == true) {

			user.speedY = 4;

		}

		if (i == KeyEvent.VK_A) {

			if (iron.track == true)
				building.speed = 4;
			else {
				building.speed = 2;
				iron.armorspeed = 2;
			}

		}

		if (i == KeyEvent.VK_D) {

			if (iron.track == true)
				building.speed = -4;
			else {
				building.speed = -2;
				iron.armorspeed = -2;
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (iron.ignore == false) {
			user.speedY = 0;
		}

		iron.armorspeed = 0;
		building.speed = 0;
	}

	public void Collision() {

		for (int i = 0; i < b.length; i++) {
			Rectangle BRec = b[i].bounds();
			for (int j = 0; j < t.length; j++) {
				Rectangle TRec = t[j].bounds();

				if (BRec.intersects(TRec) && b[i].bulletFire) {
					t[j].letDestroy = true;
					b[i].letdestroy = true;
					hitcount++;
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		fight BPanel = new fight();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(BPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
