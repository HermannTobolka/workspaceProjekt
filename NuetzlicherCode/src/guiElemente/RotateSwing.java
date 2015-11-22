package guiElemente;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class RotateSwing {
	
	public static void main(String[] args) {
		final RotateF rf = new RotateF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                rf.showFrame();
            }
        });
	}

}

class RotateF {
	private JFrame frame;
	private Color[] colors = {Color.white, Color.black};
	
	@SuppressWarnings("serial")
	public RotateF() {
		//Create and set up the window.
		frame = new JFrame("RotateSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the circle
		final Ellipse2D.Double circle = new Ellipse2D.Double(10, 10, 400, 400);
		// create panel with circle and text in it
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
		        g2d.setPaint(new GradientPaint(0, 0, Color.red, 175, 175, Color.yellow, true));
		        g2d.fill(circle);
		        g2d.setPaint(Color.black);
		        g2d.setStroke(new BasicStroke(8));
		        g2d.draw(circle);
				// set Font and draw String
				g2d.setFont(new Font("VerdanaT", Font.PLAIN, 90));
			    // Move the origin to the center of the circle.
			    g2d.translate(210.0, 210.0);
			    for (int i=0; i<16; i++) {
			      // Rotate the coordinate system around current
			      // origin, which is at the center of the circle.
			      g2d.rotate(Math.PI/8.0);
			      g2d.setPaint(colors[i%2]);
			      g2d.drawString("Java", 0, 0);
			    }
			}
		};
		panel.setPreferredSize(new Dimension( 420, 420));
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
