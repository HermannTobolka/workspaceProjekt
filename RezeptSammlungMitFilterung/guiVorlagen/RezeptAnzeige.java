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
 * Diese Klasse ist die Vorlage für die GUI Seite "Rezeptanzeige"
 * siehe Punkt 3.3.1.5 der Projektdoku
 * Um gleiche Bezeichnungen von Labels, frames etc zu vermeiden wird hier mit 
 * Label 15 fortgesetzt und Panel 14
 */

public class RezeptAnzeige implements ListSelectionListener {

	private JFrame frameRA;
	private JButton zurueck3;
	private JButton zurueckZumStart3;
	private JLabel l15;
	private String[] listA2;
	private JList<String> list2;
	//	private String[] listA3;
	//	private JList<String> list3;
	private JLabel l16;
	private JLabel l17;
	private JLabel l18;
	private JLabel l19;
	private JLabel l20;



	public RezeptAnzeige() {

		//Create and set up the window.
		frameRA = new JFrame(" Rezept des Tages ");
		frameRA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameRA.getContentPane();
		contentPane.setLayout(new GridLayout(4,1));

		//Gridlayout mit 4 Zeilen, die wieder mit Panels Gefüllt werden

		//Überschrift Rezept-Titel (JLabel dazu als Instanzvariable, da hier immer andeer Rezeptname stehen wird
		JPanel panel14 = new JPanel();
		//TODO Schriftgröße für einzelnes Panel / Label einstellbar - hier 16 am besten
		l15 = new JLabel("zum Beispiel RINDSGULASCH");
		panel14.add(l15);
		contentPane.add(panel14);

		//Zutatenliste
		JPanel panel15 = new JPanel(new BorderLayout());
		listA2 = new String[] {"500g Rindsgulaschfleisch", "500g Zwiebel", "Paprikapulver", "Weißwein"};
		list2 = new JList<String>(listA2);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.addListSelectionListener(this);
		panel15.add(list2, BorderLayout.PAGE_START);
		list2.setVisibleRowCount(3);
		JScrollPane listScrollPane2 = new JScrollPane(list2);
		panel15.add(listScrollPane2, BorderLayout.PAGE_START);
		contentPane.add(panel15);

		JPanel panel16 = new JPanel(new GridLayout(5,1));
		//TODO welches GUI ELement eignet sich besser für Kochanweisungen?
		//TODO Scrollpane wenn keine Table oder list?
		//TODO oder hier doch Liste wählen als Format?
		l16 = new JLabel("Das Fleisch würfelig schneiden");
		l17 = new JLabel("Die Zwiebel (mit dem Messer) klein schneiden - nicht hacken");
		l18 = new JLabel("Etwas Öl im Kochtopf erhitzen");
		l19 = new JLabel("Die geschnittenen Zwiebel im Öl glasig werden lassen, danach Fleisch und");
		l20 = new JLabel("Paprikapulver dazu geben. Das Fleisch scharf anbraten...");
		panel16.add(l16);
		panel16.add(l17);
		panel16.add(l18);
		panel16.add(l19);
		panel16.add(l20);
		JScrollPane anweisungenSP = new JScrollPane();
		panel16.add(anweisungenSP);
		contentPane.add(panel16);
		
		JPanel panel17 = new JPanel (new BorderLayout());
		zurueck3 = new JButton("Zurück zur Liste gefundener Rezepte");
		zurueckZumStart3 = new JButton("Zurück zum Start");
		panel17.add(zurueck3, BorderLayout.WEST);
		panel17.add(zurueckZumStart3, BorderLayout.EAST);
		contentPane.add(panel17);	
		
		
				}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameRA.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameRA.setLocation((d.width - frameRA.getSize().width)/2, (d.height - frameRA.getSize().height)/2);
		// show the frame
		frameRA.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
