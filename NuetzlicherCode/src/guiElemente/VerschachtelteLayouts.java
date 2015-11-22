package guiElemente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implementieren Sie einen JFrame mit BorderLayout. 
 * Setzen Sie in den PAGE_START Bereich ein JPanel mit FlowLayout und 2 JLabels mit Text.
 * Das gleiche machen Sie mit dem PAGE_END Bereich, allerdings fügen sie 2 JLabels mit ImageIcon ein.
 * In den CENTER Bereich setzen Sie ein JPanel mit GridLayout mit 2 Zeilen und 2 Spalten. 
 * In Jeder Zelle ist ein JLabel mit Text.
 * 
 * @author Hermann
 *
 */

public class VerschachtelteLayouts {

	private JFrame frame;

	public  VerschachtelteLayouts() {

		//Create and set up the window.
		frame = new JFrame("Verschachtelte Layouts ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout(20,10));

		// create label for page start area
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel l1 = new JLabel(" Label1 ");
		panel1.add(l1);
		JLabel l2 = new JLabel(" Label2 ");
		panel1.add(l2);

		contentPane.add(panel1, BorderLayout.PAGE_START);

//
//		// create label for line start area
//		JLabel label2 = new JLabel("linestart");
//		contentPane.add(label2, BorderLayout.LINE_START);


		JPanel panel3 = new JPanel(new GridLayout(2,2));
		JLabel l5 = new JLabel(" Zeile 1, Spalte 1");
		panel3.add(l5);
		JLabel l6 = new JLabel(" Zeile 1, Spalte 2 ");
		panel3.add(l6);
		JLabel l7 = new JLabel(" Zeile 2, Spalte 1 ");
		panel3.add(l7);
		JLabel l8 = new JLabel(" Zeile 2, Spalte 2 ");
		panel3.add(l8);


		
		contentPane.add(panel3,BorderLayout.CENTER);

//		// create label for line end area
//		JLabel label3 = new JLabel("Line End");
//		contentPane.add(label3, BorderLayout.LINE_END);

		// create label for page end area
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel l3 = new JLabel(new ImageIcon("H:\\Travel-sea-waves-icon.png"));
		panel2.add(l3);
		JLabel l4 = new JLabel(new ImageIcon("H:\\military-explosion-icon.png"));
		panel2.add(l4);

		contentPane.add(panel2, BorderLayout.PAGE_END);

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

}
