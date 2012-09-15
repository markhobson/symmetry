/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingComponentVisitor.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingComponentVisitor<P, E extends Exception> implements ComponentVisitor<P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ComponentVisitor<P, E> delegate;

	// constructors -----------------------------------------------------------
	
	public DelegatingComponentVisitor(ComponentVisitor<P, E> delegate)
	{
		this.delegate = delegate;
	}

	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		return delegate.visit(componentType, component, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public ComponentVisitor<P, E> getDelegate()
	{
		return delegate;
	}
}
