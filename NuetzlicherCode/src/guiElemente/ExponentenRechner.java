package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Implementieren Sie einen JFrame zur Eingabe einer Zahl und eines Exponenten.
 * Nach Drücken des Berechnen Button wird ein modaler Dialog angezeigt mit dem Ergebnis der Berechnung. 
 * Die Inhalte der beiden Textfelder werden an den Konstruktor des modalen Dialoges weitergegeben.
 * 
 * @author tobolkah
 *
 */

public class ExponentenRechner implements ActionListener{

	private JFrame frame;
	private JTextField tfZahl;
	private JTextField tfExp;

	public  ExponentenRechner() {

		frame = new JFrame("ExponentenRechner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(3,2,20,10));

		// create button to request display of a dialog

		JLabel l1 = new JLabel("Geben Sie eine Zahl ein");
		contentPane.add(l1);

		tfZahl= new JTextField("");
		contentPane.add(tfZahl);

		JLabel l2 = new JLabel("Geben Sie den Exponentenein");
		contentPane.add(l2);

		tfExp = new JTextField("");
		contentPane.add(tfExp);

		JButton button = new JButton("Berechnen");
		button.addActionListener(this);
		button.setMnemonic(KeyEvent.VK_B);
		button.setActionCommand("berechne");
		contentPane.add(button);
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


	@SuppressWarnings("serial")
	@Override
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		// create and show the dialog
		if (ac.equals("berechne")) {
			
			 // Try catch zur Absicherung mit fehlermeldung nur zahlen erlaubt
			double d1 = Double.parseDouble(tfZahl.getText());
			double d2 = Double.parseDouble(tfExp.getText());

			
			new JDialog(frame, "Rechner", true) {

				{

					JLabel l3= new JLabel(tfZahl.getText() +" **" +tfExp.getText()+"=" +  "          " + Math.pow(d1,d2));
					this.add(l3);

					// size the dialog
					this.setSize(150, 150);
					// position the dialog
					Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
					this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
					// show the dialog
					this.setVisible(true);
				}
			};
		}

	}


}
