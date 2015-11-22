package guiElemente;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class HelloWorld3BSwing {

	public static void main(String[] args) {
		final HelloWorld3B hw3b = new HelloWorld3B();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hw3b.showFrame();
            }
        });
	}
}

class HelloWorld3B {
	private JFrame frame;

	public HelloWorld3B() {
		//Create and set up the window.
		frame = new JFrame("HelloWorld3BSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add the button
		JButton button = new JButton("Klick mich");
		button.addActionListener(new HW3BActionListener());
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
	
	class HW3BActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Object o = event.getSource();
			((JButton)o).setText("Ich wurde geklickt");
		}

	}

}

