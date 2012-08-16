/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabBoxHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import java.beans.PropertyDescriptor;

import org.junit.Test;

import uk.co.iizuka.common.bean.Properties;
import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.state.PropertyState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedHierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.DummyComponentRehydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlTabBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTabBoxHydratorTest.java 98881 2012-02-29 17:07:14Z mark@IIZUKA.CO.UK $
 * @see HtmlTabBoxHydrator
 */
public class HtmlTabBoxHydratorTest extends AbstractXmlRenderKitTest<TabBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<TabBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(TabBox.class, new HtmlTabBoxHydrator<TabBox>());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(Tab.class, ComponentHydrators.<Tab>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(Tab.class, "tab")));

		PhasedHierarchicalComponentHydrator<DummyComponent> dummyHydrator =
			new PhasedHierarchicalComponentHydrator<DummyComponent>();
		dummyHydrator.setDelegate(DEHYDRATE, stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy"));
		dummyHydrator.setDelegate(REHYDRATE_PROPERTIES, new DummyComponentRehydrator());
		hydrator.setDelegate(DummyComponent.class, dummyHydrator);
		
		return StubPhasedUiComponentRenderKit.get(TabBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateTabBoxEmpty() throws HydrationException
	{
		TabBox tabBox = new TabBox();
		
		String expected = "<div class=\"tabbox\">"
			+ "<ul class=\"tabs\"></ul>"
			+ "</div>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTab() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab());
		
		String expected = "<div class=\"tabbox\">"
			+ "<ul class=\"tabs\"><li class=\"tab selected\"><a href=\"state\"></a></li></ul>"
			+ "[tab][/tab]"
			+ "</div>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTabAndText() throws HydrationException
	{
		TabBox tabBox = new TabBox(createTab("text"));
		
		String expected = "<div class=\"tabbox\">"
			+ "<ul class=\"tabs\"><li class=\"tab selected\"><a href=\"state\">text</a></li></ul>"
			+ "[tab][/tab]"
			+ "</div>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void dehydrateTabBoxWithTabs() throws HydrationException
	{
		// TODO: dehydrate tabs differently to differentiate in assertion
		TabBox tabBox = new TabBox(createTab("a"), createTab("b"));
		
		String expected = "<div class=\"tabbox\">"
			+ "<ul class=\"tabs\"><li class=\"tab selected\"><a href=\"state\">a</a></li>"
				+ "<li class=\"tab\"><a href=\"state\">b</a></li></ul>"
			+ "[tab][/tab]"
			+ "</div>";
		
		assertDehydrate(expected, tabBox);
	}
	
	@Test
	public void rehydrateWithSelectedIndexProperty() throws HydrationException
	{
		TabBox tabBox = new TabBox(new Tab(), new Tab());
		getRehydrationContext().get(State.class)
			.addProperty(new PropertyState(tabBox, getSelectedIndexDescriptor(), 1));
		
		rehydrate(tabBox);
		
		assertEquals(1, tabBox.getSelectedIndex());
	}
	
	@Test
	public void rehydrateWithSelectedTabComponentsProperty() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		TabBox tabBox = new TabBox(new Tab("", component));
		
		rehydrate(tabBox);
		
		assertTrue(component.getState());
	}
	
	@Test
	public void rehydrateWithUnselectedTabComponentsProperty() throws HydrationException
	{
		DummyComponent component = new DummyComponent();
		TabBox tabBox = new TabBox(new Tab(), new Tab("", component));
		
		rehydrate(tabBox);
		
		assertTrue(component.getState());
	}
	
	// TODO: rehydrateWithSelectedTabComponentsParameter
	// TODO: rehydrateWithUnselectedTabComponentsParameter
	
	// private methods --------------------------------------------------------
	
	private static Tab createTab(String text)
	{
		return new Tab(text, null);
	}
	
	private static PropertyDescriptor getSelectedIndexDescriptor()
	{
		return Properties.getDescriptor(Deck.class, Deck.SELECTED_INDEX_PROPERTY);
	}
}
