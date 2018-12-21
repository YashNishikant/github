import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class ShapePanel extends JPanel {

	boolean myBool = true;	
	
	@Override
	public void paintComponent(Graphics g) {

		g.fillOval(50, 100, 75, 75);
		g.fillOval(400, 100, 75, 75);
		

		if (myBool)
		{
			g.setColor(Color.white);
			myBool = false;
		}
		else
		{
			g.setColor(Color.black);
			myBool = true;
		}
		
		g.fillOval(70, 120, 30, 30);
		
		g.fillOval(420, 120, 30, 30);
		
		g.setColor(Color.red);
		g.fillOval(170, 300, 200, 50);

		
	}
}