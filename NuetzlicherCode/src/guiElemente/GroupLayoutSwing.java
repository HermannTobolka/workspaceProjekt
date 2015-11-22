package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class GroupLayoutSwing {

	public static void main(String[] args) {
		final GroupLayoutF glf = new GroupLayoutF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				glf.showFrame();
			}
		});
	}

}

class GroupLayoutF {
	private JFrame frame;

	public GroupLayoutF() {
		//Create and set up the window.
		frame = new JFrame("Group Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		JLabel nameL = new JLabel("Anrede");
		JLabel strL = new JLabel("Strasse");
		JLabel ortL = new JLabel("Plz / Ort");
		JComboBox<String> anrede = new JComboBox<>(new String[] {"Frau", "Herr"});
		JComboBox<String> titel = new JComboBox<>(new String[] {"Dr", "DI", "Hofrat", "Prof"});
		JTextField name = new JTextField("", 20);
		JTextField str = new JTextField();
		JTextField plz = new JTextField();
		JTextField ort = new JTextField("", 20);
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Abbrechen");

		SequentialGroup sgAnrede = layout.createSequentialGroup()
				.addComponent(anrede)
				.addComponent(titel)
				.addComponent(name);
		SequentialGroup sgPlzOrt = layout.createSequentialGroup()
				.addComponent(plz)
				.addComponent(ort);
		SequentialGroup sgOkCancel = layout.createSequentialGroup()
				.addComponent(ok)
				.addComponent(cancel);
		ParallelGroup pgAnrede = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			 	.addComponent(nameL)
				.addComponent(anrede)
				.addComponent(titel)
				.addComponent(name);
		ParallelGroup pgStrasse = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			 	.addComponent(strL)
			 	.addComponent(str);
		ParallelGroup pgPLZ = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			 	.addComponent(ortL)
			 	.addComponent(plz)
			 	.addComponent(ort);
		ParallelGroup pgOkCancel = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(ok)
				.addComponent(cancel);
		ParallelGroup col1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(nameL)
				.addComponent(strL)
				.addComponent(ortL);
		ParallelGroup col2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(sgAnrede)
				.addComponent(str)
				.addGroup(sgPlzOrt)
				.addGroup(GroupLayout.Alignment.CENTER, sgOkCancel);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(col1)
			    .addGroup(col2));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(pgAnrede)
			    .addGroup(pgStrasse)
			    .addGroup(pgPLZ)
			    .addGroup(pgOkCancel));
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