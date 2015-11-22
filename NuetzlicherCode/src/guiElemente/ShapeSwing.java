package guiElemente;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ShapeSwing {
	
		public static void main(String[] args) {
			final ShapeF sf = new ShapeF();
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                sf.showFrame();
	            }
	        });
		}

	}

	class ShapeF {
		private JFrame frame;

		@SuppressWarnings("serial")
		public ShapeF() {
			//Create and set up the window.
			frame = new JFrame("ShapeSwing");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container contentPane = frame.getContentPane();
			// create panel with circle in it using random numbers for the color
			JPanel panelOval = new JPanel() {
				public void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D)g;
					Line2D.Double l1 = new Line2D.Double(10, 10, 80, 10);
					g2d.draw(l1);
					Rectangle2D.Double r1 = new Rectangle2D.Double(10, 15, 70, 10);
					g2d.setColor(Color.blue);
					g2d.fill(r1);
					Line2D.Double l2 = new Line2D.Double(10, 60, 80, 60);
					g2d.draw(l2);
					Rectangle2D.Double r2 = new Rectangle2D.Double(10, 65, 70, 10);
					g2d.setColor(Color.blue);
					g2d.draw(r2);
					Rectangle2D.Double r3 = new Rectangle2D.Double(10,65,70, 10);
					g2d.setColor(Color.red);
					g2d.fill(r3);
					g2d.setStroke(new BasicStroke(5));
					Line2D.Double l3 = new Line2D.Double(10, 115, 80, 115);
					g2d.draw(l3);
					Rectangle2D.Double r4 = new Rectangle2D.Double(10, 125, 70, 10);
					g2d.setColor(Color.blue);
					g2d.draw(r4);
					Rectangle2D.Double r5 = new Rectangle2D.Double(10,140,70, 10);
					g2d.setColor(Color.red);
					g2d.fill(r5);
					}
			};
			panelOval.setPreferredSize(new Dimension(300, 300));
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