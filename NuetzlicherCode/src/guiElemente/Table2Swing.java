package guiElemente;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Table2Swing {

	public static void main(String[] args) {
		final Table2F t2f = new Table2F();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                t2f.showFrame();
            }
        });
	}
}

class Table2F {
	private JFrame frame;
	private JTable table;
	
	@SuppressWarnings("serial")
	public Table2F() {
		//Create and set up the window.
		frame = new JFrame("Table2Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		Object[][]data = { 	{"978-3-8273-2874-8", "Handbuch der Java Programmierung", 2009, new Boolean(true)},
							{"978-3-89842-838-5", "Java ist auch eine Insel", 2007, new Boolean(false)},
							{"978-3-89721-448-4", "Java von Kopf bis Fuﬂ", 2005, new Boolean(true)} 
		};
		String[] column = {"ISBN Nummer", "Titel", "Erscheinungsjahr", "Auf Lager"};
		table = new JTable(new DefaultTableModel(data, column) {
			
	        public Class<?> getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }

		});
		TableColumn tc2 = table.getColumnModel().getColumn(2);
		tc2.setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable arg0,
					Object arg1, boolean arg2, boolean arg3, int arg4,
					int arg5) {
				this.setHorizontalAlignment(SwingConstants.CENTER);
				super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
				return this;
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