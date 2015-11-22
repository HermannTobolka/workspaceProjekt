package guiElemente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


public class TabbedPaneSwing {
	public static void main(String[] args) {
		final TabbedPaneF tpf = new TabbedPaneF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tpf.showFrame();
            }
        });
	}
}

class TabbedPaneF {
	private JFrame frame;
	
	public TabbedPaneF() {
		//Create and set up the window.
		frame = new JFrame("TabbedPaneSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create first panel with a label in it
		JPanel panel1 = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Label in Register1");
		Dimension d = label1.getPreferredSize();
		d.setSize(d.width, d.height * 5);
		label1.setPreferredSize(d);
		panel1.add(label1, BorderLayout.CENTER);
		// create second panel with a label in it
		JPanel panel2 = new JPanel(new BorderLayout());
		JLabel label2 = new JLabel("Label in Register2");
		label2.setPreferredSize(d);
		panel2.add(label2, BorderLayout.CENTER);
		// create tabbedpane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Register 1", panel1);
		tabbedPane.add("Register 2", panel2);
		// add tabbedpane to frame
		contentPane.add(tabbedPane);
	}
	
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.setSize(300, 150);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}
}
