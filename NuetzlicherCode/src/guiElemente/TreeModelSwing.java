package guiElemente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


public class TreeModelSwing {
	
	public static void main(String[] args) {
		final TreeMF tmf = new TreeMF();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tmf.showFrame();
            }
        });
	}

}

class TreeMF implements ActionListener {
	private JFrame frame;
	private JTree tree;

	public TreeMF() {
		//Create and set up the window.
		frame = new JFrame("TreeModelSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		// create the data model for the tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		DefaultTreeModel dtm = new DefaultTreeModel(root);
		tree = new JTree(dtm);
		// restrict to single node selection
//		DefaultTreeSelectionModel dtsm = new DefaultTreeSelectionModel();
//		dtsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//		tree.setSelectionModel(dtsm);
		// create scroll pane
		JScrollPane scrollP = new JScrollPane(tree);
		// create panel ith the buttons in it
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(this);
		buttonAdd.setActionCommand("add");
		JButton buttonChange = new JButton("Change");
		buttonChange.addActionListener(this);
		buttonChange.setActionCommand("change");
		JButton buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(this);
		buttonRemove.setActionCommand("remove");
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(buttonAdd);
		buttonPanel.add(buttonChange);
		buttonPanel.add(buttonRemove);
		// add scrollPane and buttonPanel to frame
		contentPane.setLayout(new BorderLayout());
		contentPane.add(scrollP, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.PAGE_END);
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
	public void actionPerformed(ActionEvent event) {
		String actCmd = event.getActionCommand();
		// get path to currently selected node
		TreePath tp = tree.getLeadSelectionPath();
		if (tp == null)
			return;
		// get the data model used for this tree
		DefaultTreeModel dtm = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode nodeSel = (DefaultMutableTreeNode)tp.getLastPathComponent();
		// create new child, insert it into the selected node as the last child and
		// expand the tree to show the new child
		if (actCmd.equals("add")) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode("child");
			dtm.insertNodeInto(child, nodeSel, nodeSel.getChildCount());
			tree.expandPath(tp);
		}
		// get the data assigned to the selected node, modify it and assign it to the node and
		// inform the TreeModel that a node content has changed
		else if (actCmd.equals("change")) {
			String nodeSelUserObj = (String)nodeSel.getUserObject();
			nodeSel.setUserObject(nodeSelUserObj + "C"); 
			dtm.nodeChanged(nodeSel);
		}
		// entire tree cannot be deleted; get parent node of selected node, remove
		// selected node from tree and set new selection path to the parent node
		else {
			if (nodeSel.isRoot())
				return;
			DefaultMutableTreeNode parNode = (DefaultMutableTreeNode)nodeSel.getParent();
			dtm.removeNodeFromParent(nodeSel);
			tree.setSelectionPath(new TreePath(dtm.getPathToRoot(parNode)));
		}
	}
}