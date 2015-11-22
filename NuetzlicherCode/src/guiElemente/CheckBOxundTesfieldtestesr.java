package guiElemente;

import javax.swing.SwingUtilities;

 
public class CheckBOxundTesfieldtestesr {

	public static void main(String[] args) {
		final CheckBoxUndTextField cbutf = new CheckBoxUndTextField();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cbutf.showFrame();
            }
        });

	}

}

