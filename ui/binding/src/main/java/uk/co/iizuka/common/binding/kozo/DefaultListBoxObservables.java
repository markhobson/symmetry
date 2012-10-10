/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultListBoxObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import java.util.List;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.Observables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.ListBoxObservables;
import uk.co.iizuka.kozo.ui.ListBox;

/**
 * Default {@code ListBoxObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultListBoxObservables.java 98355 2012-02-08 12:04:55Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 * @see ListBoxObservables
 */
class DefaultListBoxObservables<T> extends DefaultComboBoxObservables<T> implements ListBoxObservables<T>
{
	// constructors -----------------------------------------------------------
	
	public DefaultListBoxObservables(ListBox<T> listBox)
	{
		super(listBox);
	}
	
	// ListBoxObservables methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<int[]> selectedIndexes()
	{
		return Observables.bean(getComponent()).property(ListBox.SELECTED_INDEXES_PROPERTY, int[].class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<List<T>> selectedItems()
	{
		return new ListBoxSelectedItemsObservable<T>(getComponent());
	}
	
	// DefaultComponentObservables methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListBox<T> getComponent()
	{
		return (ListBox<T>) super.getComponent();
	}
}
