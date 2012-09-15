/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ComboBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.hobsoft.symmetry.ui.BeanAssert.assertProperty;
import static org.hobsoft.symmetry.ui.BeanAssert.assertReadableProperty;
import static org.hobsoft.symmetry.ui.BeanAssert.assertWritableProperty;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.assertListModel;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.assertListModelEmpty;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItem;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItems;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItemsAsList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.event.SelectionEvent;
import org.hobsoft.symmetry.ui.event.SelectionListener;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.model.DefaultListModel;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.model.ListModelSupport;
import org.hobsoft.symmetry.ui.view.LabelListCellRenderer;
import org.jmock.Expectations;
import org.junit.Test;

/**
 * Tests {@code ComboBox}.
 * 
 * @author Mark Hobson
 * @see ComboBox
 */
public class ComboBoxTest extends AbstractComponentTest<ComboBox<?>>
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newComboBox()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>();
		
		assertListModelEmpty(comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithItem()
	{
		Object item = createItem();
		ComboBox<Object> comboBox = new ComboBox<Object>(item);
		
		assertListModel(comboBox.getModel(), item);
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithNullItem()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>((Object) null);
		
		assertListModel(comboBox.getModel(), (Object) null);
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithItemsArray()
	{
		Object[] items = createItems();
		ComboBox<Object> comboBox = new ComboBox<Object>(items);
		
		assertListModel(comboBox.getModel(), items);
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithNullItemsArray()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>((Object[]) null);
		
		assertListModelEmpty(comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithItemsCollection()
	{
		List<Object> items = createItemsAsList();
		ComboBox<Object> comboBox = new ComboBox<Object>(items);
		
		assertListModel(items, comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithNullItemsCollection()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>((Collection<Object>) null);
		
		assertListModelEmpty(comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithModel()
	{
		DefaultListModel<Object> model = new DefaultListModel<Object>();
		ComboBox<Object> comboBox = new ComboBox<Object>(model);
		
		assertSame("model", model, comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void newComboBoxWithNullModel()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>((ListModel<Object>) null);
		
		assertListModelEmpty(comboBox.getModel());
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
		assertTrue("listCellRenderer", comboBox.getListCellRenderer() instanceof LabelListCellRenderer);
	}
	
	@Test
	public void onSelect()
	{
		ComboBox<?> comboBox = new ComboBox<String>("a");
		
		@SuppressWarnings("unchecked")
		final Closure<SelectionEvent> closure = getMockery().mock(Closure.class);
		final SelectionEvent event = new SelectionEvent(comboBox, 0);
		
		getMockery().checking(new Expectations() { {
			oneOf(closure).apply(event);
		} });
		
		comboBox.onSelect(closure);
		comboBox.fireItemSelectedEvent(0);
	}
	
	@Test
	public void setModel()
	{
		ListModel<Object> model = new DefaultListModel<Object>();
		ComboBox<Object> comboBox = new ComboBox<Object>();
		
		comboBox.setModel(model);
		
		assertSame(model, comboBox.getModel());
	}
	
	@Test
	public void setModelWithNull()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>();
		
		comboBox.setModel(null);
		
		assertEquals(new DefaultListModel<Object>(), comboBox.getModel());
	}
	
	@Test
	public void selectedIndexBeanPropertyType()
	{
		assertProperty(ListBox.class, "selectedIndex", int.class);
	}
	
	@Test
	public void selectedIndexBeanPropertyIsReadable()
	{
		assertReadableProperty(ListBox.class, "selectedIndex");
	}
	
	@Test
	public void selectedIndexBeanPropertyIsWritable()
	{
		assertWritableProperty(ListBox.class, "selectedIndex");
	}
	
	@Test
	public void setSelectedIndex()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedIndex(1);
		assertEquals("selectedIndex", 1, comboBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexWithUnselected()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedIndex(-1);
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexFiresSelectionEvent()
	{
		final ComboBox<String> comboBox = new ComboBox<String>("a");
		
		final SelectionListener listener = getMockery().mock(SelectionListener.class);
		
		getMockery().checking(new Expectations() { {
			oneOf(listener).itemSelected(new SelectionEvent(comboBox, 0));
		} });
		
		comboBox.addSelectionListener(listener);
		
		comboBox.setSelectedIndex(0);
	}
	
	@Test
	public void setSelectedIndexWithUnselectedDoesNotFireSelectionEvent()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a");
		comboBox.addSelectionListener(getMockery().mock(SelectionListener.class));
		
		comboBox.setSelectedIndex(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithNegative()
	{
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setSelectedIndex(-2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithOutOfBounds()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a");
		comboBox.setSelectedIndex(1);
	}
	
	@Test
	public void getSelectedItem()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedIndex(1);
		assertEquals("selectedItem", "b", comboBox.getSelectedItem());
	}
	
	@Test
	public void getSelectedItemWhenUnselected()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		assertNull("selectedItem", comboBox.getSelectedItem());
	}
	
	@Test
	public void setSelectedItem()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedItem("b");
		assertEquals("selectedIndex", 1, comboBox.getSelectedIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedItemWhenNotFound()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedItem("x");
	}
	
	@Test
	public void setSelectedItemWhenNull()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", null, "c");
		comboBox.setSelectedItem(null);
		assertEquals("selectedIndex", 1, comboBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedItemWhenNullAndNotFound()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		comboBox.setSelectedIndex(0);
		comboBox.setSelectedItem(null);
		assertEquals("selectedIndex", -1, comboBox.getSelectedIndex());
	}
	
	@Test
	public void getItemIndex()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		assertEquals("itemIndex", 1, comboBox.getItemIndex("b"));
	}
	
	@Test
	public void getItemIndexWhenNotFound()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		assertEquals("itemIndex", -1, comboBox.getItemIndex("x"));
	}

	@Test
	public void getItemIndexWhenNull()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", null, "c");
		assertEquals("itemIndex", 1, comboBox.getItemIndex(null));
	}
	
	@Test
	public void getItemIndexWhenNullAndNotFound()
	{
		ComboBox<String> comboBox = new ComboBox<String>("a", "b", "c");
		assertEquals("itemIndex", -1, comboBox.getItemIndex(null));
	}

	@Test
	public void getItemIndexCallsModelOnce()
	{
		ListModel<String> model = ListModelSupport.mockModel(getMockery(), "a", "b", "c");
		ComboBox<String> comboBox = new ComboBox<String>(model);
		
		comboBox.getItemIndex("b");
	}

	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComboBox<?> createComponent()
	{
		return new ComboBox<Object>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<ComboBox<?>> getComponentType()
	{
		return new Generic<ComboBox<?>>() { /**/ };
	}
}
