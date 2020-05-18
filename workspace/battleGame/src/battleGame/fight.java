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

	int ammo = 10;
	int powercount = 0;
	int powerlength = 190;
	
	boolean fire = false;
	boolean create = false;
	
	bullet[] b = new bullet[10];
	target[] t = new target[10];

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();
	Timer time = new Timer(5, this);


	public fight() {

		for (int i = 0; i < b.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			b[i] = b1;
		}

		for (int i = 0; i < t.length; i++) {
			target t1 = new target((int)(Math.random()*3000) + 1900, (int)(Math.random()*800));
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
			
		}
		
		// bullet
		g.setColor(Color.BLACK);
		for (int i = 0; i < b.length; i++) {
			b[i].fire();
			g.fillRect(b[i].bulletX, b[i].bulletY + 30, 7, 3);
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

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 25));
			g.drawString("Ammo: " + ammo, 20, 50);

			//batteryDisplay
			g.setColor(Color.black);
			g.fillRect(1650, 20, 200, 30);	
			//cap
			g.fillRect(1660, 25, 200, 20);
			
			//actualbattery
			g.setColor(Color.white);
			
			if(powerlength <= 38) {
				g.setColor(Color.red);	
			}
			g.fillRect(1655, 25, powerlength, 20);	
		}

		//target
		g.setColor(Color.red);	
		for (int i = 0; i < t.length; i++) {
			g.fillRect(t[i].targetx, t[i].targety, 6, 6);	
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
		
		if(iron.track) {
		powercount++;
		}
		if((powercount % 100 == 0) && powerlength > 0 && iron.canfly) {
			powerlength -=1;
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		targetmove();
		contain();
		user.move();
		user.jump();
		iron.tracking();
		trackSystem();
		batterysystem();
		cloud.move();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

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

		}

		if (i == KeyEvent.VK_V) {

			iron.armorPosX = user.personX;
			iron.track = true;
		}

		if (i == KeyEvent.VK_C) {
			if (user.personY == 870) {
				iron.track = false;
			}
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
				user.speedX = -4;
			else {
				user.speedX = -2;
			}

		}

		if (i == KeyEvent.VK_D) {

			if (iron.track == true)
				user.speedX = 4;
			else {
				user.speedX = 2;
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (iron.ignore == false) {

			user.speedX = 0;
			user.speedY = 0;
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
