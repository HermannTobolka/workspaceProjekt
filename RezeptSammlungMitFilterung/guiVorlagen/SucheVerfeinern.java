package guiVorlagen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * Diese Klasse ist die Vorlage für die GUI Seite "Suche verfeinern"
 * siehe Punkt 3.3.1.2 der Projektdoku
 * Um gleiche Bezeichnungen von Labels, frames etc zu vermeiden wird hier mit 
 * Label 8 fortgesetzt und Panel 3
 */


public class SucheVerfeinern {

	private JFrame frameSVf;
	private JButton zutatenSelektor;
	private JButton platzHalter2;
	private JButton filterBearbeiten;
	private JButton filterErstellen;
	private JButton filterLoeschen;
	private JButton zurueck;


	public SucheVerfeinern (){

		//Create and set up the window.
		frameSVf = new JFrame(" Suche verfeinern ");
		frameSVf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameSVf.getContentPane();
		contentPane.setLayout(new BorderLayout(20,10));

		// create layout for  top of window = north
		JPanel panel3 = new JPanel(new GridLayout(2,1));

		//Text zur Beschreibung Zutatenselektor
		JLabel l8 = new JLabel("Welche Zutaten dürfen es heute sein?");
		panel3.add(l8);

		// Button "Zutaten Selektor"
		zutatenSelektor = new JButton("Zum Zutaten Selektor");
		panel3.add(zutatenSelektor);

		// oberer Bereich Fertig - JPanel in Frame integrieren
		contentPane.add(panel3, BorderLayout.NORTH );

		// create layout for  middle of window = center
		JPanel panel4 = new JPanel(new GridLayout(3,2));

		//Text für Filterverwaltung 
		JLabel l9 = new JLabel("Vorlieben können sich auch ändern");
		JLabel l10 = new JLabel("Hier kannst du deine Spezialfilter verwalten");
		panel4.add(l9);
		panel4.add(l10);

		// Combobox und Button Filter Bearbeiten
		platzHalter2 = new JButton("ComboboxFilterAuswahl");
		panel4.add(platzHalter2);	
		filterBearbeiten = new JButton("Filter bearbeiten");
		panel4.add(filterBearbeiten);
		filterErstellen = new JButton("Filter erstellen");
		panel4.add(filterErstellen);
		filterLoeschen = new JButton("Filter löschen");
		panel4.add(filterLoeschen);

		//Mittelbereich fertig, Panel 4 hinzufügen
		contentPane.add(panel4,BorderLayout.CENTER);

		// create layout for bottom of window = south
		JPanel panel5 = new JPanel(new GridLayout(2,1));

		//Zurück Button
		zurueck = new JButton("Zurück");
		panel5.add(zurueck);

		// Exit Button
		//TODO: Exit Buttons unnötig?
		//		JButton exit2 = new JButton("Exit");
		//		panel5.add(exit2);

		// unterer Bereich fertig, Panel 5 hinzufügen
		contentPane.add(panel5, BorderLayout.SOUTH);


	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameSVf.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameSVf.setLocation((d.width - frameSVf.getSize().width)/2, (d.height - frameSVf.getSize().height)/2);
		// show the frame
		frameSVf.setVisible(true);
	} 


}
