/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/TreeVisitorAdapter.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code TreeVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class TreeVisitorAdapter<T extends Tree, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TreeVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final TreeVisitor<? super T, P, E> treeDelegate;

	// constructors -----------------------------------------------------------
	
	public TreeVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullTreeVisitor());
	}
	
	public TreeVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		TreeVisitor<? super T, P, E> treeDelegate)
	{
		super(delegate);
		
		this.treeDelegate = treeDelegate;
	}

	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNode(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.visitNode(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.visitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.endVisitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNode(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.endVisitNode(tree, parameter, path);
	}
}
