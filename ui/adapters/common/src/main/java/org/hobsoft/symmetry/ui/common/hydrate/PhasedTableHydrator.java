/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedTableHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.DelegatingTableVisitor;
import org.hobsoft.symmetry.ui.traversal.TableVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedTableHydrator<T extends Table>
	extends DelegatingTableVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<TableVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedTableHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullTableVisitor());
	}
	
	public PhasedTableHydrator(TableVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<TableVisitor<? super T, HydrationContext,
			HydrationException>>(delegate);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableVisitor<? super T, HydrationContext, HydrationException> getDelegate(T table, HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public TableVisitor<? super T, HydrationContext, HydrationException> getDelegate(HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase,
		TableVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
