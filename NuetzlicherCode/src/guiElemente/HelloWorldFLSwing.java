package guiElemente;

	import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

	public class HelloWorldFLSwing {

		public static void main(String[] args) {
			final HelloWorldFL hwfl = new HelloWorldFL();
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                hwfl.showFrame();
	            }
	        });
		}
	}

	class HelloWorldFL implements ActionListener {
		private JFrame frame;

		public HelloWorldFL() {
			//Create and set up the window.
			frame = new JFrame("HelloWorldFLSwing");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new FlowLayout());
			//Add the button
			JButton button = new JButton("Klick mich");
			button.addActionListener(this);
			contentPane.add(button);
			//Add the label
			JLabel label = new JLabel("Ein Label");
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
		public void actionPerformed(ActionEvent event) {
			Object o = event.getSource();
			((JButton)o).setText("Ich wurde geklickt");
		}

	}
