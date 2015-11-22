package guiElemente;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HelloWorld2Swing {

	public static void main(String[] args) {
		final HelloWorld2 hw2 = new HelloWorld2();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hw2.showFrame();
            }
        });
	}
}

class HelloWorld2 {
	private JFrame frame;

	public HelloWorld2() {
		//Create and set up the window.
		frame = new JFrame("HelloWorld2Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add the button
		JButton button = new JButton("Klick mich");
		frame.getContentPane().add(button);
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
}
