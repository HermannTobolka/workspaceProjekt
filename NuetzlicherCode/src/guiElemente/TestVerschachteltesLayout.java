package guiElemente;



import javax.swing.SwingUtilities;

public class TestVerschachteltesLayout {

	public static void main(String[] args) {
		final VerschachtelteLayouts vl = new VerschachtelteLayouts();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                vl.showFrame();
            }
        });

	}

}
