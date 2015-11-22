package guiElemente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class TableSortSwing {

	public static void main(String[] args) {
		final TableSF tsf = new TableSF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tsf.showFrame();
            }
        });
	}
}

class TableSF {
	private JFrame frame;
	private JTable table;
	private JLabel label;

	@SuppressWarnings("serial")
	public TableSF() {
		//Create and set up the window.
		frame = new JFrame("TableSortSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		Object[][]data = { 	{"978-3-8273-2874-8", "Handbuch der Java Programmierung", "2009", new Boolean(true)},
				{"978-3-89842-838-5", "Java ist auch eine Insel", "2007", new Boolean(false)},
				{"978-3-89721-448-4", "Java von Kopf bis Fuﬂ", "2005", new Boolean(true)} 
		};
		final String[] column = {"ISBN Nummer", "Titel", "Erscheinungsjahr", "Auf Lager"};
		table = new JTable(new DefaultTableModel(data, column) {

			public Class<?> getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

		});
		table.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		sorter.setComparator(1, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				String[] strings1 = s1.split("\\s");
				String[] strings2 = s2.split("\\s");
				return strings1[strings1.length - 1].compareTo(strings2[strings2.length - 1]);
			} 
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int viewRow = table.getSelectedRow();
				int viewColumn = table.getSelectedColumn();
				if (viewRow < 0 || viewColumn < 0) 
					label.setText("");
				else {
					int modelRow = table.convertRowIndexToModel(viewRow);
					int modelColumn = table.convertColumnIndexToModel(viewColumn);
					label.setText(String.format("View Row and column: (%d, %d) " +
							"Model Row and column: (%d, %d)", 
							viewRow, viewColumn, modelRow, modelColumn));
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		label = new JLabel("");
		contentPane.add(label, BorderLayout.PAGE_END);
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

}
