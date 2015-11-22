package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class HelloWorldWLSwing {

	public static void main(String[] args) {
		final HelloWorldWL hwwl = new HelloWorldWL();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hwwl.showFrame();
            }
        });
	}
}

class HelloWorldWL implements WindowListener {
	private JFrame frame;

	public HelloWorldWL() {
		//Create and set up the window.
		frame = new JFrame("HelloWorldWLSwing");
		Container contentPane = frame.getContentPane();
		frame.addWindowListener(this);

		//Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		contentPane.add(label);
	}

	/**
	 * show main application window on desktop
	 */
	public void showFrame() {
		//Display the window.
		frame.setSize(300,200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}
	

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Window in closing state");
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

}
