/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/VisibleNodeTreeVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class VisibleNodeTreeVisitor<T extends Tree, P, E extends Exception> extends DelegatingTreeVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public VisibleNodeTreeVisitor(TreeVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		if (!tree.isPathExpanded(path))
		{
			return SKIP_CHILDREN;
		}
		
		return super.visitNodeChildren(tree, parameter, path);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		// TODO: remove implementation since we need to visit all nodes
		
		if (!tree.isPathExpanded(path))
		{
			return VISIT_SIBLINGS;
		}
		
		return super.endVisitNodeChildren(tree, parameter, path);
	}
}
