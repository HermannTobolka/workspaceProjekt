package guiElemente;
import javax.imageio.ImageIO;
import javax.swing.*; 

import java.awt.event.*;   
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LabelTextfieldSwing {
	
	public static void main(String[] args) {
		final LabelTextfield lt = new LabelTextfield();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lt.showFrame();
            }
        });
	}
}

class LabelTextfield implements ActionListener {
	private JFrame frame;
	
	public LabelTextfield() {
		//Create and set up the window.
		frame = new JFrame("LabelTextfieldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20,10));

		//Add a one line label.
		JLabel label1 = new JLabel("Eine Zeile");
		label1.setBackground(Color.green);
		label1.setOpaque(true);
		label1.setForeground(Color.red);
		contentPane.add(label1);

		//Add two line label.
		JLabel label2 = new JLabel("<html><font color=red>Erste</font> <u>Zeile</u><p/><font size=\"-2\">Zweite</font> <i><font size=\"+2\">Zeile</font></i></html>");
		contentPane.add(label2);

		//Add label with css definition
		StringBuilder css = new StringBuilder();
		css.append("<html><head><style type='text/css'>");
		css.append("body { color: #ff5595; font-weight: normal; font-style: italic; font-size: 25pt; }");
		css.append("</head><body>");
		JLabel label3 = new JLabel(css + "CSS Text");
		contentPane.add(label3);

		//Add textfield
		JTextField txtField = new JTextField("Initialwert", 15);
		contentPane.add(txtField);
		
		txtField.addActionListener(this);
		
		// Add label with image
		JLabel label4 = new JLabel(new ImageIcon(".\\resources\\Duke_Images\\Duke1.gif"));
		contentPane.add(label4);
		
		// Add label with bitmap
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File(".\\resources\\potrace.bmp"));
			if (bi != null) {
				JLabel label5 = new JLabel(new ImageIcon(bi));
				contentPane.add(label5);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

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
