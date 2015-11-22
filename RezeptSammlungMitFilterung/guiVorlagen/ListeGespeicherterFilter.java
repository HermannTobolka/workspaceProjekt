package guiVorlagen;

//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.ListSelectionModel;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;

/*
 * Diese Klasse ist die Vorlage für die GUI Seite "Liste gespeicherter Filter"
 *  
 * Um gleiche Bezeichnungen von Labels, frames etc zu vermeiden wird hier mit 
 * Label 21 fortgesetzt und Panel 17
 * 
 *  DIE GESAMTE KLASSE IST AUSKOMMENTIERT
 *  GRUND: Die Filterliste wird nur als Combobox angezeigt. Falls sich das später einmal ändern soll wird der Code aber unter GUI Vorlagen gespeichert. 
 *  Für Später auch interessenant: Warum funktioniert der GUI Bilder nicht? Einmal stört ihn JPanel, dann JLabel, dann JList...)
 */


//public class ListeGespeicherterFilter 	implements ListSelectionListener {
//
//		private JFrame frameLGF;
//		
//		private JButton zurueck4;
//		private JButton zurueckZumStart4;
//		private String[] listA3;
//		private JList<String> list3;
//
//		 public ListeGespeicherterFilter() {
//			
//
//			//Create and set up the window.
//			frameLGF = new JFrame(" Liste gespeicherter Filter ");
//			frameLGF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			Container contentPane = frameLGF.getContentPane();
//			contentPane.setLayout(new GridLayout(5,1));
//
//			// Wieder Zeilen für einzelene GUI Elemente in Panels in einem Gridlayout
//
//			//Überschrift für Ergebnisliste gefundener Rezepte
//			JPanel panel17 = new JPanel();
//			JLabel l21 = new JLabel("Diese Filter stehen zur Auswahl. Bitte einen davon wählen");
//			panel17.add(l21);
//			contentPane.add(panel17);
//
//			//
//
//			//Versuchsweise mit JList aus SourceCode Kurs
//			JPanel panel18 = new JPanel(new BorderLayout());
//			listA3 = new String[] {"Ohnepaprika", "VegetarischOhnepaprika", "OhneRind", "Funktioniert die ScrollPane?"};
//			list3 = new JList<String>(listA3);
//			list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			list3.addListSelectionListener(this);
//			panel18.add(list3, BorderLayout.PAGE_START);
//			list3.setVisibleRowCount(3);
//			JScrollPane listScrollPane2= new JScrollPane(list3);
//			panel18.add(listScrollPane2, BorderLayout.PAGE_START);
//			contentPane.add(panel18);
//			
//			
//			
//			// Am Schluss noch 2 zurück Buttons, ein Schritt zurück und zurück zum Start
//			// Exit Button unnötig, gibt eh x
//			JPanel panel19 = new JPanel(new BorderLayout());
//			zurueck4 = new JButton("Zurück");
//			zurueckZumStart4= new JButton("Zurück zum Start");
//			panel19.add(zurueck4, BorderLayout.WEST);
//			panel19.add(zurueckZumStart4,BorderLayout.EAST);
//			contentPane.add(panel19);
//			
//			
//
//		}
//
//
//		public void showFrame() {
//			// size the frame to fit the preferred size of all components
//			frameLGF.pack();
//			// position the frame
//			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//			frameLGF.setLocation((d.width - frameLGF.getSize().width)/2, (d.height - frameLGF.getSize().height)/2);
//			// show the frame
//			frameLGF.setVisible(true);
//		}
//
//
//		@Override
//		public void valueChanged(ListSelectionEvent e) {
//			// TODO Auto-generated method stub
//
//		} 
//
//}
