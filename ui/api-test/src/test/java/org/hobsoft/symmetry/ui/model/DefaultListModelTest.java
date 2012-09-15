/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/DefaultListModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.assertListModel;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.assertListModelEmpty;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItem;
import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code DefaultListModel}.
 * 
 * @author Mark Hobson
 * @see DefaultListModel
 */
public class DefaultListModelTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultListModel<Object> model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultListModel<Object>();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newDefaultListModel()
	{
		assertListModelEmpty(model);
	}
	
	@Test
	public void newDefaultListModelWithItem()
	{
		Object item = createItem();
		model = new DefaultListModel<Object>(item);
		
		assertListModel(model, item);
	}
	
	@Test
	public void newDefaultListModelWithNullItem()
	{
		model = new DefaultListModel<Object>((Object) null);
		
		assertListModel(model, (Object) null);
	}
	
	@Test
	public void newDefaultListModelWithItemsArray()
	{
		Object[] items = createItems();
		model = new DefaultListModel<Object>(items);
		
		assertListModel(model, items);
	}
	
	@Test
	public void newDefaultListModelWithNullItemsArray()
	{
		model = new DefaultListModel<Object>((Object[]) null);
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void newDefaultListModelWithItemsCollection()
	{
		Object[] items = createItems();
		model = new DefaultListModel<Object>(Arrays.asList(items));
		
		assertListModel(model, items);
	}
	
	@Test
	public void newDefaultListModelWithNullItemsCollection()
	{
		model = new DefaultListModel<Object>((Collection<Object>) null);
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void insertItemAtStart()
	{
		Object item1 = createItem();
		model.addItem(item1);
		
		Object item2 = createItem();
		model.insertItem(0, item2);
		
		assertListModel(model, item2, item1);
	}
	
	@Test
	public void insertItemAtMiddle()
	{
		Object item1 = createItem();
		Object item2 = createItem();
		model.addItems(item1, item2);
		
		Object item3 = createItem();
		model.insertItem(1, item3);
		
		assertListModel(model, item1, item3, item2);
	}
	
	@Test
	public void insertItemAtEnd()
	{
		Object item1 = createItem();
		model.addItems(item1);
		
		Object item2 = createItem();
		model.insertItem(1, item2);
		
		assertListModel(model, item1, item2);
	}
	
	@Test
	public void insertItemAfterEnd()
	{
		Object item1 = createItem();
		model.addItems(item1);
		
		Object item2 = createItem();
		model.insertItem(2, item2);
		
		assertListModel(model, item1, null, item2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertItemBeforeStart()
	{
		model.insertItem(-1, createItem());
	}
	
	@Test
	public void addItem()
	{
		Object item = createItem();
		model.addItem(item);
		
		assertListModel(model, item);
	}
	
	@Test
	public void addItemsArray()
	{
		Object[] items = createItems();
		model.addItems(items);
		
		assertListModel(model, items);
	}
	
	@Test
	public void addItemsCollection()
	{
		Object[] items = createItems();
		model.addItems(Arrays.asList(items));
		
		assertListModel(model, items);
	}
	
	@Test
	public void removeItem()
	{
		Object item = createItem();
		model.addItem(item);
		model.removeItem(item);
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void removeItemsArray()
	{
		Object[] items = createItems();
		model.addItems(items);
		model.removeItems(items);
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void removeItemsCollection()
	{
		Object[] items = createItems();
		model.addItems(items);
		model.removeItems(Arrays.asList(items));
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void setItem()
	{
		model.addItem(createItem());
		
		Object newItem = createItem();
		model.setItem(0, newItem);
		
		assertEquals(newItem, model.getItem(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setItemBeforeStart()
	{
		model.setItem(-1, createItem());
	}
	
	@Test
	public void setItemAtEnd()
	{
		Object item = createItem();
		model.setItem(0, item);
		
		assertListModel(model, item);
	}
	
	@Test
	public void setItemAfterEnd()
	{
		Object item = createItem();
		model.setItem(1, item);
		
		assertListModel(model, null, item);
	}
	
	@Test
	public void setItemCountGrowsItems()
	{
		Object item = createItem();
		model.addItem(item);
		
		model.setItemCount(2);
		
		assertListModel(model, item, null);
	}
	
	@Test
	public void setItemCountShrinksItems()
	{
		Object item1 = createItem();
		Object item2 = createItem();
		model.addItems(item1, item2);
		
		model.setItemCount(1);
		
		assertListModel(model, item1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setItemCountWithNegative()
	{
		model.setItemCount(-1);
	}
	
	@Test
	public void clear()
	{
		model.addItems(createItems());
		model.clear();
		
		assertListModelEmpty(model);
	}
	
	@Test
	public void hashCodeWhenEqual()
	{
		DefaultListModel<?> model1 = createModel();
		DefaultListModel<?> model2 = createModel();
		
		assertEquals(model1.hashCode(), model2.hashCode());
	}
	
	@Test
	public void equalsWhenEqual()
	{
		DefaultListModel<?> model1 = createModel();
		DefaultListModel<?> model2 = createModel();
		
		assertTrue(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentItemCount()
	{
		DefaultListModel<String> model1 = createModel();
		DefaultListModel<String> model2 = createModel();
		model2.setItemCount(2);
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentItem()
	{
		DefaultListModel<String> model1 = createModel();
		DefaultListModel<String> model2 = createModel();
		model2.setItem(0, "x");
		
		assertFalse(model1.equals(model2));
	}
	
	@Test
	public void equalsWithDifferentClass()
	{
		DefaultListModel<?> model = createModel();
		Object object = new Object();
		
		assertFalse(model.equals(object));
	}
	
	@Test
	public void equalsWithNull()
	{
		DefaultListModel<?> model = createModel();
		
		// workaround Checkstyle bug 2809655
		// CHECKSTYLE:OFF
		assertFalse(model.equals(null));
		// CHECKSTYLE:ON
	}
	
	@Test
	public void toStringTest()
	{
		String actual = new DefaultListModel<String>("a", "b", "c").toString();
		
		assertEquals("org.hobsoft.symmetry.ui.model.DefaultListModel[items=[a, b, c]]", actual);
	}
	
	// private methods --------------------------------------------------------
	
	private static DefaultListModel<String> createModel()
	{
		return new DefaultListModel<String>("a", "b", "c");
	}
}
