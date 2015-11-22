package guiElemente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class HelloWorld3Swing {

	public static void main(String[] args) {
		final HelloWorld3 hw3 = new HelloWorld3();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hw3.showFrame();
            }
        });
	}
}

class HelloWorld3 implements ActionListener {
	private JFrame frame;
	

	public HelloWorld3() {
		//Create and set up the window.
		frame = new JFrame("HelloWorld3Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add the button
		JButton button = new JButton("Klick mich");
		button.addActionListener(this);
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
	

	@Override
	public void actionPerformed(ActionEvent event) {
		Object o = event.getSource();
		((JButton)o).setText("Ich wurde geklickt");
	}

}
