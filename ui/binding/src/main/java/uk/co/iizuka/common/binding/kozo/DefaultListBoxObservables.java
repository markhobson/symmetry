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

import java.util.List;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;
import org.hobsoft.symmetry.ui.ListBox;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ListBoxObservables;

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
