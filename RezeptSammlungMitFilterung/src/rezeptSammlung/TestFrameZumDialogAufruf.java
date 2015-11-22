package rezeptSammlung;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/*
 * Diese Klasse dient dazu, neue GUI Fenster als JDialog Testen zu können
 * So müssten diese beim Einbau in die View Klasse nicht von JFrame auf JDialog 
 * umgeschrieben werden
 * 
 * Der Frame enthält einenb Button, über den der neue JDialog dann aufgerufen wird
 * 
 */

public class TestFrameZumDialogAufruf {

	public static void main(String[] args) {
		final  TestFrameZumDialogAufruf tFrame = new TestFrameZumDialogAufruf();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tFrame.showFrame();
			}
		});
	}

	private JFrame testFrame;
	private JButton startAnlegen;



	public TestFrameZumDialogAufruf(){

		//Create and set up the window.
		testFrame = new JFrame(" Testframe ");
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = testFrame.getContentPane();
		contentPane.setLayout(new GridLayout(4,1));

		startAnlegen = new JButton("Rezept anlegen");
		startAnlegen.addActionListener(new ActionListener() {
			@Override
			/**
			 * in der Action Performed Methode tauscht man ggf. einfach den 
			 * Namen des Dialogs aus, je nachdem welcher angezeit werden soll
			 */
			public void actionPerformed(ActionEvent arg0) {
				new RezeptAnlegenTestDialog(null);				
			} 
		});
		contentPane.add(startAnlegen);

	}

	protected void showFrame() {
		// size the frame to fit the preferred size of all components
		testFrame.setSize(300, 300);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		testFrame.setLocation((d.width - testFrame.getSize().width)/2, (d.height - testFrame.getSize().height)/2);
		// show the frame
		testFrame.setVisible(true);		
	}

}
