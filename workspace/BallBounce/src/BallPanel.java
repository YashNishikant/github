import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

public class BallPanel extends JPanel implements ActionListener, KeyListener {


	Timer time = new Timer(5, this);

	ball bounceBall = new ball();
	paddleR RPaddle = new paddleR();
	paddleL LPaddle = new paddleL();

	int scoreL = 0;
	int scoreR = 0;
	
	int hitNum = 0;
	
	int x = 1;
	
	boolean speedUP = false;
	boolean startScreen = true;
	boolean replay = false;
	
	boolean gWin = false;
	boolean bWin = false;
	
	boolean goingUpL = false;
	boolean goingDownL = false;
	
	boolean goingUpR = false;
	boolean goingDownR = false;
	
	boolean startDirection = true;
	
	public BallPanel() {
		
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		//Score left
		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("TEAM BLUE: " + scoreL, 10, 20);
		
		//Score right		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("TEAM GREEN: " + scoreR, 1700, 20);
		
		//hits tracker
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("Total hits: " + hitNum, 10, 1010);
		
		// ball
		g.setColor(Color.red);
		g.fillOval(bounceBall.xCoordBall, bounceBall.yCoordBall, 50, 50);

		// paddle on the left
		g.setColor(Color.blue);
		g.fillRect(LPaddle.xPaddleL, LPaddle.yPaddleL, 30, 150);

		// paddle on the right
		g.setColor(Color.green);
		g.fillRect(RPaddle.xPaddleR + 30, RPaddle.yPaddleR, 30, 150);
	
		if(startScreen == true) {
			
			gWin = false;
			bWin = false;
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,75));
			g.drawString("PING PONG", 700, 300);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,45));
			g.drawString("Press P to play", 750, 400);	
			
			bounceBall.speedBall = 0;
			
		}
	
		else {
			
			bounceBall.speedBall = x;	
			
		}
		
		if(gWin == true) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,75));
			g.drawString("GREEN WINS", 700, 300);

			g.setColor(Color.green);
			g.fillRect(RPaddle.xPaddleR + 30, RPaddle.yPaddleR, 30, 150);		
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,45));
			g.drawString("Press R to go back to the main menu", 580, 400);	
			
			scoreL = 0; 
			scoreR = 0;
			
			if(replay == true) {
				
			gWin = false;
			bWin = false;
			startScreen = true;
			}
				
		}
		
		if(bWin == true) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,75));
			g.drawString("BLUE WINS", 700, 300);

			g.setColor(Color.blue);
			g.fillRect(LPaddle.xPaddleL, LPaddle.yPaddleL, 30, 150);	
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,45));
			g.drawString("Press R to go back to the main menu", 580, 400);	
		
			scoreL = 0; 
			scoreR = 0;
			
			if(replay == true) {
			
			bWin = false;
			gWin = false;
			startScreen = true;
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (startDirection == true) {
		bounceBall.starting();
		startDirection = false;
		}
		bounceBall.move();
		RPaddle.move();
		LPaddle.move();
		
		Collision();
		
		//Ultimate winner
		
		if(scoreL == 3) {
			
			bWin = true;

		}
		
		if(scoreR == 3) {
			
			gWin = true;
		
		}
		
		//NUMBER OF HITS FOR SPEED UP
		if(hitNum % 5 == 0 && hitNum >= 5 && speedUP == true) {
			
		x += 2;	
		speedUP = false;
		
		}
		
		if(bounceBall.xCoordBall >= 1890) {
			
			scoreL++;
		
			bounceBall.xCoordBall = 900;
			bounceBall.yCoordBall = 480;
			
			startDirection = true;
			
			hitNum = 0;
			x = 1;
		}
		
		if(bounceBall.xCoordBall <= -14) {
			
			scoreR++;
		
			bounceBall.xCoordBall = 900;
			bounceBall.yCoordBall = 480;
			
			startDirection = true;
			
			hitNum = 0;
			x = 1;
		}
		
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_R) {

			replay = true;

		}
		
		if (i == KeyEvent.VK_P) {

			startScreen = false;

		}
		
		if (i == KeyEvent.VK_S) {

			LPaddle.speed = 2;

			goingUpL = false;
			
		}

		if (i == KeyEvent.VK_W) {

			LPaddle.speed = -2;
			goingUpL = true;

		}

		if (i == KeyEvent.VK_DOWN) {

			RPaddle.speed2 = 2;
			goingDownR = true;
			goingUpR = false;

		}

		if (i == KeyEvent.VK_UP) {

			RPaddle.speed2 = -2;
			goingDownR = false;
			goingUpR = true;
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		LPaddle.speed = 0;
		RPaddle.speed2 = 0;

	}

	public BallPanel(int startX, int startY) {

		bounceBall.xCoordBall = startX;
		bounceBall.yCoordBall = startY;

	}

	public void Collision() {
		
	Rectangle ballRec = bounceBall.bounds();
	Rectangle paddleLeft = LPaddle.bounds();
	Rectangle paddleRight = RPaddle.bounds();
	
	if(ballRec.intersects(paddleLeft)) {
		
		bounceBall.right = true;
		
		if(goingUpL == true) 
		bounceBall.down = false;

		if(goingUpL == false) 
		bounceBall.down = true;
		
		hitNum++;
		speedUP = true;
	}
	
	if(ballRec.intersects(paddleRight)) {
		
		bounceBall.right = false;
		
		if(goingUpR == true) 
		bounceBall.down = false;	
		
		if(goingDownR == true) 
		bounceBall.down = true;

		hitNum++;
		speedUP = true;
	}
	
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		BallPanel BPanel = new BallPanel();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(BPanel);

		frame.setVisible(true);
	}

}
