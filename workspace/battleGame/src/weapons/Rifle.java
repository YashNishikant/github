package weapons;
import java.awt.Graphics;

import engine.Textures;

public class Rifle extends Textures{
	
	public double X;
	public int Y;

	public boolean ready;
	public boolean fire;
	public boolean fireweapon;
	public int fireCount;
	
	public Rifle() {
		X = 0;
		Y = 0;
	}

	public void draw(Graphics g) {
		if(ready) {
			addImage(g, "Weapons//AR.png", X, Y);
		}
	
		if(fireweapon) {
			addImage(g, "//Weapons//fireWeapon.png", X, Y);
		}
		
	}
}
