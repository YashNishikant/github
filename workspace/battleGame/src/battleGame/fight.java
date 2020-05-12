package battleGame;

import java.awt.Color; 
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

public class fight extends JPanel implements ActionListener, KeyListener{

	human user = new human();
	clouds cloud = new clouds();
	armor iron = new armor();

	
	Timer time = new Timer(5, this);
	
	private ImageIcon image;
	private JLabel label;
	
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
		g.fillRect(cloud.cloud1start, 60, 200, 40);
		
		g.setColor(Color.white);
		g.fillRect(cloud.cloud2start, 120, 200, 40);
		
		g.setColor(Color.white);
		g.fillRect(cloud.cloud3start, 80, 200, 40);
		
		//mechIMG
		g.setColor(Color.black);
		
		//main
		g.fillRect(user.personX, user.personY + 30, 20, 30);
		
		//legs
		g.setColor(Color.blue);	
		g.fillRect(user.personX + 12, user.personY + 61, 7, 19);		
		g.fillRect(user.personX + 1, user.personY + 61, 7, 19);		
	
		//arms
		g.setColor(Color.blue);		
		g.fillRect(user.personX + 21, user.personY + 30, 7, 25);	
		g.fillRect(user.personX - 8, user.personY + 30, 7, 25);
		
		ImageIcon i2 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
		i2.paintIcon(this, g, user.personX, user.personY+10);
		
		ImageIcon i = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanNOFire.png");
		i.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		
		if(iron.fire) {
		ImageIcon i3 = new ImageIcon("C:\\Users\\yash0\\Pictures\\ironmanSuitJavaCanvasIMG.png");
		i3.paintIcon(this, g, iron.armorPosX, iron.armorPosY);
		}
	}

	public void trackSystem() {
		
		if(iron.track) {
			iron.armorPosX = user.personX-12;
			iron.armorPosY = user.personY + 8;
		}		
		
	}
	
	public void actionPerformed(ActionEvent e) {
			
		user.move();
		user.jump();
		iron.tracking();
		trackSystem();
		cloud.move();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_V) {
		
			iron.armorPosX = user.personX;
			iron.track = true;
		}

		if (i == KeyEvent.VK_C) {
			if(user.personY == 870) {
				iron.track = false;
			}
		}
		if (i == KeyEvent.VK_W) {
			
			if(iron.track == true) {
			user.speedY = -4;	
			}
		
			if(!iron.track) {
			user.jump = true;	
				
			}
		}
		
		if (i == KeyEvent.VK_S && iron.track == true) {

			user.speedY = 4;	
			
		}

		if (i == KeyEvent.VK_A) {

			if(iron.track == true)
				user.speedX = -4;
			else {
				user.speedX = -2;	
			}
			
		}

		if (i == KeyEvent.VK_D) {

			if(iron.track == true)
				user.speedX = 4;
			else {
				user.speedX = 2;	
			}
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(iron.ignore == false) {
			
			user.speedX = 0;
			user.speedY = 0;
		}
		
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

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
