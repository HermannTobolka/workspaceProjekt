package guiElemente;

import java.applet.Applet;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class FormApplet extends Applet {

	private JLabel label;
	private JTextField textField;

	public void init() {
		label = new JLabel();
		textField = new JTextField("Initialwert", 20);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setText(textField.getText());
			} 
		});
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Geben Sie einen Text ein"));
		this.add(textField);
		this.add(label);
	}

}
