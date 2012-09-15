/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/FilteringComponentVisitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.skipChildren;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FilteringComponentVisitor.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class FilteringComponentVisitor<P, E extends Exception> extends DelegatingComponentVisitor<P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ComponentFilter filter;
	
	// constructors -----------------------------------------------------------
	
	public FilteringComponentVisitor(ComponentVisitor<P, E> delegate, ComponentFilter filter)
	{
		super(delegate);
		
		this.filter = filter;
	}
	
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return skipChildren();
		}
		
		return super.visit(componentType, component, parameter);
	}
}
