/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedGridHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.DelegatingGridVisitor;
import org.hobsoft.symmetry.ui.traversal.GridVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedGridHydrator<T extends Grid>
	extends DelegatingGridVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<GridVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedGridHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullGridVisitor());
	}
	
	public PhasedGridHydrator(GridVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<GridVisitor<? super T, HydrationContext,
			HydrationException>>(delegate);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GridVisitor<? super T, HydrationContext, HydrationException> getDelegate(T grid, HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setDelegate(HydrationPhase phase, GridVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
