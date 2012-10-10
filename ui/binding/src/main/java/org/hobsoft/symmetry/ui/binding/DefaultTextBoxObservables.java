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
package org.hobsoft.symmetry.ui.binding;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.binding.KozoObservables.EnableableObservables;
import org.hobsoft.symmetry.ui.binding.KozoObservables.TextBoxObservables;

/**
 * Default {@code TextBoxObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTextBoxObservables.java 97533 2012-01-04 17:51:28Z mark@IIZUKA.CO.UK $
 * @see TextBoxObservables
 */
class DefaultTextBoxObservables extends DefaultComponentObservables implements TextBoxObservables
{
	// fields -----------------------------------------------------------------
	
	private final EnableableObservables enableable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTextBoxObservables(TextBox textBox)
	{
		super(textBox);
		
		enableable = new DefaultEnableableObservables(textBox);
	}
	
	// TextBoxObservables methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<String> text()
	{
		return Observables.bean(getComponent()).string(TextBox.TEXT_PROPERTY);
	}
	
	// EnableableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> enabled()
	{
		return enableable.enabled();
	}
}
