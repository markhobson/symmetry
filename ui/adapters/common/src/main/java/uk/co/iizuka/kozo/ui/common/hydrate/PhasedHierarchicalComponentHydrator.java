/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedHierarchicalComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitors;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PhasedHierarchicalComponentHydrator.java 98861 2012-02-29 10:43:27Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedHierarchicalComponentHydrator<T extends Component> extends DelegatingHierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedHierarchicalComponentHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullHierarchicalVisitor());
	}
	
	public PhasedHierarchicalComponentHydrator(HierarchicalComponentVisitor<? super T, HydrationContext,
		HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<? super T,
			HydrationContext, HydrationException>>(delegate);
	}

	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> getDelegate(T component,
		HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> getDelegate(
		HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase,
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
