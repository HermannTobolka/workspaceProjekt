package guiElemente;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListModelSwing {
	
	public static void main(String[] args) {
		final ListModelSwingF lmf = new ListModelSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lmf.showFrame();
            }
        });
	}

}

class ListModelSwingF  implements ListSelectionListener {
	private JFrame frame;
	private ArrayList<FlagData> flagL = new ArrayList<FlagData>();
	
	@SuppressWarnings("serial")
	public ListModelSwingF() {
		// create frame
		frame = new JFrame("ListModelSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();		
		// create itemlist for ListModel
		flagL.add(new FlagData("Austria", "Austria"));
		flagL.add(new FlagData("Belgien", "Belgien"));
		flagL.add(new FlagData("Deutschland", "BRD"));
		flagL.add(new FlagData("Frankreich", "Frankreich"));
		flagL.add(new FlagData("Groﬂbritannien", "GB"));
		flagL.add(new FlagData("Niederlande", "NL"));
		flagL.add(new FlagData("Portugal", "Portugal"));
		flagL.add(new FlagData("Schweden", "Schweden"));
		flagL.add(new FlagData("Spanien", "Spanien"));
		flagL.add(new FlagData("Tuerkei", "Tuerkei"));
		DefaultListModel<FlagData> listModel = new DefaultListModel<>();
		for(FlagData fd: flagL)
			listModel.addElement(fd);
		JList<FlagData> list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(3);
		list.addListSelectionListener(this);
		// set cell renderer
		list.setCellRenderer(new DefaultListCellRenderer(){

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list,value,index,isSelected,hasFocus);
				ImageIcon icon = ((FlagData)value).getIcon();
				this.setIcon(icon);
				return this;
			}
			
		});
		// make JList scrollable
		JScrollPane listScrollPane = new JScrollPane(list);
		contentPane.add(listScrollPane, BorderLayout.PAGE_START);
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
			JList<FlagData> list = (JList<FlagData>)event.getSource();
			System.out.println("Es wurde selektiert: " + list.getSelectedValue());
		}
	}

}

class FlagData {
	private String name;
	private ImageIcon icon;
	
	public FlagData(String s, String flagName) {
		name = s;
		icon = new ImageIcon(".\\resources\\Flags\\" + flagName + ".gif");
	}
	
	public String getName() { return name; }
	
	public ImageIcon getIcon() { return icon; }

	@Override
	public String toString() {
		return name;
	}
	
}