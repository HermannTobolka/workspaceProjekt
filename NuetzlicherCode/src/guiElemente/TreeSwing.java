package guiElemente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeSwing {

	public static void main(String[] args) {
		final TreeF tf = new TreeF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tf.showFrame();
            }
        });
	}
}

class TreeF implements TreeSelectionListener, TreeExpansionListener, TreeWillExpandListener {
	private JFrame frame;

	@SuppressWarnings("serial")
	public TreeF() {
		//Create and set up the window.
		frame = new JFrame("TreeSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create the data structure for the tree
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Restaurants");
		DefaultMutableTreeNode chinR = new DefaultMutableTreeNode("Chinesisch");
		chinR.add(new DefaultMutableTreeNode("Mr Lee"));
		chinR.add(new DefaultMutableTreeNode("Jade"));
		top.add(chinR);
		DefaultMutableTreeNode italR = new DefaultMutableTreeNode("Italienisch");
		italR.add(new DefaultMutableTreeNode("Pizzeria Al Capone"));
		italR.add(new DefaultMutableTreeNode("Ristorante Alfredo"));
		italR.add(new DefaultMutableTreeNode("Pizzeria Venezia"));
		top.add(italR);
		DefaultMutableTreeNode indiR = new DefaultMutableTreeNode("Indisch");
		indiR.add(new DefaultMutableTreeNode("Taj Mahal"));
		top.add(indiR);
		DefaultMutableTreeNode grieR = new DefaultMutableTreeNode("Griechisch");
		grieR.add(new DefaultMutableTreeNode("Alexis Sorbas"));
		grieR.add(new DefaultMutableTreeNode("El Greco"));
		top.add(grieR);
		final JTree tree = new JTree(top);
		// restrict to single node selection
		DefaultTreeSelectionModel dtsm = new DefaultTreeSelectionModel();
		dtsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(dtsm);
		// register for tree selection events
		tree.addTreeSelectionListener(this);
		tree.addTreeExpansionListener(this);
		tree.addTreeWillExpandListener(this);
		// register for mouse Clicks
		tree.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
		         int selRow = tree.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		         if (selPath == null)
		        	 return;
		         DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) selPath.getLastPathComponent();
		         if(selRow != -1) {
		        	 if(e.getClickCount() == 1) 
		                 System.out.println("SingleClick auf Knoten " + dmtn.getUserObject() + " mit Mausbutton " + e.getButton());
		             else if(e.getClickCount() == 2) 
		            	 System.out.println("DoubleClick auf Knoten " + dmtn.getUserObject() + " mit Mausbutton " + e.getButton());
		         }
			}

		 });
		// set renderer
		tree.setCellRenderer(new DefaultTreeCellRenderer() { 
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				if (leaf) 
					this.setForeground(Color.orange);
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

	@Override
	/**
	 * listen for tree selection events
	 */
	public void valueChanged(TreeSelectionEvent event) {
		TreePath tp = event.getNewLeadSelectionPath();
		if (tp == null)
			return;
		Object treeNode = tp.getLastPathComponent();
		if (treeNode == null)
			return;
		System.out.println("Knoten selektiert: " + ((DefaultMutableTreeNode)treeNode).getUserObject());
	}

	@Override
	public void treeCollapsed(TreeExpansionEvent event) {
		TreePath tp = event.getPath();
		if (tp == null)
			return;
		Object treeNode = tp.getLastPathComponent();
		System.out.println("Knoten collapsed: " + ((DefaultMutableTreeNode)treeNode).getUserObject());
	}

	@Override
	public void treeExpanded(TreeExpansionEvent event) {
		TreePath tp = event.getPath();
		if (tp == null)
			return;
		Object treeNode = tp.getLastPathComponent();
		System.out.println("Knoten expanded: " + ((DefaultMutableTreeNode)treeNode).getUserObject());
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		TreePath tp = event.getPath();
		if (tp == null)
			return;
		Object treeNode = tp.getLastPathComponent();
		System.out.println("Knoten will collapse: " + ((DefaultMutableTreeNode)treeNode).getUserObject());
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		TreePath tp = event.getPath();
		if (tp == null)
			return;
		Object treeNode = tp.getLastPathComponent();
		System.out.println("Knoten will expand: " + ((DefaultMutableTreeNode)treeNode).getUserObject());
	}
}