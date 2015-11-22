package guiElemente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class KeySwing {

	public static void main(String[] args) {
		final KeyF kf = new KeyF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				kf.showFrame();
			}
		});
	}

}


class KeyF  {
	private JFrame frame;
	
	public KeyF() {
		//Create and set up the window.
		frame = new JFrame("Key Event Handler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				Color c = null;				
				switch(e.getKeyChar()  ){
				case 'r':
					c = Color.RED;
					break;
				case 'b':
					c = Color.BLUE;
					break;
				case 'g':
					c = Color.GREEN;
					break;

				}
				if(c != null) {
					frame.getContentPane().setBackground(c);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					frame.dispose();
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			} 
		});
//		frame.requestFocus();  // damit Tastatur events an den Frame geschickt werden
	}
	
	public void showFrame() {
		//Display the window.
		frame.setSize(300, 200);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2,(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}

}
