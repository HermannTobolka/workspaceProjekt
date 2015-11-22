package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

 

import javax.swing.JButton;
 
import javax.swing.JFrame;
import javax.swing.JTextField;
 

/**
 * Implementieren Sie einen JFrame mit GridLayout mit einer Zeile und zwei Spalten. 
 * In der linken Zelle ist ein JButton und in der rechten Zelle ein JTextField. 
 * Wenn der JButton gedrückt wird, dann soll der Inhalt   des JTextField gelöscht werden.
 * @author tobolkah
 *
 */
public class ButtonMitTextfeld implements ActionListener {
	
	private JFrame frame;
	private JTextField textField;

	
	public ButtonMitTextfeld() {
		// create and set-up frame
		frame = new JFrame("ButtonSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1, 2, 20,10));
		 
		// Button 
		
		JButton button = new JButton("Löschen");

		button.addActionListener(this);
		button.setMnemonic(KeyEvent.VK_E);
		button.setActionCommand("löschen");
		contentPane.add(button);
		// make this the default button
		frame.getRootPane().setDefaultButton(button);
		
		// Textfeld
		textField = new JTextField("Hier tust du den Text eingeben", 20);
		contentPane.add(textField);
		textField.addActionListener(this);
	}
	
	 
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

	/**
	 * listen for actions performed on the end button
	 */
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		System.out.println("ActionCommand: " + ac);
		textField.setText("");
		 
	}


	
}
