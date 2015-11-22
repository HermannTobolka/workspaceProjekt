package guiVorlagen;

import javax.swing.SwingUtilities;



public class StartBildschirmTester {

	public static void main(String[] args) {
		final StartBildchirm sb = new StartBildchirm();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sb.showFrame();
			}
		});
	}

}
