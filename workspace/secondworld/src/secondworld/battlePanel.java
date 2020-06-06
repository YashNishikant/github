package secondworld;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;

public class battlePanel {

	static GraphicsConfiguration gc;

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame(gc);

		Container contentpane = frame.getContentPane();
		sandboxEXPERIMENTAL sPanel = new sandboxEXPERIMENTAL();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);

		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * while (true) { 
		 *	sPanel.repaint(); 
		 *	TimeUnit.MILLISECONDS.sleep(1); 
		 * }
		 */
	}
}
