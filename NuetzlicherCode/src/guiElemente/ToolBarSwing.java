package guiElemente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;


public class ToolBarSwing {
	
	public static void main(String[] args) {
		final ToolBarF tbf = new ToolBarF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tbf.showFrame();
            }
        });
	}

}

class ToolBarF {
	private JFrame frame;
	
	public ToolBarF() {
		// create frame
		frame = new JFrame("ToolBarSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create toolbar with the items in it
		JToolBar tb = new JToolBar();
		tb.add(createButton("Neu", ".\\resources\\Icon_New.jpg"));
		tb.add(createButton("Oeffnen", ".\\resources\\Icon_Open.jpg"));
		tb.add(createButton("Speichern", ".\\resources\\Icon_Save.jpg"));
		frame.getContentPane().add(tb, BorderLayout.PAGE_START);
	}
	
	private JButton createButton(final String cmd, String iconName) {
		JButton button = new JButton(new ImageIcon(iconName));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(cmd + " wurde aufgerufen");				
			} });
		return button;
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