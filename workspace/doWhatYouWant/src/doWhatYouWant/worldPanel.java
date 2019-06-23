package doWhatYouWant;

import java.awt.Color;

import java.awt.Graphics;


import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

public class worldPanel extends JPanel implements ActionListener, KeyListener {

	boolean right2 = true;
	boolean right = true;
	Timer time = new Timer(5, this);
	int x = 210, speedX = 0, y = 490, speedY = 0, a = 100, b = 700, c = 7, d = 25;

	public worldPanel() {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.fillRect(30, 10, 90, 90);
		
		g.setColor(Color.cyan);
		
		g.fillRect(0, 0, 7000, 7000);
		
		g.setColor(Color.green);
		
		g.fillRect(-100, 970, 7000, 80);
			
		g.setColor(Color.yellow);
		
		g.fillRect(30, 10, 90, 90);
		
		g.setColor(Color.DARK_GRAY);
		
		g.fillRect(x, y, 30, 60);
		
		g.setColor(Color.LIGHT_GRAY);
		
		g.fillRect(x, y, 30, 30);
		
		g.setColor(Color.white);
		
		g.fillRect(x, y + 8, 10, 5);
		
		g.setColor(Color.white);
		
		g.fillRect(x + 20, y + 8, 10, 5);
		
		g.setColor(Color.cyan);
		
		g.fillRect(x, y + 20, 5, 10);
		
		g.setColor(Color.cyan);
		
		g.fillRect(x + 25, y + 20, 5, 10);
		
		g.setColor(Color.white);
		
		g.fillRect(x + 12, y + 40, 8, 8);
		
		g.setColor(Color.gray);
		
		g.fillRect(x + 3, y + 61, 7, 25);
		
		g.setColor(Color.gray);
		
		g.fillRect(x + 19, y + 61, 7, 25);
		
		//left arm
		g.setColor(Color.gray);
		
		g.fillRect(x + 31, y - 30 + 60, c, d);
		
		//right arm
		g.setColor(Color.gray);
		
		g.fillRect(x - 8, y - 30 + 60, 7, 25);
		
		if (a == 100) {
			
		right = true;
		}
		
		if (b == 700) {
			
		right2 = true;
		}
		
		if (right = true) {
			
		a = a + 1;
		}
		
		if (right2 = true) {
			
		b = b + 1;
		}
		
		g.setColor(Color.white);
		g.fillRect(a, 100, 140, 30);
		
		if(a == 2000) {
			
		a = -900;	
		}
	
		
		g.fillRect(b, 70, 90, 20);
		
		if(b == 3000) {
			
		b = -900;	
		}
		
		
	
		
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
		if (x < 0) 
		{
		speedX = 0;	
		x = 0;
		
		}
		
		if (x > 1900) 
		{
		speedX = 0;	
		x = 1900;
		}
		
		x = x + speedX;

		if (y > 885) 
		
		{
		
		speedY = 0;	
		y = 885;
		
		}
		
		if (y < 0) 
		
		{
		
		speedY = 0;	
		y = 0;
		
		}
		
		y = y + speedY;
		
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
	
		
		
		if (i == KeyEvent.VK_W) {

		speedY = -2;
		
		}
		
		if (i == KeyEvent.VK_S) {

		speedY = 2;
		
		}
		
		
		if (i == KeyEvent.VK_V) {

		c = 25;
		d = 7;
					
		}
	
	
		if (i == KeyEvent.VK_B) {

			c = 7;
			d = 25;
						
			}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	speedX = 0;
	speedY = 0;	
	}
}
	