package guiElemente;
import javax.swing.*; 

import java.awt.event.*;   
import java.awt.*;
public class DialogSwing {
	
	public static void main(String[] args) {
		final DialogF df = new DialogF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                df.showFrame();
            }
        });
	}

}

class DialogF implements ActionListener {
	
	private JFrame frame;

	public DialogF() {
		// create frame and set Flow layout
		frame = new JFrame("DialogSwing");
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

	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		// create and show the dialog
		if (ac.equals("zd"))
			new MyDialog(frame);
	}

}

@SuppressWarnings("serial")
class MyDialog extends JDialog {

	public MyDialog(JFrame frame) {
		super(frame,"Mein Dialog", true); 
		//Add a one line label.
		JLabel label1 = new JLabel("Label mit einer Zeile");
		this.add(label1);
		// size the dialog
		this.setSize(200, 200);
		// position the dialog
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
//		this.setLocationRelativeTo(this.getOwner());
		// show the dialog
		this.setVisible(true);
	}
}