/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlDeckHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedHierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.DummyComponentRehydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlDeckHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlDeckHydratorTest.java 98880 2012-02-29 17:06:58Z mark@IIZUKA.CO.UK $
 * @see HtmlDeckHydrator
 */
public class HtmlDeckHydratorTest extends AbstractXmlRenderKitTest<Deck>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Deck> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Deck.class, new HtmlDeckHydrator<Deck>());
		
		PhasedHierarchicalComponentHydrator<DummyComponent> dummyHydrator =
			new PhasedHierarchicalComponentHydrator<DummyComponent>();
		dummyHydrator.setDelegate(DEHYDRATE, stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy"));
		dummyHydrator.setDelegate(REHYDRATE_PARAMETERS, new DummyComponentRehydrator());
		hydrator.setDelegate(DummyComponent.class, dummyHydrator);
		
		return StubPhasedUiComponentRenderKit.get(Deck.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateDeckWhenEmpty() throws HydrationException
	{
		Deck deck = new Deck();
		
		assertDehydrate("<div class=\"deck\"></div>", deck);
	}
	
	@Test
	public void dehydrateDeckWithComponent() throws HydrationException
	{
		Deck deck = new Deck(new DummyComponent());
		
		assertDehydrate("<div class=\"deck\">[dummy][/dummy]</div>", deck);
	}

	@Test
	public void dehydrateDeckWithComponents() throws HydrationException
	{
		// TODO: dehydrate tab components differently to differentiate in assertion
		Deck deck = new Deck(new DummyComponent(), new DummyComponent());
		
		assertDehydrate("<div class=\"deck\">[dummy][/dummy]</div>", deck);
	}

	@Test
	public void rehydrateWithSelectedComponentParameter() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		Deck deck = new Deck(component);
		
		rehydrate(deck);
		
		assertTrue(component.getState());
	}
	
	@Test
	public void rehydrateWithUnselectedComponentParameter() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		Deck deck = new Deck(new DummyComponent(), component);
		
		rehydrate(deck);
		
		assertFalse(component.getState());
	}
}
