package battleGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class controls extends JPanel {

	int ammo;
	int enemyAmmoL;
	int enemyAmmoR;
	int hitcount;
	int greenblue;
	int grenadeAmmo;
	boolean darkenSky;
	int knockbackRNG;
	int shieldHP;
	public boolean beginRain = false;

	String assetsPath;

	public controls(int x) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		hitcount = 0;
		ammo = 100;
		enemyAmmoL = 99;
		enemyAmmoR = 99;
		greenblue = 250;
		grenadeAmmo = 20;
		shieldHP = 190;
	}

	public void naturaldrawings(Graphics g) {
		if (!beginRain) {
			addImage(g, "//Backgrounds//Sky.png", 0, 0);
		}

		else {
			addImage(g, "//Backgrounds//RainSky.png", 0, 0);
		}
		
	}

	public void addImage(Graphics g, String s, int x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, x, (int) y);
	}

	public void drawArmor(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(20, 182, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 184, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("F Ready Blaster", 24, 200);

		g.setColor(Color.BLACK);
		g.fillRect(20, 232, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 234, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("R Fire Bullets", 24, 250);

		g.setColor(Color.BLACK);
		g.fillRect(20, 132, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 134, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("X Fire Explosives", 24, 150);

		g.setColor(Color.BLACK);
		g.fillRect(20, 282, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 284, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("C Exit Suit", 24, 300);

		g.setColor(Color.BLACK);
		g.fillRect(20, 332, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 334, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("G Unready Blaster", 24, 350);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString("Ammo: " + ammo, 20, 50);

//		g.setColor(Color.BLACK);
//		g.setFont(new Font("default", Font.BOLD, 25));
//		g.drawString("Explosive Ammo: " + grenadeAmmo, 20, 80);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.drawString("Battery", 1650, 15);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.drawString("Shield HP", 1650, 115);

		// battery
		g.setColor(Color.black);
		g.fillRect(1650, 120, 200, 30);
		// cap
		g.setColor(Color.BLACK);
		g.fillRect(1660, 125, 200, 20);

		g.setColor(Color.GREEN);
		g.fillRect(1655, 125, shieldHP, 20);

	}

}