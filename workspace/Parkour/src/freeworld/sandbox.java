package freeworld;

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
import javax.swing.Timer;

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
	boolean onBlock = false;

	Physics Physics = new Physics();
	mouseClicker click = new mouseClicker();
	Platform[] block = new Platform[50];

	Miscellaneous game = new Miscellaneous();
	Player user = new Player(100, 100);

	Timer time = new Timer(5, this);
	String assetsPath;

	public sandbox() {

		for (int i = 0; i < block.length; i++) {
			Platform block1 = new Platform(500, 80, spacing);
			block[i] = block1;
			spacing += (int) (Math.random() * 700) + 500;
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

		keepWorldMoving();
		Collision();
		user.gravity(DOWNWARD_FORCE);
		user.applyForceVertical(5);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.drawBackground(g);
		drawBlocks(g);
		user.draw(g);
	}

	public void drawBlocks(Graphics g) {
		for (int i = 0; i < block.length; i++) {
			block[i].draw(g);
		}
	}

	public void keepWorldMoving() {
			movePlayer(user.speed);
	}

	public void movePlayer(double speed) {
			for (int i = 0; i < block.length; i++) {
				block[i].applyForceHorizontal(speed);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_SPACE) {
			user.forceUp = true;
			user.setForce = true;
		}

		if (i == KeyEvent.VK_A) {
			user.speed = 6;
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = true;
			}
		}

		if (i == KeyEvent.VK_D) {
			user.speed = -6;
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = false;
			}
		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = false;
			}
		}

		if (i == KeyEvent.VK_R) {
			user.y = 100;
			user.fallingFactor = 0;
		}

	}

	public void Collision() {

		Rectangle player = user.bounds();
		user.setForce = false;
		for (int j = 0; j < block.length; j++) {

			Rectangle top = block[j].boundsTop();
			Rectangle left = block[j].boundsLeft();
			Rectangle right = block[j].boundsRight();

			onBlock = false;
			
			if (player.intersects(top)) {
				user.fallingFactor = 0;
				user.setForce = true;
				onBlock = true;
			}
			if (player.intersects(left)) {
				for (int x = 0; x < block.length; x++) {
					block[x].speedObject = 0;	
					user.speed = 0;
				}
				
				user.x = block[j].x - user.width - 0.001;

			}
			if (player.intersects(right)) {
				for (int x = 0; x < block.length; x++) {
					block[x].speedObject = 0;
					user.speed = 0;
				}
				
				user.x = block[j].x + block[j].width + 0.001;
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension newSize = e.getComponent().getBounds().getSize();
		// System.out.println("1");
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

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
