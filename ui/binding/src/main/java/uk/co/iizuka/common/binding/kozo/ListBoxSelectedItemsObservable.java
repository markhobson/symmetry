/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.iizuka.common.binding.kozo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.ObservableEvent;
import org.hobsoft.entangle.ObservableListener;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.model.ListModels;

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
