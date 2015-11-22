package guiElemente;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TransparentSwing {
	
	public static void main(String[] args) {
		final TransparentF tf = new TransparentF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tf.showFrame();
            }
        });
	}

}

class TransparentF {
	private JFrame frame;
	
	@SuppressWarnings("serial")
	public TransparentF() {
		//Create and set up the window.
		frame = new JFrame("TransparentSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the two rectangles
		final Rectangle2D.Double redSquare = new Rectangle2D.Double(10, 10, 20, 20);
		final Rectangle2D.Double blueSquare = new Rectangle2D.Double(20, 20, 20, 20);
		// create panel with blue and red rectangles in it
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				// repeat 11 times
				for(int i=0; i<11; i++) {
					// draw the rectangles with the appropriate alpha value
					drawSquares(g2d, i*0.1F); 
					// shift the coordinate space to the right
					g2d.translate(40, 0);			
				}
			}
			// draw the rectangles
			private void drawSquares(Graphics2D g2d, float alpha) {
				// save the original composite setting
				Composite originalComposite = g2d.getComposite();
				// paint in blue the first rectangle
				g2d.setPaint(Color.BLUE);
				g2d.fill(blueSquare);
				// set new composite with appropriate alpha value for transparency
				// and paint in red the second rectangle
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g2d.setPaint(Color.RED);
				g2d.fill(redSquare);
				// reset to original composite setting
				g2d.setComposite(originalComposite);
				}
		};
		panel.setPreferredSize(new Dimension(450, 100));
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