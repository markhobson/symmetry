/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedListBoxHydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitors;
import uk.co.iizuka.kozo.ui.traversal.DelegatingListBoxVisitor;
import uk.co.iizuka.kozo.ui.traversal.ListBoxVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PhasedListBoxHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedListBoxHydrator<T extends ComboBox<?>>
	extends DelegatingListBoxVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<ListBoxVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedListBoxHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullListBoxVisitor());
	}
	
	public PhasedListBoxHydrator(ListBoxVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<ListBoxVisitor<? super T, HydrationContext,
			HydrationException>>(delegate);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListBoxVisitor<? super T, HydrationContext, HydrationException> getDelegate(T listBox,
		HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public ListBoxVisitor<? super T, HydrationContext, HydrationException> getDelegate(HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase,
		ListBoxVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
