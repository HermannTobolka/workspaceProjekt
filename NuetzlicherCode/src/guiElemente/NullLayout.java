package guiElemente;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class NullLayout {

	public static void main(String[] args) {
		final NullLayoutF nlf = new NullLayoutF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				nlf.showFrame();
			}
		});
	}

}

class NullLayoutF {
	private JFrame frame;

	public NullLayoutF() {
		//Create and set up the window.
		frame = new JFrame("NullLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		JPanel panel = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black, 5);
		panel.setBorder(blackline);
		panel.setLayout(null);        
		JButton b1 = new JButton("one");
		JButton b2 = new JButton("two");
		JButton b3 = new JButton("three"); 
		panel.add(b1);
		panel.add(b2);
		panel.add(b3); 
		Insets insets = contentPane.getInsets();
		Dimension size = b1.getPreferredSize();
		b1.setBounds(25 + insets.left, 10 + insets.top, size.width, size.height);
		size = b2.getPreferredSize();
		b2.setBounds(55 + insets.left, 40 + insets.top, size.width, size.height);
		size = b3.getPreferredSize();
		b3.setBounds(150 + insets.left, 15 + insets.top, size.width + 50, size.height + 20);
		contentPane.add(panel);
	}

	/**
	 * show main application window on desktop
	 */
	public void showFrame() {
		//Display the window.
		Insets insets = frame.getInsets();
		frame.setSize(300 + insets.left + insets.right,
				125 + insets.top + insets.bottom);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}
}