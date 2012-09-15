/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTabBoxHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.support.bean.Properties;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.DummyComponentRehydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

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
