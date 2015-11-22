package guiElemente;
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


public class StringFontSwing {
	
	public static void main(String[] args) {
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		String[] fontNames = env.getAvailableFontFamilyNames();
//		for(String fontN: fontNames)
//			System.out.println(fontN);
		final StringFontF sff = new StringFontF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                sff.showFrame();
            }
        });
	}

}

class StringFontF {
	private JFrame frame;
	
	@SuppressWarnings("serial")
	public StringFontF() {
		//Create and set up the window.
		frame = new JFrame("StringFontSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the circle
		final Ellipse2D.Double circle = new Ellipse2D.Double(10, 10, 400, 400);
		// create panel with circle and text in it
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				// create circle outline and circle filled with gradient color
				GradientPaint gradient = new GradientPaint(0, 0, Color.RED, 175, 175, Color.YELLOW, true);
				g2d.setPaint(gradient);
				g2d.fill(circle);
				g2d.setPaint(Color.BLACK);
				g2d.draw(circle);
				// set Font and draw String
				g2d.setFont(new Font("VerdanaT", Font.PLAIN, 100));
				g2d.setPaint(Color.BLACK);
				g2d.drawString("Java 2D", 25, 250);
			}
		};
		panel.setPreferredSize(new Dimension(420, 420));
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