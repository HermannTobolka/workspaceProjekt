package guiMitTable;

import javax.swing.SwingUtilities;



public class RunBootsverkauf {

	public static void main(String[] args) {

		final GebrauchteBooteVerkauf gbv = new GebrauchteBooteVerkauf();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gbv.showFrame();
			}
		});

	}

}
