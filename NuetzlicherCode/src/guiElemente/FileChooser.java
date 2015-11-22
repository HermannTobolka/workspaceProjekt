package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileChooser {

	public static void main(String[] args) {
		final FileChooserF fcf = new FileChooserF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				fcf.showFrame();
			}
		});
	}

}

class FileChooserF {
	private JFrame frame;

	public FileChooserF() {
		//Create and set up the window.
		frame = new JFrame("JFileChooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));        
		final JButton b1 = new JButton("File chooser open dialog");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(b1);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
					JOptionPane.showMessageDialog(b1, "Ausgewählt wurde " + chooser.getSelectedFile().getAbsolutePath(), "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		final JButton b2 = new JButton("File chooser save dialog");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(b2);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
					JOptionPane.showMessageDialog(b1, "Ausgewählt wurde " + chooser.getSelectedFile().getAbsolutePath(), "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(b1);
		contentPane.add(b2);
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