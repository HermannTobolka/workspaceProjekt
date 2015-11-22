package guiVorlagen;

import javax.swing.SwingUtilities;

public class RezeptAnzeigeTester {

	public static void main(String[] args) {
		final  RezeptAnzeige ra = new RezeptAnzeige();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ra.showFrame();
			}
		});

	}

}
