package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class EditorPaneSwing {
	
	public static void main(String[] args) {
		// create the application frame
		final EditorPaneF epf = new EditorPaneF();
		// show the application frame
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                epf.showFrame();
            }
        });
	}

}

class EditorPaneF implements HyperlinkListener {

	private JFrame frame;
	private JEditorPane text; 

	/**
	 * Constructor; 
	 */
	public EditorPaneF() {
		// create the frame
		frame = new JFrame("EditorPaneSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create the JEditorPane to show the text of the HTMLFile and register a HyperLinkListener on it
		try {
			text = new JEditorPane(new URL("file", "", ".\\resources\\index.html"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		text.setEditable(false);
		text.addHyperlinkListener(this);
		// setup JSCrollPane for the JEditorPane and set its sizes appropriately
		JScrollPane textView = new JScrollPane(text);
		textView.setMinimumSize(new Dimension(250, 350));
		textView.setPreferredSize(new Dimension(400, 350));
		Container contentPane = frame.getContentPane();
		contentPane.add(textView);
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

	
	@Override
	/**
	 * Is called whenever the user activated a hyperlink in the HTML text.
	 * We cannot honor mailto and http links. We only act on file links.
	 */
	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			URL newPage = event.getURL();
			try {
				text.setPage(new URL("file", "", ".\\resources\\" + newPage.getPath().substring(1)));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
        }
	}
	
}