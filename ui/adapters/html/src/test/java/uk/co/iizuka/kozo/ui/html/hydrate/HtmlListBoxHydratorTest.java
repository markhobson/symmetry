/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlListBoxHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import com.googlecode.jtype.Generic;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.HydrationPhase;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.SelectionMode;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import uk.co.iizuka.kozo.ui.model.DefaultListModel;
import uk.co.iizuka.kozo.ui.model.ListModel;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.test.model.ListModelSupport;
import uk.co.iizuka.kozo.ui.test.view.DummyComponentListCellRenderer;
import uk.co.iizuka.kozo.ui.test.view.NullComponentListCellRenderer;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlListBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlListBoxHydratorTest.java 99704 2012-03-20 16:49:00Z mark@IIZUKA.CO.UK $
 * @see HtmlListBoxHydrator
 */
@RunWith(JMock.class)
// TODO: use ListBox<?> when parameterized type literals supported
public class HtmlListBoxHydratorTest extends AbstractXmlRenderKitTest<ListBox>
{
	// TODO: test that rehydration doesn't visit children
	
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<ListBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(new Generic<ListBox<?>>() { /**/ }, createHydrator());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(ListBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(createListBox());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateListBoxWithEmptyModel() throws HydrationException
	{
		ListBox<Object> listBox = createListBox();
		
		assertDehydrate("<select class=\"listbox\" size=\"5\"></select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithName() throws HydrationException
	{
		ListBox<Object> listBox = createListBox();
		getStateCodec().setEncodedBean(listBox, "1");
		
		assertDehydrate("<select class=\"listbox\" name=\"1\" size=\"5\"></select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithVisibleRowCount() throws HydrationException
	{
		ListBox<Object> listBox = createListBox();
		listBox.setVisibleRowCount(10);
		
		assertDehydrate("<select class=\"listbox\" size=\"10\"></select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxMultiple() throws HydrationException
	{
		ListBox<Object> listBox = createListBox();
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		assertDehydrate("<select class=\"listbox\" size=\"5\" multiple=\"multiple\"></select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithItem() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		
		assertDehydrate("<select class=\"listbox\" size=\"5\">"
			+ "<option>[dummy][/dummy]</option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithItems() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x", "y", "z");
		
		assertDehydrate("<select class=\"listbox\" size=\"5\">"
			+ "<option>[dummy][/dummy]</option>"
			+ "<option>[dummy][/dummy]</option>"
			+ "<option>[dummy][/dummy]</option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithValuedItem() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		getStateCodec().setEncodedPropertyValue(listBox, ListBox.SELECTED_INDEXES_PROPERTY, new int[] {0}, "0");
		
		assertDehydrate("<select class=\"listbox\" size=\"5\">"
			+ "<option value=\"0\">[dummy][/dummy]</option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithSelectedItem() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		listBox.setSelectedIndex(0);
		
		assertDehydrate("<select class=\"listbox\" size=\"5\">"
			+ "<option selected=\"selected\">[dummy][/dummy]</option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithSelectedItems() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x", "y", "z");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedIndexes(new int[] {0, 1, 2});
		
		assertDehydrate("<select class=\"listbox\" size=\"5\" multiple=\"multiple\">"
			+ "<option selected=\"selected\">[dummy][/dummy]</option>"
			+ "<option selected=\"selected\">[dummy][/dummy]</option>"
			+ "<option selected=\"selected\">[dummy][/dummy]</option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxWithNullComponentRenderer() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		listBox.setListCellRenderer(new NullComponentListCellRenderer<String>());
		
		assertDehydrate("<select class=\"listbox\" size=\"5\">"
			+ "<option></option>"
			+ "</select>", listBox);
	}
	
	@Test
	public void dehydrateListBoxCallsModelOnce() throws HydrationException
	{
		ListModel<String> model = ListModelSupport.mockModel(mockery, "x");
		ListBox<String> listBox = createListBox(model);
		
		dehydrate(listBox);
	}

	@Test
	public void rehydrateWithSelectedIndexesParameter() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		getStateCodec().setEncodedBean(listBox, "1");
		getStateCodec().setEncodedPropertyValue(listBox, ListBox.SELECTED_INDEXES_PROPERTY, new int[] {0}, "0");
		setEncodedState(createEncodedState("1", "0"));
		
		rehydrate(listBox);
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void rehydrateWithNoSelectedIndexesParameter() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		listBox.setSelectedIndex(0);
		getStateCodec().setEncodedBean(listBox, "1");
		
		rehydrate(listBox);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void rehydrateWithUnvaluedSelectedIndexesParameter() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x");
		listBox.setSelectedIndex(0);
		getStateCodec().setEncodedBean(listBox, "1");
		setEncodedState(createEncodedState("1"));
		
		rehydrate(listBox);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void rehydrateWithMultivaluedSelectedIndexesParameter() throws HydrationException
	{
		ListBox<String> listBox = createListBox("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedIndex(0);
		getStateCodec().setEncodedBean(listBox, "1");
		getStateCodec().setEncodedPropertyValue(listBox, ListBox.SELECTED_INDEXES_PROPERTY, new int[] {0}, "0");
		getStateCodec().setEncodedPropertyValue(listBox, ListBox.SELECTED_INDEXES_PROPERTY, new int[] {1}, "1");
		setEncodedState(createEncodedState("1", "0", "1"));
		
		rehydrate(listBox);
		
		assertArrayEquals(new int[] {0, 1}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void rehydratePropertiesSkipsChildren() throws HydrationException
	{
		ListBox<String> comboBox = createListBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_PROPERTIES)));
	}
	
	@Test
	public void rehydrateEventsSkipsChildren() throws HydrationException
	{
		ListBox<String> comboBox = createListBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_EVENTS)));
	}
	
	@Test
	public void rehydrateParametersSkipsChildren() throws HydrationException
	{
		ListBox<String> comboBox = createListBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_PARAMETERS)));
	}
	
	// private methods --------------------------------------------------------
	
	private static HtmlListBoxHydrator<ListBox<?>> createHydrator()
	{
		return new HtmlListBoxHydrator<ListBox<?>>();
	}
	
	// TODO: centralise
	private RehydrationContext getRehydrationContext(HydrationPhase phase)
	{
		RehydrationContext context = getRehydrationContext();
		context.set(HydrationPhase.class, phase);
		return context;
	}
	
	private static <T> ListBox<T> createListBox(T... items)
	{
		return createListBox(new DefaultListModel<T>(items));
	}
	
	private static <T> ListBox<T> createListBox(ListModel<T> model)
	{
		ListBox<T> listBox = new ListBox<T>(model);
		listBox.setListCellRenderer(new DummyComponentListCellRenderer<T>());
		
		return listBox;
	}
}
