package guiVorlagen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Diese Klasse ist die Vorlage für die GUI Seite "Liste gefundener Rezepte"
 * siehe Punkt 3.3.1.4 der Projektdoku
 * Um gleiche Bezeichnungen von Labels, frames etc zu vermeiden wird hier mit 
 * Label 14 fortgesetzt und Panel 10
 */

public class ListeGefundenerRezepte implements ListSelectionListener {

	private JFrame frameLGR;
	private JButton rezeptAnzeige;
	private JButton rezeptErstellen;
	private JButton zurueck2;
	private JButton zurueckZumStart2;
	private String[] listA1;
	private JList<String> list1;

	public ListeGefundenerRezepte(){

		//Create and set up the window.
		frameLGR = new JFrame(" Die gute Auswahl ");
		frameLGR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameLGR.getContentPane();
		contentPane.setLayout(new GridLayout(5,1));

		// Wieder Zeilen für einzelene GUI Elemente in Panels in einem Gridlayout

		//Überschrift für Ergebnisliste gefundener Rezepte
		JPanel panel10 = new JPanel();
		JLabel l14 = new JLabel("Diese Rezepte entsprechen deinen Suchkriterien. Wähle eines, um es anzuzeigen");
		panel10.add(l14);
		contentPane.add(panel10);


		//Anzeige als JLIST
		
		JPanel panel11 = new JPanel(new BorderLayout());
		listA1 = new String[] {"Gulasch", "Putencurry", "Paprikahuhn", "Rindfleisch-Eintopf"};
		list1 = new JList<String>(listA1);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.addListSelectionListener(this);
		panel11.add(list1, BorderLayout.PAGE_START);
		list1.setVisibleRowCount(3);
		JScrollPane listScrollPane = new JScrollPane(list1);
		panel11.add(listScrollPane, BorderLayout.PAGE_START);
		contentPane.add(panel11);
		
		// Buttons Rezept anzeigen - neues Rezept anlegen
		//TODO eventuell hier noch erklärenden Text vorher einschieben
		
		JPanel panel12 = new JPanel(new BorderLayout());
		rezeptAnzeige = new JButton("Rezept anzeigen");
		rezeptErstellen= new JButton("Neues Rezept anlegen");
		panel12.add(rezeptAnzeige, BorderLayout.WEST);
		panel12.add(rezeptErstellen,BorderLayout.EAST);
		contentPane.add(panel12);
		
		// Am Schluss noch 2 zurück Buttons, ein Schritt zurück und zurück zum Start
		// Exit Button unnötig, gibt eh x
		JPanel panel13 = new JPanel(new BorderLayout());
		zurueck2 = new JButton("Zurück");
		zurueckZumStart2= new JButton("Zurück zum Start");
		panel13.add(zurueck2, BorderLayout.WEST);
		panel13.add(zurueckZumStart2,BorderLayout.EAST);
		contentPane.add(panel13);
		
		

	}


	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameLGR.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameLGR.setLocation((d.width - frameLGR.getSize().width)/2, (d.height - frameLGR.getSize().height)/2);
		// show the frame
		frameLGR.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	} 

}
