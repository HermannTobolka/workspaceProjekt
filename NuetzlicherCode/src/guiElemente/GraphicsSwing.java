package guiElemente;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class GraphicsSwing {
	
	public static void main(String[] args) {
		final GraphicsF gf = new GraphicsF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gf.showFrame();
            }
        });
	}

}

class GraphicsF {
	private JFrame frame;
	private Image cat;

	@SuppressWarnings("serial")
	public GraphicsF() {
		//Create and set up the window.
		frame = new JFrame("GraphicsSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// load picture of cat and turn into Image
		ImageIcon img = new ImageIcon(".\\resources\\katze.jpg");
		cat = img.getImage();
		// show cat image in first panel
		JPanel panelImg = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(cat, 1, 1, this);
			}
		};
		// set size of first panel to fit size of cat image
		panelImg.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		// create second panel with orange rectangle in it
		JPanel panelRect = new JPanel(){
			public void paintComponent(Graphics g) {
				g.setColor(Color.orange);
				g.fillRect(10, 10, 100, 100);
			}
		};
		// create third panel with circle in it using random numbers for the color
		JPanel panelOval = new JPanel() {
			public void paintComponent(Graphics g) {
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				int red = (int)(Math.random() * 255);
				int blue = (int)(Math.random() * 255);
				int green = (int)(Math.random() * 255);
				g.setColor(new Color(red, blue, green));
				g.fillOval(10, 10, 100, 100);
			}
		};
		// set grid layout and add all three panels to the frame
		contentPane.setLayout(new GridLayout(1, 3));
		contentPane.add(panelImg);
		contentPane.add(panelRect);
		contentPane.add(panelOval);
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