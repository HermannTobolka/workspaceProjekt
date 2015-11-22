package guiElemente;


import javax.swing.SwingUtilities;
 

public class TestExp {
	
	public static void main(String[] args) {
		final ExponentenRechner er = new ExponentenRechner();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                er.showFrame();
            }
        });
	}

}
