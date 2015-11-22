package guiElemente;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class LineSwing {
	
	public static void main(String[] args) {
		final LineF lf = new LineF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lf.showFrame();
            }
        });
	}

}

class LineF {
	private JFrame frame;
	private int[] caps = { BasicStroke.CAP_SQUARE, BasicStroke.CAP_BUTT, BasicStroke.CAP_ROUND };
	private String[] capNames = { "CAP_SQUARE", "CAP_BUTT", "CAP_ROUND" };
	private int[] joins = { BasicStroke.JOIN_MITER, BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_ROUND };
	private String[] joinNames = { "JOIN_MITER", "JOIN_BEVEL", "JOIN_ROUND" };
	private GeneralPath path;
	private static int x = 30, deltaX = 150, y = 300, deltaY = 250, thickness = 40;
	private Ellipse2D.Double p1Large, p1Small, p2Large, p2Small, p3Large, p3Small;
	private AlphaComposite transparentComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F);

	
	@SuppressWarnings("serial")
	public LineF() {
		//Create and set up the window.
		frame = new JFrame("LineSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the circle
		final Ellipse2D.Double circle = new Ellipse2D.Double(10, 10, 100, 100);
		// create panel with circle and text in it
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setPaint(Color.green);
				g2d.setStroke(new BasicStroke(8));
				g2d.draw(circle);
				g2d.translate(120, 0);
				g2d.setPaint(Color.blue);
				// 30-pixel line, 10-pixel gap, 10-pixel line, 10-pixel gap
				float[] dashPattern = { 30, 10, 10, 10 };
				g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
				g2d.draw(circle);
				g2d.translate(-120, 120);
				lineStyles(g2d);
				g2d.setColor(Color.lightGray);
				for(int i=0; i<caps.length; i++) {
					g2d.setStroke(new BasicStroke(thickness, caps[i], joins[i]));
					g2d.draw(path);
					labelEndPoints(g2d, capNames[i], joinNames[i]);
					g2d.translate(3*x + 2*deltaX, 0);
				}
			}
			public void lineStyles(Graphics2D g2d) {
				path = new GeneralPath();
				path.moveTo(x, y);
				p1Large = new Ellipse2D.Double(x - thickness/2, y - thickness/2, 2.0*thickness/2, 2.0*thickness/2);
				p1Small = new Ellipse2D.Double(x - 2, y - 2, 2.0*2, 2.0*2);
				path.lineTo(x + deltaX, y - deltaY);
				p2Large = new Ellipse2D.Double(x + deltaX - thickness/2, y - deltaY - thickness/2, 2.0*thickness/2, 2.0*thickness/2);
				p2Small = new Ellipse2D.Double(x + deltaX - 2, y - deltaY - 2, 2.0*2, 2.0*2);
				path.lineTo(x + 2*deltaX, y);
				p3Large = new Ellipse2D.Double(x + 2*deltaX - thickness/2, y - thickness/2, 2.0*thickness/2, 2.0*thickness/2);
				p3Small = new Ellipse2D.Double(x + 2*deltaX - 2, y - 2, 2.0*2, 2.0*2);
				g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
			}

			// Draw translucent circles to illustrate actual end points.
			// Include text labels for cap/join style.
			private void labelEndPoints(Graphics2D g2d, String capLabel,
					String joinLabel) {
				Paint origPaint = g2d.getPaint();
				Composite origComposite = g2d.getComposite();
				g2d.setPaint(Color.black);
				g2d.setComposite(transparentComposite);
				g2d.fill(p1Large);
				g2d.fill(p2Large);
				g2d.fill(p3Large);
				g2d.setPaint(Color.yellow);
				g2d.setComposite(origComposite);
				g2d.fill(p1Small);
				g2d.fill(p2Small);
				g2d.fill(p3Small);
				g2d.setPaint(Color.black);
				g2d.drawString(capLabel, x + thickness - 5, y + 5);
				g2d.drawString(joinLabel, x + deltaX + thickness - 5, y - deltaY);
				g2d.setPaint(origPaint);
			}

		};
		panel.setPreferredSize(new Dimension( 9*x + 6*deltaX, 120 + y + 60));
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
