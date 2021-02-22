import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class testing extends JPanel implements ActionListener, KeyListener {

	Color c = new Color(255, 0, 0);
	Color c1 = new Color(0, 0, 0);
	block Block = new block();
	double perspective = 1;
	int sideSpeed = 15;
	public testing() {

		Timer time = new Timer(5, this);
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(Block.scale);
		Block.move();
		Block.moveLines();
		Block.moveLinesSide();
		Block.moveSide();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setColor(g, c);
		drawRect(g, Block.x, Block.y, -(Block.width * Block.scale), -(Block.height * Block.scale));
		setColor(g, c1);
		g.drawLine(1920, 1015, (int) Block.x, (int) Block.y);
		g.drawLine(0, 1015, (int) Block.endptX, (int) Block.endptY);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W) {
			if (Block.scale > 0.01) {
				Block.scale += perspective / 50;
				Block.x += perspective;
				Block.y += perspective;
				Block.endptX -= perspective;
				Block.endptY += perspective;
			}
		}
		if (i == KeyEvent.VK_S) {
			if (Block.scale > 0.02) {
				Block.scale -= perspective / 50;
				Block.x -= perspective;
				Block.y -= perspective;
				Block.endptX += perspective;
				Block.endptY -= perspective;
			}
		}
		if (i == KeyEvent.VK_A) {
			Block.speedSide = sideSpeed;
			Block.speedLinesSides = sideSpeed;
		}
		if (i == KeyEvent.VK_D) {
			Block.speedSide = -sideSpeed;
			Block.speedLinesSides = -sideSpeed;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Block.speed = 0;
		Block.speedSide = 0;
		Block.speedLinesSides = 0;
	}

	public void setColor(Graphics g, Color c) {
		g.setColor(c);
	}

	public void drawRect(Graphics g, double x, double y, double width, double height) {
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		testing Panel = new testing();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(Panel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}