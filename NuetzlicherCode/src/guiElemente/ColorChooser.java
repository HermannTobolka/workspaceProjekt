package guiElemente;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class ColorChooser {
	
	public static void main(String[] args) {
		final ColorChooserF ccf = new ColorChooserF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ccf.showFrame();
			}
		});
	}

}

class ColorChooserF {
	private JFrame frame;

	public ColorChooserF() {
		//Create and set up the window.
		frame = new JFrame("JOptionPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));        
		final JButton b1 = new JButton("Color chooser dialog");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser.showDialog(b1, "Farbe auswählen", Color.GREEN);				
			} 
		});
		contentPane.add(b1);
	}

	/**
	 * show main application window on desktop
	 */
	public void showFrame() {
		//Display the window.
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}
}