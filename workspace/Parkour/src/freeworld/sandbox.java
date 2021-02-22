package freeworld;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Structures.JumpPlatform;
import Structures.Platform;
import engine.Physics;
import engine.engine;
import engine.mouseClicker;
import player.Player;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine
		implements ActionListener, MouseMotionListener, KeyListener, MouseListener, ComponentListener {

	int spacing = 0;
	boolean onground;
	boolean lock = true;
	double jumpStrength;

	boolean disableleft;
	boolean disableright;

	boolean worldGen = true;
	boolean hitJump = false;

	boolean oneTime = true;

	int cameraSpeed = 1;

	Physics Physics = new Physics();
	mouseClicker click = new mouseClicker();
	Platform[] block = new Platform[500];

	Miscellaneous game = new Miscellaneous();
	Player user = new Player(50, 50);

	Timer time = new Timer(5, this);
	String assetsPath;
	ArrayList<JumpPlatform> ju = new ArrayList<JumpPlatform>();
	JumpPlatform ju1 = new JumpPlatform(0, 0);

	public sandbox() {

		int x = randomInt(1, 2);
		for (int i = 0; i < block.length; i++) {
			Platform block1 = new Platform(spacing, 1000);
			block[i] = block1;
			spacing += randomInt(900, 1500);
		}
		for (int i = 1; i < block.length; i++) {
			if (x == 1) {
				block[i].y = block[i - 1].y + randomInt(40, 150);
			} else if (block[i - 1].y < 700) {
				block[i].y = block[i - 1].y - randomInt(40, 150);
			}

			x = randomInt(1, 2);

		}

		time.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void actionPerformed(ActionEvent e) {
		misc();
		moveforceHorizontal(user.speed);
		gravity();
		cameraFollow();
		Collision();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.drawBackground(g);
		drawJumps(g);
		drawBlocks(g);
		user.draw(g);
	}

	public void misc() {

	}

	public void drawJumps(Graphics g) {
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).draw(g);
		}
	}

	public void cameraFollow() {
		if ((int) user.y > user.yCoord) {
			for (int i = 0; i < block.length; i++) {
				block[i].y -= cameraSpeed;

			}
			user.y -= cameraSpeed;
		}

		if ((int) user.y < user.yCoord) {

			for (int i = 0; i < block.length; i++) {
				block[i].y += cameraSpeed;

			}
			user.y += cameraSpeed;
		}

//		if ((int) user.x > 700) {
//			for (int x = 0; x < block.length; x++) {
//				block[x].x += -cameraSpeed;
//				user.x += -cameraSpeed;
//			}
//		}
//
//		if ((int) user.x < 700) {
//			for (int x = 0; x < block.length; x++) {
//				block[x].x += cameraSpeed;
//				user.x += cameraSpeed;
//			}
//		}
	}

	public void drawBlocks(Graphics g) {

		for (int i = 0; i < block.length; i++) {
			if (block[i].x > 0 - block[i].width || block[i].x < 1920) {
				block[i].draw(g);
			}
		}
	}

	public void gravity() {
		moveforceHorizontal(user.speed);
		for (int i = 0; i < block.length; i++) {
			block[i].applyForceVerticalDOWN(jumpStrength);
			block[i].gravityInverse(-DOWNWARD_FORCE);
		}
		for (int i = 0; i < ju.size(); i++) {
			if (user.force <= 0) {
				ju.get(i).applyForceVerticalDOWN(jumpStrength);
				ju.get(i).gravityInverse(-DOWNWARD_FORCE);
			}
		}
	}

	public void moveforceHorizontal(double speed) {
		for (int i = 0; i < block.length; i++) {
			block[i].applyForceHorizontal(speed);
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).applyForceHorizontal(speed);
		}
	}

	public void movePlayerL() {
		if (!disableleft) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontalL = true;
			}
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontalR = false;
			}
			for (int j = 0; j < ju.size(); j++) {
				ju.get(j).forceHorizontalL = true;
			}
			for (int j = 0; j < ju.size(); j++) {
				ju.get(j).forceHorizontalR = false;
			}
		}
	}

	public void movePlayerR() {
		if (!disableleft) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontalL = false;
			}
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontalR = true;
			}
			for (int j = 0; j < ju.size(); j++) {
				ju.get(j).forceHorizontalL = false;
			}
			for (int j = 0; j < ju.size(); j++) {
				ju.get(j).forceHorizontalR = true;
			}
		}
	}

	public void jump() {
		for (int j = 0; j < block.length; j++) {
			block[j].forceUp = true;
			block[j].setForce = true;
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).setForce = true;
			ju.get(i).forceUp = true;
		}

	}

	public void stopVertical() {
		for (int i = 0; i < block.length; i++) {
			block[i].fallingFactor = 0;
			block[i].setForce = true;
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).fallingFactor = 0;
			ju.get(i).setForce = true;
		}
	}

	public void stopHorizontal() {

		for (int j = 0; j < block.length; j++) {
			block[j].forceHorizontalL = false;
		}
		for (int j = 0; j < block.length; j++) {
			block[j].forceHorizontalR = false;
		}
		for (int j = 0; j < ju.size(); j++) {
			ju.get(j).forceHorizontalL = false;
		}
		for (int j = 0; j < ju.size(); j++) {
			ju.get(j).forceHorizontalR = false;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_R) {

			if (ju.size() >= 1) {
				ju.remove(0);
			}

			ju.add(ju1);
			ju.get(ju.size() - 1).x = user.x + user.width + 10;
			ju.get(ju.size() - 1).y = user.y + user.height - 20;
		}

		if (i == KeyEvent.VK_SPACE) {
			if (block[1].setForce) {
				jump();
				jumpStrength = 15;
			}
		}

		if (i == KeyEvent.VK_A && !disableleft) {
			movePlayerL();
		}

		if (i == KeyEvent.VK_D && !disableright) {
			movePlayerR();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			stopHorizontal();
		}
		if (i == KeyEvent.VK_A) {
			stopHorizontal();
		}
	}

	public void Collision() {

		Rectangle player = user.bounds();
		for (int i = 0; i < ju.size(); i++) {
			Rectangle jumpPlatform = ju.get(i).bounds();

			if (player.intersects(jumpPlatform)) {
				jumpStrength = 28;
				jump();
			}
		}

		for (int j = 0; j < block.length; j++) {
			block[j].setForce = false;
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).setForce = false;
		}
		onground = false;
		
		for (int j = 0; j < block.length; j++) {
			Rectangle rec = block[j].bounds();

			if (player.intersects(rec)) {

				if ((user.y + user.height / 5) < block[j].y) {
					user.y = block[j].y - user.height + 1;
					stopVertical();
				}
				// LEFT COLLISION
				if ((user.y > block[j].y) && (user.x < block[j].x)) {
					//stopHorizontal();
					user.x = block[j].x - user.width;
					disableright = true;
				}
				else {
					disableright = false;
				}
				// RIGHT COLLISION
				if ((user.y > block[j].y) && (user.x > block[j].x)) {
					//stopHorizontal();
					user.x = block[j].x + block[j].width;
					disableleft = true;
				}
				else {
					disableleft = false;
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void componentHidden(ComponentEvent arg0) {

	}

	public void componentMoved(ComponentEvent arg0) {

	}

	public void componentResized(ComponentEvent e) {
		Dimension newSize = e.getComponent().getBounds().getSize();

	}

	public void componentShown(ComponentEvent arg0) {

	}

	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		double screensizeX = screenSize.getWidth();
		double screensizeY = screenSize.getHeight();

		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		sandbox sPanel = new sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(screensizeX, screensizeY);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
