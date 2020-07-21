package battleGame;


import java.awt.Color;
import java.awt.Graphics;

public class battery {

	int powercount = 0;
	int powerlength = 190;
	
	boolean isflyingforbattery = false;
	boolean track = false;
	
	public battery() {
		
		powercount = 0;
		powerlength = 190;
		
	}
	public void batteryfunction() {
		
		if (track) {
			powercount++;
		}
		if ((powercount % 100 == 0) && powerlength > 0 && isflyingforbattery) {
			powerlength -= 1;
		}
		
	}
	
	public void draw(Graphics g) {
	
		// batteryDisplay
		g.setColor(Color.black);
		g.fillRect(1650, 20, 200, 30);
		
		// cap
		g.setColor(Color.BLACK);
		g.fillRect(1660, 25, 200, 20);
		
		g.setColor(Color.white);
		if (powerlength <= 38) {
			g.setColor(Color.red);
		}
		g.fillRect(1655, 25, powerlength, 20);

		
	}
	
}