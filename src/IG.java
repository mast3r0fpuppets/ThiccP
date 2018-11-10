import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class IG {
	private JFrame frame;
	
	public IG() {
		frame = new JFrame("Interface Grafica");
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
	}
	
	public void open() {
		frame.setVisible(true);
	}
	
	private void addFrameContent() {
		
	}

}