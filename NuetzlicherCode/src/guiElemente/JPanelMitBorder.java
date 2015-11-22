package guiElemente;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


/**
 * Implementieren Sie einen JFrame mit einem JPanel, welches einen JLabel mit Text und 
 * ImageIcon enthält. Zeichnen Sie verschiedene Borders um das JPanel. Sie können auch 
 * einen JMenuBar anhängen über den 
 * Sie den zu zeichnenden Border auswählen können. Verwenden Sie folgende Bordertypen: 
 *  -) Line
 *  -) Etched
 *  -) Bevel
 *  -) Matte
 * @author Hermann
 *
 */

public class JPanelMitBorder implements ActionListener{

	private JLabel l1;

	private JFrame frame;

	// Line Border 
	private Border blackline = BorderFactory.createLineBorder(Color.black);

	// Etched
	private Border etched = BorderFactory.createEtchedBorder(Color.YELLOW, Color.BLACK );

	private Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	// Bevel
	private Border bevelborder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray);

	// Matte
	private Border matteborder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow);

	private JPanel panel1;

	public JPanelMitBorder(){


		frame = new JFrame("JPanel mit Border");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		// create main menu
		JMenu m1 = new JMenu("Menu");
		mb.add(m1);
		JMenuItem mi11 = new JMenuItem("LineBorder", KeyEvent.VK_L);
		mi11.setActionCommand("mi11");
		mi11.addActionListener(this);
		JMenuItem mi12 = new JMenuItem("EtchedBorder", KeyEvent.VK_E);
		mi12.setActionCommand("mi12");
		mi12.addActionListener(this);
		JMenuItem mi13 = new JMenuItem("BevelBorder", KeyEvent.VK_B);
		mi13.setActionCommand("mi13");
		mi13.addActionListener(this);

		JMenuItem mi14 = new JMenuItem("MatteBorder", KeyEvent.VK_M);
		mi14.setActionCommand("mi14");
		mi14.addActionListener(this);

		JMenuItem mi15 = new JMenuItem("RaisedEtched", KeyEvent.VK_R);
		mi15.setActionCommand("mi15");
		mi15.addActionListener(this);

		JMenuItem mi16 = new JMenuItem("LoweredEtched", KeyEvent.VK_T);
		mi16.setActionCommand("mi16");
		mi16.addActionListener(this);



		m1.add(mi11);
		m1.add (mi12);
		m1.add (mi13);
		m1.add (mi14);
		m1.add (mi15);
		m1.add (mi16);

		panel1 = new JPanel(new GridLayout(3,3)); 
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		
		l1 = new JLabel("Verschiedene Border"); 
	
		 
		panel1.add(l1); 
		
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
//		panel1.add(new JLabel());
//
//		JLabel labelC = new JLabel(new ImageIcon("C:\\Users\\Hermann\\workspace\\Travel-sea-waves-icon.png"));
//		panel1.add(labelC);



		Container contentPane = frame.getContentPane();
		// add both panels to the frame
		contentPane.add(panel1);

	}

	public void showFrame() {
		frame.setMinimumSize(new Dimension (450,450));
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		switch(ac){
		case "mi11": l1.setBorder(blackline);
		break;
		case "mi12": l1.setBorder(etched);
		break;
		case "mi13": l1.setBorder(bevelborder);
		break;
		case "mi14": l1.setBorder(matteborder);
		break;
		case "mi15": l1.setBorder(raisedetched);
		break;
		case "mi16": l1.setBorder(loweredetched);
		default:
			panel1.setBorder(null);
		}

	}


}
