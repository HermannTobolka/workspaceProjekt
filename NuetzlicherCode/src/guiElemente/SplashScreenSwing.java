package guiElemente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class SplashScreenSwing {

	public static void main(String[] args) {
		// create the splashscreeen window
		final SplashWindowF ssf = new SplashWindowF();
		// create the splashscreeen window
		new SplashWindow(".\\resources\\javaMug.jpg", ssf, 3000);
	}
}

@SuppressWarnings("serial")
class SplashWindow extends JWindow
{
	public SplashWindow(String filename, final SplashWindowF sw3f, final int waitTime)
	{
		super(sw3f.getFrame());
		JLabel l = new JLabel(new ImageIcon(filename));
		getContentPane().add(l, BorderLayout.CENTER);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = l.getPreferredSize();
		setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
				sw3f.showFrame();
			}
		});
		final Runnable closerRunner = new Runnable() {
			public void run() {
				setVisible(false);
				dispose();
				sw3f.showFrame();
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
		setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}
}

class SplashWindowF {
	private JFrame frame;

	public SplashWindowF() {
		frame = new JFrame("SplashScreen2Swing");
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
