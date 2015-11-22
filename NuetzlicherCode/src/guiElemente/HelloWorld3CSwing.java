package guiElemente;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class HelloWorld3CSwing {

		public static void main(String[] args) {
			final HelloWorld3C hw3c = new HelloWorld3C();
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                hw3c.showFrame();
	            }
	        });
		}
	}

	class HelloWorld3C {
		private JFrame frame;

		public HelloWorld3C() {
			//Create and set up the window.
			frame = new JFrame("HelloWorld3CSwing");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Add the button
			JButton button = new JButton("Klick mich");
			button.addActionListener(event ->  ((JButton)event.getSource()).setText("Ich wurde geklickt"));
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
