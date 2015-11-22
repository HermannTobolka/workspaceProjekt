package guiElemente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class MouseSwing {
	
	public static void main(String[] args) {
		final MouseF mf = new MouseF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mf.showFrame();
			}
		});
	}

}

class MouseF implements MouseListener, MouseMotionListener {
	private JFrame frame;
	private JLabel label;
	
	public MouseF() {
		//Create and set up the window.
		frame = new JFrame("Mouse Handler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		Container contentPane = frame.getContentPane();		
		JButton btn = new JButton("Hintergrund Farbe ändern");
		btn.setBounds(40, 50, 200, 23);
		btn.setName("Farbe");
		btn.addMouseListener(this);
		contentPane.add(btn);		
		btn = new JButton("Klick und Doppelklick");
		btn.setBounds(40, 150, 200, 23);
		btn.setName("Klick");
		btn.addMouseListener(this);
		contentPane.add(btn);	
		label = new JLabel();
		label.setBounds(200, 150, 350, 23);
		contentPane.add(label);
		btn= new JButton("Ziehen");
		btn.setBounds(40, 250, 200, 23);
		btn.setName("Ziehen");
		btn.addMouseListener(this);
		btn.addMouseMotionListener(this);
		contentPane.add(btn);
	}
	
	public void showFrame() {
		//Display the window.
		frame.setSize(600, 400);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}
	
	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getComponent().getName().equals("Farbe"))
			me.getComponent().setBackground(Color.MAGENTA);		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getComponent().getName().equals("Farbe"))
			me.getComponent().setBackground(Color.GREEN);		
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getComponent().getName().equals("Klick")) 
			label.setText("Mouse button " + me.getButton() + " Klicks " + me.getClickCount());
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		if (me.getComponent().getName().equals("Ziehen")) 
			me.getComponent().setCursor(Cursor.getDefaultCursor());
	}

	private int distX, distY;
	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getComponent().getName().equals("Ziehen")) {
			distX = me.getX();
			distY = me.getY();			
			me.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		Component cmp = me.getComponent();
		if (cmp.getName().equals("Ziehen")) 
			cmp.setLocation(cmp.getX() + me.getX() - distX, 
					cmp.getY() + me.getY() - distY );
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
	}
}

