/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulTabDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

/**
 * Tests {@code XulTabDehydrator}.
 * 
 * @author Mark Hobson
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
