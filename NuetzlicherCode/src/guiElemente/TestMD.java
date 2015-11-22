package guiElemente;

 

import javax.swing.SwingUtilities;

public class TestMD {

	public static void main(String[] args) {
		final ModalerDialogI md = new ModalerDialogI();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                md.showFrame();
            }
        });
	}

}
