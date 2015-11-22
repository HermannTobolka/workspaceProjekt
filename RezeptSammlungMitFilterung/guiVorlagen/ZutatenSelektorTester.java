package guiVorlagen;

import javax.swing.SwingUtilities;

/*
 * Mit dieser Klasse wird die GUI Vorlage für den "ZutatenSelektor" separat gestartet 
 * (3.3.1.3)
 */

public class ZutatenSelektorTester {
	
	public static void main(String[] args) {
		final ZutatenSelektor zs = new ZutatenSelektor();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				zs.showFrame();
			}
		});
	}

}
