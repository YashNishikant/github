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

import Structures.Block;
import Structures.Map;
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
	Miscellaneous game = new Miscellaneous();
	Player player = new Player(100, 100);
	Map map = new Map();
	Block[] block = new Block[2000];

	Timer time = new Timer(5, this);
	String assetsPath;

	String assetspath;
	
	public sandbox() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		
		for (int i = 0; i < block.length; i++) {
			Block block1 = new Block();
			block[i] = block1;
			
			block[i].x = randomInt(-18000,18000);
			block[i].y = randomInt(-10000,10000);
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
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.drawBackground(g);
		player.draw(g);
		blocksDraw(g);

	}

	public void blocksDraw(Graphics g) {
		for (int i = 0; i < block.length; i++) {
			block[i].draw(g);
		}
	}

	public void keepWorldMoving() {
		movePlayerHorizontal(player.speedx);
		movePlayerVertical(player.speedy);
	}

	public void movePlayerHorizontal(double speed) {
		for (int i = 0; i < block.length; i++) {
			block[i].applyForceHorizontal(speed);
		}
		map.applyForceHorizontal(speed);
	}

	public void movePlayerVertical(double speed2) {
		for (int i = 0; i < block.length; i++) {
			block[i].applyForceVertical(speed2);
		}
		map.applyForceVertical(speed2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceVertical = true;
			}
				map.forceVertical = true;
			player.speedy = player.speedY;

		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = true;
			}
				map.forceHorizontal = true;
			player.speedx = player.speedX;

		}
		if (i == KeyEvent.VK_S) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceVertical = true;
			}
				map.forceVertical = true;
			player.speedy = -player.speedY;

		}

		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = true;
			}
				map.forceHorizontal = true;
			player.speedx = -player.speedX;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceVertical = false;
				map.forceVertical = false;
			}
		}
		if (i == KeyEvent.VK_S) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceVertical = false;
				map.forceVertical = false;
			}
		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = false;
				map.forceHorizontal = false;
			}
		}
		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = false;
				map.forceHorizontal = false;
			}
		}
	}

	public void Collision() {

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
