package guiElemente;


import javax.swing.*; 

import java.awt.event.*;   
import java.awt.*;

/**
 * Implementieren Sie einen JFrame mit einem GridLayout mit 3 Zeilen und 2 Spalten. 
 * In der jeweils linken Zelle jeder Zeile ist ein JTextField und in der rechten Zelle 
 * jeder Zeile ist ein JLabel.
 * Im JLabel wird jeweils der Text angezeigt welcher im JTextField eingegeben wurde.
 *  
 * @author tobolkah
 *
 */

public class GridLayoutG implements ActionListener{

	private JFrame frame;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

	public GridLayoutG() {

		//Create and set up the window.
		frame = new JFrame("GridLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		// define GridLayout with 3 rows and 2 columns and the gaps
		contentPane.setLayout(new GridLayout(3, 2, 20, 10));

		textField1= new JTextField("Initialwert", 20);
		contentPane.add(textField1);
		textField1.addActionListener(this);

		label1 = new JLabel(textField1.getText());
		contentPane.add(label1);

		textField2= new JTextField("Initialwert", 20);
		contentPane.add(textField2);
		textField2.addActionListener(this);

		label2 = new JLabel("Label2");
		contentPane.add(label2);

		textField3= new JTextField("Initialwert", 20);
		contentPane.add(textField3);
		textField3.addActionListener(this);

		label3 = new JLabel("Label3");
		contentPane.add(label3);
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
		if (tf == textField1)
			label1.setText(tf.getText());
		else if (tf == textField2)
			label2.setText(tf.getText());
		else if (tf == textField3)
			label3.setText(tf.getText());
	}

}
