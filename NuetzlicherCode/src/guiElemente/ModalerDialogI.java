package guiElemente;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("unused")
public class ModalerDialogI implements ActionListener {

	private JFrame frame;


	public ModalerDialogI() {

		// create frame and set Flow layout
		frame = new JFrame("ModalerDialog");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		// create button to request display of a dialog
		JButton button = new JButton("Zeige Dialog ...");
		button.addActionListener(this);
		button.setMnemonic(KeyEvent.VK_Z);
		button.setActionCommand("zd");
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
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		// create and show the dialog
		if (ac.equals("zd")) {
			new JDialog(frame, "Mein Dialog", true) {

				{

					GridLayout gridLayout = new GridLayout(3, 1);


					this.setLayout(gridLayout ); 
					JLabel l1= new JLabel("LabelmitText");
					JLabel l2= new JLabel("NocheinLabelmitText");

					this.add(l1);
					this.add(l2);

					// size the dialog
					this.setSize(400, 400);
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
