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

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedListBoxHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asListBoxVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code ComboBox} component using an HTML {@code <select/>} tag.
 * 
 * @author Mark Hobson
 * @see ComboBox
 * @param <T>
 *            the combo box type this visitor can visit
 */
public class HtmlComboBoxHydrator<T extends ComboBox<?>> extends PhasedListBoxHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlComboBoxHydrator()
	{
		// TODO: remove unnecessary actual type argument when javac can cope
		super(asListBoxVisitor(ComponentHydrators.<T>skipChildrenOnRehydrate(new PhasedBeanHydrator<T>())));
		
		setDelegate(DEHYDRATE_INITIALIZE, asListBoxVisitor(HtmlComponentHydrators.<T>form(true, false)));
		
		setDelegate(DEHYDRATE, new HtmlComboBoxDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, asListBoxVisitor(ComponentHydrators.<T>beanProperty(
			ComboBox.SELECTED_INDEX_PROPERTY, true)));
	}
}
