/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComboBoxHydratorTest.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.ui.model.DefaultListModel;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.test.event.DummySelectionListener;
import org.hobsoft.symmetry.ui.test.model.ListModelSupport;
import org.hobsoft.symmetry.ui.test.view.DummyComponentListCellRenderer;
import org.hobsoft.symmetry.ui.test.view.NullComponentListCellRenderer;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code HtmlComboBoxHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlComboBoxHydratorTest.java 99704 2012-03-20 16:49:00Z mark@IIZUKA.CO.UK $
 * @see HtmlComboBoxHydrator
 */
@RunWith(JMock.class)
// TODO: use ComboBox<?> when parameterized type literals supported
public class HtmlComboBoxHydratorTest extends AbstractXmlRenderKitTest<ComboBox>
{
	// TODO: test that rehydration doesn't visit children
	
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<ComboBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(new Generic<ComboBox<?>>() { /**/ }, createHydrator());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(ComboBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(createComboBox());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateComboBoxWithEmptyModel() throws HydrationException
	{
		ComboBox<Object> comboBox = createComboBox();
		
		assertDehydrate("<select class=\"combobox\"></select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithId() throws HydrationException
	{
		ComboBox<Object> comboBox = createComboBox();
		getIdEncoder().setEncodedObject(comboBox, "1");
		
		assertDehydrate("<select class=\"combobox\" id=\"1\"></select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithName() throws HydrationException
	{
		ComboBox<Object> comboBox = createComboBox();
		getStateCodec().setEncodedBean(comboBox, "1");
		
		assertDehydrate("<select class=\"combobox\" name=\"1\"></select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithItem() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		
		assertDehydrate("<select class=\"combobox\">"
			+ "<option>[dummy][/dummy]</option>"
			+ "</select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithItems() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x", "y", "z");
		
		assertDehydrate("<select class=\"combobox\">"
			+ "<option>[dummy][/dummy]</option>"
			+ "<option>[dummy][/dummy]</option>"
			+ "<option>[dummy][/dummy]</option>"
			+ "</select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithValuedItem() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		getStateCodec().setEncodedPropertyValue(comboBox, ComboBox.SELECTED_INDEX_PROPERTY, 0, "0");
		
		assertDehydrate("<select class=\"combobox\">"
			+ "<option value=\"0\">[dummy][/dummy]</option>"
			+ "</select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithSelectedItem() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		comboBox.setSelectedIndex(0);
		
		assertDehydrate("<select class=\"combobox\">"
			+ "<option selected=\"selected\">[dummy][/dummy]</option>"
			+ "</select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithNullComponentRenderer() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		comboBox.setListCellRenderer(new NullComponentListCellRenderer<String>());
		
		assertDehydrate("<select class=\"combobox\">"
			+ "<option></option>"
			+ "</select>", comboBox);
	}
	
	@Test
	public void dehydrateComboBoxWithSelectionListener() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		comboBox.addSelectionListener(new DummySelectionListener());
		
		String expected = "<select class=\"combobox\" onchange=\"state\">"
			+ "<option>[dummy][/dummy]</option>"
			+ "</select>";
		
		assertDehydrate(expected, comboBox);
	}
	
	@Test
	public void dehydrateComboBoxCallsModelOnce() throws HydrationException
	{
		ListModel<String> model = ListModelSupport.mockModel(mockery, "x");
		ComboBox<String> comboBox = createComboBox(model);
		
		dehydrate(comboBox);
	}
	
	@Test
	public void rehydrateWithSelectedIndexParameter() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		getStateCodec().setEncodedBean(comboBox, "1");
		getStateCodec().setEncodedPropertyValue(comboBox, ComboBox.SELECTED_INDEX_PROPERTY, 0, "0");
		setEncodedState(createEncodedState("1", "0"));
		
		rehydrate(comboBox);
		
		assertEquals(0, comboBox.getSelectedIndex());
	}
	
	@Test
	public void rehydrateWithNoSelectedIndexParameter() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		comboBox.setSelectedIndex(0);
		getStateCodec().setEncodedBean(comboBox, "1");
		
		rehydrate(comboBox);
		
		assertEquals(0, comboBox.getSelectedIndex());
	}
	
	@Test
	public void rehydrateWithUnvaluedSelectedIndexParameter() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		comboBox.setSelectedIndex(0);
		getStateCodec().setEncodedBean(comboBox, "1");
		getStateCodec().setEncodedPropertyValue(comboBox, ComboBox.SELECTED_INDEX_PROPERTY, -1, null);
		setEncodedState(createEncodedState("1"));
		
		rehydrate(comboBox);
		
		assertEquals(-1, comboBox.getSelectedIndex());
	}
	
	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedSelectedIndexParameter() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		getStateCodec().setEncodedBean(comboBox, "1");
		setEncodedState(createEncodedState("1", "0", "0"));
		
		rehydrate(comboBox);
	}
	
	@Test
	public void rehydratePropertiesSkipsChildren() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_PROPERTIES)));
	}
	
	@Test
	public void rehydrateEventsSkipsChildren() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_EVENTS)));
	}
	
	@Test
	public void rehydrateParametersSkipsChildren() throws HydrationException
	{
		ComboBox<String> comboBox = createComboBox("x");
		
		assertEquals(SKIP_CHILDREN, createHydrator().visit(comboBox, getRehydrationContext(REHYDRATE_PARAMETERS)));
	}
	
	// private methods --------------------------------------------------------
	
	private static HtmlComboBoxHydrator<ComboBox<?>> createHydrator()
	{
		return new HtmlComboBoxHydrator<ComboBox<?>>();
	}

	// TODO: centralise
	private RehydrationContext getRehydrationContext(HydrationPhase phase)
	{
		RehydrationContext context = getRehydrationContext();
		context.set(HydrationPhase.class, phase);
		return context;
	}
	
	private static <T> ComboBox<T> createComboBox(T... items)
	{
		return createComboBox(new DefaultListModel<T>(items));
	}
	
	private static <T> ComboBox<T> createComboBox(ListModel<T> model)
	{
		ComboBox<T> listBox = new ComboBox<T>(model);
		listBox.setListCellRenderer(new DummyComponentListCellRenderer<T>());
		
		return listBox;
	}
}
