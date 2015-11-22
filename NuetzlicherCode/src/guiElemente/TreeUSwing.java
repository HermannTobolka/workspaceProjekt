package guiElemente;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class TreeUSwing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final TreeUF tuf = new TreeUF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tuf.showFrame();
			}
		});
	}

}

class TreeUF {
	private JFrame frame;
	private Flugzeug[] planes = {new Flugzeug("Fokker 70", 70, 10700, 2000, ".\\resources\\fokker70.png"),
			new Flugzeug("Airbus A380", 850, 13000, 15000, ".\\resources\\a380.png"),
			new Flugzeug("Boeing 777", 350, 13000, 15000, ".\\resources\\boeing777.png")
	};

	@SuppressWarnings("serial")
	public TreeUF() {
		//Create and set up the window.
		frame = new JFrame("Tree User Object Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create the data structure for the tree
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Flugzeuge");
		for(Flugzeug f : planes)
			top.add(new DefaultMutableTreeNode(f));
		final JTree tree = new JTree(top);
		// restrict to single node selection
		DefaultTreeSelectionModel dtsm = new DefaultTreeSelectionModel();
		dtsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(dtsm);
		// register for tree selection events
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				TreePath tp = event.getNewLeadSelectionPath();
				if (tp == null)
					return;
				Object treeNode = tp.getLastPathComponent();
				if (treeNode == null)
					return;
				Object plane = ((DefaultMutableTreeNode)treeNode).getUserObject();
				if (plane instanceof Flugzeug)
					new FlugzeugDetails((Flugzeug)plane, frame);
			}
		});
		// set renderer
		tree.setCellRenderer(new DefaultTreeCellRenderer() { 
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				if (leaf) {
					Object o = ((DefaultMutableTreeNode)value).getUserObject();
					// set the icon in the tree
					if (o instanceof Flugzeug)
						setIcon(new ImageIcon(((Flugzeug)o).getImage()));
				}
				return this;
			}
		});
		// create scroll pane
		JScrollPane scrollP = new JScrollPane(tree);
		// add scrollPane to frame
		contentPane.add(scrollP);
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

class Flugzeug {
	private String modell;
	private int pass;
	private int hoehe;
	private int distance;
	private String image;

	public Flugzeug(String modell, int pass, int hoehe, int distance, String image) {
		super();
		this.modell = modell;
		this.pass = pass;
		this.hoehe = hoehe;
		this.distance = distance;
		this.image = image;
	}

	@Override
	public String toString() {
		return modell;
	}

	public String getModell() {
		return modell;
	}

	public int getPass() {
		return pass;
	}

	public int getHoehe() {
		return hoehe;
	}

	public int getDistance() {
		return distance;
	}

	public String getImage() {
		return image;
	}	

}

@SuppressWarnings("serial")
class FlugzeugDetails extends JDialog {
	public FlugzeugDetails(Flugzeug plane, JFrame frame) {
		super(frame,"Flugzeug Details", true); 
		this.setLayout(new GridLayout(0, 2));
		this.add(new JLabel("Modellbezeichnung"));
		this.add(new JLabel(plane.getModell()));
		this.add(new JLabel("Maximale Passagieranzahl"));
		this.add(new JLabel(Integer.toString(plane.getPass())));
		this.add(new JLabel("Maximale Reiseflughöhe (m)  "));
		this.add(new JLabel(Integer.toString(plane.getHoehe())));
		this.add(new JLabel("Maximale Reichweite (km)"));
		this.add(new JLabel(Integer.toString(plane.getDistance())));
		// size the dialog
		this.pack();
		// position the dialog
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - frame.getSize().width)/3, (d.height - frame.getSize().height)/3);
		//		this.setLocationRelativeTo(this.getOwner());
		// show the dialog
		this.setVisible(true);
	}

}
