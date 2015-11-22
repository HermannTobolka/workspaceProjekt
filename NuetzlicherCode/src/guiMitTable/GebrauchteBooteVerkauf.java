package guiMitTable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Implementieren Sie einen JFrame mit einem JTree und der Möglichkeit Boottypen auszuwählen.
 * Nach Auswahl eines Boottyps erscheint ein modaler Dialog mit der Anzeige aller zum Verkauf stehenden Boote dieses Typs in einer JTable.
 * Durch Auswahl eines Bootes in der JTable und Drücken des Anzeigen JButton kann ein modaler Dialog mit den vollständigen Details des Bootes geöffnet werden.
 * Für die Implementierung der Boote verwenden Sie eine entsprechende Klassenhierarchie. Jeder Boottyp hat die Instanzvariablen
 Modell
 Plätze
 Preis
 Bilddatei
Bei Segelboot kommt noch die Segelfläche dazu und beim Motorboot die Motorleistung. Diese Informationen werden dann im oben gezeigten Dialog im Feld Zusatzinfo angezeigt.
 * Bei Segelboot kommt noch die Segelfläche dazu und beim Motorboot die Motorleistung. Diese Informationen werden dann im oben gezeigten Dialog im Feld Zusatzinfo angezeigt.
 * 
 * @author tobolkah
 *
 */

//TODO 3 Arrays mit Ruder- Segel und Motorboot

public class GebrauchteBooteVerkauf  implements TreeSelectionListener  {
	private JFrame frame;
	private  JTable table;
	
	public  GebrauchteBooteVerkauf() {
	
		//Create and set up the window.
		frame = new JFrame("Gebrauchte Boote");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create the data structure for the tree
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Boote");
	
		DefaultMutableTreeNode ruder = new DefaultMutableTreeNode("Ruderboote");
		top.add(ruder);
		DefaultMutableTreeNode segel = new DefaultMutableTreeNode("Segelboote");
		top.add(segel);
		DefaultMutableTreeNode motor = new DefaultMutableTreeNode("Motorboote");
		top.add(motor);
	
		final JTree tree = new JTree(top);
		// restrict to single node selection
		DefaultTreeSelectionModel dtsm = new DefaultTreeSelectionModel();
		dtsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(dtsm);
		// register for tree selection events
		tree.addTreeSelectionListener(this);
	
		// create scroll pane
		JScrollPane scrollP = new JScrollPane(tree);
		// add scrollPane to frame
		contentPane.add(scrollP);
	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.pack();
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

	@Override

	public void valueChanged(TreeSelectionEvent event) {
		TreePath tp = event.getNewLeadSelectionPath();
		if (tp == null)
			return;
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
		if (treeNode == null)
			return;
		Object userobject = treeNode.getUserObject();
		
		new BootsTabelle(frame, userobject.toString());
	}

	// Dialog mit Bootstabelle je nach Typ 

	@SuppressWarnings("serial")
	class BootsTabelle extends JDialog {

		public  BootsTabelle(JFrame frame, String bootType ) {
			super(frame, bootType, true);
			this.setLayout(new BorderLayout());

			final Ruderboot[] ruderArray = new Ruderboot[] {fatSir, gelberBlitz};

			final Segelboot[] segelArray = new Segelboot[]{kat416, dyas};

			final Motorboot[] motorArray = new Motorboot[]{zodiac, scarab};

			final String[] column = {"Name", "Plätze", "Preis"};

			// create TableModel 
			AbstractTableModel atm = new AbstractTableModel() {

				@Override
				public int getColumnCount() {
					return column.length;
				}

				@Override
				public int getRowCount() {
					if (bootType.equals("Ruderboote"))
						return ruderArray.length;
					else if (bootType.equals("Segelboote"))
						return segelArray.length;
					else return motorArray.length;
				}
				@Override
				public Object getValueAt(int row, int column) {
					Boot helper;
					if(bootType.equals("Ruderboote"))
						helper= ruderArray[row];
					else if (bootType.equals("Segelboote"))
						helper= segelArray[row];
					else 
						helper = motorArray[row];
					switch (column){
					case 0: return helper.getModell();
					case 1: return helper.getPlaetze();
					case 2: return helper.getPreis();

					}
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

			table = new JTable(atm,dtcm);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {
					System.out.println("selected");
					System.out.println(table.getSelectedColumn());
					System.out.println(table.getSelectedRow());
					System.out.println(table.getValueAt(table.getSelectedRow(),0));
				}
			});

			JPanel spPanel = new JPanel();
			JScrollPane sp = new JScrollPane(table);
			spPanel.add(sp);
			this.add(sp, BorderLayout.PAGE_START);

			JButton button = new JButton("Anzeigen");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(button);
			this.add(button, BorderLayout.PAGE_END);

			ActionListener l = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new JDialog(frame, "Detailinfos", true) {

						{BorderLayout bo = new BorderLayout();
						this.setLayout(bo ); 
						JPanel p1 = new JPanel();
						JLabel lb =new JLabel(bild1);
						p1.add(lb);

						JPanel p2 = new JPanel();
						GridLayout gl = new GridLayout(4,2);
						p2.setLayout(gl);

						JLabel l1= new JLabel("Modell");
						String name = new String();
						if (bootType.equals("Ruderboote"))
						{
							switch (table.getSelectedRow()){
							case 0: name=ruderArray[0].getModell();
							case 1: name=ruderArray[1].getModell();
							}						
						}
						JLabel l2= new JLabel(name);
						JLabel l3= new JLabel("Plätze");
						JLabel l4= new JLabel("4");
						JLabel l5= new JLabel("Zusatzinfo");
						JLabel l6= new JLabel("");
						JLabel l7= new JLabel("Preis");
						JLabel l8= new JLabel("100");
						p2.add(l1);
						p2.add(l2);
						p2.add(l3);
						p2.add(l4);
						p2.add(l5);
						p2.add(l6);
						p2.add(l7);
						p2.add(l8);
						this.add(p1, BorderLayout.PAGE_START);
						this.add(p2,BorderLayout.PAGE_END);
						// size the dialog
						this.pack();
						// position the dialog
						Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
						this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
						// show the dialog
						this.setVisible(true);
						}
					};
				}
			};
			button.addActionListener(l);

			// position the dialog
			this.pack();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
			// show the dialog
			this.setVisible(true);
		}
	}

	//ImageIcons für Ruder boote definieren
	private ImageIcon bild1 = new ImageIcon("c:\\users\\Hermann\\Workspace\\fatsir.jpg");  // pfad am laptop: c:\\users\\Hermann\\Workspace
	private ImageIcon bild2 = new ImageIcon("c:\\users\\Hermann\\Workspace\\gelberBlitz.jpg");
	private ImageIcon bild3 = new ImageIcon("c:\\users\\Hermann\\Workspace\\kat416web.jpg");
	private ImageIcon bild4 = new ImageIcon("c:\\users\\Hermann\\Workspace\\dyas.jpg");
	private ImageIcon bild5 = new ImageIcon("c:\\users\\Hermann\\Workspace\\zodiac.jpg");
	private ImageIcon bild6 = new ImageIcon("c:\\users\\Hermann\\Workspace\\scarab.jpg");

	// Boot Objekte anlegen
	private Ruderboot fatSir = new Ruderboot("Fat Sir", 4, 100, bild1);
	private Ruderboot gelberBlitz = new Ruderboot("Gelber Blitz",2,200, bild2);
	private Segelboot kat416 = new Segelboot("Kat416",4,150,bild3,9);
	private Segelboot dyas = new Segelboot("Dyas",4,200, bild4, 22);
	private Motorboot zodiac= new Motorboot ("Zodiac",6,150, bild5,5);
	private Motorboot scarab = new Motorboot ("Scarab",8, 300, bild6,500);


}



