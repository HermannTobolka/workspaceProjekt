package guiElemente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListSwing {
	
	public static void main(String[] args) {
		final ListSwingF lf = new ListSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lf.showFrame();
            }
        });
	}

}

class ListSwingF  implements ListSelectionListener {
	private JFrame frame;
	
	public ListSwingF() {
		// create frame
		frame = new JFrame("ListSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();		
		String[] listA = {"Austria", "Deutschland", "Frankreich", "Italien"};
		JList<String> list = new JList<String>(listA);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		contentPane.add(list, BorderLayout.PAGE_START);
//		list.setVisibleRowCount(3);
//		JScrollPane listScrollPane = new JScrollPane(list);
//		contentPane.add(listScrollPane, BorderLayout.PAGE_START);
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

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// handle only, if selection has been finalized
		if (!event.getValueIsAdjusting()) {
			JList<String> list = (JList<String>)event.getSource();
			System.out.println("Es wurde selektiert: " + list.getSelectedValue());
		}
		
	}

}