package guiElemente;


import javax.swing.SwingUtilities;

 

public class TestBildBetrachter {

	public static void main(String[] args) {
		
		final Bildbetrachter bb = new Bildbetrachter();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bb.showFrame();
            }
        });

	}

}
