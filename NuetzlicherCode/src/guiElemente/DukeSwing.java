package guiElemente;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class DukeSwing {

	public static void main(String[] args) {
		final DukeSwingF dsf = new DukeSwingF();
		// create the splashscreeen window
		new DukeSplash(".\\resources\\Duke_Images\\Duke", dsf, 500);
	}

}

@SuppressWarnings("serial")
class DukeSplash extends JWindow
{
	ImageIcon[] dukeImgs;
	int loopslot = 0;  //the current frame number
	int off = 0;        //the current offset
	int nimgs;          //number of images to animate
	int maxWidth;       //width of widest image
	int maxHeight;

	public DukeSplash(String ds, final DukeSwingF sw2f, final int waitTime)
	{
		super(sw2f.getFrame());
		this.setBackground(Color.white);
		nimgs = 16;
		dukeImgs = new ImageIcon[nimgs];
		for (int i = 0; i < nimgs; i++) {
			dukeImgs[i] = new ImageIcon(ds + (i + 1) + ".gif");
			maxWidth = Math.max(maxWidth, dukeImgs[i].getIconWidth());
			maxHeight = Math.max(maxHeight, dukeImgs[i].getIconHeight());
		}
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int locX = (d.width - maxWidth)/2;
		int locY = (d.height - maxHeight)/2; 
		this.setLocation(locX, locY);
		this.setSize(maxWidth+5+17*15, maxHeight+5);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
				sw2f.showFrame();
			}
		});
		final Runnable closerRunner = new Runnable() {
			public void run() {
				setVisible(false);
				dispose();
				sw2f.showFrame();
			}
		};
		Runnable waitRunner = new Runnable() {
			public void run() {
				try {
					Thread.sleep(waitTime);
					SwingUtilities.invokeAndWait(closerRunner);
				}
				catch(Exception e) { }
			}
		};
		showMe();
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}
	public void showMe() {
		off = 17*15;
		this.setVisible(true);
		for (int i = 1; i < nimgs; i++) {
			loopslot++;
			off -= 15;
			this.repaint();
			try {
				Thread.sleep(300);
			}
			catch(InterruptedException e) { }
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		dukeImgs[loopslot].paintIcon(this, g, off, 0);
	}

}
class DukeSwingF {
	private JFrame frame;

	public DukeSwingF() {
		frame = new JFrame("Tumbling Duke");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void showFrame() {
		// size the frame 
		frame.setSize(300,200);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

}