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

	boolean fire = false;

	bullet[] b = new bullet[100];
	target[] t = new target[100];
	buildings[] towers = new buildings[50];

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();
	Timer time = new Timer(5, this);
	controls gui = new controls();
	battery power = new battery();

	public fight() {

		for (int i = 0; i < b.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			b[i] = b1;
		}

		for (int i = 0; i < t.length; i++) {
			target t1 = new target((int) (Math.random() * 4000) + 2900, (int) (Math.random() * 800));
			t[i] = t1;
		}

		for (int i = 0; i < towers.length; i++) {
			buildings building = new buildings(((int) (Math.random() * 30000) + 1));
			towers[i] = building;
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
			towers[i].draw(g);
			ImageIcon i5 = new ImageIcon("C:\\Users\\yash0\\Pictures\\buildingIMG.png");
			i5.paintIcon(this, g, towers[i].movingsurrounding1, towers[i].bY);
		}

		cloud.draw(g);

		if (!iron.track) {

			user.draw(g);
			iron.normal = true;
			iron.fire = false;

			gui.draw(g);

			ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
			i2.paintIcon(this, g, user.personX, user.personY + 10);

			if (power.powerlength <= 0) {
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

	public void batterydecrease() {
		if (user.insideSuit && iron.armorPosY <= 869) {
			power.isflyingforbattery = true;
		} else {
			power.isflyingforbattery = false;
		}
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < towers.length; i++) {
			towers[i].move();
		}

		iron.move();
		batterydecrease();
		targetmove();
		contain();
		user.move();
		user.jump();
		iron.tracking();
		trackSystem();
		power.batteryfunction();
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

			for (int j = 0; j < t.length; j++)
				t[j].move = true;
		}

		if (i == KeyEvent.VK_R) {
			if (iron.blast) {
				if (gui.ammo > 0) {
					gui.ammo--;
					fire = true;
					b[gui.ammo].bulletFire = true;
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
			power.track = true;
			user.insideSuit = true;
		}

		if (i == KeyEvent.VK_C) {
			if (user.personY >= 870) {
				iron.track = false;
				power.track = false;
			}
			user.insideSuit = false;
		}
		if (i == KeyEvent.VK_W) {

			if (iron.track == true) {
				user.speedY = -4;

				if (user.personY >= 870) {
					power.isflyingforbattery = false;
				}
			}

			if (!iron.track) {
				user.jump = true;

			}
		}

		if (i == KeyEvent.VK_S && iron.track == true) {

			user.speedY = 6;

		}

		if (i == KeyEvent.VK_A) {

			if (iron.track == true)
				for (int j = 0; j < towers.length; j++) {
					towers[j].speed = 6;
				}
			else {
				for (int j = 0; j < towers.length; j++) {
					towers[j].speed = 2;
				}
				iron.armorspeed = 2;
			}

		}

		if (i == KeyEvent.VK_D) {

			if (iron.track == true)
				for (int j = 0; j < towers.length; j++) {
					towers[j].speed = -6;
				}
			else {
				for (int j = 0; j < towers.length; j++) {
					towers[j].speed = -2;
				}
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
		for (int j = 0; j < towers.length; j++) {
			towers[j].speed = 0;
		}

	}

	public void Collision() {

		for (int i = 0; i < b.length; i++) {
			Rectangle BRec = b[i].bounds();
			for (int j = 0; j < t.length; j++) {
				Rectangle TRec = t[j].bounds();

				if (BRec.intersects(TRec) && b[i].bulletFire) {
					t[j].letDestroy = true;
					b[i].letdestroy = true;
					gui.hitcount++;
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
