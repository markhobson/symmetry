/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/PostorderComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class PostorderComponentVisitor<P, E extends Exception> extends NullComponentVisitor<P, E>
{
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType,
		T component, P parameter) throws E
	{
		// TODO: cache, although jmock-legacy does not call super constructors
		return new NullHierarchicalComponentVisitor<T, P, E>()
		{
			@Override
			public EndVisit endVisit(T component, P parameter) throws E
			{
				PostorderComponentVisitor.this.visit(component, parameter);
				
				return VISIT_SIBLINGS;
			}
		};
	}
	
	// protected methods ------------------------------------------------------
	
	// TODO: do we want componentType here too?
	protected abstract void visit(Component component, P parameter) throws E;
}
