/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/test/java/uk/co/iizuka/kozo/ui/common/hydrate/PhasedHierarchicalComponentHydratorSupportTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitors;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;

/**
 * Tests {@code PhasedHierarchicalComponentHydratorSupport}.
 * 
 * @author Mark Hobson
 * @version $Id: PhasedHierarchicalComponentHydratorSupportTest.java 98885 2012-02-29 17:37:56Z mark@IIZUKA.CO.UK $
 * @see PhasedHierarchicalComponentHydratorSupport
 */
public class PhasedHierarchicalComponentHydratorSupportTest
{
	// fields -----------------------------------------------------------------
	
	private PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> support;
	
	private HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> defaultVisitor;
	
	private HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> visitor;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		support = createSupport();
		defaultVisitor = ComponentVisitors.<Component, HydrationContext, HydrationException>nullHierarchicalVisitor();
		visitor = ComponentVisitors.<Component, HydrationContext, HydrationException>nullHierarchicalVisitor();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void getDelegateHydrationContext()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenNotSet()
	{
		assertNull(support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegateHydrationContextWhenNotSetAndDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		
		assertEquals(defaultVisitor, support.getDelegate(createHydrationContext(DEHYDRATE)));
	}
	
	@Test
	public void getDelegatePhase()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		support.setDelegate(DEHYDRATE, visitor);
		
		assertEquals(visitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenNotSet()
	{
		assertNull(support.getDelegate(DEHYDRATE));
	}
	
	@Test
	public void getDelegatePhaseWhenNotSetAndDefaultDelegate()
	{
		support = createSupport(defaultVisitor);
		
		assertEquals(defaultVisitor, support.getDelegate(DEHYDRATE));
	}
	
	@Test(expected = NullPointerException.class)
	public void setDelegateWithNullPhase()
	{
		support.setDelegate(null, visitor);
	}
	
	@Test
	public void setDelegateWithNullVisitor()
	{
		support.setDelegate(DEHYDRATE, visitor);
		
		support.setDelegate(DEHYDRATE, null);
		
		assertNull(support.getDelegate(DEHYDRATE));
	}
	
	// private methods --------------------------------------------------------
	
	private static PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> createSupport()
	{
		return createSupport(null);
	}
	
	private static PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
		HydrationException>> createSupport(
			HierarchicalComponentVisitor<Component, HydrationContext, HydrationException> delegate)
	{
		return new PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<Component, HydrationContext,
			HydrationException>>(delegate);
	}

	private static HydrationContext createHydrationContext()
	{
		// TODO: use FakeHydrationContext
		return new RehydrationContext();
	}
	
	private static HydrationContext createHydrationContext(HydrationPhase phase)
	{
		HydrationContext context = createHydrationContext();
		context.set(HydrationPhase.class, phase);
		return context;
	}
}
