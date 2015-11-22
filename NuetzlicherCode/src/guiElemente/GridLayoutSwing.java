package guiElemente;
import javax.swing.*; 

import java.awt.event.*;   
import java.awt.*;

public class GridLayoutSwing {

	public static void main(String[] args) {
		final GridLayoutF gl = new GridLayoutF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gl.showFrame();
            }
        });
	}
}

class GridLayoutF implements ActionListener {
	private JFrame frame;

	public GridLayoutF() {
		//Create and set up the window.
		frame = new JFrame("GridLayoutSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// define GridLayout with 3 rows and 2 columns and the gaps
		contentPane.setLayout(new GridLayout(3, 2, 20, 10));
		JLabel label1 = new JLabel("Label1", SwingConstants.CENTER);
		contentPane.add(label1);
		JLabel label2 = new JLabel("Label2");
		contentPane.add(label2);
		JTextField txtField = new JTextField("Initialwert", 20);
		contentPane.add(txtField);
		txtField.addActionListener(this);
		JLabel label3 = new JLabel("Label3");
		contentPane.add(label3);
		JLabel label4 = new JLabel("Label4", SwingConstants.CENTER);
		contentPane.add(label4);
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
	 * listen for actions performed on the textfield
	 */
	public void actionPerformed(ActionEvent event) {
		JTextField tf = (JTextField)event.getSource();
		System.out.println("ActionEvent: text in textfield is '" + tf.getText() + "'");
	}
}