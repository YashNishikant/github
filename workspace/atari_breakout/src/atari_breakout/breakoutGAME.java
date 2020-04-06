package atari_breakout;

import java.awt.Color; 
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;


public class breakoutGAME  extends JPanel implements ActionListener, MouseMotionListener {
	
	paddleClass paddle = new paddleClass();
	paddleL pL = new paddleL();
	paddleR pR = new paddleR();
	ballBreakout ball = new ballBreakout();
	
	Timer time = new Timer(5, this);	
	
	int mouseX;
	boolean mouseMove = false;
	
	boolean pHit = false;
	boolean pLHit = false;
	boolean pRHit = false;
	
	public breakoutGAME() {
		time.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//paddle right
		if(pRHit == true) {
			g.setColor(Color.RED);
			g.fillRect(pR.paddleXR, 970, 75, 20);	
			
			if(ball.ballY < 900)
			pRHit = false;	
		}
		else {
			g.setColor(Color.BLUE);
			g.fillRect(pR.paddleXR, 970, 75, 20);
		}
		
		//paddle left
		if(pLHit == true) {
			g.setColor(Color.RED);
			g.fillRect(pL.paddleXL, 970, 75, 20);
		
			if(ball.ballY < 900)
			pLHit = false;
		
		}
		else {
			g.setColor(Color.BLUE);
			g.fillRect(pL.paddleXL, 970, 75, 20);
		}
		
		//paddle mid
		if(pHit == true) {
		g.setColor(Color.RED);
		g.fillRect(paddle.paddleX, 970, 150, 20);
		
		if(ball.ballY < 900)		
		pHit = false;
		
		}
		else {		
			g.setColor(Color.BLUE);	
			g.fillRect(paddle.paddleX, 970, 150, 20);	
		}
		//ball
		g.setColor(Color.GRAY);
		g.fillOval(ball.ballX, ball.ballY, 30, 30);
		
		if(mouseMove) {

			paddle.paddleX = mouseX;
			pR.paddleXR = mouseX + 150;
			pL.paddleXL = mouseX - 75;
	
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		paddle.move();
		pL.moveL();
		pR.moveR();
		
		ball.move();
		Collision();
		repaint();
		
	}
	
	public void Collision() {
		
	Rectangle ballRec = ball.bounds();
	Rectangle paddleRec = paddle.bounds();
	Rectangle RECpL = pL.bounds();
	Rectangle RECpR = pR.bounds();
	
	if(ballRec.intersects(paddleRec)) {
	
		ball.ballSpeedY = 4;
		ball.down = false;
		
		pHit = true;
	}

	if(ballRec.intersects(RECpL)) {
		
		ball.down = false;
		ball.leftangled = true;
		ball.right = false;

		pLHit = true;

	}
	
	if(ballRec.intersects(RECpR)) {
		
		ball.down = false;
		ball.rightangled = true;
		ball.right = true;
		
		pRHit = true;
		
	}
	
	}
	
	public static void main(String[] args) {
		
		breakoutGAME mouse = new breakoutGAME();
		
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		breakoutGAME bPanel = new breakoutGAME();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(bPanel);

		frame.setVisible(true);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX()-10;
		
		e.consume();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX()-10;
		mouseMove = true;
		e.consume();
		
	}
	
}