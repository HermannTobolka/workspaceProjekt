package guiElemente;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;


public class ComboBoxModelSwing {
	public static void main(String[] args) {
		final ComboBoxModelSwingF cbf = new ComboBoxModelSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cbf.showFrame();
            }
        });
	}

}

class ComboBoxModelSwingF {
	private JFrame frame;
	ArrayList<ToolData> tools;
	
	@SuppressWarnings("serial")
	public ComboBoxModelSwingF() {
		// create frame
		frame = new JFrame("ComboBoxModelSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();	
		tools = new ArrayList<ToolData>();
		tools.add(new ToolData("Hammer", "hammer"));
		tools.add(new ToolData("Bohrer", "bohrer"));
		tools.add(new ToolData("Feile", "feile"));
		tools.add(new ToolData("Schraubenzieher", "schraubenzieher"));
		tools.add(new ToolData("Zange", "zange"));
		DefaultComboBoxModel<ToolData> dcbm = new DefaultComboBoxModel<ToolData>();
		for(ToolData td: tools)
			dcbm.addElement(td);
		JComboBox<ToolData> combo = new JComboBox<ToolData>(dcbm);
		combo.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<ToolData> combo = (JComboBox<ToolData>)event.getSource();
				System.out.println("Es wurde selektiert: " + combo.getSelectedItem());
			}
			
		});
		combo.setRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list,value,index,isSelected,hasFocus);
				ImageIcon icon = ((ToolData)value).getIcon();
				this.setIcon(icon);
				return this;
			} });
		contentPane.add(combo);
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

class ToolData {
	
	private String name;
	private ImageIcon icon;
	
	public ToolData(String s, String toolName) {
		name = s;
		icon = new ImageIcon(".\\resources\\" + toolName + ".jpg");
	}
	
	public String getName() { return name; }
	
	public ImageIcon getIcon() { return icon; }

	@Override
	public String toString() {
		return name;
	}
	
}