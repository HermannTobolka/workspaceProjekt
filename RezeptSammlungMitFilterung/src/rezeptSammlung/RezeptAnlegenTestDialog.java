package rezeptSammlung;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.derby.iapi.util.JBitSet;

@SuppressWarnings({ "unused", "serial" })
public class RezeptAnlegenTestDialog extends JDialog implements   ItemListener {
	boolean lc = false;
	boolean kh = false;
	boolean veg = false;

	String kochAnweisungen ="";
	String rName ="";
	private JComboBox<String> cBZutaten;
	private String[] zutatenArray2;
	//private ArrayList<String> rezeptKategorie2 = new ArrayList<String>();
	private ArrayList<String>zutatenNamenList = new ArrayList<String>();
	ArrayList<String> mengenList = new ArrayList<String>();
	String zutat ="";

	public RezeptAnlegenTestDialog (JFrame TestFrameZumDialogAufruf){
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
			@SuppressWarnings("unchecked")
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
		String[] zutatenArray2 = {"Salz", "Pfeffer", "Rindfleisch", "Rindssuppe"}; 
		// später austausch zutatenArray2 = controller.zutatenAuflisten();
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
			@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unchecked")
			@Override		
			public void actionPerformed(ActionEvent event) {
				kochAnweisungen = kochAnwEingeben.getText();
				// controller.rezeptAnlegen(rName,lc,kh,veg, kochAnweisungen,zutatenNamenList, mengenList);
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




