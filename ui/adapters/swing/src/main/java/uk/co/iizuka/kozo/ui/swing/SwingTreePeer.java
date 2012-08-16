/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingTreePeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreeModel;
import uk.co.iizuka.kozo.ui.swing.model.TreeModelAdapter;
import uk.co.iizuka.kozo.ui.swing.view.TreeCellRendererAdapter;
import uk.co.iizuka.kozo.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingTreePeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwingTreePeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
//	private class TreeSelectionListenerAdapter extends ListenerAdapter
//		implements javax.swing.event.TreeSelectionListener
//	{
//		public TreeSelectionListenerAdapter(TreeSelectionListener listener)
//		{
//			super(listener);
//		}
//		
//		public void valueChanged(TreeSelectionEvent event)
//		{
//			TreePath path = new TreePathAdapter(event.getPath());
//			TreeEvent treeEvent = new TreeEvent((Tree) getComponent(), path);
//			((TreeSelectionListener) getListener()).treeSelected(treeEvent);
//		}
//	}
	
	// constructors -----------------------------------------------------------
	
	public SwingTreePeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		JTree tree = new JTree();
		ToolTipManager.sharedInstance().registerComponent(tree);
		return new JScrollPane(tree);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Tree component = (Tree) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JTree tree = (JTree) ((JScrollPane) getPeerManager().getPeer(component)).getViewport().getView();
		
		if (Tree.MODEL_PROPERTY.equals(name))
		{
			tree.setModel(new TreeModelAdapter((TreeModel) newValue));
			
			// Swing always expands the root node
			tree.collapseRow(0);
		}
		else if (Tree.TREE_NODE_RENDERER_PROPERTY.equals(name))
		{
			tree.setCellRenderer(new TreeCellRendererAdapter(component, (TreeNodeRenderer) newValue));
		}
		else if (Tree.TREE_EXPANSION_LISTENER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.TREE_SELECTION_LISTENER_PROPERTY.equals(name))
		{
			if (oldValue == null)
			{
				// TODO: reimplement
//				tree.addTreeSelectionListener(new TreeSelectionListenerAdapter((TreeSelectionListener) newValue));
			}
			else if (newValue == null)
			{
				// TODO: reimplement
//				tree.removeTreeSelectionListener((javax.swing.event.TreeSelectionListener)
//					getListener((TreeSelectionListener) oldValue));
			}
		}
		else if (Tree.EXPANDED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.SELECTED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.SELECTION_MODE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
	}
}
