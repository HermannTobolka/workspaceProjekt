package guiElemente;
import javax.swing.*;    

import java.awt.*;

public class HelloWorldSwing {

	public static void main(String[] args) {
		final HelloWorld hw = new HelloWorld();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hw.showFrame();
            }
        });
	}
}

class HelloWorld {
	private JFrame frame;
	
	public HelloWorld() {
		//Create and set up the window.
		frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
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
}
