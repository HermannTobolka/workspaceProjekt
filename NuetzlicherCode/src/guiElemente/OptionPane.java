package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class OptionPane {

	public static void main(String[] args) {
		final OptionPaneF opf = new OptionPaneF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				opf.showFrame();
			}
		});
	}

}

class OptionPaneF {
	private JFrame frame;

	public OptionPaneF() {
		//Create and set up the window.
		frame = new JFrame("JOptionPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));        
		final JButton b1 = new JButton("Confirm dialog");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(b1, "Sollen die Änderungen gespeichert werden?", "Bestätigung", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);				
			} 
		});
		final JButton b2 = new JButton("Input dialog mit Eingabefeld");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showInputDialog(b2, "Geben Sie den Namen ein", "Text eingeben", JOptionPane.QUESTION_MESSAGE);				
			} 
		});
		final JButton b3 = new JButton("Input dialog mit ComboBox");
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showInputDialog(b3, "Wählen Sie einen Wert", "Auswahl", JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Äpfel", "Birnen", "Orangen"}, "Orangen");				
			} 
		});
		final JButton b4 = new JButton("Message dialog"); 
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(b4, "Nur Ziffern von 0 bis 9", "Anzahl", JOptionPane.ERROR_MESSAGE);				
			} 
		});
		final JButton b5 = new JButton("Option dialog"); 
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showOptionDialog(b5, "Wählen Sie einen Wert", "Auswahl", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Äpfel", "Birnen", "Orangen", "Bananen"}, "Orangen");				
			} 
		});
		contentPane.add(b1);
		contentPane.add(b2);
		contentPane.add(b3); 
		contentPane.add(b4);
		contentPane.add(b5);
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