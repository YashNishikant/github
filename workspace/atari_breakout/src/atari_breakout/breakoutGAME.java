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

public class breakoutGAME extends JPanel implements ActionListener, MouseMotionListener {

	paddleClass paddle = new paddleClass();
	paddleL pL = new paddleL();
	paddleR pR = new paddleR();
	ballBreakout ball = new ballBreakout();

	block block1 = new block(200, 200);
	block block2 = new block(400, 200);
	block block3 = new block(600, 200);

	Timer time = new Timer(5, this);

	int yCoordLVL1 = 200;
	int paddleYLoc = 970;

	int blockwidth = 60;
	int blocklength = 60;

	int mouseX;
	boolean mouseMove = false;

	boolean pHit = false;
	boolean pLHit = false;
	boolean pRHit = false;

	boolean collisionlock = true;
	boolean collisionlock2 = true;
	boolean collisionlock3 = true;
	
	boolean collisionlockCheck = true;
	boolean collisionlockCheck2 = true;
	boolean collisionlockCheck3 = true;

	boolean right = true;
	boolean left = true;
	boolean bottom = true;
	boolean top = true;

	public breakoutGAME() {
		time.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// block1----------------------------------------

		if (block1.hitBlock) {
			block1.disappear();
		} else {
			g.setColor(Color.RED);
			g.fillRect(block1.xBlock, yCoordLVL1, 60, 60);
		}

		// block2----------------------------------------

		if (block2.hitBlock) {
			block2.disappear();
		} else {
			g.setColor(Color.RED);
			g.fillRect(block2.xBlock, yCoordLVL1, 60, 60);
		}

		// block3----------------------------------------

		if (block3.hitBlock) {
			block3.disappear();
		} else {
			g.setColor(Color.RED);
			g.fillRect(block3.xBlock, yCoordLVL1, 60, 60);
		}

		// paddle right----------------------------------------
		if (pRHit == true) {
			g.setColor(Color.RED);
			g.fillRect(pR.paddleXR, paddleYLoc, 75, 20);

			if (ball.ballY < 900)
				pRHit = false;
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(pR.paddleXR, paddleYLoc, 75, 20);
		}

		// paddle left----------------------------------------
		if (pLHit == true) {
			g.setColor(Color.RED);
			g.fillRect(pL.paddleXL, paddleYLoc, 75, 20);

			if (ball.ballY < 900)
				pLHit = false;

		} else {
			g.setColor(Color.BLUE);
			g.fillRect(pL.paddleXL, paddleYLoc, 75, 20);
		}

		// paddle mid----------------------------------------

		if (pHit == true) {
			g.setColor(Color.RED);
			g.fillRect(paddle.paddleX, paddleYLoc, 150, 20);

			if (ball.ballY < 900)
				pHit = false;

		} else {
			g.setColor(Color.BLUE);
			g.fillRect(paddle.paddleX, paddleYLoc, 150, 20);
		}
		// ball----------------------------------------
		g.setColor(Color.GRAY);
		g.fillOval(ball.ballX, ball.ballY, 30, 30);

		if (mouseMove) {

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
		block1.hitdetect();

		repaint();

	}

	public boolean BlockComparisonsRight(block b, boolean blocklock) {
		if (ball.ballX >= b.xBlock) {
			if (ball.ballX <= b.xBlock + blockwidth) {
				if (ball.ballY >= b.yBlock) {
					if (ball.ballY <= b.yBlock + blocklength) {
						top = false;
						if (ball.right == false) {
							b.hitBlock = true;
							ball.right = true;
							blocklock = false;
							top = true;
							System.out.println("right");
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsBottom(block b, boolean blocklock) {
		if (ball.ballY >= b.yBlock) {
			if (ball.ballY <= b.yBlock + blocklength) {
				if (ball.ballX >= b.xBlock) {
					if (ball.ballX <= b.xBlock + blockwidth) {
						left = false;
						if (ball.down == false) {
							b.hitBlock = true;
							ball.down = true;
							blocklock = false;
							left = true;
							System.out.println("bottom");
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsTop(block b, boolean blocklock) {
		if (ball.ballY >= b.yBlock - 30) {
			if (ball.ballY <= b.yBlock + blocklength) {
				if (ball.ballX >= b.xBlock) {
					if (ball.ballX <= b.xBlock + blockwidth) {
						if (top == false) {
							if (ball.down == true) {
								b.hitBlock = true;
								ball.down = false;
								blocklock = false;
								System.out.println("top");
							}
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsLeft(block b, boolean blocklock) {
		if (ball.ballX >= b.xBlock - 30) {
			if (ball.ballX <= (b.yBlock + blockwidth)) {
				if (ball.ballY >= b.yBlock) {
					if (ball.ballY <= b.yBlock + blocklength) {
						if (left == true) {
							if (ball.right == true) {
								b.hitBlock = true;
								ball.right = false;
								blocklock = false;
								System.out.println("left");
							}
						}
					}
				}
			}
		}
		return blocklock;
	}

	public void Collision() {

		Rectangle ballRec = ball.bounds();
		Rectangle paddleRec = paddle.bounds();
		Rectangle RECpL = pL.bounds();
		Rectangle RECpR = pR.bounds();
		Rectangle block = block1.bounds();
		Rectangle blocktwo = block2.bounds();
		Rectangle blockthree = block3.bounds();

		// block1-------------------------------------------

		// RIGHT HIT
		if (ballRec.intersects(block) && collisionlock == true) {
			collisionlock = BlockComparisonsRight(block1, collisionlock);
			
		}

		// BOTTOM HIT
		if (ballRec.intersects(block) && collisionlock == true) {
			collisionlock = BlockComparisonsBottom(block1, collisionlock);
		}

		// TOP HIT
		if (ballRec.intersects(block) && collisionlock == true) {
			collisionlock = BlockComparisonsTop(block1, collisionlock);
		}

		// LEFT HIT
		if (ballRec.intersects(block) && collisionlock == true) {
			collisionlock = BlockComparisonsLeft(block1, collisionlock);
		}

		// block2-------------------------------------------

		// RIGHT HIT
		if (ballRec.intersects(blocktwo) && collisionlock2 == true) {

			collisionlock2 = BlockComparisonsRight(block2, collisionlock2);
		}

		// BOTTOM HIT
		if (ballRec.intersects(blocktwo) && collisionlock2 == true) {

			collisionlock2 = BlockComparisonsBottom(block2, collisionlock2);
		}

		// TOP HIT
		if (ballRec.intersects(blocktwo) && collisionlock2 == true) {

			collisionlock2 = BlockComparisonsTop(block2, collisionlock2);
		}

		// LEFT HIT
		if (ballRec.intersects(blocktwo) && collisionlock2 == true) {

			collisionlock2 = BlockComparisonsLeft(block2, collisionlock2);
		}

		// block3---------------------------------------------------

		// RIGHT HIT
		if (ballRec.intersects(blockthree) && collisionlock3 == true) {

			collisionlock3 = BlockComparisonsRight(block3, collisionlock3);
		}

		// BOTTOM HIT
		if (ballRec.intersects(blockthree) && collisionlock3 == true) {

			collisionlock3 = BlockComparisonsBottom(block3, collisionlock3);
		}

		// TOP HIT
		if (ballRec.intersects(blockthree) && collisionlock3 == true) {

			collisionlock3 = BlockComparisonsTop(block3, collisionlock3);
		}

		// LEFT HIT
		if (ballRec.intersects(blockthree) && collisionlock3 == true) {

			collisionlock3 = BlockComparisonsLeft(block3, collisionlock3);
		}

		// block4---------------------------------------------------
		if (ballRec.intersects(RECpL)) {

			ball.down = false;
			ball.leftangled = true;
			ball.right = false;

			pLHit = true;

		}

		if (ballRec.intersects(RECpR)) {

			ball.down = false;
			ball.rightangled = true;
			ball.right = true;

			pRHit = true;

		}

		if (ballRec.intersects(paddleRec)) {

			ball.ballSpeedY = 4;

			ball.down = false;
			pHit = true;
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
		mouseX = e.getX() - 10;

		e.consume();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX() - 10;
		mouseMove = true;
		e.consume();

	}

}
