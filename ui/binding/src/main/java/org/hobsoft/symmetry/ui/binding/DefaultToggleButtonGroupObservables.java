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
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.binding.KozoObservables.ToggleButtonGroupObservables;

/**
 * Default {@code ToggleButtonGroupObservables} implementation.
 * 
 * @author Mark Hobson
 * @see ToggleButtonGroupObservables
 */
class DefaultToggleButtonGroupObservables extends DefaultBoxObservables implements ToggleButtonGroupObservables
{
	// constructors -----------------------------------------------------------

	public DefaultToggleButtonGroupObservables(ToggleButtonGroup toggleButtonGroup)
	{
		super(toggleButtonGroup);
	}
	
	// ToggleButtonGroupObservables methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<ToggleButton> selectedButton()
	{
		return new ToggleButtonGroupSelectedButtonObservable(getComponent());
	}

	// DefaultComponentObservables methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToggleButtonGroup getComponent()
	{
		return (ToggleButtonGroup) super.getComponent();
	}
}
