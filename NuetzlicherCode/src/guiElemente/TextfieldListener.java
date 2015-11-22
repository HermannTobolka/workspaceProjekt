package guiElemente;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class TextfieldListener {
	public static void main(String[] args) {
		final TextfieldListenerF tff = new TextfieldListenerF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tff.showFrame();
			}
		});
	}

}

class TextfieldListenerF  {
	private JFrame frame;

	public TextfieldListenerF() {
		// create frame
		frame = new JFrame("MenuSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(0, 2, 20,10));
		contentPane.add(new JLabel("Textfeld 1"));
		final JTextField tf1 = new JTextField("", 20);
		contentPane.add(tf1);
		tf1.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				tf1.setBackground(Color.green);				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				tf1.setBackground(Color.yellow);				
			} 
		});
		contentPane.add(new JLabel("Textfeld 2"));
		final JTextField tf2 = new JTextField("", 20);
		contentPane.add(tf2);
		contentPane.add(new JLabel("Caret Information"));
		final JLabel lbl = new JLabel();
		contentPane.add(lbl);
		tf2.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				int dot = arg0.getDot();
				int mark = arg0.getMark();
				System.out.println("dot " + dot + " mark " + mark);
				String tf2t = tf2.getText();
				if (dot < tf2t.length() || mark < tf2t.length())
					if (mark < dot)
						lbl.setText(tf2t.substring(mark, dot));
					else
						lbl.setText(tf2t.substring(dot, mark));
			} 
		});
	}

	public void showFrame() {
		// size the frame to fit the preferred size of all components
		frame.setSize(200, 200);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
	}

}
