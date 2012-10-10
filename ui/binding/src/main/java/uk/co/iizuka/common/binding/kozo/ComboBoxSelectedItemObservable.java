/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/ComboBoxSelectedItemObservable.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
