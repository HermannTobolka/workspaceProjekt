package guiVorlagen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Diese Klasse ist die Vorlage für den späteren Startbildschirm inklusive Rezeptsuche mit Namen
 * siehe Punkt 3.3.1.1 der Projektdoku
 */

public class StartBildchirm {

	private JFrame frameSBS;
	private JTextField textfieldRezeptSuche;
	private JButton rezeptSuchen;
	private JButton sucheVerfeinern;
	//Einstweilen Bild ausgeborgt aus Bootsverleih
	private ImageIcon bild1 = new ImageIcon("c:\\users\\Hermann\\Workspace\\fatsir.jpg");
	private JButton exitButton;
	private JButton platzHalter1; // daraus wird Combobox filter Laden - wieder verwenden von GUi 1 möglich?

	public StartBildchirm (){ 

		//Create and set up the window.
		frameSBS = new JFrame("Hermanns Rezeptsammlung mit Spezialfilter ");
		frameSBS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameSBS.getContentPane();
		contentPane.setLayout(new BorderLayout(20,10));

		// create layout for left side of window = west
		JPanel panel1 = new JPanel(new GridLayout(10,1));

		// Text zur Beschreibung der Rezeptsuche mit Namen
		JLabel l1 = new JLabel(" Willst du einfach nur schnell ein Rezept suchen? ");
		panel1.add(l1);

		// Textfield und Button zur Rezeptsuche mit Namen

		// Textfield
		textfieldRezeptSuche = new JTextField("Gericht eingeben",20);
		panel1.add(textfieldRezeptSuche);

		// Button
		rezeptSuchen = new JButton("Rezept suchen");
		panel1.add(rezeptSuchen);

		// Text zur Beschreibung von Suche verfeinern
		JLabel l2 = new JLabel(" Wir helfen dir, das passende Rezept zu finden ");
		panel1.add(l2);

		JLabel l3 = new JLabel(" ob low-carb oder zur Auffülung des KH Speichers, vegetarisch, oder mit Fleisch.  ");
		panel1.add(l3);

		JLabel l4 = new JLabel(" Wenn du heute einfach Lust auf bestimmte Zutaten hat (oder andere vermeiden willst) ");
		panel1.add(l4);

		JLabel l5 = new JLabel(" Hier wird dir geholfen: ");
		panel1.add(l5);


		// Button für Suche verfeinern
		sucheVerfeinern = new JButton( "          Suche verfeinern          ");
		panel1.add(sucheVerfeinern);

		// Text zur Beschreibung von Filter laden
		JLabel l6 = new JLabel(" Für Stammgäste. Deine Vorlieben sind schon gespeichert? Lade hier deinen gespeicherten Spezialfilter");
		panel1.add(l6);

		// Combobox zum Laden der Filter 
		// JLabel nur als Platzhalter
		 platzHalter1 = new JButton("Filter laden (ich bin eine Combobox");
		panel1.add(platzHalter1);

		// Linke Fensterseite = fertig - panel 1 ab in den Frame
		contentPane.add(panel1, BorderLayout.WEST);


		// create layout for right side of window = east

		//Zusammenfassung in JPanel
		JPanel panel2 = new JPanel(new BorderLayout());

		// nettes Bild als Eyecatcher
		JLabel l7 = new JLabel(bild1);
		panel2.add(l7, BorderLayout.NORTH);

		// Exit Button
		exitButton = new JButton("Exit");
		panel2.add(exitButton,BorderLayout.SOUTH);

		// rechte Fensterseite = fertig, panel 2 in den Frame
		contentPane.add(panel2, BorderLayout.EAST);



	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameSBS.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameSBS.setLocation((d.width - frameSBS.getSize().width)/2, (d.height - frameSBS.getSize().height)/2);
		// show the frame
		frameSBS.setVisible(true);
	} 

}
