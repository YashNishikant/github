import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallPanel extends JPanel {

	boolean up = false;

	int a = 50;
	int b = 100;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.red);
		
		if (up == false) {
			g.fillOval(a--, b++, 50, 50);
		}

		else {
			g.fillOval(a--, b++, 50, 50);
		}

		if (a == 510 || b == 510) {
			up = true;
		}
		
		if (a == 0 || b == 0) {
			up = false;
		}
		
	}
}
