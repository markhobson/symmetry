/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/ListBoxSelectedItemsObservable.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.ObservableEvent;
import uk.co.iizuka.common.binding.ObservableListener;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.model.ListModels;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ListBoxSelectedItemsObservable.java 98355 2012-02-08 12:04:55Z mark@IIZUKA.CO.UK $
 * @param <T> 
 *            the list box item type
 */
class ListBoxSelectedItemsObservable<T> implements Observable<List<T>>
{
	// TODO: implement observable list when available
	// TODO: remove when ListBox.SELECTED_ITEMS property implemented

	// fields -----------------------------------------------------------------
	
	private final ListBox<T> listBox;
	
	// constructors -----------------------------------------------------------
	
	public ListBoxSelectedItemsObservable(ListBox<T> listBox)
	{
		this.listBox = listBox;
	}
	
	// Observable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addObservableListener(final ObservableListener<List<T>> listener)
	{
		PropertyChangeListener selectedIndexesListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				Observable<List<T>> source = ListBoxSelectedItemsObservable.this;
				
				int[] oldSelectedIndexes = (int[]) event.getOldValue();
				int[] newSelectedIndexes = (int[]) event.getNewValue();
				
				List<T> oldSelectedItems = ListModels.getItems(listBox.getModel(), oldSelectedIndexes);
				List<T> newSelectedItems = ListModels.getItems(listBox.getModel(), newSelectedIndexes);
				
				listener.valueChanged(new ObservableEvent<List<T>>(source, oldSelectedItems, newSelectedItems));
			}
		};
		
		listBox.addPropertyChangeListener(ListBox.SELECTED_INDEXES_PROPERTY, selectedIndexesListener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeObservableListener(ObservableListener<List<T>> listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> getValue()
	{
		return listBox.getSelectedItemsAsList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(List<T> value)
	{
		listBox.setSelectedItems(value);
	}
}
