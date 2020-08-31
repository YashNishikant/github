package battleGame;
import java.awt.Rectangle;

public class Shield {

	int x;
	int y;
	int width;
	int height;

	boolean activateShield;

	public Shield(int x1, int y1) {

		x = x1;
		y = y1;

		width = 10;
		height = 80;
	}

	public Rectangle bounds() {
		if (activateShield) {
			return (new Rectangle(x, y, width, height));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}

}
