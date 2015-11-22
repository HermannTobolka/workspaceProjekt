package guiVorlagen;

import javax.swing.SwingUtilities;

/*
 * Mit dieser Klasse wird die GUI Vorlage für die "Liste gefundener Rezepte" separat gestartet 
 * (3.3.1.4)
 */

public class ListeGefundernerRezepteTester {

	public static void main(String[] args) {
		final ListeGefundenerRezepte lgr = new ListeGefundenerRezepte();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				lgr.showFrame();
			}
		});
	}

}
