/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedContainerHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.ui.traversal.DelegatingContainerVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedContainerHydrator<T extends Container>
	extends DelegatingContainerVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<ContainerVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedContainerHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullContainerVisitor());
	}
	
	public PhasedContainerHydrator(ContainerVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<ContainerVisitor<? super T, HydrationContext,
			HydrationException>>(delegate);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContainerVisitor<? super T, HydrationContext, HydrationException> getDelegate(T box,
		HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public ContainerVisitor<? super T, HydrationContext, HydrationException> getDelegate(HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase,
		ContainerVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
