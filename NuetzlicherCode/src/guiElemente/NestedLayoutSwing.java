package guiElemente;
import javax.swing.*; 
import javax.swing.border.*;
import java.awt.*;

public class NestedLayoutSwing {

	public static void main(String[] args) {
		final NestedLayout nl = new NestedLayout();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                nl.showFrame();
            }
        });
	}

}

class NestedLayout {

	private JFrame frame;
	
	public NestedLayout() {
		// create frame
		frame = new JFrame("NestedLayoutSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create a black border
		Border blackline = BorderFactory.createLineBorder(Color.black);
		// create panel with grid layout with 3 rows and 1 column
		// and ad components to this panel
		JPanel panel1 = new JPanel(new GridLayout(3,1));
		panel1.setBorder(blackline);
		JLabel l1 = new JLabel(" Label1 ");
		panel1.add(l1);
		JLabel l2 = new JLabel(" Label2 ");
		panel1.add(l2);
		JLabel l3 = new JLabel(" Label3 ");
		panel1.add(l3);
		// create green border
		Border greenline = BorderFactory.createEtchedBorder(Color.green, Color.red);
		// create panel with border layout and add components to it
		JPanel panel2 = new JPanel(new BorderLayout());
		JLabel l4 = new JLabel(" Label4 ", SwingConstants.CENTER);
		l4.setBorder(greenline);
		panel2.add(l4, BorderLayout.PAGE_START);
		JLabel l5 = new JLabel(" Label5 ");
		l5.setBorder(greenline);
		panel2.add(l5, BorderLayout.LINE_START);
		JLabel l6 = new JLabel(" Label6 ");
		l6.setBorder(greenline);
		panel2.add(l6, BorderLayout.CENTER);
		JLabel l7 = new JLabel(" Label7 ");
		l7.setBorder(greenline);
		panel2.add(l7, BorderLayout.LINE_END);
		JLabel l8 = new JLabel(" Label 8", SwingConstants.CENTER);
		l8.setBorder(greenline);
		panel2.add(l8, BorderLayout.PAGE_END);
		// set grid layout with 1 row and 2 columns for frame
		frame.setLayout(new GridLayout(1,2));
		Container contentPane = frame.getContentPane();
		// add both panels to the frame
		contentPane.add(panel1);
		contentPane.add(panel2);
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
