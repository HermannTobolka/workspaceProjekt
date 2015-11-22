package guiElemente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Graphics2DSwing {
	
	public static void main(String[] args) {
		final Graphics2DF g2df = new Graphics2DF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                g2df.showFrame();
            }
        });

	}

}

class Graphics2DF implements ActionListener {
	private JFrame frame;

	@SuppressWarnings("serial")
	public Graphics2DF() {
		//Create and set up the window.
		frame = new JFrame("Graphics2DSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create panel with circle in it using random numbers for the color
		JPanel panelOval = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				int red = (int)(Math.random() * 255);
				int blue = (int)(Math.random() * 255);
				int green = (int)(Math.random() * 255);
				Color startColor = new Color(red, blue, green);
				red = (int)(Math.random() * 255);
				blue = (int)(Math.random() * 255);
				green = (int)(Math.random() * 255);
				Color endColor = new Color(red, blue, green);
				GradientPaint gradient = new GradientPaint(new Point(70, 70), startColor, new Point(150, 150), endColor);
				g2d.setPaint(gradient);
				Ellipse2D.Double circle = new Ellipse2D.Double(70, 70, 100, 100);
				g2d.fill(circle);
			}
		};
		panelOval.setPreferredSize(new Dimension(300, 300));
		// create button for color change
		JButton butColor = new JButton("Farbe ändern");
		butColor.addActionListener(this);
		// set border layout and add everything to the frame
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panelOval, BorderLayout.CENTER);
		contentPane.add(butColor, BorderLayout.PAGE_END);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.repaint();
	}
}