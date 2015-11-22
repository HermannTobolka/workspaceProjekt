package rezeptSammlung;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.derby.tools.sysinfo;


/*
 * Die GUI Vorlagen sind im View eingebunden.
 *  Alle GUI Seiten aus der ProjektDoku sind im View als innere Klassen implementiert.
 * Der Startbildschirm ausgenommen, der ist direkt Constructor von View enthalten. 
 * Zur besseren Übersichtlichkeit sind die Klassen ausführlich kommentiert, mit Kommentaren zu Layout und
 * zu den Stellen, wo ein Methodenaufruf passiert, also eine Funktion aus Kapitel 3 der Projekt-Dokuementation aufgerufen wird
 * 

 * 
 * 
 */

@SuppressWarnings("unused")
public class View  {


	private Controller controller;

	//Instanzvariablen für GUI Übergänge von Seite zu Seite

	private String rezeptNameUser;
	private String s1;
	private String selectedRezept;
	private Rezept r;

	// für Rezept-Anzeige
	private JTable zutatMengeTable;

	// für Filter speichern und RezeptSuche mit Filter

	private ArrayList<String> zutatenJa = new ArrayList<String>();
	private ArrayList<String>zutatenNein= new ArrayList<String>();
	private ArrayList<String> rezeptKategorie = new ArrayList<String>();
	private String fNameSBS;
	private String [] rArray;

	

	// Instanzvariablen für Startbildschirm -GUI

	private JFrame frameSBS;
	private JTextField textfieldRezeptSuche;
	private JButton rezeptSuchen;
	private JButton sucheVerfeinern;
	private ImageIcon bild1 = new ImageIcon("c:\\users\\Hermann\\Workspace\\fatsir.jpg"); //Einstweilen Bild ausgeborgt aus Bootsverleih
	private JButton filterAnwendenSBS;
	private JComboBox<String> cbFilter;  

	/**
	 * Constructor; MIT ANZEIGE STARTBILDSCHIRMkeep reference to the controller and have controller register the view.
	 * Constructor enthält auch Layout Startbildschirm mit liste gespeicherter Filter und RezeptSuche mit Namen
	 * @param c
	 */
	public View(Controller c) {
		controller = c;
		controller.registerView(this);

		/*
		 * LAYOUT StartBildschirm
		 * Borderlayout, 
		 * Frame mittels 2 Panels in links und rechts geteilt
		 * Links panel1 Text, Buttons, Textfield und Combobox,
		 * rechts in Panel2 ein Appetitanregendes Bild
		 * 
		 */
		frameSBS = new JFrame("Hermanns Rezeptsammlung mit Spezialfilter ");
		frameSBS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameSBS.getContentPane();
		contentPane.setLayout(new BorderLayout(20,10));

		// StartBildchirm links
		JPanel panel1 = new JPanel(new GridLayout(11,1));

		/*
		 * REZEPTSUCHE MIT NAMEN
		 * Label zur Beschreibung, Textfield zur Eingabe und Button um Suche zu starten. 
		 * Derzeit Ausgabe auf der Console 
		 */ 


		JLabel l1 = new JLabel(" Willst du einfach nur schnell ein Rezept suchen? ");
		panel1.add(l1);		
		JLabel l1a = new JLabel("Tipp: gib einen Teil des Suchbegriffs ein, etwa Rind statt Rindsgulasch");
		panel1.add(l1a);
		textfieldRezeptSuche = new JTextField("",20);
		panel1.add(textfieldRezeptSuche);

		/*
		 * REZEPTSUCHE MIT Namen AUFRUF Rezepte auflisten
		 */
		rezeptSuchen = new JButton("Rezept suchen");
		rezeptSuchen.addActionListener(new ActionListener() {

			/**
			 * Ruft Rezept auflisten und RezeptSuche mit Namen Methoden aus dem Model auf
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rezeptNameUser  = textfieldRezeptSuche.getText();
				rArray = controller.rezepteAuflisten(controller.rezeptSuchenName(rezeptNameUser));
				new ListeGefundenerRezepte(null);
			} 
		});
		panel1.add(rezeptSuchen);


		/*
		 * AUFRUF SUCHE VERFEINERN 
		 */

		JLabel l2 = new JLabel(" Wir helfen Dir, das passende Rezept zu finden ");
		JLabel l3 = new JLabel(" ob low-carb oder zur Auffüllung des Kohlehydrat-Speichers, vegetarisch oder mit Fleisch.  ");
		JLabel l4 = new JLabel(" Wenn Du heute einfach Lust auf bestimmte Zutaten hat (oder andere vermeiden willst) ");
		JLabel l5 = new JLabel(" Hier wird Dir geholfen: ");

		sucheVerfeinern = new JButton( "Suche verfeinern");
		sucheVerfeinern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SucheVerfeinern(frameSBS);				
			} 
		});
		panel1.add(l2);
		panel1.add(l3);
		panel1.add(l5);
		panel1.add(l4);
		panel1.add(sucheVerfeinern);

		/*
		 * ComboBox, die alle Filter auflistet
		 */
		JLabel l6 = new JLabel(" Für Stammgäste. Deine Vorlieben sind schon gespeichert? Lade hier deinen gespeicherten Spezialfilter");
		panel1.add(l6);
		String[] filterArray = controller.filterAuflisten(); 
		cbFilter = new JComboBox<String>(filterArray);
		cbFilter.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> cbFilter = (JComboBox<String>)event.getSource();
				System.out.println("Es wurde selektiert: " + cbFilter.getSelectedItem());
				fNameSBS = cbFilter.getSelectedItem().toString();
			}
		});
		panel1.add(cbFilter);
		contentPane.add(panel1, BorderLayout.WEST);


		// Startbildchirm rechts

		/*
		 * BILD als Eyecatcher  
		 */

		JPanel panel2 = new JPanel(new BorderLayout());
		// nettes Bild als Eyecatcher
		JLabel l7 = new JLabel(bild1);
		panel2.add(l7, BorderLayout.NORTH);

		/*
		 * Filter anwenden
		 */

		filterAnwendenSBS = new JButton("Filter Anwenden");
		filterAnwendenSBS.addActionListener(new ActionListener() {
 			@Override
			public void actionPerformed(ActionEvent arg0) {
 				System.out.println(fNameSBS);
 				
			if (fNameSBS != null){
 				rArray =   controller.filterAnwenden(fNameSBS);		
			System.out.println(rArray[0]);
				new ListeGefundenerRezepte(null);
			}
			else return;
			} 
		});
		panel2.add(filterAnwendenSBS,BorderLayout.SOUTH);
		contentPane.add(panel2, BorderLayout.EAST);
	}

	/**
	 * StartBildschirm anzeigen 
	 */
	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameSBS.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameSBS.setLocation((d.width - frameSBS.getSize().width)/2, (d.height - frameSBS.getSize().height)/2);
		// show the frame
		frameSBS.setVisible(true);
	} 

	/**
	 * initialize View
	 */
	public void initialize() { 

	}

	/*
	 * SUCHE VERFEINERN GUI FENSTER
	 * Als modaler JDialog, der vom Startbildschirm aus aufgerufen wird
	 */

	@SuppressWarnings("serial")
	class SucheVerfeinern extends JDialog {

		//Buttons hier instanziiert
		private JButton zutatenSelektor;
		private JButton filterLoeschen;
		// hier eigener fName Parameter - fraglich ob notwendig, aber ist besser lesabar
		private String fNameSucheVerfeinern;

		public SucheVerfeinern (JFrame frameSBS ){
			super(frameSBS, "Suche verfeinern", true);

			/*
			 * Layout SUCHE VERFEINERN		
			 * Derzeit Borderlayout mit 3 Panels mit innerem Gridlayout
			 * Oben Aufruf ZutatenSelektor Panel3
			 * Mitte Verwaltung der Filter Panel4
			 * Unten leeres Panel
			 * 
			 */

			this.setLayout(new BorderLayout(20,10));

			// Suche Verfeinern Oben Panel3

			/*
			 * AUFRUF ZUTATENSELEKTOR
			 * Label zur Beschreibung
			 * Button mit Actionlistener
			 * 
			 */
			JPanel panel3 = new JPanel(new GridLayout(2,1));	 
			JLabel l8 = new JLabel("Im Zutatenselektor kannst du Rezepte nach Kategorie und Zutaten "
					+ "suchen und deine Suchkriterien als Filter speichern ");
			panel3.add(l8);
			zutatenSelektor = new JButton("Zum Zutaten Selektor");
			zutatenSelektor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ZutatenSelektor(null);				
				} 
			});
			panel3.add(zutatenSelektor);
			this.add(panel3, BorderLayout.NORTH );

			//Suche Verfeinern Mitte Panel4

			/*
			 * AUFRUF FILTER-VERWALTUNG (anlegen, löschen speichern, bearbeiten)
			 * Label mit Beschreibung
			 * Combobox mit Filterliste
			 * Buttons für jeweilige AKtivität
			 * 
			 */
			JPanel panel4 = new JPanel(new GridLayout(3,2));
			JLabel l9 = new JLabel("Vorlieben können sich auch ändern");
			JLabel l10 = new JLabel("Hier kannst du Filter wieder löschen");
			panel4.add(l9);
			panel4.add(l10);

			/*
			 * AUFLISTEN Filter mittels Combobox
			 */

			String[] filterArray = controller.filterAuflisten(); 
			cbFilter = new JComboBox<String>(filterArray);
			cbFilter.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent event) {
					JComboBox<String> cbFilter = (JComboBox<String>)event.getSource();
					System.out.println("Es wurde selektiert: " + cbFilter.getSelectedItem());
					fNameSucheVerfeinern = new String(cbFilter.getSelectedItem().toString());
				}
			});
			panel4.add(cbFilter);
			
			/*
			 * Aufruf FILTER LÖSCHEN
			 */

			filterLoeschen = new JButton("Filter löschen");
			filterLoeschen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.filterLoeschen(fNameSucheVerfeinern);
				}});
			panel4.add(filterLoeschen);
			this.add(panel4,BorderLayout.CENTER);

			// Suche Verfeinern Unten Panel 5

			/*
			 *   Unten 
			 */
			JPanel panel5 = new JPanel(new GridLayout(2,1));
			this.add(panel5, BorderLayout.SOUTH);


			//position the "Suche verfeinern" Dialog
			this.pack();
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);
		}
	}

	/*
	 * ZUTATEN SELEKTOR GUI FENSTER
	 * Als modaler JDialog, der von der GUI Seite Suche Verfeienern aus aufgerufen wird
	 * 
	 */

	@SuppressWarnings("serial")
	class ZutatenSelektor extends JDialog implements ItemListener {

		//Buttons, Checkboxen, Table für Zutatenselektor hier instanziiert
		private JRadioButton lowCarbButton;
		private JRadioButton khButton;
		private JCheckBox vegCB;
		private JTable zutatenTable;
		private JButton filterSpeichern;
		private JButton rezeptSucheZutatenselektor;
		private JButton zutatAnlegen;
		private boolean zutatJaNein;
		// ArrayListen für die DB Abfrage via Controller und Modell 



		public ZutatenSelektor(JDialog SucheVerfeinern){

			super(SucheVerfeinern, "Zutaten Selektor",true);

			/*
			 * LAYOUT für ZUTATENSELEKTOR
			 * Borderlayout für oben, Mitte, unten
			 * Oben Checkboxen für Rezeptkategorie (mit innerem Borderlayout)
			 * Mitte ZutatenListe als Table mit Plus und Minus Spalte, Plus Buttons für Aktionen
			 * Unten Zurück Button
			 * 
			 */

			this.setLayout(new BorderLayout());

			//ZUTATENSELEKTOR Oben Panel 6 

			/*
			 * Rezeptkategorie per Checkbox wählen
			 * Beschreibung in JLABEL oben
			 * Checkboxen in innerem JPanel Panel7 unten
			 * 
			 */

			JPanel panel6 = new JPanel(new BorderLayout());
			JLabel l11 = new JLabel("Hier kannst du optional eine Kategorie wählen (oder mehrere)");
			panel6.add(l11, BorderLayout.NORTH);
			JPanel panel7 = new JPanel(new GridLayout(1,3));
			lowCarbButton = new JRadioButton("low Carb");
			khButton = new JRadioButton("Kohlehydrat-reich");
			vegCB = new JCheckBox("vegetarisch");
			lowCarbButton.addItemListener(this);
			khButton.addItemListener(this);
			vegCB.addItemListener(this);
			lowCarbButton.setActionCommand("lowcarb");
			khButton.setActionCommand("khreich");
			vegCB.setActionCommand("vegetarisch");
			ButtonGroup bg = new ButtonGroup();
			bg.add(lowCarbButton);
			bg.add(khButton);

			panel7.add(lowCarbButton);
			panel7.add(khButton);
			panel7.add(vegCB);
			panel6.add(panel7,BorderLayout.SOUTH);
			this.add(panel6, BorderLayout.NORTH);

			// ZUTATENSELEKTOR Mitte Panel 8 JTABLE

			/*
			 * Label zur Beschreibung
			 * JTable mit Zuatenliste 
			 * Mit Plus und Minus Spalte
			 * 
			 */

			JPanel panel8 = new JPanel(new BorderLayout());
			JPanel panel8a = new JPanel(new GridLayout(2,1));
			JLabel l12 = new JLabel("Such Dir aus, auf welche Zutaten Du so richtig Gusto hast, und welche Du weglassen willst");
			JLabel l12a = new JLabel("Gib je maximal 2 Zutaten ein, bei mehr wird die Filterung nach Zutaten ignoriert ");
			panel8a.add(l12);
			panel8a.add(l12a);
			panel8.add(panel8a,BorderLayout.NORTH);

			//StringArray für Spalten der Zutaten Table ZUTATENSELEKTOR
			final String[] column = {"Zutat" , "Ja bitte, her damit!", "Nein danke, bitte weglassen"};

			// Stringarray für Zutatenamen 
			String [] zutatenNamenArray = controller.zutatenAuflisten();

			System.out.println(zutatenNamenArray.length);

			// create TableModel Zutatentable ZUTATENSELEKTOR
			AbstractTableModel atm = new AbstractTableModel() {


				@Override
				public int getColumnCount() {
					return column.length;
				}

				@Override
				public int getRowCount() {

					return zutatenNamenArray.length ;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {

					switch (columnIndex){

					case 0: return zutatenNamenArray[rowIndex];
					case 1: return "ja";
					case 2: return  "nein";
					}
					return null;
				}
			};

			// create Column Model Zutatentable ZUTATENSELEKTOR
			DefaultTableColumnModel dtcm = new DefaultTableColumnModel();
			for(int i = 0; i < column.length; i++) {
				TableColumn tc = new TableColumn(i);
				tc.setHeaderValue(column[i]);
				dtcm.addColumn(tc);
			}

			// Tabelle ZUTATENSELEKTOR zusammenbauen
			zutatenTable = new JTable(atm, dtcm);
			zutatenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			// Zutat inkludieren oder exkludieren - Zutaten werden in Arraylist gesammelt, mit der Suchstring gebaut werden kann

			zutatenTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {

					int spaltenwahl;
					String zutat; 				
					spaltenwahl = zutatenTable.getSelectedColumn();
					System.out.println(zutatenTable.getValueAt(zutatenTable.getSelectedRow(), 0).toString());
					zutat =  zutatenTable.getValueAt(zutatenTable.getSelectedRow(), 0).toString();
					if (spaltenwahl == 1){
						//zutatJaNein = true;
						zutatenJa.add(zutat);
					}
					else if (spaltenwahl == 2) {
						//zutatJaNein = false;
						zutatenNein.add(zutat);
					}
					else return;
					// Kontrollausgabe auf Console	
					//					System.out.println("selected");
					//					System.out.println(zutatenTable.getValueAt(zutatenTable.getSelectedRow(),0) + " inkludieren " + zutatJaNein ) ;

					// value Changed Methode erfasst alle Klicks, produziert aber doppelte Einträge - diese werden mit removeDuplicates Methode bereinigt
					removeDuplicates(zutatenJa);
					removeDuplicates(zutatenNein);
					System.out.println("Zutaten für inklusion " + zutatenJa);
					System.out.println("Zutaten für exklusion " + zutatenNein);
				}
			});
			JScrollPane sp = new JScrollPane(zutatenTable);
			panel8.add(sp,BorderLayout.CENTER);
			this.add(panel8, BorderLayout.CENTER);


			//  ZUTATENSELEKTOR Unten Panel9

			/*
			 * Buttons für FILTER SPEICHERN,   AUFRUF LISTE GEFUNDENER REZEPTE
			 */
			JPanel panel9 = new JPanel(new BorderLayout());

			//Zutatenselektor AUFRUF FILTER SPEICHERN - führt zu kleinem Filter speichern Dialog
			filterSpeichern = new JButton("Auswahl als Filter speichern");
			filterSpeichern.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new FilterAnlegen(null);				 
				} 
			});

			//Zutatenselektor AUFRUF LISTE GEFUNDENER REZEPTE

			rezeptSucheZutatenselektor = new JButton("Zeig mir Rezepte");
			rezeptSucheZutatenselektor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					rArray = controller.rezeptSucheFilter(rezeptKategorie, zutatenJa, zutatenNein);
					new ListeGefundenerRezepte( null);
				} 
			});

			/*
			 *  AUFRUF Zutat Anlegen 
			 */

			zutatAnlegen = new JButton("Zutat anlegen");
			zutatAnlegen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ZutatAnlegen(null);

				}});
			panel9.add(filterSpeichern, BorderLayout.LINE_START);
			panel9.add(rezeptSucheZutatenselektor,BorderLayout.CENTER);
			panel9.add(zutatAnlegen,BorderLayout.PAGE_END);
			this.add(panel9,BorderLayout.SOUTH);

			// size the Dialog ZuatenSelektor
			this.pack();
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);


		}

		/**
		 * liefert eine Rezept-Kategorie, nach der gesucht werden soll (also Lowcarb vs. khreich, vegetarisch)
		 * in Form einer arraylist, in der nur die Kategorie aufgelistet ist, die true ist
		 */
		@Override
		public void itemStateChanged(ItemEvent event) {
			Object o = event.getSource();
			int seldesel = event.getStateChange();
			String ac = "?";
			if (o instanceof AbstractButton)
				ac = ((AbstractButton)o).getActionCommand();
			if (seldesel == ItemEvent.SELECTED){
				System.out.println(ac + " wurde selektiert");
				rezeptKategorie.add(ac);
				System.out.println(rezeptKategorie);
			}

			else
			{System.out.println(ac + " wurde deselektiert");		
			rezeptKategorie.remove(ac);}
			System.out.println(rezeptKategorie);

		}
	}


	/*
	 * LISTE gefundener REZEPTE GUI FENSTER
	 * Als modaler JDialog, mit JList
	 * 
	 */


	@SuppressWarnings("serial")
	class ListeGefundenerRezepte extends JDialog implements ListSelectionListener {

		// Buttons, Liste instanziieren für LISTE gef REZEPTE
		private JButton rAnzeigButton;
		private JButton rErstellenButton;
		private JButton zurueck2;
		private JButton zurueckZumStart2;



		 
		private JList<String> rezeptListe;

		public ListeGefundenerRezepte(JDialog ZutatenSelektor){
			super( ZutatenSelektor, "Liste gefundener Rezepte",true);		

			/*
			 * LAYOUT LISTE gef REZEPTE
			 * Hier funktioniert einfaches Gridlayout mit 5 Zeilen recht gut
			 * Einzelene Bereiche in Panels gegliedert, Panel 10-Panel13
			 * Beschreibung, Liste, Buttons mit Aktionen 
			 */

			this.setLayout(new GridLayout(5,1));

			// LISTE Gef REZEPTE Oben, Panel10 mit Überschrift, Panel 11 mit JLIST

			JPanel panel10 = new JPanel();
			JLabel l14 = new JLabel("Diese Rezepte entsprechen deinen Suchkriterien. Wähle eines, um es anzuzeigen");
			panel10.add(l14);
			this.add(panel10);
			JPanel panel11 = new JPanel(new BorderLayout());


			/*
			 * Aufruf Rezepte Auflisten und Anzeige als Liste. 
			 * Param rName hier noch fix ausgefüllt zu Testzwecken- noch nicht eindeutig wie der aus Zutatenselektor geliefert wird 
			 * noch nicht sicher, ob mehrere Rezeptsuchen Methoden brauche. Oder doch eine mit Auflisten und eine mit suchen. 
			 */

			//String[] rezeptArray =  controller.rezepteAuflisten(controller.rezeptSuchenName(rezeptNameUser));
			// Testausgabe rezeptArray
//			System.out.println("=======Testausgabe Inhalt String[] Rezeptarray aus dem View=====");
//			System.out.println("");
//			for (String s: rezeptArray){
//				System.out.println(s);
//			}
	//		System.out.println("======= Ende TEstausgabe aus View==========");
			rezeptListe = new JList<String>(rArray);
			rezeptListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			rezeptListe.addListSelectionListener(this);
			panel11.add(rezeptListe, BorderLayout.PAGE_START);
			rezeptListe.setVisibleRowCount(4);
			JScrollPane listScrollPane = new JScrollPane(rezeptListe);
			panel11.add(listScrollPane, BorderLayout.PAGE_START);
			this.add(panel11);

			// LISTE GEF REZEPTE Mitte Panel 12
			JPanel panel12 = new JPanel(new BorderLayout());

			/*
			 * AUFRUF REZEPTANZEIGE aus LISTE Gef REzepte 
			 */
			rAnzeigButton = new JButton("Rezept anzeigen");
			rAnzeigButton.addActionListener(new ActionListener() {

				/**
				 * ruft die rezepAnzeigen Methode aus Model auf. 
				 * @param: selectedRezept = Name gewähltes REzept, das angezeigt werden soll
				 * @return: rezeptObjekt, das Daten zum Befüllen der Rezeptanzeige liefert
				 */
				@Override
				public void actionPerformed(ActionEvent arg0) {
					r= controller.rezeptAnzeigen(selectedRezept);
					//					System.out.println("Testausgabe Rezept Anzeigen Button");
					//					System.out.println(r.toString());
					//					System.out.println(" &&&&& ENDE Testausgabe Rezept Anzeigen Button");
					//					
					new RezeptAnzeige(null);				
				} 
			});

			/*
			 * AUFRUF REZEPT ERstellen (aus LISTE gef REZEPTE)
			 */
			rErstellenButton= new JButton("Neues Rezept anlegen");
			rErstellenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new RezeptAnlegen(null);
				}});

			panel12.add(rAnzeigButton, BorderLayout.WEST);
			panel12.add(rErstellenButton,BorderLayout.EAST);
			this.add(panel12);

			// LISTE GEFUNDENER REZEPTE Unten, Panel 13 mit Navigations-Buttons

			JPanel panel13 = new JPanel(new BorderLayout());
			zurueck2 = new JButton("Zurück");
			zurueckZumStart2= new JButton("Zurück zum Start");
			panel13.add(zurueck2, BorderLayout.WEST);
			panel13.add(zurueckZumStart2,BorderLayout.EAST);
			this.add(panel13);

			// size LISTE Gef REZEPTE Windwos the preferred size of all components
			this.pack();
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);

		}

		@SuppressWarnings("unchecked")
		@Override
		/**
		 * erfasst Name des Rezepts, dass im nächsten GUI Fenster (Rezeptanzeige) angezeigt werden soll
		 */
		public void valueChanged(ListSelectionEvent event) {
			// handle only, if selection has been finalized
			if (!event.getValueIsAdjusting()) {
				JList<String> list = (JList<String>)event.getSource();
				System.out.println("Es wurde selektiert: " + list.getSelectedValue());
				selectedRezept = list.getSelectedValue();

			}

		}

	}


	/*
	 * REZEPTANZEIGE GUI FENSTER
	 * Als modaler JDialog, der von der GUI Seite Liste gefundener REzepte aus aufgerufen wird
	 *
	 * 
	 */


	@SuppressWarnings("serial")
	class RezeptAnzeige extends JDialog implements ListSelectionListener {


		private JList<String> list2;



		public RezeptAnzeige(JDialog ListeGefundenerRezepte) {
			super(ListeGefundenerRezepte, " Rezept des Tages",true);

			/*
			 * LAYOUT REZEPTANZEIGE
			 * Borderlayout                   << ALT: Gridlayout mit 4 Zeilen, die wieder mit Panels Gefüllt werden>>
			 * Oben Überschrift
			 * Mitte Zutatenliste
			 * unten Kochanweiseungen
			 * 
			 * 
			 */
			this.setLayout(new BorderLayout());

			// REZEPTANZEIGE Oben Panel14	ÜBERSCHRIFT		
			JPanel panel14 = new JPanel();
			// TODO schöner Machen Überschrift Rezeptanzeigen zb mit fetter großer Schrift für nächste Version
			JLabel l15 = new JLabel(r.getRezeptName().toUpperCase(),2);
			panel14.add(l15);
			this.add(panel14,BorderLayout.NORTH);



			//REZEPTANZEIGE Mitte ZUTATEN + ZUTATENMENGE  Panel 15mit JTABLE 

			// Panel und Scrollpane für Tabelle ZUTATENSELEKTOR
			zutatMengeTable = zutatMengeFuellen(r);
			JPanel spPanelRA = new JPanel();
			JScrollPane spRA = new JScrollPane(zutatMengeTable);
			spPanelRA.add(spRA);
			spPanelRA.add(spRA,BorderLayout.CENTER);
			this.add(spPanelRA, BorderLayout.CENTER);


			//REZEPTANZEIGE Mitte Kochanweisungen Panel 16 mit TEXTAREA

			JPanel panel16 = new JPanel(new BorderLayout());
			JTextArea rAnzeigeArea = new JTextArea(r.getKochAnweisung());
			rAnzeigeArea.setEditable(false);
			// rAnzeigeArea.setPreferredSize(new Dimension(400	, 150));

			//Zeilenumbruch wird eingeschaltet
			rAnzeigeArea.setLineWrap(true);

			//Zeilenumbrüche erfolgen nur nach ganzen Wörtern
			rAnzeigeArea.setWrapStyleWord(true);

			JScrollPane anweisungenSP = new JScrollPane(rAnzeigeArea);
			panel16.add(anweisungenSP,BorderLayout.PAGE_START);
			this.add(panel16,BorderLayout.SOUTH);

			/*
			 * Zurück Buttons kann man ausblenden, da modaler Dialog
			 */

			//			//REZEPTANZEIGE Unten Navigations-BUttons Panel 17
			//			JPanel panel17 = new JPanel (new BorderLayout());
			//			zurueck3 = new JButton("Zurück zur Liste gefundener Rezepte");
			//			zurueckZumStart3 = new JButton("Zurück zum Start");
			//			panel17.add(zurueck3, BorderLayout.WEST);
			//			panel17.add(zurueckZumStart3, BorderLayout.EAST);
			//			this.add(panel17);	

			//REZEPTANZEIGE Positionierung Fenster
			// TODO: bei Gelegenheit Fenster schöner dimensionieren 
			this.pack();
			//this.setSize(600, 500); 
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			//hier passiert nichts, Liste wird nur zur Anzeige der Zutaten und Mengen verwendet
		}

	}

	/*
	 * Zutat anlegen GUI Fenster (Textfeld für Namen, Button zum anlegen)
	 */

	@SuppressWarnings("serial")
	class ZutatAnlegen extends JDialog{

		//Intanzvariablen ZUTAT ANLEGEN 
		private JTextField textField;

		public ZutatAnlegen(JDialog ZutatenSelektor) {

			super(ZutatenSelektor, "Zutat anlegen",true);		

			/*
			 * LAYOUT Zutaten Anlegn
			 * ist recht einfach, BUtton und Textfeld   
			 */

			this.setLayout(new BorderLayout());

			// JPANEL für Zutat-Anlegen Teil
			JPanel zaPanel = new JPanel (new GridLayout (1,2,20,10));

			/*
			 * AUFRUF ZUTAT ANLEGEN aus Model über Controler 
			 */
			JButton zAnleg = new JButton("Zutat anlegen");
			zAnleg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String zEingabe = textField.getText();
					controller.zutatAnlegen(zEingabe);

				}});
			zaPanel.add(zAnleg);	
			// Textfeld
			textField = new JTextField("neueZutat", 20);
			zaPanel.add(textField);
			this.add(zaPanel);

			// size the frame to fit the preferred size of all components
			this.pack();
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);
		}
	}

	/*
	 * Filter Anlegen GUI Fenster (Textfeld für Namen, Button zum Anlegen)
	 */

	@SuppressWarnings("serial")
	class FilterAnlegen  extends JDialog{
		String fName;
		//Intanzvariablen Filter ANLEGEN 
		private JTextField textField;

		public FilterAnlegen(JDialog ZutatenSelektor) {

			super(ZutatenSelektor , "Filter speichern",true);		

			/*
			 * LAYOUT Filter Anlegen
			 * ist recht einfach, BUtton und Textfeld   
			 */

			this.setLayout(new BorderLayout());

			// JPANEL für Zutat-Anlegen Teil
			JPanel faPanel = new JPanel (new GridLayout (2,2,20,10));

			/*
			 * AUFRUF Filter ANLEGEN Methode aus MOdel über Controller
			 */

			// Textfeld
			textField = new JTextField("NameFilter", 20);
			faPanel.add(textField);
			JButton fAnleg = new JButton("Name speichern");
			fAnleg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					fName = textField.getText();
				}});
			faPanel.add(fAnleg);	
			JButton fspeichern = new JButton("Filter speichern");
			fspeichern.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.filterAnlegen(fName, rezeptKategorie, zutatenJa, zutatenNein);
				}});

			faPanel.add(fspeichern);
			this.add(faPanel);

			// size the frame to fit the preferred size of all components
			this.pack();
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);
		}
	}

	/**
	 * Aus dem Rezept-Objekt werden die Instanzvariablen ausgelesen, die angezeigt werden sollen
	 * diese werden in einer Tabelle angezeigt. 
	 * @param r
	 */
	@SuppressWarnings("serial")
	public JTable zutatMengeFuellen (Rezept r){

		// Arrays mit zutat und zutatxmenge zum befüllen der tabelle

		String [] zutatenArray =   r.getZutatenRezept().toArray(new String[r.getZutatenRezept().size()]);


		//StringArray für Spalten der von Zutaten und Mengenangabe Rezeptanzeige
		final String[] column = {"Zutaten"};

		// create TableModel zutat x Menge Rezept anzeige
		AbstractTableModel atm = new AbstractTableModel() {

			@Override
			public int getColumnCount() {
				return column.length;
			}

			@Override
			public int getRowCount() {
				int rc = 0;
				for (int i =0;i<zutatenArray.length; i++) {
					if (!(zutatenArray[i].equals("nichts")))
						rc++;
				}

				return rc;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex){
				case 0: return zutatenArray[rowIndex];


				}

				return null;
			}

		};

		// create Column Model Zutatentable Rezeptanzeige
		DefaultTableColumnModel dtcm = new DefaultTableColumnModel();
		for(int i = 0; i < column.length; i++) {
			TableColumn tc = new TableColumn(i);
			tc.setHeaderValue(column[i]);
			dtcm.addColumn(tc);
		}

		// Tabelle zutat x Mange zusammenbauen
		zutatMengeTable = new JTable(atm, dtcm);
		//zutatMengeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		zutatMengeTable.getColumnModel().getColumn(0).setMinWidth(70);
		zutatMengeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//hier Passiert nichts, Tabelle dient nur der Anzeige
			}
		});

		return zutatMengeTable;

	}

	/**
	 * Entfernt doppelte Einträge aus einer ArrayList mit Strings
	 * @param strings
	 * @return
	 */

	public  int removeDuplicates(ArrayList<String> strings) {

		int size = strings.size();
		int duplicates = 0;

		// not using a method in the check also speeds up the execution
		// also i must be less that size-1 so that j doesn't
		// throw IndexOutOfBoundsException
		for (int i = 0; i < size - 1; i++) {
			// start from the next item after strings[i]
			// since the ones before are checked
			for (int j = i + 1; j < size; j++) {
				// no need for if ( i == j ) here
				if (!strings.get(j).equals(strings.get(i)))
					continue;
				duplicates++;
				strings.remove(j);
				// decrease j because the array got re-indexed
				j--;
				// decrease the size of the array
				size--;
			} // for j
		} // for i

		return duplicates;

	}

	/**
	 * Dialog für rezept Anlegen
	 * @author Hermann
	 *
	 */

	@SuppressWarnings("serial")
	class RezeptAnlegen  extends JDialog implements   ItemListener {
		boolean lc = false;
		boolean kh = false;
		boolean veg = false;

		String kochAnweisung ="";
		String rName ="";
		private JComboBox<String> cBZutaten;
		private String[] zutatenArray2;
		//private ArrayList<String> rezeptKategorie2 = new ArrayList<String>();
		private ArrayList<String>zutatenNamenList = new ArrayList<String>();
		ArrayList<String> mengenList = new ArrayList<String>();
		String zutat ="";

		public RezeptAnlegen (JDialog ZutatenSelektor){
			super(ZutatenSelektor , "Rezept anlegen",true);

			/*
			 * LAYOUT Rezept Anlegen 
			 * Borderlayout                   
			 * Oben         Titel Rezept + Kategorie
			 * Mitte 		Zutatenliste  + TF für Menge eingeben  + Button für nächste Zutat
			 * unten 		Text Area für Kochanweiseungen , Button für speichern
			 *		 
			 */
			this.setLayout(new BorderLayout());


			/*
			 * OBEN / 1
			 *  Name Abfragen und speichern
			 */
			JPanel panelRAnlTop = new JPanel(new GridLayout(2,2));
			//JLabel l15 = new JLabel("r.getRezeptName().toUpperCase(),2)");
			JTextField rezeptNameTF = new JTextField("Name_Rezept");
			panelRAnlTop.add(rezeptNameTF);
			JButton rezeptNameSpeichern = new JButton("Name speichern");
			panelRAnlTop.add(rezeptNameSpeichern);
			rezeptNameSpeichern.addActionListener(new ActionListener() {
				@Override			
				public void actionPerformed(ActionEvent event) {
					rName = rezeptNameTF.getText();
				}
			});

			/*
			 * Oben / 2
			 * Rezept Kategorie  - Item-Listener dazu unten 
			 */
			JPanel panelRKat = new JPanel(new BorderLayout());
			JLabel l11 = new JLabel("Wähle zwischen Low Carb und KH reich, setz Haken wenn Rezept vegetarisch ist");
			panelRKat.add(l11, BorderLayout.NORTH);
			JPanel panelRKatInnen = new JPanel(new GridLayout(1,3));
			JRadioButton lowCarbButton = new JRadioButton("low Carb");
			JRadioButton khButton = new JRadioButton("Kohlehydrat-reich");
			JCheckBox vegCB = new JCheckBox("vegetarisch");
			lowCarbButton.addItemListener(this);
			khButton.addItemListener(this);
			vegCB.addItemListener(this);
			lowCarbButton.setActionCommand("lowcarb");
			khButton.setActionCommand("khreich");
			vegCB.setActionCommand("vegetarisch");
			ButtonGroup bg = new ButtonGroup();
			bg.add(lowCarbButton);
			bg.add(khButton);

			panelRKatInnen.add(lowCarbButton);
			panelRKatInnen.add(khButton);
			panelRKatInnen.add(vegCB);
			panelRKat.add(panelRKatInnen,BorderLayout.SOUTH);
			panelRAnlTop.add(panelRKat);
			this.add(panelRAnlTop,BorderLayout.NORTH);

			/*
			 * Mitte  
			 * Zutatentabelle , TF für Menge und Button speichern/Weiter  
			 * (in eigenem Panel mit entspr Layout
			 */

			JPanel rAnlegenMitte = new JPanel(new GridLayout(1,3));

			//Combobox mit Zutatenliste
			//String[] zutatenArray2 = {"Salz", "Pfeffer", "Rindfleisch", "Rindssuppe"}; 
			zutatenArray2 = controller.zutatenAuflisten();
			cBZutaten = new JComboBox<String>(zutatenArray2);
			cBZutaten.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override			
				public void actionPerformed(ActionEvent event) {
					JComboBox<String> cBZutaten = (JComboBox<String>)event.getSource();
					System.out.println("Es wurde selektiert: " + cBZutaten.getSelectedItem());
					zutat = cBZutaten.getSelectedItem().toString();
				}
			});
			rAnlegenMitte.add(cBZutaten);

			// Textfeld für Mengeneingabe
			JTextField mengenEingabe = new JTextField("Menge_Eingeben");
			rAnlegenMitte.add(mengenEingabe);

			// Button für speichern / nächste Zutat
			JButton nextZutat = new JButton("nächste Zutat");
			nextZutat.addActionListener(new ActionListener() {
				@Override		
				public void actionPerformed(ActionEvent event) {
					System.out.println("nächste Zutat geklickt");
					mengenList.add(mengenEingabe.getText());
					zutatenNamenList.add(zutat);
					System.out.println(zutatenNamenList);
					System.out.println(mengenList);
				}
			});
			rAnlegenMitte.add(nextZutat);
			this.add(rAnlegenMitte,BorderLayout.CENTER);

			/*
			 * UNTEN 
			 * Textarea für Kochanweisungen
			 * Button für speichern und aufrufen der rezeptAnlegen Methode
			 */

			// Textarea für Anweisungen
			JPanel panelRAbnlUnten = new JPanel(new BorderLayout());
			JTextArea kochAnwEingeben = new JTextArea("Hier die Koch-Anweisungen eingeben");
			kochAnwEingeben.setPreferredSize(new Dimension(400	, 150));
			//Zeilenumbruch wird eingeschaltet
			kochAnwEingeben.setLineWrap(true);
			//Zeilenumbrüche erfolgen nur nach ganzen Wörtern
			kochAnwEingeben.setWrapStyleWord(true);
			JScrollPane anweisungenSP = new JScrollPane(kochAnwEingeben);
			panelRAbnlUnten.add(anweisungenSP,BorderLayout.PAGE_START);

			// Button für speichern und aufrufen derRezeptAnlegen Methode mit allen Parametern 		
			JButton rezeptSpeichern = new JButton("Rezept SPEICHERN");
			rezeptSpeichern.addActionListener(new ActionListener() {
				@Override		
				public void actionPerformed(ActionEvent event) {
					kochAnweisung = kochAnwEingeben.getText();
					controller.rezeptAnlegen(rName,lc,kh,veg, kochAnweisung,zutatenNamenList, mengenList);
				}
			});
			panelRAbnlUnten.add(rezeptSpeichern,BorderLayout.SOUTH);
			this.add(panelRAbnlUnten,BorderLayout.SOUTH);

			/*
			 * Layout 
			 */
			this.pack();
			//this.setSize(600, 500); 
			// position the frame
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
			// show the frame
			this.setVisible(true);
		}

		/** Item Listener für Rezept-Kategor
		 *  @ return Boolean für lc, kh und veg
		 */


		@Override
		public void itemStateChanged(ItemEvent event) {
			Object o = event.getSource();
			int seldesel = event.getStateChange();
			String ac = "?";
			if (o instanceof AbstractButton)
				ac = ((AbstractButton)o).getActionCommand();
			if (seldesel == ItemEvent.SELECTED){
				System.out.println(ac + " wurde selektiert");

				switch(ac) {
				case "lowcarb" : lc = true;
				break;
				case "khreich"  : kh = true;
				break;
				case "vegetarisch": veg = true;
				break;
				}
				System.out.println("lc= " + lc);
				System.out.println("kh= "+ kh);
				System.out.println("veg= " +veg);
			}

			else
			{System.out.println(ac + " wurde deselektiert");		

			switch(ac) {
			case "lowcarb" : lc = false;
			case "khreich"  : kh = false;
			case "vegetarisch": veg = false;
			}
			System.out.println("lc= " + lc);
			System.out.println("kh= "+ kh);
			System.out.println("veg= " +veg);
			}
		}

	}


	// Ende View
}
