package guiElemente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class AnimationSwing {
	
	public static void main(String[] args) {
		final AnimationF af = new AnimationF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				af.showFrame();
			}
		});
	}

}

class AnimationF {
	private JFrame frame;
	private int x = 70;
	private int y = 70;
	JPanel panelOval;

	@SuppressWarnings("serial")
	public AnimationF() {
		//Create and set up the window.
		frame = new JFrame("AnimationSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create panel for circle
		panelOval = new JPanel() {
			public void paintComponent(Graphics g) {
				// fill entire panel area with background color
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				// now place circle at specified position
				g.setColor(Color.green);
				g.fillOval(x, y, 40, 40);
			}
		};
		frame.getContentPane().add(panelOval);
	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.setSize(300, 300);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
		Runnable runner = new Runnable() {
			public void run() {
				try {
					x++;
					y++;
					// panel must be painted again
					panelOval.paintImmediately(new Rectangle(panelOval.getBounds()));
					Thread.sleep(50);
				}
				catch(Exception e) { }
			}
		};
		for(int i = 0; i < 130; i++) {
			Thread paintThread = new Thread(runner);
			paintThread.start();
			try {
				paintThread.join();
			} catch (InterruptedException e) { }
		}
	}

}