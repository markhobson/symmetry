/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultComboBoxObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.Observables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.ComboBoxObservables;
import uk.co.iizuka.kozo.ui.ComboBox;

/**
 * Default {@code ComboBoxObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultComboBoxObservables.java 98273 2012-02-06 12:44:23Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 * @see ComboBoxObservables
 */
class DefaultComboBoxObservables<T> extends DefaultComponentObservables implements ComboBoxObservables<T>
{
	// constructors -----------------------------------------------------------
	
	public DefaultComboBoxObservables(ComboBox<T> comboBox)
	{
		super(comboBox);
	}
	
	// ComboBoxObservables methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Integer> selectedIndex()
	{
		return Observables.bean(getComponent()).property(ComboBox.SELECTED_INDEX_PROPERTY, int.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<T> selectedItem()
	{
		return new ComboBoxSelectedItemObservable<T>(getComponent());
	}
	
	// DefaultComponentObservables methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComboBox<T> getComponent()
	{
		// guaranteed by constructor
		@SuppressWarnings("unchecked")
		ComboBox<T> comboBox = (ComboBox<T>) super.getComponent();
		
		return comboBox;
	}
}
