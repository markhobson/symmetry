/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlTabHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTabHydratorTest.java 98871 2012-02-29 11:44:00Z mark@IIZUKA.CO.UK $
 * @see HtmlTabHydrator
 */
public class HtmlTabHydratorTest extends AbstractXmlRenderKitTest<Tab>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Tab> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Tab.class, new HtmlTabHydrator<Tab>());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(Tab.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTab() throws HydrationException
	{
		Tab tab = new Tab("", null);
		
		String expected = "<div class=\"tabpanel\"></div>";
		
		assertDehydrate(expected, tab);
	}
	
	@Test
	public void dehydrateTabWithComponent() throws HydrationException
	{
		Tab tab = new Tab("", new DummyComponent());
		
		String expected = "<div class=\"tabpanel\">[dummy][/dummy]</div>";
		
		assertDehydrate(expected, tab);
	}
}
