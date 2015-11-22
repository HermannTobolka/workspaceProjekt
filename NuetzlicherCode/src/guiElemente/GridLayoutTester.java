package guiElemente;


 

import javax.swing.SwingUtilities;

public class GridLayoutTester {

	public static void main(String[] args) {

		final GridLayoutG gl = new GridLayoutG();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gl.showFrame();
            }
        });
	}

	}

