/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ContainerVisitorAdapter.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Container;

/**
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code ContainerVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: ContainerVisitorAdapter.java 100084 2012-03-30 10:23:59Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class ContainerVisitorAdapter<T extends Container, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ContainerVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ContainerVisitor<? super T, P, E> containerDelegate;

	// constructors -----------------------------------------------------------
	
	public ContainerVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullContainerVisitor());
	}
	
	public ContainerVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		ContainerVisitor<? super T, P, E> containerDelegate)
	{
		super(delegate);
		
		this.containerDelegate = containerDelegate;
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T container, P parameter, int childIndex) throws E
	{
		return containerDelegate.visitChild(container, parameter, childIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitChild(T container, P parameter, int childIndex) throws E
	{
		return containerDelegate.endVisitChild(container, parameter, childIndex);
	}
}
