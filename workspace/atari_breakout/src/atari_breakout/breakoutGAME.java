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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;


public class breakoutGAME  extends JPanel implements ActionListener, KeyListener {

	//add to paddle class

	paddleClass paddle = new paddleClass();
	ballBreakout ball = new ballBreakout();
	
	Timer time = new Timer(5, this);	
	
	public breakoutGAME() {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// paddle
		g.setColor(Color.cyan);
		g.fillRect(paddle.paddleX, 970, 300, 20);
		
		//ball
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(ball.ballX, ball.ballY, 30, 30);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		paddle.move();
		ball.move();
		Collision();
		repaint();
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if (i == KeyEvent.VK_A) {

			paddle.speedPaddle = -4;

		}
		
		if (i == KeyEvent.VK_D) {

			paddle.speedPaddle = 4;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		paddle.speedPaddle = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	
		
	}

	public breakoutGAME(int startX, int startY) {

		ball.ballX = startX;
		ball.ballY = startY;

	}
	
	public void Collision() {
		
	Rectangle ballRec = ball.bounds();
	Rectangle paddleRec = paddle.bounds();

	
	if(ballRec.intersects(paddleRec)) {
		
		ball.down = false;
		
	}
}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		breakoutGAME bPanel = new breakoutGAME();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(bPanel);

		frame.setVisible(true);
		
	}
	
}
