package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Implementieren Sie 2 Checkboxes und ein JTextField. Im JTextField wird angezeigt welche 
 * Checkboxes selektiert sind. 
 * Beachten Sie, dass 0, 1 oder beide Checkboxes selektiert sein können. 
 * Mit checkbox.isSelected() können Sie den Zustand der Checkbox erfragen.  
 * 
 * @author Hermann
 *
 */

public class CheckBoxUndTextField implements ItemListener {
	private JFrame frame;
	private JTextField textField;
	private JCheckBox cb1;
	private JCheckBox cb2;


	public CheckBoxUndTextField() {

		// create and set-up frame
		frame = new JFrame("Checkbox und Textfeld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(2,2, 20,10));

		// create checkbox, preselect it and register item listener
		cb1 = new JCheckBox("CheckBox1");
		//cb1.setSelected(false); kann man weglassen, default = false=leere Checkbox 
		cb1.addItemListener(this);
		cb1.setActionCommand("checkbox1");
		contentPane.add(cb1);

		cb2 = new JCheckBox("CheckBox2");
		//cb2.setSelected(false);
		cb2.addItemListener(this);
		cb2.setActionCommand("checkbox2");
		contentPane.add(cb2);

		// Textfeld
		textField = new JTextField("Noch tut sich nichts, bitte auf Checkboxen herumklicken",30);
		contentPane.add(textField);

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

	@Override

	public void itemStateChanged(ItemEvent event) {

		if ((cb1.isSelected() == true) && (cb2.isSelected()==true))
			textField.setText("Beide Checkboxen sind selektiert");
		else if ((!cb1.isSelected() == true) && (!cb2.isSelected()==true))
			textField.setText("Keine Checkbox ist selektiert");
		else if ((cb1.isSelected() == true) && (!cb2.isSelected()==true))
			textField.setText("Checkbox1 ist selektiert");
		else if ((!cb1.isSelected() == true) && (cb2.isSelected()==true))
			textField.setText("Checkbox2 ist selektiert");
	}
}
