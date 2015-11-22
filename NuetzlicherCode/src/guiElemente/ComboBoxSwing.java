package guiElemente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class ComboBoxSwing {
	
	public static void main(String[] args) {
		final ComboBoxSwingF cbf = new ComboBoxSwingF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cbf.showFrame();
            }
        });
	}

}

class ComboBoxSwingF {
	private JFrame frame;
	
	public ComboBoxSwingF() {
		// create frame
		frame = new JFrame("ComboBoxSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();	
		String[] cbItems = {"Hammer", "Bohrer", "Zange", "Schraubenzieher", "Feile"}; 
		JComboBox<String> combo = new JComboBox<String>(cbItems);
		combo.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>)event.getSource();
				System.out.println("Es wurde selektiert: " + combo.getSelectedItem());
			}
			
		});
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
