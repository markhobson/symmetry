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
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanProperty;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrators.form;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code CheckBox} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see CheckBox
 * @param <T>
 *            the check box type this visitor can visit
 */
public class HtmlCheckBoxHydrator<T extends CheckBox> extends PhasedBeanHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlCheckBoxHydrator()
	{
		setDelegate(DEHYDRATE_INITIALIZE, form(true, false));
		
		setDelegate(DEHYDRATE, new HtmlCheckBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, beanProperty(Selectable.SELECTED_PROPERTY, false));
	}
}
