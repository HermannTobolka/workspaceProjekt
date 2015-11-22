package guiElemente;




import javax.swing.SwingUtilities;

public class JPanelMItBorderTester {

	public static void main(String[] args) {

		final JPanelMitBorder jpmb = new JPanelMitBorder();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jpmb.showFrame();
			}
		});

	}
}