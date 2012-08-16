/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DefaultListModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static com.google.common.base.Objects.equal;
import static uk.co.iizuka.kozo.ui.internal.Preconditions.checkNonNegative;
import static uk.co.iizuka.kozo.ui.model.Utilities.grow;
import static uk.co.iizuka.kozo.ui.model.Utilities.setSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultListModel.java 99067 2012-03-08 13:04:22Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public class DefaultListModel<T> implements ListModel<T>
{
	// TODO: insertItems
	
	// fields -----------------------------------------------------------------
	
	private final List<T> items;
	
	// constructors -----------------------------------------------------------
	
	// provided to avoid generic varargs warning
	public DefaultListModel()
	{
		this(Collections.<T>emptyList());
	}
	
	// provided to avoid generic varargs warning
	public DefaultListModel(T item)
	{
		this(Collections.singleton(item));
	}
	
	public DefaultListModel(T... items)
	{
		this(nullAsList(items));
	}
	
	public DefaultListModel(Collection<? extends T> items)
	{
		if (items == null)
		{
			items = Collections.emptySet();
		}
		
		this.items = new ArrayList<T>();
		
		addItems(items);
	}
	
	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getItem(int index)
	{
		return items.get(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount()
	{
		return items.size();
	}
	
	// public methods ---------------------------------------------------------
	
	public void insertItem(int index, T item)
	{
		checkNonNegative(index, "index");
		
		growItems(index);
		items.add(index, item);
	}
	
	public void addItem(T item)
	{
		insertItem(getItemCount(), item);
	}
	
	public void addItems(T... items)
	{
		addItems(Arrays.asList(items));
	}
	
	public void addItems(Collection<? extends T> items)
	{
		this.items.addAll(items);
	}
	
	public void removeItem(T item)
	{
		items.remove(item);
	}
	
	public void removeItems(T... items)
	{
		removeItems(Arrays.asList(items));
	}
	
	public void removeItems(Collection<? extends T> items)
	{
		this.items.removeAll(items);
	}
	
	public void setItem(int index, T item)
	{
		checkNonNegative(index, "index");
		
		growItems(index + 1);
		items.set(index, item);
	}
	
	public void setItemCount(int items)
	{
		checkNonNegative(items, "items");
		
		setSize(this.items, items);
	}
	
	public void clear()
	{
		items.clear();
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return items.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof ListModel<?>))
		{
			return false;
		}
		
		ListModel<?> model = (ListModel<?>) object;
		
		if (getItemCount() != model.getItemCount())
		{
			return false;
		}
		
		for (int index = 0; index < getItemCount(); index++)
		{
			if (!equal(getItem(index), model.getItem(index)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[items=" + items + "]";
	}
	
	// private methods --------------------------------------------------------
	
	private void growItems(int items)
	{
		grow(this.items, items);
	}
	
	private static <T> List<T> nullAsList(T... elements)
	{
		return (elements != null) ? Arrays.asList(elements) : null;
	}
}
