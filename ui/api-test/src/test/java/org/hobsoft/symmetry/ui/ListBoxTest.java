/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ListBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.hobsoft.symmetry.ui.BeanAssert.assertIndexedProperty;
import static org.hobsoft.symmetry.ui.BeanAssert.assertProperty;
import static org.hobsoft.symmetry.ui.BeanAssert.assertReadableProperty;
import static org.hobsoft.symmetry.ui.BeanAssert.assertWritableProperty;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.assertListModelEmpty;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.model.DefaultListModel;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests {@code ListBox}.
 * 
 * @author Mark Hobson
 * @see ListBox
 */
public class ListBoxTest extends AbstractComponentTest<ListBox<?>>
{
	// TODO: extend ComboBoxTest
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void newListBox()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		assertListBox(-1, new int[0], SelectionMode.SINGLE, 5, listBox);
		assertListModelEmpty(listBox.getModel());
	}

	@Test
	public void newListBoxWithNullModel()
	{
		ListBox<Object> listBox = new ListBox<Object>((ListModel<Object>) null);
		
		assertListBox(-1, new int[0], SelectionMode.SINGLE, 5, listBox);
		assertListModelEmpty(listBox.getModel());
	}
	
	@Test
	public void setModel()
	{
		ListModel<Object> model = new DefaultListModel<Object>();
		ListBox<Object> listBox = new ListBox<Object>();
		
		listBox.setModel(model);
		
		assertSame(model, listBox.getModel());
	}
	
	@Test
	public void setModelWithNull()
	{
		ListBox<Object> listBox = new ListBox<Object>();
		
		listBox.setModel(null);
		
		assertEquals(new DefaultListModel<Object>(), listBox.getModel());
	}
	
	@Test
	public void setSelectedIndex()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndex(0);
		
		assertEquals(0, listBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexSetsSelectedIndexes()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndex(0);
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedIndexWithUnselected()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndex(-1);
		
		assertEquals(-1, listBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexWithUnselectedSetsSelectedIndexes()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndex(-1);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithNegative()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndex(-2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexWithOutOfBounds()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		
		listBox.setSelectedIndex(1);
	}
	
	@Test
	public void selectedIndexesBeanPropertyType()
	{
		assertProperty(ListBox.class, "selectedIndexes", int[].class);
	}
	
	@Test
	public void selectedIndexesBeanPropertyIsIndexed()
	{
		assertIndexedProperty(ListBox.class, "selectedIndexes");
	}
	
	@Test
	public void selectedIndexesBeanPropertyIsReadable()
	{
		assertReadableProperty(ListBox.class, "selectedIndexes");
	}
	
	@Test
	public void selectedIndexesBeanPropertyIsWritable()
	{
		assertWritableProperty(ListBox.class, "selectedIndexes");
	}
	
	@Test
	public void setSelectedIndexesWithNone()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndexes(new int[0]);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedIndexesWithNoneSetsSelectedIndex()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndexes(new int[0]);
		
		assertEquals(-1, listBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexesWithSingle()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndexes(new int[] {0});
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedIndexesWithSingleSetsSelectedIndex()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");

		listBox.setSelectedIndexes(new int[] {0});
		
		assertEquals(0, listBox.getSelectedIndex());
	}
	
	@Test
	public void setSelectedIndexesWithSingleWhenSelectionModeMultiple()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);

		listBox.setSelectedIndexes(new int[] {0});
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexesWithSingleUnselected()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndexes(new int[] {-1});
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexesWithSingleNegative()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndexes(new int[] {-2});
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void setSelectedIndexesWithSingleOutOfBounds()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		
		listBox.setSelectedIndexes(new int[] {1});
	}
	
	@Test
	public void setSelectedIndexesWithMultiple()
	{
		ListBox<Object> listBox = new ListBox<Object>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);

		listBox.setSelectedIndexes(new int[] {0, 1});
		
		assertArrayEquals(new int[] {0, 1}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedIndexesWithMultipleSetsSelectedIndex()
	{
		ListBox<Object> listBox = new ListBox<Object>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);

		listBox.setSelectedIndexes(new int[] {0, 1});
		
		assertEquals(0, listBox.getSelectedIndex());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedIndexesWithMultipleWhenSelectionModeSingle()
	{
		ListBox<Object> listBox = new ListBox<Object>("x", "y");

		listBox.setSelectedIndexes(new int[] {0, 1});
	}
	
	@Test
	public void setSelectedIndexesWithNull()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectedIndexes(null);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void selectedItemsBeanPropertyType()
	{
		assertProperty(ListBox.class, "selectedItems", Object[].class);
	}
	
	@Test
	public void selectedItemsBeanPropertyIsIndexed()
	{
		assertIndexedProperty(ListBox.class, "selectedItems");
	}
	
	@Test
	public void selectedItemsBeanPropertyIsReadable()
	{
		assertReadableProperty(ListBox.class, "selectedItems");
	}
	
	@Test
	public void selectedItemsBeanPropertyIsWritable()
	{
		assertWritableProperty(ListBox.class, "selectedItems");
	}
	
	@Test
	public void getSelectedItemsWhenNone()
	{
		ListBox<String> listBox = new ListBox<String>();

		assertArrayEquals(new Object[0], listBox.getSelectedItems());
	}
	
	@Test
	public void getSelectedItemsWhenSingle()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		listBox.setSelectedIndex(0);

		assertArrayEquals(new Object[] {"x"}, listBox.getSelectedItems());
	}
	
	@Test
	public void getSelectedItemsWhenMultiple()
	{
		ListBox<String> listBox = new ListBox<String>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedIndexes(new int[] {0, 1});

		assertArrayEquals(new Object[] {"x", "y"}, listBox.getSelectedItems());
	}
	
	@Test
	public void getSelectedItemsArrayWhenNone()
	{
		ListBox<String> listBox = new ListBox<String>();

		assertArrayEquals(new String[0], listBox.getSelectedItems(new String[0]));
	}
	
	@Test
	public void getSelectedItemsArrayWhenSingle()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		listBox.setSelectedIndex(0);

		assertArrayEquals(new String[] {"x"}, listBox.getSelectedItems(new String[0]));
	}
	
	@Test
	public void getSelectedItemsArrayWhenMultiple()
	{
		ListBox<String> listBox = new ListBox<String>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedIndexes(new int[] {0, 1});

		assertArrayEquals(new String[] {"x", "y"}, listBox.getSelectedItems(new String[0]));
	}
	
	@Test
	public void getSelectedItemsArrayReturnsItemTypeArray()
	{
		ListBox<String> listBox = new ListBox<String>();

		assertEquals(String[].class, listBox.getSelectedItems(new String[0]).getClass());
	}
	
	@Test
	public void setSelectedItemsWithEmptyArray()
	{
		ListBox<String> listBox = new ListBox<String>();
		
		listBox.setSelectedItems();
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedItemsWithSingletonArray()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems("x");
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedItemsWithSingletonArrayWhenNotFound()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems("y");
	}
	
	@Test
	public void setSelectedItemsWithSingletonArrayWhenNull()
	{
		ListBox<String> listBox = new ListBox<String>((String) null);
		
		listBox.setSelectedItems((String) null);
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedItemsWithSingletonArrayWhenNullAndNotFound()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems((String) null);
	}
	
	@Test
	public void setSelectedItemsWithArray()
	{
		ListBox<String> listBox = new ListBox<String>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		listBox.setSelectedItems("x", "y");
		
		assertArrayEquals(new int[] {0, 1}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedItemsWithNullArray()
	{
		ListBox<String> listBox = new ListBox<String>();
		
		listBox.setSelectedItems((String[]) null);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void getSelectedItemsAsListWhenNone()
	{
		ListBox<String> listBox = new ListBox<String>();

		assertEquals(Collections.emptyList(), listBox.getSelectedItemsAsList());
	}
	
	@Test
	public void getSelectedItemsAsListWhenSingle()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		listBox.setSelectedIndex(0);

		assertEquals(Collections.singletonList("x"), listBox.getSelectedItemsAsList());
	}
	
	@Test
	public void getSelectedItemsAsListWhenMultiple()
	{
		ListBox<String> listBox = new ListBox<String>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedIndexes(new int[] {0, 1});

		assertEquals(Arrays.asList("x", "y"), listBox.getSelectedItemsAsList());
	}
	
	@Test
	public void getSelectedItemsAsListReturnsLiveView()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		List<String> selectedItems = listBox.getSelectedItemsAsList();
		
		listBox.setSelectedIndex(0);
		
		assertEquals(Collections.singletonList("x"), selectedItems);
	}
	
	@Test
	public void setSelectedItemsWithEmptyList()
	{
		ListBox<String> listBox = new ListBox<String>();
		
		listBox.setSelectedItems(Collections.<String>emptyList());
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedItemsWithSingletonList()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems(Collections.singletonList("x"));
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedItemsWithSingletonListWhenNotFound()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems(Collections.singletonList("y"));
	}
	
	@Test
	public void setSelectedItemsWithSingletonListWhenNull()
	{
		ListBox<String> listBox = new ListBox<String>((String) null);
		
		listBox.setSelectedItems(Collections.<String>singletonList(null));
		
		assertArrayEquals(new int[] {0}, listBox.getSelectedIndexes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setSelectedItemsWithSingletonListWhenNullAndNotFound()
	{
		ListBox<String> listBox = new ListBox<String>("x");
		
		listBox.setSelectedItems(Collections.<String>singletonList(null));
	}
	
	@Test
	public void setSelectedItemsWithList()
	{
		ListBox<String> listBox = new ListBox<String>("x", "y");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		listBox.setSelectedItems(Arrays.asList("x", "y"));
		
		assertArrayEquals(new int[] {0, 1}, listBox.getSelectedIndexes());
	}
	
	@Test
	public void setSelectedItemsWithNullList()
	{
		ListBox<String> listBox = new ListBox<String>();
		
		listBox.setSelectedItems((List<String>) null);
		
		assertArrayEquals(new int[0], listBox.getSelectedIndexes());
	}
	
	@Test
	public void isIndexSelectedWhenUnselected()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		
		assertFalse(listBox.isIndexSelected(0));
	}
	
	@Test
	public void isIndexSelectedWhenSelected()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		listBox.setSelectedIndex(0);
		
		assertTrue(listBox.isIndexSelected(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isIndexSelectedWithNegative()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		listBox.isIndexSelected(-1);
	}
	
	// TODO: this will hit the model -- do we really want that?
	@Ignore
	@Test(expected = IllegalArgumentException.class)
	public void isIndexSelectedWithOutOfBounds()
	{
		ListBox<Object> listBox = new ListBox<Object>("x");
		listBox.isIndexSelected(1);
	}
	
	@Test
	public void selectionModeBeanPropertyType()
	{
		assertProperty(ListBox.class, "selectionMode", SelectionMode.class);
	}
	
	@Test
	public void selectionModeBeanPropertyIsReadable()
	{
		assertReadableProperty(ListBox.class, "selectionMode");
	}
	
	@Test
	public void selectionModeBeanPropertyIsWritable()
	{
		assertWritableProperty(ListBox.class, "selectionMode");
	}
	
	@Test
	public void setSelectionMode()
	{
		ListBox<Object> listBox = new ListBox<Object>();

		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		assertEquals(SelectionMode.MULTIPLE, listBox.getSelectionMode());
	}
	
	@Test(expected = NullPointerException.class)
	public void setSelectionModeWithNull()
	{
		ListBox<Object> listBox = new ListBox<Object>();
		
		listBox.setSelectionMode(null);
	}
	
	@Test
	public void visibleRowCountBeanPropertyType()
	{
		assertProperty(ListBox.class, "visibleRowCount", int.class);
	}
	
	@Test
	public void visibleRowCountBeanPropertyIsReadable()
	{
		assertReadableProperty(ListBox.class, "visibleRowCount");
	}
	
	@Test
	public void visibleRowCountBeanPropertyIsWritable()
	{
		assertWritableProperty(ListBox.class, "visibleRowCount");
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListBox<?> createComponent()
	{
		return new ListBox<Object>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<ListBox<?>> getComponentType()
	{
		return new Generic<ListBox<?>>() { /**/ };
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertListBox(int expectedSelectedIndex, int[] expectedSelectedIndexes,
		SelectionMode expectedSelectionMode, int expectedVisibleRowCount, ListBox<?> actual)
	{
		assertEquals("selectedIndex", expectedSelectedIndex, actual.getSelectedIndex());
		assertArrayEquals("selectedIndexes", expectedSelectedIndexes, actual.getSelectedIndexes());
		assertEquals("selectionMode", expectedSelectionMode, actual.getSelectionMode());
		assertEquals("visibleRowCount", expectedVisibleRowCount, actual.getVisibleRowCount());
	}
}
