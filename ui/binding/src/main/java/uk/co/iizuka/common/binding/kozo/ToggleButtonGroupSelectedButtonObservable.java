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
import org.hobsoft.entangle.ObservableListener;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ToggleButtonGroupSelectedButtonObservable.java 97896 2012-01-18 18:02:06Z mark@IIZUKA.CO.UK $
 */
class ToggleButtonGroupSelectedButtonObservable implements Observable<ToggleButton>
{
	// TODO: remove when ToggleButtonGroup.SELECTED_BUTTON property implemented
	
	// fields -----------------------------------------------------------------

	private final ToggleButtonGroup group;
	
	// constructors -----------------------------------------------------------

	public ToggleButtonGroupSelectedButtonObservable(ToggleButtonGroup group)
	{
		this.group = group;
	}

	// Observable methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addObservableListener(ObservableListener<ToggleButton> listener)
	{
		// TODO: implement
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeObservableListener(ObservableListener<ToggleButton> listener)
	{
		// TODO: implement
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToggleButton getValue()
	{
		return group.getSelectedButton();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(ToggleButton value)
	{
		group.setSelectedButton(value);
	}
}
