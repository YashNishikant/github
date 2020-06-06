
package secondworld;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;
import java.awt.Rectangle;

@SuppressWarnings("serial")

public class sandboxEXPERIMENTAL extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener {

	int buildspacing = 0;
	int knockbackRNG = 0;
	int mouseX;
	int xmouse;
	int ymouse;
	boolean fire = false;
	boolean startScreen = true;
	boolean changecolor = false;
	boolean collide = false;

	bullet[] b = new bullet[100];
	target[] t = new target[100];

	human user = new human();
	armor iron = new armor();
	Timer time = new Timer(5, this);
	controls gui = new controls();
	clicker c = new clicker();
	playbutton p = new playbutton();

	public sandboxEXPERIMENTAL() {
		for (int i = 0; i < b.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			b[i] = b1;
		}

		for (int i = 0; i < t.length; i++) {
			target t1 = new target((int) (Math.random() * 4000) + 2900, (int) (Math.random() * 800));
			t[i] = t1;
		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseMotionListener(this);
		addMouseListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		gui.naturaldrawings(g);

		if (!iron.track) {

			user.draw(g);
			iron.normal = true;
			iron.fire = false;

			gui.draw(g);
			ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
			i2.paintIcon(this, g, user.personX, user.personY + 10);

		} else {

			gui.drawArmor(g);

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

		if (iron.confirmgroundfire) {
			ImageIcon i4 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanBlastOnGround.png");
			i4.paintIcon(this, g, iron.armorPosX, iron.armorPosY);

		}

		// target
		g.setColor(Color.red);

		for (int i = 0; i < t.length; i++) {
			g.fillRect(t[i].targetx, t[i].targety, 30, 30);
		}

		if (startScreen == true) {

			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("IRON MAN", 700, 300);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("SIMULATOR", 670, 400);

			 g.setColor(Color.green);
			if (changecolor) {
			g.setColor(Color.red);
			}
			g.fillOval(p.x, p.y, 80, 80);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("Play", 900, 600);
			g.fillRect(c.xclick, c.yclick, 10, 10);
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

	public void actionPerformed(ActionEvent e) {
		iron.move();
		targetmove();
		contain();
		user.move();
		user.jump();
		iron.tracking();
		trackSystem();
		Collision();
		for (int i = 0; i < t.length; i++) {
			t[i].destroy();
		}
		for (int i = 0; i < t.length; i++) {
			b[i].destroy();
		}
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
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
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
			user.insideSuit = true;
		}

		if (i == KeyEvent.VK_C) {
			if (user.personY >= 870) {
				iron.track = false;
				iron.armorPosX = -5;
			}
			user.insideSuit = false;
		}
		if (i == KeyEvent.VK_W) {

			if (iron.track == true && iron.confirmgroundfire == false && iron.fireonground == false) {
				user.speedY = -5;
			}

			if (!iron.track) {
				user.jump = true;

			}
		}

		if (i == KeyEvent.VK_S && iron.track == true && !(iron.armorPosY >= 870)) {

			user.speedY = 5;

		}

		if (i == KeyEvent.VK_A) {
			user.speedX = -5;
		}

		if (i == KeyEvent.VK_D) {
			user.speedX = 5;
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
		user.speedX = 0;
	}

	public void Collision() {

		Rectangle suit = iron.bounds();
		Rectangle human = user.bounds();
		Rectangle button = p.bounds();
		Rectangle mouseREC = c.bounds();

		if (button.intersects(mouseREC)) {
			collide = true;
		} else {
			collide = false;
		}

		for (int i = 0; i < b.length; i++) {
			Rectangle BRec = b[i].bounds();

			for (int j = 0; j < t.length; j++) {
				Rectangle TRec = t[j].bounds();

				if (BRec.intersects(TRec) && b[i].bulletFire) { // the (&& b[i].bulletFire) is to make sure that the
																// interaction only occurs when bullet is fired
					t[j].letDestroy = true;
					b[i].letdestroy = true;
					gui.hitcount++;
				}
			}
		}

		if (suit.intersects(human)) {
			iron.track = true;
		}

	}

	/*
	 * public static void main(String[] args) {	
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		sandboxEXPERIMENTAL sPanel = new sandboxEXPERIMENTAL();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	*/
	@Override
	public void mouseDragged(MouseEvent e) {
		xmouse = e.getX() - 10;
		ymouse = e.getY() - 10;

		e.consume();

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		xmouse = e.getX() - 10;
		ymouse = e.getY() - 10;

		c.xclick = xmouse;
		c.yclick = ymouse;

		e.consume();

		if (collide) {
			changecolor = true;
		}
		else {
			changecolor = false;
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(changecolor) {
			startScreen = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
