/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/ListModels.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkElementIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class ListModels
{
	// TODO: implement removeItem(ListModel<T> delegate, T item) - complicated by potentially multiple matching items
	// to filter whilst maintaining live view on delegate
	
	// constants --------------------------------------------------------------
	
	private static final ListModel<Boolean> BOOLEAN = new BooleanListModel();

	// constructors -----------------------------------------------------------
	
	private ListModels()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static ListModel<Boolean> forBoolean()
	{
		return BOOLEAN;
	}

	public static <E extends Enum<E>> ListModel<E> forEnum(Class<E> enumType)
	{
		return new EnumListModel<E>(enumType);
	}
	
	public static <T> ListModel<T> cacheItemCount(ListModel<T> delegate)
	{
		return new DelegatingListModel<T>(delegate)
		{
			private int itemCount = -1;
			
			@Override
			public int getItemCount()
			{
				if (itemCount == -1)
				{
					itemCount = super.getItemCount();
				}
				
				return itemCount;
			}
		};
	}
	
	public static <T> ListModel<T> insertItem(final ListModel<T> delegate, int index, final T item)
	{
		checkNonNegative(index, "index");
		
		final int insertIndex = index;
		final int oldItemCount = delegate.getItemCount();
		final int newItemCount = Math.max(oldItemCount + 1, index + 1);
		
		return new ListModel<T>()
		{
			@Override
			public T getItem(int index)
			{
				if (index == insertIndex)
				{
					return item;
				}
				
				if (index > insertIndex)
				{
					index--;
				}
				
				return (index < oldItemCount) ? delegate.getItem(index) : null;
			}
			
			@Override
			public int getItemCount()
			{
				return newItemCount;
			}
		};
	}
	
	public static <T> ListModel<T> addItem(final ListModel<T> delegate, final T item)
	{
		return insertItem(delegate, delegate.getItemCount(), item);
	}
	
	public static <T> ListModel<T> removeItem(final ListModel<T> delegate, int index)
	{
		checkElementIndex(index, delegate.getItemCount(), "index");
		
		final int removeIndex = index;
		
		return new ListModel<T>()
		{
			@Override
			public T getItem(int index)
			{
				if (index >= removeIndex)
				{
					index++;
				}
				
				return delegate.getItem(index);
			}
			
			@Override
			public int getItemCount()
			{
				return delegate.getItemCount() - 1;
			}
		};
	}
	
	public static <T> List<T> getItems(ListModel<? extends T> model, int... indexes)
	{
		List<T> items = new ArrayList<T>();
		
		for (int index : indexes)
		{
			T item = model.getItem(index);
			
			items.add(item);
		}
		
		return items;
	}
	
	public static <T> int getItemIndex(ListModel<T> model, T item)
	{
		int itemCount = model.getItemCount();
		
		for (int index = 0; index < itemCount; index++)
		{
			if (equal(item, model.getItem(index)))
			{
				return index;
			}
		}
		
		return -1;
	}
	
	public static <T> int[] getItemIndexes(ListModel<T> model, T... items)
	{
		return getItemIndexes(model, Arrays.asList(items));
	}
	
	public static <T> int[] getItemIndexes(ListModel<T> model, Collection<? extends T> items)
	{
		int[] indexes = new int[items.size()];
		Arrays.fill(indexes, -1);
		
		int itemCount = model.getItemCount();
		List<T> searchItems = new ArrayList<T>(items);
		
		for (int index = 0; index < itemCount; index++)
		{
			T item = model.getItem(index);
			int searchIndex = searchItems.indexOf(item);
			
			if (searchIndex != -1)
			{
				indexes[searchIndex] = index;
			}
		}
		
		return indexes;
	}
}
