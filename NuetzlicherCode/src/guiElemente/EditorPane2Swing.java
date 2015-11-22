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
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


public class EditorPane2Swing {
	public static void main(String[] args) {
		// create the application frame
		final EditorPane2F ep2f = new EditorPane2F();
		// show the application frame
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ep2f.showFrame();
            }
        });
	}

}

class EditorPane2F {

	private JFrame frame;
	private JEditorPane text; 

	/**
	 * Constructor; 
	 */
	public EditorPane2F() {
		// create the frame
		frame = new JFrame("EditorPaneSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HTMLEditorKit hed = new HTMLEditorKit();
		StyleSheet ss = hed.getStyleSheet(); 
		ss.addRule("h2 {color : green; font-weight: bold;}");
		ss.addRule("li {color :  #bb0022; font-style:italic;}");
		// create the JEditorPane to show the text of the HTMLFile and register a HyperLinkListener on it
		try {
			text = new JEditorPane(new URL("file", "", ".\\resources\\textcss.html"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		text.setEditable(false);
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
}