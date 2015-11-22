package guiElemente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;


public class MDISwing {

	public static void main(String[] args) {
		final MDIF mdi = new MDIF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mdi.showFrame();
            }
        });
	}

}

class MDIF {
	private JFrame frame;
	
	public MDIF() {
		//Create and set up the window.
		frame = new JFrame("Multiple Document Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		
		// define Desktop Pane and add the child frames to it
		JDesktopPane desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		contentPane.add(desktop, BorderLayout.CENTER);
		for(int i=0; i<3; i++) {
			JInternalFrame iFrame = new JInternalFrame(("Internal Frame " + i), true, true, true, true);
			iFrame.setLocation(i*50+10, i*50+10);
			iFrame.setSize(200, 150);
			iFrame.setBackground(Color.WHITE);
			iFrame.setVisible(true);
			desktop.add(iFrame);
			iFrame.moveToFront();
			}
	}
	
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.setSize(450, 400);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}
}
