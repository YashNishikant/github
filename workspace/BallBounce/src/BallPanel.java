import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class BallPanel extends JPanel implements ActionListener, KeyListener {

	Timer time = new Timer(5, this);
	int x = 210, speedX = 0;

	public BallPanel() {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	boolean down = true;
	boolean right = true;
	int a = 0;
	int b = 300;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.red);

		// down right
		if (a == 0) {
			down = true;
			right = true;
		}

		// up right
		if (b == 510) {
			down = false;
			right = true;
		}

		// up left
		if (a == 510) {
			down = false;
			right = false;
		}

		// down left
		if (b == 0) {
			down = true;
			right = false;
		}

		if (down == true) {
			b++;
		} else {
			b--;
		}

		if (right == true) {
			a++;
		} else {
			a--;
		}
		g.fillOval(a, b, 50, 50);

		
		g.setColor(Color.gray);
	
		g.fillRect(x, 540, 200, 30);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (x < 0) 
		{
		speedX = 0;	
		x = 0;
		}
		
		if (x > 385) 
		{
		speedX = 0;	
		x = 385;
		}
		
		x = x + speedX;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_D) {

			speedX = 1;

		}

		if (i == KeyEvent.VK_A) {

			speedX = -1;

		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	speedX = 0;
		
	}

	
	
	

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		Container contentpane = frame.getContentPane();
		BallPanel BPanel = new BallPanel();
		
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);
		
		//newPanel.setSize(preferredSize);
		frame.setSize(preferredSize);
		contentpane.add(BPanel);
	
		frame.setVisible(true);
	}
	
}
