package guiElemente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TexturePaintSwing {
	
	public static void main(String[] args) {
		final TexturePaintF tpf = new TexturePaintF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tpf.showFrame();
            }
        });
	}

}

class TexturePaintF {
	private JFrame frame;
	private BufferedImage cat;
	
	@SuppressWarnings("serial")
	public TexturePaintF() {
		//Create and set up the window.
		frame = new JFrame("TexturePaintSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// prepare the image of the cat
		try {
			// read the jpg file for the cat and scale it down to 30 pixel
			Image catImg = ImageIO.read(new File(".\\resources\\katze.jpg")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			// create BufferedImage object which we need later on
			cat = new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB);
			// get graphics content of the BufferedImage
			Graphics2D gCat = cat.createGraphics();
			// draw the cat (which is an object of type Image) into the graphics content
			gCat.drawImage(catImg, null, null);
		} catch (IOException e) {
			return;
		}
		// create panel with ellipse in it filled with little cats
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				TexturePaint catPaint =	new TexturePaint(cat, new Rectangle(0, 0, 30, 30));
				g2d.setPaint(catPaint);
				Ellipse2D.Double elli = new Ellipse2D.Double(25, 50, 350, 250);
				g2d.fill(elli);
			}
		};
		panel.setPreferredSize(new Dimension(400, 400));
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