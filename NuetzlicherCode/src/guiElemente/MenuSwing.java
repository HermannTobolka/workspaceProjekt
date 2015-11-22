package guiElemente;
import javax.swing.*; 

import java.awt.event.*;   
import java.awt.*;

public class MenuSwing {
	
	public static void main(String[] args) {
		final MenuSwingF msf = new MenuSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                msf.showFrame();
            }
        });
	}

}

class MenuSwingF implements ActionListener {
	private JFrame frame;
	
	public MenuSwingF() {
		// create frame
		frame = new JFrame("MenuSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(3, 2, 20,10));
		// create menubar and add it to the frame
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		// create main menus
		JMenu m1 = new JMenu("Menu1");
		JMenu m2 = new JMenu("Menu2");
		// add main menus to the menubar
		mb.add(m1);
		mb.add(m2);
		// create menu items for first main menu
		JMenuItem mi11 = new JMenuItem("MenuItem11", KeyEvent.VK_1);
		mi11.setActionCommand("mi11");
		mi11.addActionListener(this);
		JMenuItem mi12 = new JMenuItem("MenuItem12", KeyEvent.VK_2);
		mi12.setActionCommand("mi12");
		mi12.addActionListener(this);
		m1.add(mi11);
		m1.add (mi12);
		// create submenu with menu items
		JMenu um2 = new JMenu("Menu2u");
		JMenuItem mi2u1 = new JMenuItem("MenuItem2u1");
		mi2u1.setActionCommand("mi2u1");
		mi2u1.addActionListener(this);
		JMenuItem mi2u2 = new JMenuItem("MenuItem2u2");
		mi2u2.setActionCommand("mi2u2");
		mi2u2.addActionListener(this);
		um2.add(mi2u1);
		um2.add(mi2u2);
		// create menu items for second main menu
		JMenuItem mi21 = new JMenuItem("MenuItem21");
		mi21.setActionCommand("mi21");
		mi21.addActionListener(this);
		JMenuItem mi22 = new JMenuItem("MenuItem22");
		mi22.setActionCommand("mi22");
		mi22.addActionListener(this);
		// add menu items and submenu to the second main menu
		m2.add(mi21);
		m2.add(um2);
		m2.add(mi22);
	}
	
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.setSize(200, 200);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

	/**
	 * listen to find out which menu item was selected
	 */
	public void actionPerformed(ActionEvent event) {
		String ac = event.getActionCommand();
		System.out.println("ActionCommand " + ac);
	}

}
