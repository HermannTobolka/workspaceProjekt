package guiElemente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;


public class SplitPaneSwing {
	public static void main(String[] args) {
		final SplitPaneF spf = new SplitPaneF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                spf.showFrame();
            }
        });
	}
}

class SplitPaneF implements ActionListener {
	private JFrame frame;
	private JSplitPane splitPane;
	private JMenuItem mVert;
	private JMenuItem mHor;
	
	public SplitPaneF() {
		//Create and set up the window.
		frame = new JFrame("SplitPaneSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create first panel with a label in it
		JPanel panel1 = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Label in panel1");
		Dimension d = label1.getPreferredSize();
		d.setSize(d.width, d.height * 5);
		label1.setPreferredSize(d);
		panel1.add(label1, BorderLayout.CENTER);
		// create second panel with a label in it
		JPanel panel2 = new JPanel(new BorderLayout());
		JLabel label2 = new JLabel("Label in panel2");
		label2.setPreferredSize(d);
		panel2.add(label2, BorderLayout.CENTER);
		// create splitpane
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
		splitPane.setOneTouchExpandable(true);
		// continuous painting during movement of the separator
		splitPane.setContinuousLayout(true);
		// add the splitpane to the frame
		contentPane.add(splitPane);
		// prepare the menu bar and add it to the frame
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		JMenu mD = new JMenu("Richtung");
		mb.add(mD);
		mVert = new JMenuItem("Vertikal");
		mVert.setActionCommand("mVert");
		mVert.addActionListener(this);
		mHor = new JMenuItem("Horizontal");
		mHor.setActionCommand("mHor");
		mHor.setEnabled(false);
		mHor.addActionListener(this);
		mD.add(mVert);
		mD.add(mHor);
	}
	
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		Dimension d = splitPane.getPreferredSize();
		frame.setSize(d.width + 20, d.height * 2 + 100);
		// position the frame
		d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

	/**
	 * listen for actions performed on the textfield
	 */
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		if (ac.equals("mVert")) {
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mVert.setEnabled(false);
			mHor.setEnabled(true);			
		}
		else {
			splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			mVert.setEnabled(true);
			mHor.setEnabled(false);	
		}
	}
}
