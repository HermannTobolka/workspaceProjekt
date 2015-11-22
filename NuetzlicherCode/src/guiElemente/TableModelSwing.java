package guiElemente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class TableModelSwing {
	
	public static void main(String[] args) {
		final TableModelF tmf = new TableModelF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tmf.showFrame();
            }
        });
	}

}

class TableModelF {
	private JFrame frame;
	private JTable table;
	
	@SuppressWarnings("serial")
	public TableModelF() {
		//Create and set up the window.
		frame = new JFrame("TableModelSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		final Object[][]data = { {"978-3-8273-2874-8", "Handbuch der Java Programmierung", "2009", new Boolean(true)},
							{"978-3-89842-838-5", "Java ist auch eine Insel", "2007", new Boolean(false)},
							{"978-3-89721-448-4", "Java von Kopf bis Fuﬂ", "2005", new Boolean(true)} 
		};
		final String[] column = {"ISBN Nummer", "Titel", "Erscheinungsjahr", "Lagernd"};
		// create TableModel 
		AbstractTableModel atm = new AbstractTableModel() {

			@Override
			public int getColumnCount() {
				return column.length;
			}

			@Override
			public int getRowCount() {
				return data.length;
			}

			@Override
			public Object getValueAt(int row, int column) {
				return data[row][column];
			}
		};
		// create Column Model
		DefaultTableColumnModel dtcm = new DefaultTableColumnModel();
		for(int i = 0; i < column.length; i++) {
			TableColumn tc = new TableColumn(i);
			tc.setHeaderValue(column[i]);
			// for the third column set background and foreground colors by defining
			// an appropriate CellRenderer
			if (i == column.length - 2) {
				tc.setCellRenderer(new DefaultTableCellRenderer() {

					@Override
					public Component getTableCellRendererComponent(JTable arg0,
							Object arg1, boolean arg2, boolean arg3, int arg4,
							int arg5) {
						setBackground(Color.CYAN);
						setForeground(Color.RED);
						setValue(arg1.toString());
						return this;
					}
					
				});
			}
			dtcm.addColumn(tc);
		}
		table = new JTable(atm, dtcm);
		// for the last column show a checkbox by assigning an appropriate CellRenderer
		TableColumn tc4 = dtcm.getColumn(dtcm.getColumnCount() - 1);
		tc4.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// put the table into a scrollPane and the ScrollPane into the frame
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