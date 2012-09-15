/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/TreeModelAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.model;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import org.hobsoft.symmetry.ui.model.TreeModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreeModelAdapter implements javax.swing.tree.TreeModel
{
	// fields -----------------------------------------------------------------
	
	private final TreeModel model;
	
	// constructors -----------------------------------------------------------
	
	public TreeModelAdapter(TreeModel model)
	{
		this.model = model;
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRoot()
	{
		return model.getRoot();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getChild(Object parent, int index)
	{
		return model.getChild(parent, index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount(Object parent)
	{
		return model.getChildCount(parent);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf(Object node)
	{
		// TODO: properly
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child)
	{
		return model.getChildIndex(parent, child);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTreeModelListener(TreeModelListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener listener)
	{
		// TODO: implement
	}
}
