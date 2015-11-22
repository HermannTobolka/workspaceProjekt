package guiElemente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;







import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Bildbetrachter{

	private JFrame frame;
	private JTextField textField;

	public  Bildbetrachter() {

		frame = new JFrame("Bildbetrachter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(2,2,20,10));

		//   Textfeld	 
		textField = new JTextField("");
		contentPane.add(textField);


		// suchen Button
		final JButton suchen = new JButton("Suchen...");
		suchen.setMnemonic(KeyEvent.VK_S);
		contentPane.add(suchen);
		suchen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(suchen);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(suchen, "Ausgewählt wurde " + chooser.getSelectedFile().getAbsolutePath(), "Information", JOptionPane.INFORMATION_MESSAGE);
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		//Anzeigen Button

		final JButton anzeigen = new JButton("Anzeigen...");
		anzeigen.setMnemonic(KeyEvent.VK_A);
		contentPane.add(anzeigen);

		anzeigen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BilderDialog(frame, "Bilder", true);
			}
		});
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

	@SuppressWarnings("serial")
	class BilderDialog extends JDialog implements ActionListener {
		private int i;
		private Path[] pathArray;
		private JButton start;
		private JButton back;
		private JButton next;
		private JButton end;
		private JLabel labelC;

		public BilderDialog(JFrame frame, String title, boolean modal) {
			super(frame, title, modal);
				// Directory Stream mit Path zu bildern, Patharray  aus einzelnen dateipfaden
				try { 
					Path p = Paths.get(textField.getText());
					int bildZaehler=0;
					DirectoryStream<Path> dsp = Files.newDirectoryStream(p); 
					for(@SuppressWarnings("unused") Path pt : dsp)
						bildZaehler++;					
					dsp.close();
					pathArray = new Path[bildZaehler];
					bildZaehler = 0;
					dsp = Files.newDirectoryStream(p);
					for(Path pt : dsp) 
						pathArray[bildZaehler++] = pt;
					dsp.close(); 
				} 
				catch (IOException e) {
					e.printStackTrace(); 
				}

				// Layout für Bilderdialog
				this.setLayout(new BorderLayout(20,10));

				// Label zur Anzeige der Bilder
				labelC = new JLabel();
			 
				this.add(labelC , BorderLayout.CENTER);
				
				//Panel für die 4 Buttons unten im Bilderdialog
				JPanel panel1 = new JPanel(new GridLayout (1,4));
				this.add(panel1, BorderLayout.PAGE_END );

				//Buttons zur Navigation mit Actionlistener und Shortcut

				start = new JButton("Anfang");
				start.addActionListener(this);
				start.setMnemonic(KeyEvent.VK_A);
				start.setActionCommand("start");


				back = new JButton("zurück");
				back.addActionListener(this);
				back.setMnemonic(KeyEvent.VK_Z);
				back.setActionCommand("back");


				next = new JButton("weiter");
				next.addActionListener(this);
				next.setMnemonic(KeyEvent.VK_W);
				next.setActionCommand("next");


				end = new JButton("Ende");
				end.addActionListener(this);
				end.setMnemonic(KeyEvent.VK_E);
				end.setActionCommand("end");



				//Buttons zum panel dazu
				panel1.add(start);
				panel1.add(back);
				panel1.add(next);
				panel1.add(end);

				bilderLaden();

				// size the dialog
				Dimension gugug = new Dimension(400, 500);
				//this.setPreferredSize(gugug); 
				this.setSize(gugug);
				// position the dialog
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
				// show the dialog
				this.setVisible(true);
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			String ac = e.getActionCommand();
			System.out.println("ActionCommand: " + ac);
			if (ac.equals("start")){
				i=0;
			}
			else if (ac.equals("back")){
				 i--;
			}
			else if (ac.equals("next")) {
				i++;
			}
			else if (ac.equals("end")){
				i=pathArray.length-1;
			}
			bilderLaden();
		}

		public void bilderLaden(){
			ImageIcon icon = new ImageIcon(pathArray[i].toString());
			icon.setImage(icon.getImage().getScaledInstance(370, 300, Image.SCALE_DEFAULT));
			labelC.setIcon(icon);
			back.setEnabled(true);
			start.setEnabled(true);
			next.setEnabled(true);
			end.setEnabled(true);

			if (i == 0){
				start.setEnabled(false);
				back.setEnabled(false);
			}
			if (i>=pathArray.length-1) {
				next.setEnabled(false);
				end.setEnabled(false);
			}
		}
	}
}









