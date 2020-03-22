  package battleGame;

import java.awt.Color;
import java.awt.Component;
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

public class fight extends JPanel implements ActionListener, KeyListener{

	int a = 50, b = -800, c = -1000;
	int mechX = 210; 
	int mechY = 865; 
	int fireNum = 61;
	int speedX = 5;
	int speedY = 5;
	int armMoveX = 5;
	int armMoveY = 25;
	
	boolean cloudMove = true;
	Timer time = new Timer(5, this);
	
	public fight() {
		
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Sky
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 2000, 2000);
		
		//Ground
		g.setColor(Color.gray);
		g.fillRect(0, 950, 2000, 2000);
		
		//Clouds
		g.setColor(Color.white);
		g.fillRect(a, 60, 200, 40);
		
		g.setColor(Color.white);
		g.fillRect(b, 120, 200, 40);
		
		g.setColor(Color.white);
		g.fillRect(c, 80, 200, 40);
		
		//mechIMG
		g.setColor(Color.black);
		
		//main
		g.fillRect(mechX + 5, mechY + 30, 20, 30);
		
		//eyes
		g.setColor(Color.white);
		g.fillRect(mechX + 5, mechY + 16, 5, 5);			
		g.fillRect(mechX + 20, mechY + 16, 5, 5);			
		
		//fire
		g.setColor(Color.red);	
		g.fillRect(mechX + 5, mechY + fireNum, 5, 15);		
		g.fillRect(mechX + 19, mechY + fireNum, 5, 15);		
		
		//legs
		g.setColor(Color.blue);	
		g.fillRect(mechX + 5, mechY + 61, 5, 25);		
		g.fillRect(mechX + 19, mechY + 61, 5, 25);		
	
		//arms
		g.setColor(Color.blue);		
		g.fillRect(mechX + 26, mechY + 30, armMoveX, armMoveY);	
		g.fillRect(mechX - 2, mechY + 30, 5, 25);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(mechY < 865)
		fireNum = 85;
		
		if(mechY >=865)
		fireNum = 61;
			
		if(mechY >= 865) {
			
		mechY = 865;	
			
		}
		
		//CLOUDS
		a+=2;
		b+=2;
		
		if(a == 2000) {			
		a = -90;			
		}
		
		if(b == 2000) {			
		b = -100;			
		}

		if(c == 2000) {			
		c = -1600;			
		}		
		
		//CLOUDS		
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_V) {

			armMoveX = 25;	
			armMoveY = 5;
		}

		if (i == KeyEvent.VK_C) {

			armMoveX = 5;	
			armMoveY = 25;
		}
		
		if (i == KeyEvent.VK_W) {

			mechY = mechY - speedY;	

		}

		if (i == KeyEvent.VK_S) {

			mechY = mechY + speedY;
			
		}

		if (i == KeyEvent.VK_A) {

			mechX = mechX - speedX;
			
		}

		if (i == KeyEvent.VK_D) {

			mechX = mechX + speedX;
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		fight BPanel = new fight();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(BPanel);

		frame.setVisible(true);
	}

}
