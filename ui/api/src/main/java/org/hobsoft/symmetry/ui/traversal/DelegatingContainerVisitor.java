/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingContainerVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Container;

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
public abstract class DelegatingContainerVisitor<T extends Container, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ContainerVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingContainerVisitor(ContainerVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T container, P parameter, int childIndex) throws E
	{
		return getDelegate(container, parameter).visitChild(container, parameter, childIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T container, P parameter, int childIndex) throws E
	{
		return getDelegate(container, parameter).endVisitChild(container, parameter, childIndex);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContainerVisitor<? super T, P, E> getDelegate(T container, P parameter)
	{
		return (ContainerVisitor<? super T, P, E>) super.getDelegate(container, parameter);
	}
}
