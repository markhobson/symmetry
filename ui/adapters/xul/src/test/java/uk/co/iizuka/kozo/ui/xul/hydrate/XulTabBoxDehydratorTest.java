/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/test/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulTabBoxDehydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code XulTabBoxDehydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: XulTabBoxDehydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see XulTabBoxDehydrator
 */
public class XulTabBoxDehydratorTest extends AbstractXmlRenderKitTest<TabBox>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TabBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(TabBox.class, new XulTabBoxDehydrator<TabBox>());
		
		hydrator.setDelegate(Tab.class, stubXmlHierarchicalComponentHydrator(Tab.class, "tab"));
		
		return StubUiComponentRenderKit.get(TabBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTabBoxEmpty() throws HydrationException
	{
		TabBox tabBox = new TabBox();
		
		assertDehydrate("<tabbox></tabbox>", tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTab() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab());
		
		String expected = "<tabbox>"
			+ "<tabs><tab/></tabs>"
			+ "<tabpanels>[tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTabAndText() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab("text", null));
		
		String expected = "<tabbox>"
			+ "<tabs><tab label=\"text\"/></tabs>"
			+ "<tabpanels>[tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}

	@Test
	public void dehydrateTabBoxWithTabs() throws HydrationException
	{
		// TODO: dehydrate tabs differently to differentiate in assertion
		TabBox tabBox = new TabBox(new Tab("a", null), new Tab("b", null));
		
		String expected = "<tabbox>"
			+ "<tabs><tab label=\"a\"/><tab label=\"b\"/></tabs>"
			+ "<tabpanels>[tab][/tab][tab][/tab]</tabpanels>"
			+ "</tabbox>";
		
		assertDehydrate(expected, tabBox);
	}
}
