/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtTreePeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.event.TreeModelListener;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtTreePeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwtTreePeer extends AbstractPeerHandler
{
	// fields -----------------------------------------------------------------
	
	// TODO: no local fields!
	private TreeModelListener modelListener;

	// types ------------------------------------------------------------------
	
	// TODO: reimplement
//	private class ModelListener implements TreeModelListener
//	{
//		public void nodeDeleted(TreeModelEvent event)
//		{
//			getTreeItem(event.getPath()).dispose();
//		}
//	}
//	
//	private class ExpandListener implements Listener
//	{
//		public void handleEvent(Event event)
//		{
//			TreeItem item = (TreeItem) event.item;
//			
//			// dispose dummy item
//			TreeItem[] children = item.getItems();
//			if (children.length != 1 || children[0].getData() != null)
//				return;
//			children[0].dispose();
//			
//			Object node = item.getData();
//			TreeModel model = ((Tree) getComponent()).getModel();
//			int count = model.getChildCount(node);
//			for (int i = 0; i < count; i++)
//			{
//				Object childNode = model.getChild(node, i);
//				TreeItem child = newTreeItem(item, childNode);
//				if (!model.isLeaf(childNode))
//					new TreeItem(child, SWT.NONE);
//			}
//		}
//	}
//	
//	private class CollapseListener implements Listener
//	{
//		public void handleEvent(Event event)
//		{
//			TreeItem item = (TreeItem) event.item;
//			
//			TreeItem[] children = item.getItems();
//			for (int i = 0; i < children.length; i++)
//				children[i].dispose();
//			
//			new TreeItem(item, SWT.NONE);
//		}
//	}
//	
//	private class TreeListenerAdapter extends ListenerAdapter implements TreeListener
//	{
//		public TreeListenerAdapter(TreeExpansionListener listener)
//		{
//			super(listener);
//		}
//		
//		public void treeCollapsed(org.eclipse.swt.events.TreeEvent event)
//		{
//			TreePath path = getTreePath((TreeItem) event.item);
//			TreeEvent treeEvent = new TreeEvent((Tree) getComponent(), path);
//			((TreeExpansionListener) getListener()).treeCollapsed(treeEvent);
//		}
//		
//		public void treeExpanded(org.eclipse.swt.events.TreeEvent event)
//		{
//			TreePath path = getTreePath((TreeItem) event.item);
//			TreeEvent treeEvent = new TreeEvent((Tree) getComponent(), path);
//			((TreeExpansionListener) getListener()).treeExpanded(treeEvent);
//		}
//	}
//	
//	private class SelectionListenerAdapter extends ListenerAdapter implements SelectionListener
//	{
//		public SelectionListenerAdapter(TreeSelectionListener listener)
//		{
//			super(listener);
//		}
//		
//		public void widgetSelected(SelectionEvent event)
//		{
//			widgetDefaultSelected(event);
//		}
//		
//		public void widgetDefaultSelected(SelectionEvent event)
//		{
//			TreeItem item = (TreeItem) event.item;
//			TreePath path = getTreePath(item);
//			TreeEvent treeEvent = new TreeEvent((Tree) getComponent(), path);
//			((TreeSelectionListener) getListener()).treeSelected(treeEvent);
//		}
//	}
	
	// constructors -----------------------------------------------------------
	
	public SwtTreePeer(PeerManager peerManager)
	{
		super(peerManager);
		
		// TODO: reimplement
//		modelListener = new ModelListener();
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Composite parent = SwtUtils.getComposite((Widget) getPeerManager().getPeer(((Component) component)
			.getParent()));
		
		org.eclipse.swt.widgets.Tree tree = new org.eclipse.swt.widgets.Tree(parent, SWT.BORDER);
		// TODO: reimplement
//		tree.addListener(SWT.Expand, new ExpandListener());
//		tree.addListener(SWT.Collapse, new CollapseListener());
		return tree;
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
		org.eclipse.swt.widgets.Tree tree = (org.eclipse.swt.widgets.Tree) getPeerManager().getPeer(component);
		
		if (Tree.EXPANDED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.MODEL_PROPERTY.equals(name))
		{
			setModel(tree, (TreeModel) newValue);
		}
		else if (Tree.SELECTED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.SELECTION_MODE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.TREE_EXPANSION_LISTENER_PROPERTY.equals(name))
		{
			// TODO: reimplement
//			if (oldValue == null)
//				tree.addTreeListener(new TreeListenerAdapter((TreeExpansionListener) newValue));
//			else if (newValue == null)
//				tree.removeTreeListener((TreeListener) getListener((TreeExpansionListener) oldValue));
		}
		else if (Tree.TREE_NODE_RENDERER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.TREE_SELECTION_LISTENER_PROPERTY.equals(name))
		{
			// TODO: reimplement
//			if (oldValue == null)
//				tree.addSelectionListener(new SelectionListenerAdapter((TreeSelectionListener) newValue));
//			else if (newValue == null)
//				tree.removeSelectionListener((SelectionListener) getListener((TreeSelectionListener) oldValue));
		}
	}
	
	// private methods --------------------------------------------------------
	
	private void setModel(org.eclipse.swt.widgets.Tree tree, TreeModel model)
	{
		// TODO: dispose old model's TreeItems
		
		Object root = model.getRoot();
		TreeItem item = newTreeItem(tree, root);
		if (!model.isLeaf(root))
		{
			new TreeItem(item, SWT.NONE);
		}
		
		model.addTreeModelListener(modelListener);
	}
	
	private TreeItem newTreeItem(org.eclipse.swt.widgets.Tree parent, Object node)
	{
		return newTreeItemImpl(new TreeItem(parent, SWT.NONE), node);
	}
	
	private TreeItem newTreeItem(TreeItem parent, Object node)
	{
		return newTreeItemImpl(new TreeItem(parent, SWT.NONE), node);
	}
	
	private TreeItem newTreeItemImpl(TreeItem item, Object node)
	{
		item.setData(node);
		
		// TODO: reimplement
//		Tree tree = (Tree) getComponent();
//		TreeNodeRenderer renderer = tree.getTreeNodeRenderer();
//		Component component = renderer.getTreeNodeComponent(tree, getTreePath(item));
//		SWTPeer peer = (SWTPeer) getPeerManager().getPeer(component);
//		peer.asTreeItem(item);
		return item;
	}
	
	private TreePath getTreePath(TreeItem item)
	{
		Object node = item.getData();
		TreeItem parent = item.getParentItem();
		return (parent == null) ? new TreePath(node) : new TreePath(getTreePath(parent), node);
	}
	
	private TreeItem getTreeItem(TreePath path)
	{
		// TODO: reimplement
//		org.eclipse.swt.widgets.Tree widget = (org.eclipse.swt.widgets.Tree) getPeer();
//		TreePath parentPath = path.getParentPath();
//		Object node = path.getNode();
//		
//		TreeItem[] items = (parentPath == null) ? widget.getItems() : getTreeItem(parentPath).getItems();
//		for (int i = 0; i < items.length; i++)
//			if (items[i].getData().equals(node))
//				return items[i];
		return null;
	}
}
