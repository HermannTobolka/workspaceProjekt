package guiElemente;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class TableSwing {

	public static void main(String[] args) {
		final TableF tf = new TableF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tf.showFrame();
            }
        });
	}
}

class TableF implements ActionListener {
	private JFrame frame;
	private JTable table;
	
	public TableF() {
		//Create and set up the window.
		frame = new JFrame("TableSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		String[][]data = { 	{"978-3-8273-2874-8", "Handbuch der Java Programmierung", "2009"},
							{"978-3-89842-838-5", "Java ist auch eine Insel", "2007"},
							{"978-3-89721-448-4", "Java von Kopf bis Fuﬂ", "2005"} 
		};
		String[] column = {"ISBN Nummer", "Titel", "Erscheinungsjahr"};
		table = new JTable(data, column);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
		// prepare the menu bar and add it to the frame
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		JMenu mD = new JMenu("Ansicht");
		mb.add(mD);
		JMenuItem mFit = new JMenuItem("Passend");
		mFit.setActionCommand("mFit");
		mFit.addActionListener(this);
		mD.add(mFit);
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
	public void actionPerformed(ActionEvent arg0) {
	    for (int c=0; c<table.getColumnCount(); c++) {
	        packColumn(table, c, 2);
	    }
	}
	
	// Sets the preferred width of the visible column specified by vColIndex. The column
	// will be just wide enough to show the column head and the widest cell in the column.
	// margin pixels are added to the left and right
	// (resulting in an additional width of 2*margin pixels).
	public void packColumn(JTable table, int vColIndex, int margin) {
	    DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
	    TableColumn col = colModel.getColumn(vColIndex);
	    int width = 0;
	    // Get width of column header
	    TableCellRenderer renderer = col.getHeaderRenderer();
	    if (renderer == null) {
	        renderer = table.getTableHeader().getDefaultRenderer();
	    }
	    Component comp = renderer.getTableCellRendererComponent(
	        table, col.getHeaderValue(), false, false, -1, vColIndex);
	    width = comp.getPreferredSize().width;
	    // Get maximum width of column data
	    for (int r=0; r<table.getRowCount(); r++) {
	        renderer = table.getCellRenderer(r, vColIndex);
	        comp = renderer.getTableCellRendererComponent(
	            table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
	        width = Math.max(width, comp.getPreferredSize().width);
	    }
	    // Add margin
	    width += 2*margin;
	    // Set the width
	    col.setPreferredWidth(width);
	}

}