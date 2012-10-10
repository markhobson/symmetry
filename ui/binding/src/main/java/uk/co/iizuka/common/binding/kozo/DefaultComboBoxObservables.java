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

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;
import org.hobsoft.symmetry.ui.ComboBox;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ComboBoxObservables;

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
