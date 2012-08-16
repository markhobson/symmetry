/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingTreeVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingTreeVisitor.java 98542 2012-02-16 18:16:41Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingTreeVisitor<T extends Tree, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TreeVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingTreeVisitor(TreeVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNode(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).visitNode(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).visitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).endVisitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNode(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).endVisitNode(tree, parameter, path);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeVisitor<? super T, P, E> getDelegate(T tree, P parameter)
	{
		return (TreeVisitor<? super T, P, E>) super.getDelegate(tree, parameter);
	}
}
