package guiElemente;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;


public class BoxLayoutSwing {

	public static void main(String[] args) {
		final BoxLayoutF blf = new BoxLayoutF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				blf.showFrame();
			}
		});
	}

}

class BoxLayoutF implements ActionListener {
	private JFrame frame;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	public BoxLayoutF() {
		//Create and set up the window.
		frame = new JFrame("Box Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		// create main menus
		JMenu m1 = new JMenu("Elemente");
		// add main menus to the menubar
		mb.add(m1);
		// create menu items for first main menu
		JMenuItem mi1c = new JMenuItem("Center");
		mi1c.setActionCommand("mi1c");
		mi1c.addActionListener(this);
		JMenuItem mi1l = new JMenuItem("Links");
		mi1l.setActionCommand("mi1l");
		mi1l.addActionListener(this);
		JMenuItem mi1r = new JMenuItem("Rechts");
		mi1r.setActionCommand("mi1r");
		mi1r.addActionListener(this);
		m1.add(mi1c);
		m1.add(mi1l);
		m1.add(mi1r);
		JMenu m2 = new JMenu("Box");
		// add main menus to the menubar
		mb.add(m2);
		// create menu items for first main menu
		JMenuItem mi2x = new JMenuItem("X Axis");
		mi2x.setActionCommand("mi2x");
		mi2x.addActionListener(this);
		JMenuItem mi2y = new JMenuItem("Y Axis");
		mi2y.setActionCommand("mi2y");
		mi2y.addActionListener(this);
		m2.add(mi2x);
		m2.add(mi2y);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		button1 = new JButton("Button 1");
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(button1);	
		contentPane.add(Box.createGlue());
		button2 = new JButton("Button 2");
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(button2);
		contentPane.add(Box.createGlue());
		button3 = new JButton("Langer Text Button 3");
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(button3);	
		contentPane.add(Box.createGlue());
		button4 = new JButton("4");
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(button4);	
	}

	/**
	 * show main application window on desktop
	 */
	public void showFrame() {
		//Display the window.
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		Container contentPane = frame.getContentPane();
		switch(ac) {
		case "mi1c":
			setAlignment(Component.CENTER_ALIGNMENT);
			break;
		case "mi1l":
			setAlignment(Component.LEFT_ALIGNMENT);
			break;
		case "mi1r":
			setAlignment(Component.RIGHT_ALIGNMENT);
			break;
		case "mi2x":
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
			contentPane.revalidate();
			break;
		case "mi2y":
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			contentPane.revalidate();
			break;
		}
	}

	private void setAlignment(float alignment) {
		button1.setAlignmentX(alignment);
		button2.setAlignmentX(alignment);
		button3.setAlignmentX(alignment);
		button4.setAlignmentX(alignment);
		button1.revalidate();
		button2.revalidate();
		button3.revalidate();
		button4.revalidate();
	}
}