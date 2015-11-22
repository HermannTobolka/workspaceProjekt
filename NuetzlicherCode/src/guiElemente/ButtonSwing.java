package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;



public class ButtonSwing {
	public static void main(String[] args) {
		final ButtonSwingF bsf = new ButtonSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bsf.showFrame();
            }
        });

	}

}

class ButtonSwingF implements ActionListener, ItemListener {
	private JFrame frame;

	public ButtonSwingF() {
		// create and set-up frame
		frame = new JFrame("ButtonSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(3, 2, 20,10));
		// create radiobuttons and register item listener
		JRadioButton rb1 = new JRadioButton("radioButton1");
		rb1.addItemListener(this);
		rb1.setActionCommand("radiobutton 1");
		JRadioButton rb2 = new JRadioButton("radioButton2");
		rb2.addItemListener(this);
		rb2.setActionCommand("radiobutton 2");
		// put both radiobuttons into a button group
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		// add radiobuttons to the content pane 
		contentPane.add(rb1);
		contentPane.add(rb2);
		// create checkbox, preselect it and register item listener
		JCheckBox cb = new JCheckBox("CheckBox");
		cb.setSelected(true);
		cb.addItemListener(this);
		cb.setActionCommand("checkbox");
		contentPane.add(cb);
		// create button and register action listener
		JButton button = new JButton("Ende");
		button.addActionListener(this);
		button.setMnemonic(KeyEvent.VK_E);
		button.setActionCommand("end");
		contentPane.add(button);
		// make this the default button
		frame.getRootPane().setDefaultButton(button);
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
		// close the window
		frame.dispose();
	}

	/**
	 * listen for selection/deselection of the checkbox or the radiobuttons
	 */
	public void itemStateChanged(ItemEvent event) {
		Object o = event.getSource();
		int seldesel = event.getStateChange();
		String ac = "?";
		if (o instanceof AbstractButton)
			ac = ((AbstractButton)o).getActionCommand();
		if (seldesel == ItemEvent.SELECTED)
			System.out.println(ac + " wurde selektiert");
		else
			System.out.println(ac + " wurde deselektiert");
	}

}
