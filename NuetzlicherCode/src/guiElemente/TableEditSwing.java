package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class TableEditSwing {

	public static void main(String[] args) {
		final TableEF tef = new TableEF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tef.showFrame();
            }
        });
	}
}

class TableEF {
	private JFrame frame;
	private JTable table;
	
	@SuppressWarnings("serial")
	public TableEF() {
		//Create and set up the window.
		frame = new JFrame("TableEditSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		Object[][]data = { 	{"978-3-8273-2874-8", "Handbuch der Java Programmierung", "2009", new Boolean(true)},
							{"978-3-89842-838-5", "Java ist auch eine Insel", "2007", new Boolean(false)},
							{"978-3-89721-448-4", "Java von Kopf bis Fuﬂ", "2005", new Boolean(true)} 
		};
		final String[] column = {"ISBN Nummer", "Titel", "Erscheinungsjahr", "Auf Lager"};
		table = new JTable(new DefaultTableModel(data, column) {
			
			@Override
	        public Class<?> getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }

			@Override
			public boolean isCellEditable(int ro, int col) {
				return col >= column.length - 2;
			}

			@Override
			public void setValueAt(Object o, int row, int col) {
				super.setValueAt(o, row, col); 
			}

		});
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
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