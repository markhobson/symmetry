/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingHierarchicalComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import static com.google.common.base.Preconditions.checkState;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingHierarchicalComponentVisitor.java 99745 2012-03-22 10:17:52Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingHierarchicalComponentVisitor<T extends Component, P, E extends Exception>
	implements HierarchicalComponentVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final HierarchicalComponentVisitor<? super T, P, E> delegate;

	// constructors -----------------------------------------------------------
	
	public DelegatingHierarchicalComponentVisitor(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		// TODO: how best to support the two modes of operation?
		// 1) a non-null delegate is provided at construction (need to checkNotNull)
		// 2) no delegate is provided at construction, but getDelegate is overridden for dynamic delegation (how to
		//    enforce?)
		// ideally we don't want two classes for this since we have many Delegating*Visitors
		
		this.delegate = delegate;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, P parameter) throws E
	{
		return getDelegate(component, parameter).visit(component, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, P parameter) throws E
	{
		return getDelegate(component, parameter).endVisit(component, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	// TODO: can this be protected?
	public HierarchicalComponentVisitor<? super T, P, E> getDelegate(T component, P parameter)
	{
		checkState(delegate != null, "getDelegate must be overridden when no delegate provided at construction");
		
		return delegate;
	}
}
