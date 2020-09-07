package structures;

import java.awt.Graphics;

import battleGame.Textures;

public class Airport extends Textures{

	public double X;
	public double Y;
	public double illusionSpeed;
	
	public Airport() {
		X = (int)(Math.random()* 20000);
		Y = -36;
		illusionSpeed = 0;
	}
	
	public void move() {
		X += illusionSpeed;
	}
	
	public void draw(Graphics g) {
		addImage(g, "//Backgrounds//Airport.png", (int) X, (int) Y);
	}
	
}
