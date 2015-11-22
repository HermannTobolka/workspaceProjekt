package guiVorlagen;

import java.awt.Checkbox;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/*
 * Diese Klasse ist die Vorlage für die GUI Seite "Zutatenselektor"
 * siehe Punkt 3.3.1.3 der Projektdoku
 * Um gleiche Bezeichnungen von Labels, frames etc zu vermeiden wird hier mit 
 * Label 11 fortgesetzt und Panel 6
 */
public class ZutatenSelektor {

	private JFrame frameZS;
	private Checkbox lowCarbCB;
	private Checkbox khCB;
	private Checkbox vegCB;
	private JTable zutatenTable;
	private JButton filterSpeichern;
	private JButton filterErgebnisAnzeigen;
	private JButton zurueckZuStart;

	public ZutatenSelektor(){

		//Create and set up the window.
		frameZS = new JFrame(" Zutaten SELEKTOR ");
		frameZS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frameZS.getContentPane();
		contentPane.setLayout(new GridLayout(5,1));

		//Überschrift für Rezeptkategorien in eignem Panel
		JPanel panel6 = new JPanel();
		JLabel l11 = new JLabel("Hier kannst du optional eine Kategorie wählen (oder mehrere)");
		panel6.add(l11);
		contentPane.add(panel6);

		//Checkboxen für Rezeptkategorien in eigenem Panel
		JPanel panel7 = new JPanel(new GridLayout(1,3));
		lowCarbCB = new Checkbox("low Carb");
		khCB = new Checkbox("Kohlehydrat-reich");
		vegCB = new Checkbox("vegetarisch");
		panel7.add(lowCarbCB);
		panel7.add(khCB);
		panel7.add(vegCB);
		contentPane.add(panel7);
		
		//Textbeschreibung für Tabelle mit Zutaten, wieder im Panel
		JPanel panel8 = new JPanel(new GridLayout(2,1));
		JLabel l12 = new JLabel("Es geht auch noch genauer. Such dir aus, auf welche Zutaten");
		JLabel l13 = new JLabel("du so richtig Gusto hast, und welche du weglassen willst");
		panel8.add(l12);
		panel8.add(l13);
		contentPane.add(panel8);
		
		// JTABLE für Zutateninklusion oder Exklusion

		//Spalten der Zutatentabelle Zutat/ja/nein
		final String[] column = {"Zutat", "Ja bitte, her damit!", "Nein danke, bitte weglassen"};

		// create TableModel 
		@SuppressWarnings("serial")
		AbstractTableModel atm = new AbstractTableModel() {

			@Override
			public int getColumnCount() {
				return column.length;
			}

			@Override
			public int getRowCount() {
				// Testhalber mal 10 Reihen gemacht
				return 10;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		// create Column Model
		DefaultTableColumnModel dtcm = new DefaultTableColumnModel();
		for(int i = 0; i < column.length; i++) {
			TableColumn tc = new TableColumn(i);
			tc.setHeaderValue(column[i]);
			dtcm.addColumn(tc);
		}
		// Tabelle zusammenbauen
		zutatenTable = new JTable(atm, dtcm);
		zutatenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		zutatenTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("selected");
				System.out.println(zutatenTable.getSelectedColumn());
				System.out.println(zutatenTable.getSelectedRow());
				System.out.println(zutatenTable.getValueAt(zutatenTable.getSelectedRow(),0));
			}
		});
		
		// Panel und Scrollpane für Tabelle
		JPanel spPanel = new JPanel();
		JScrollPane sp = new JScrollPane(zutatenTable);
		spPanel.add(sp);
		contentPane.add(sp);
		
		//panel mit Buttons unten am Window
		JPanel panel9 = new JPanel(new GridLayout(1,3));
		filterSpeichern = new JButton("Auswahl als Filter speichern");
		filterErgebnisAnzeigen = new JButton("Zeig mir Rezepte");
		zurueckZuStart = new JButton("Zurück zum Start");
		panel9.add(filterSpeichern);
		panel9.add(filterErgebnisAnzeigen);
		panel9.add(zurueckZuStart);
		contentPane.add(panel9);
		

	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frameZS.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frameZS.setLocation((d.width - frameZS.getSize().width)/2, (d.height - frameZS.getSize().height)/2);
		// show the frame
		frameZS.setVisible(true);
	} 

}
