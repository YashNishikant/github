package freeworld;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import Item.Coin;
import Structures.AirBlock;
import Structures.JumpPlatform;
import Structures.Platform;
import Structures.StoneBlock;
import engine.engine;
import engine.mouseClicker;
import player.Player;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	int spacingX = 0;
	int spacingY = 0;
	boolean onground;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int cameraSpeed = 1;
	int coinnum;
	int randomColor = randomInt(1, 2);
	boolean flip;
	int rotate;

	int playerx;
	int playery;
	
	boolean swing;
	
	mouseClicker click = new mouseClicker();
	ArrayList<Platform> blocks = new ArrayList<Platform>();

	Miscellaneous game = new Miscellaneous();
	Player user = new Player(50, 73);

	Timer time = new Timer(5, this);
	String assetsPath;
	ArrayList<JumpPlatform> ju = new ArrayList<JumpPlatform>();
	ArrayList<Coin> Coins = new ArrayList<Coin>();

	JumpPlatform ju1 = new JumpPlatform(0, 0);
	Platform block1;
	AirBlock air1;
	StoneBlock sb1;
	Coin c1;

	String mapGen = "------------------------a" + 
					"########################a" + 
					".######################.a" + 
					".##############......##.a" + 
					".-----------####....###.a" + 
					".----------#####...####.a" + 
					".-###############.#####.a" + 
					".-#####################.a" + 
					".----------##########...a" + 
					".--##--##---#########...a" + 
					".--##--##----########...a" + 
					".--##--##-----#######...a" + 
					"........................a";

	public sandbox() {

		for (char ch : mapGen.toCharArray()) {
			if (ch == '#') {
				air1 = new AirBlock(spacingX, spacingY);
				blocks.add(air1);
				spacingX += 80;
			}
			if (ch == 'a') {
				air1 = new AirBlock(spacingX, spacingY);
				blocks.add(air1);
				spacingY += 80;
				spacingX = 0;
			}
			if (ch == '.') {
				block1 = new Platform(spacingX, spacingY);
				blocks.add(block1);
				spacingX += 80;
			}

			if (ch == '-') {
				sb1 = new StoneBlock(spacingX, spacingY);
				blocks.add(sb1);
				spacingX += 80;
			}

		}
		for (int i = 0; i < 300; i++) {
			if (blocks.get(i).getSolid() && (blocks.get(i + 25).getSolid())) {
				blocks.get(i + 25).setDirtTexture(true);
				blocks.get(i + 25).stopCollisionTop(true);
			}
		}

		for (int i = 0; i < mapGen.length(); i++) {
			if (blocks.get(i).getSolid() && (blocks.get(i + 1).getSolid())) {
				blocks.get(i + 1).stopCollisionLeft(true);
				blocks.get(i).stopCollisionRight(true);
			}
		}

		spacingX = (int) ((blocks.get(0).width));

		for (int i = 0; i < 4; i++) {
			randomColor = randomInt(1, 2);
			c1 = new Coin(100 + spacingX, 100);
			spacingX += block1.width;
			if (randomColor == 1) {
				c1.setGem(true);
			} else {
				c1.setGem(false);
			}
			Coins.add(c1);

		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		for (Coin c : Coins) {
			c.gravity(DOWNWARD_FORCE);
		}
		moveforceHorizontal(user.speed);
		user.gravity(DOWNWARD_FORCE);
		jumpPadGravity();
		user.applyForceVertical(jumpStrength);
		Collision();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.drawBackground(g);
		drawJumps(g);
		for (Coin c : Coins) {
			c.draw(g);
		}
		drawBlocks(g);
		user.draw(g);
		addText(g, "Coins: " + coinnum, (int) 10, (int) 150, 20);

		BufferedImage knight = LoadImage("Player.png");
		BufferedImage sword = LoadImage("Sword.png");
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform at = AffineTransform.getTranslateInstance(playerx, playery);
		
		if (flip) {
			g2d.drawImage(knight, (int) (user.x + user.width), (int) (user.y), (int) -user.width, (int) user.height, null);
			if(!swing) {
				g2d.drawImage(sword, (int) (user.x + user.width) - 15, (int) (user.y) - 15, 25, 75, null);
			}
			playerx = (int)(user.x) + 40;
			playery = (int)(user.y);
			
			rotate = -rotate;
			
		} else {
			g2d.drawImage(knight, (int) (user.x), (int) (user.y), (int) user.width, (int) user.height, null);
			if(!swing) {
				g2d.drawImage(sword, (int) (user.x) - 15, (int) (user.y) - 15, 25, 75, null);
			}
			playerx = (int)(user.x) - 10;
			playery = (int)(user.y);
		}
		if(swing && !flip) {
			
			at.rotate(Math.toRadians(-rotate), 25/1.8, 75/1.8);
			g2d.drawImage(sword, at, null);
			rotate+=10;
			
			if(rotate >= 90) {
				
				rotate -= 10;
				rotate = 0;
				swing = false;

			}
		}
	
		if(swing && flip) {
			
			at.rotate(Math.toRadians(rotate), 25/1.8, 75/1.8);
			g2d.drawImage(sword, at, null);
			rotate+=10;
			
			if(rotate >= 90) {
				
				rotate -= 10;
				rotate = 0;
				swing = false;

			}
		}
		
	}

	BufferedImage LoadImage(String FileName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(FileName));
		} catch (IOException e) {
			System.out.println("no");
		}
		return img;
	}

	public void jumpPadGravity() {
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).gravity(DOWNWARD_FORCE);
		}
	}

	public void drawJumps(Graphics g) {
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).draw(g);
		}
	}

	public void drawBlocks(Graphics g) {

		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).x > 0 - blocks.get(i).width || blocks.get(i).x < 1920) {
				blocks.get(i).draw(g);
			}
		}
	}

	public void moveforceHorizontal(double speed) {
		user.applyForceHorizontal(speed);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_R) {
			user.x = 100;
			user.y = 100;
		}

		if (i == KeyEvent.VK_SPACE && onground) {
			jumpStrength = 10;
			user.forceUp = true;
			user.setForce = true;

		}

		if (i == KeyEvent.VK_A && !disableleft) {
			user.forceHorizontalR = true;
			flip = false;
		}

		if (i == KeyEvent.VK_D && !disableright) {
			user.forceHorizontalL = true;
			flip = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			user.forceHorizontalL = false;
		}
		if (i == KeyEvent.VK_A) {
			user.forceHorizontalR = false;
		}
	}

	public void Collision() {

		Rectangle player = user.bounds();
		for (int i = 0; i < ju.size(); i++) {
			Rectangle jumpPlatform = ju.get(i).bounds();

			if (player.intersects(jumpPlatform)) {
				user.fallingFactor = 0;
			}
		}

		for (int j = 0; j < blocks.size(); j++) {
			blocks.get(j).setForce = false;
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).setForce = false;
		}
		onground = false;

		for (int j = 0; j < blocks.size(); j++) {

			Rectangle recTop = blocks.get(j).bounds1();
			Rectangle recLeft = blocks.get(j).bounds2();
			Rectangle recRight = blocks.get(j).bounds3();
			Rectangle recBottom = blocks.get(j).bounds4();

			for (int k = 0; k < Coins.size(); k++) {
				Rectangle collectable = Coins.get(k).bounds();

				if (player.intersects(collectable)) {
					Coins.remove(k);
					coinnum++;
				}

				// TOP COLLISION COIN
				if (collectable.intersects(recTop)) {
					Coins.get(k).y = blocks.get(j).y - Coins.get(k).getHeight() + 2;
					Coins.get(k).fallingFactor = 0;
					Coins.get(k).gravityActivate = false;
				}

			}

			// TOP COLLISION
			if (player.intersects(recTop)) {
				user.y = blocks.get(j).y - user.height + 1;
				user.fallingFactor = 0;
				onground = true;
			}
			// LEFT COLLISION
			if (player.intersects(recLeft)) {
				user.x = blocks.get(j).x - user.width;
				disableright = true;
			} else {
				disableright = false;
			}

			// RIGHT COLLISION
			if (player.intersects(recRight)) {
				user.x = blocks.get(j).x + blocks.get(j).width;
				disableleft = true;
			} else {
				disableleft = false;
			}

			// BOTTOM COLLISION
			if (player.intersects(recBottom)) {
				user.y = blocks.get(j).y + blocks.get(j).height;
				user.stopJump();
			}

		}
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		swing = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//swing = false;
	}
}
