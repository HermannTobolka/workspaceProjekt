package guiElemente;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Graphics2DShapeSwing {
	public static void main(String[] args) {
		final Graphics2DLineF g2dlf = new Graphics2DLineF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                g2dlf.showFrame();
            }
        });

	}

}

class Graphics2DLineF {
	private JFrame frame;

	@SuppressWarnings("serial")
	public Graphics2DLineF() {
		//Create and set up the window.
		frame = new JFrame("Graphics2DSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create panel with circle in it using random numbers for the color
		JPanel panelOval = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				Rectangle2D.Double rect = new Rectangle2D.Double(70, 70, 100,100);
				g2d.setPaint(Color.red);
				g2d.fill(rect);
				g2d.setPaint(Color.black);
				g2d.draw(rect);
				g2d.translate(150, 0);
				g2d.setPaint(Color.red);
				g2d.fill(rect);
				g2d.setStroke(new BasicStroke(8));
				g2d.setPaint(Color.black);
				g2d.draw(rect);
				g2d.setStroke(new BasicStroke(1));
				g2d.setPaint(Color.green);
				g2d.draw(rect);
			}
		};
		panelOval.setPreferredSize(new Dimension(500, 300));
		// set border layout and add everything to the frame
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panelOval, BorderLayout.CENTER);
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