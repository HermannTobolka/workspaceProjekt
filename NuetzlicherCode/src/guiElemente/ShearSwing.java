package guiElemente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ShearSwing {

	public static void main(String[] args) {
		final ShearF sf = new ShearF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sf.showFrame();
            }
        });
	}

}

class ShearF {
	private JFrame frame;
	private static int gap=10, width=100;

	@SuppressWarnings("serial")
	public ShearF() {
		//Create and set up the window.
		frame = new JFrame("ShearSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the rectangle
		final Rectangle rect = new Rectangle(gap, gap, 100, 100);
		// create panel with circle and text in it
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON));
				for (int i=0; i<5; i++) {
					// draw rectangle
					g2d.setPaint(Color.RED);
					g2d.fill(rect);
					// Each new square gets 0.2 more x shear
					g2d.shear(0.2, 0.0);
					g2d.translate(2*gap + width, 0);
				}
			}
		};
		panel.setPreferredSize(new Dimension( 700, 150));
		// add everything to the frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}
	
}
