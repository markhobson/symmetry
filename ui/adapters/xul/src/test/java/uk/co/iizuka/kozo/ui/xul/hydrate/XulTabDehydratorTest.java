/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulTabDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulTabDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulTabDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulTabDehydrator
 */
public class XulTabDehydratorTest extends AbstractXmlRenderKitTest<Tab>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Tab> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Tab.class, new XulTabDehydrator<Tab>());
		
		hydrator.setDelegate(DummyComponent.class, stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy"));
		
		return StubUiComponentRenderKit.get(Tab.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTab() throws HydrationException
	{
		Tab tab = new Tab();
		
		assertDehydrate("<tabpanel></tabpanel>", tab);
	}
	
	@Test
	public void dehydrateTabWithComponent() throws HydrationException
	{
		Tab tab = new Tab(new DummyComponent());
		
		assertDehydrate("<tabpanel>[dummy][/dummy]</tabpanel>", tab);
	}
}
