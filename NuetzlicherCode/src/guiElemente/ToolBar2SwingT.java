package guiElemente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;


public class ToolBar2SwingT {
	
	public static void main(String[] args) {
		final ToolBar2F tbf = new ToolBar2F();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tbf.showFrame();
            }
        });
	}

}

class ToolBar2F {
	private JFrame frame;
	private boolean fileOpened;
	private AbstractAction[] actions;
	
	private static final int NEW_ACTION = 0;
	private static final int OPEN_ACTION = 1;
	private static final int SAVE_ACTION = 2;
	private static final int DRILL_ACTION = 3;
	private static final int CUT_ACTION = 4;
	
	private static final int FILESTATUS_OPEN = 1;
	private static final int FILESTATUS_CLOSED = 2;
	private static final int FILESTATUS_UNCHANGED = 3;
	
	public ToolBar2F() {
		// create frame
		frame = new JFrame("ToolBar2Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		actions = new AbstractAction[5];
		// create action to be performed for Neu menuitem or toolbar item
		actions[NEW_ACTION] = createAction("Neu", FILESTATUS_OPEN, ".\\resources\\Icon_New.jpg", ".\\resources\\Icon_Small_New.jpg", 0, KeyEvent.VK_N);
		// create action to be performed for Öffnen menuitem or toolbar item
		actions[OPEN_ACTION] = createAction("Öffnen", FILESTATUS_OPEN, ".\\resources\\Icon_Open.jpg", ".\\resources\\Icon_Small_Open.jpg", 1, KeyEvent.VK_F);
		// create action to be performed for Speichern menuitem or toolbar item
		actions[SAVE_ACTION] = createAction("Speichern", FILESTATUS_CLOSED, ".\\resources\\Icon_Save.jpg", ".\\resources\\Icon_Small_Save.jpg", 0, KeyEvent.VK_S); 
		// create action to be performed for Speichern menuitem or toolbar item
		actions[DRILL_ACTION] = createAction("Bohren", FILESTATUS_UNCHANGED, ".\\resources\\Icon_Drill.jpg", ".\\resources\\Icon_Small_Drill.jpg", 0, KeyEvent.VK_B);
		// create action to be performed for Speichern menuitem or toolbar item
		actions[CUT_ACTION] = createAction("Schneiden", FILESTATUS_UNCHANGED, ".\\resources\\Icon_Cut.jpg", ".\\resources\\Icon_Small_Cut.jpg", 0, KeyEvent.VK_S);
		// create menubar and add it to the frame
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		// create main menus
		JMenu m1 = new JMenu("Datei");
		// add main menus to the menubar
		mb.add(m1);
		// create menu items
		m1.add(new JMenuItem(actions[NEW_ACTION]));
		m1.add(new JMenuItem(actions[OPEN_ACTION]));
		m1.add(new JMenuItem(actions[SAVE_ACTION]));
		JMenu m2 = new JMenu("Bearbeiten");
		// add main menus to the menubar
		mb.add(m2);
		// create menu items
		m2.add(new JMenuItem(actions[DRILL_ACTION]));
		m2.add(new JMenuItem(actions[CUT_ACTION]));
		// create toolbar with the items in it
		JToolBar tb = new JToolBar();
		for(AbstractAction aa : actions)
			tb.add(aa);
		contentPane.add(tb, BorderLayout.PAGE_START);
		enableDisableActions();
		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(1,0));
		for (AbstractAction aa: actions)
			bp.add(new JButton(aa));
		contentPane.add(bp, BorderLayout.PAGE_END);
	}
	
	@SuppressWarnings("serial")
	public AbstractAction createAction(String s, final int fileStatus, String icon, String smallIcon, int mnemKeyIndex, int mnemKey) {
		AbstractAction action = new AbstractAction(s) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(arg0.getActionCommand() + " wurde aufgerufen");
				switch(fileStatus) {
				case FILESTATUS_OPEN:
					fileOpened = true;
					break;
				case FILESTATUS_CLOSED:
					fileOpened = false;
				}
				enableDisableActions();
			}
		};
		action.putValue(AbstractAction.ACTION_COMMAND_KEY, s);
		action.putValue(AbstractAction.DISPLAYED_MNEMONIC_INDEX_KEY, mnemKeyIndex);
		action.putValue(AbstractAction.MNEMONIC_KEY, mnemKey);
		action.putValue(AbstractAction.LARGE_ICON_KEY, new ImageIcon(icon));
		action.putValue(AbstractAction.SMALL_ICON, new ImageIcon(smallIcon));
		return action;
	}
	
	public void enableDisableActions() {
		if (fileOpened) {
			actions[NEW_ACTION].setEnabled(false);
			actions[OPEN_ACTION].setEnabled(false);
			actions[SAVE_ACTION].setEnabled(true);
			actions[DRILL_ACTION].setEnabled(true);
			actions[CUT_ACTION].setEnabled(true);			
		}
		else {
			actions[NEW_ACTION].setEnabled(true);
			actions[OPEN_ACTION].setEnabled(true);
			actions[SAVE_ACTION].setEnabled(false);
			actions[DRILL_ACTION].setEnabled(false);
			actions[CUT_ACTION].setEnabled(false);			
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

}