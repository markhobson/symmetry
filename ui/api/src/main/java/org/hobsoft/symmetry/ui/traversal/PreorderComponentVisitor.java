/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/PreorderComponentVisitor.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class PreorderComponentVisitor<P, E extends Exception> extends NullComponentVisitor<P, E>
{
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType,
		T component, P parameter) throws E
	{
		visit(component, parameter);
		
		return null;
	}
	
	// protected methods ------------------------------------------------------
	
	// TODO: do we want componentType here too?
	protected abstract void visit(Component component, P parameter) throws E;
}
