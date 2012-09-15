/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/model/ListModelSupport.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.ui.model.ListModel;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class ListModelSupport
{
	// constructors -----------------------------------------------------------
	
	private ListModelSupport()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Object createItem()
	{
		return new Object();
	}
	
	public static Object[] createItems()
	{
		return new Object[] {createItem(), createItem(), createItem()};
	}
	
	public static List<Object> createItemsAsList()
	{
		return Arrays.asList(createItems());
	}
	
	public static <T> ListModel<T> mockModel(Mockery mockery, final T... items)
	{
		// cannot mock generic types
		@SuppressWarnings("unchecked")
		final ListModel<T> model = mockery.mock(ListModel.class);
		
		mockery.checking(new Expectations() { {
			atMost(1).of(model).getItemCount(); will(returnValue(items.length));
			
			for (int i = 0; i < items.length; i++)
			{
				atMost(1).of(model).getItem(i); will(returnValue(items[i]));
			}
		} });
		
		return model;
	}
	
	public static void assertListModelEmpty(ListModel<?> actual)
	{
		assertListModel(Collections.emptyList(), actual);
	}
	
	public static void assertListModel(ListModel<?> actual, Object... expectedItems)
	{
		assertListModel(Arrays.asList(expectedItems), actual);
	}
	
	public static void assertListModel(List<Object> expectedItems, ListModel<?> actual)
	{
		assertNotNull("Model was null", actual);
		assertEquals("Items", expectedItems, toItemList(actual));
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> List<T> toItemList(ListModel<T> model)
	{
		List<T> items = new ArrayList<T>();
		
		for (int index = 0; index < model.getItemCount(); index++)
		{
			items.add(model.getItem(index));
		}
		
		return items;
	}
}
