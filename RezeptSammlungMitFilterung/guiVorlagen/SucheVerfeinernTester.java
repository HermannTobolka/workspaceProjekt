package guiVorlagen;

import javax.swing.SwingUtilities;

/*
 * Mit dieser Klasse wird die GUI Vorlage für den "Suche verfeinern" separat gestartet 
 * (3.3.1.2)
 */

public class SucheVerfeinernTester {

	public static void main(String[] args) {
		final SucheVerfeinern sv = new SucheVerfeinern();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sv.showFrame();
			}
		});

	}

}
