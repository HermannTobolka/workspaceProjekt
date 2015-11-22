package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class HelloWorldLFSwing {

	public static void main(String[] args) {
		final HelloWorldLF hwlf = new HelloWorldLF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                hwlf.showFrame();
            }
        });
	}
}

class HelloWorldLF implements ActionListener {
	private JFrame frame;
	private LookAndFeelInfo[] lafs;
	private ArrayList<JMenuItem> mI = new ArrayList<JMenuItem>();

	public HelloWorldLF() {
		//Create and set up the window.
		frame = new JFrame("HelloWorldLFSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();

		//Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		contentPane.add(label);
		
		// create menubar
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		// create main menu and add it to the menubar
		JMenu mLF = new JMenu("Look and Feel");
		mb.add(mLF);
		// create menu items 
		lafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo laf: lafs) {
			JMenuItem jmi = new JMenuItem(laf.getName());
			jmi.setActionCommand(laf.getName());
			jmi.addActionListener(this);
			if (laf.getName().equals("Metal"))
				jmi.setEnabled(false);
			mI.add(jmi);
			mLF.add(jmi);
		}
	}

	/**
	 * show main application window on desktop
	 */
	public void showFrame() {
		//Display the window.
		frame.setSize(300,200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		String s = null;
		int i = 0;
		for (LookAndFeelInfo laf: lafs) {
			if (ac.equals(laf.getName())) {
				s = laf.getClassName();
				mI.get(i).setEnabled(false);
			}
			else
				mI.get(i).setEnabled(true);
			i++;
		}
		try {
			System.out.println(s);
			UIManager.setLookAndFeel(s);
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}
		}
		// must update all GUI components to adapt to the new look and feel, if necessary
		SwingUtilities.updateComponentTreeUI(frame);
	}

}
