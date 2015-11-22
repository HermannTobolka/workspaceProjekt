package guiElemente;

 

import javax.swing.SwingUtilities;

public class ButtonMitTextfeldTester {

	public static void main(String[] args) {
		final ButtonMitTextfeld bsf = new ButtonMitTextfeld();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bsf.showFrame();
            }
        });


	}

}
