package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GridBagLayoutSwing {
	
	public static void main(String[] args) {
		final GridBagLayoutF gbl = new GridBagLayoutF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gbl.showFrame();
            }
        });
	}

}

class GridBagLayoutF {
	private JFrame frame;
	private GridBagLayout gbl;

	public GridBagLayoutF() {
		//Create and set up the window.
		frame = new JFrame("GridBagLayoutSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// define GridBagLayout
	    gbl = new GridBagLayout();
	    contentPane.setLayout(gbl);
	    //                                          x  y  w  h  wx   wy
	    addComponent(contentPane, new JButton("1"), 0, 0, 2, 2, 1.0, 1.0);
	    addComponent(contentPane, new JButton("2"), 2, 0, 1, 1, 0  , 1.0);
	    addComponent(contentPane, new JButton("3"), 2, 1, 1, 1, 0  , 0  );
	    addComponent(contentPane, new JButton("4"), 0, 2, 3, 1, 0  , 1.0);
	    addComponent(contentPane, new JButton("5"), 0, 3, 2, 1, 0  , 0  );
	    addComponent(contentPane, new JButton("6"), 0, 4, 2, 1, 0  , 0  );
	    addComponent(contentPane, new JButton("7"), 2, 3, 1, 2, 0  , 0  );
	}
	
	private void addComponent(Container cont, JComponent c, int x, int y, int width, int height, double weightx, double weighty){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x; gbc.gridy = y;
		gbc.gridwidth = width; gbc.gridheight = height;
		gbc.weightx = weightx; gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		cont.add( c );
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
