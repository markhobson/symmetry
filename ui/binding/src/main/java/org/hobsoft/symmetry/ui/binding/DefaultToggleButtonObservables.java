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
import org.hobsoft.symmetry.ui.binding.SymmetryObservables.SelectableObservables;
import org.hobsoft.symmetry.ui.binding.SymmetryObservables.ToggleButtonObservables;

/**
 * Default {@code ToggleButtonObservables} implementation.
 * 
 * @author Mark Hobson
 * @see ToggleButtonObservables
 */
class DefaultToggleButtonObservables extends DefaultButtonObservables implements ToggleButtonObservables
{
	// fields -----------------------------------------------------------------
	
	private final SelectableObservables selectable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultToggleButtonObservables(ToggleButton toggleButton)
	{
		super(toggleButton);
		
		selectable = new DefaultSelectableObservables(toggleButton);
	}
	
	// ToggleButtonObservables methods ----------------------------------------

	// TODO: implement when added
	
	// SelectableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> selected()
	{
		return selectable.selected();
	}
}