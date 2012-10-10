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

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.ObservableEvent;
import org.hobsoft.entangle.ObservableListener;
import org.hobsoft.symmetry.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComboBoxSelectedItemObservable.java 96845 2011-12-16 15:36:22Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the combo box item type
 */
class ComboBoxSelectedItemObservable<T> implements Observable<T>
{
	// TODO: remove when ComboBox.SELECTED_ITEM property implemented

	// fields -----------------------------------------------------------------
	
	private final ComboBox<T> comboBox;
	
	// constructors -----------------------------------------------------------
	
	public ComboBoxSelectedItemObservable(ComboBox<T> comboBox)
	{
		this.comboBox = comboBox;
	}

	// Observable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addObservableListener(final ObservableListener<T> listener)
	{
		PropertyChangeListener selectedIndexListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				Observable<T> source = ComboBoxSelectedItemObservable.this;
				
				int oldSelectedIndex = (Integer) event.getOldValue();
				int newSelectedIndex = (Integer) event.getNewValue();
				
				T oldSelectedItem = getItem(comboBox, oldSelectedIndex);
				T newSelectedItem = getItem(comboBox, newSelectedIndex);
				
				listener.valueChanged(new ObservableEvent<T>(source, oldSelectedItem, newSelectedItem));
			}
		};
		
		comboBox.addPropertyChangeListener(ComboBox.SELECTED_INDEX_PROPERTY, selectedIndexListener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeObservableListener(ObservableListener<T> listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getValue()
	{
		return comboBox.getSelectedItem();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(T value)
	{
		comboBox.setSelectedItem(value);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> T getItem(ComboBox<T> comboBox, int index)
	{
		return (index != -1) ? comboBox.getModel().getItem(index) : null;
	}
}
