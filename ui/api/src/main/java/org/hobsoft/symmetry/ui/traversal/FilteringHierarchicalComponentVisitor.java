/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/FilteringHierarchicalComponentVisitor.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Component;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FilteringHierarchicalComponentVisitor.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class FilteringHierarchicalComponentVisitor<T extends Component, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ComponentFilter filter;

	// constructors -----------------------------------------------------------
	
	public FilteringHierarchicalComponentVisitor(HierarchicalComponentVisitor<? super T, P, E> delegate,
		ComponentFilter filter)
	{
		super(delegate);
		
		this.filter = filter;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return SKIP_CHILDREN;
		}
		
		return super.visit(component, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, P parameter) throws E
	{
		if (!filter.accept(component))
		{
			return VISIT_SIBLINGS;
		}
		
		return super.endVisit(component, parameter);
	}
}
