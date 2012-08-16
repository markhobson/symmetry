/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DefaultTreeModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTreeModel.java 95332 2011-11-18 12:22:38Z mark@IIZUKA.CO.UK $
 */
public class DefaultTreeModel extends AbstractTreeModel
{
	// fields -----------------------------------------------------------------
	
	private final TreeNode root;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTreeModel()
	{
		this(null);
	}
	
	public DefaultTreeModel(TreeNode root)
	{
		this.root = root;
	}
	
	// TreeModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRoot()
	{
		return root;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getChild(Object parent, int index)
	{
		return ((TreeNode) parent).getChildAt(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount(Object parent)
	{
		return ((TreeNode) parent).getChildCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildIndex(Object parent, Object child)
	{
		return ((TreeNode) parent).getIndex((TreeNode) child);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf(Object node)
	{
		return (getChildCount(node) == 0);
	}
}
