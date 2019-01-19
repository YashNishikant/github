import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;

public class BounceBall{

	static GraphicsConfiguration gc;

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame(gc);
		
		Container contentpane = frame.getContentPane();
		BallPanel BPanel = new BallPanel();
		
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);
		
		//newPanel.setSize(preferredSize);
		frame.setSize(preferredSize);
		contentpane.add(BPanel);
	
		frame.setVisible(true);
		
		while(true)
		{
			BPanel.repaint();
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}
}