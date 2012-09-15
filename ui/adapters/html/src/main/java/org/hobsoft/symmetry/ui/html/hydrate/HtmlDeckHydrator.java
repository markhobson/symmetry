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

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.selectedComponent;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Deck} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @see Deck
 * @param <T>
 *            the deck type this visitor can visit
 */
public class HtmlDeckHydrator<T extends Deck> extends AbstractHtmlContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlDeckHydrator()
	{
		setDelegate(DEHYDRATE, selectedComponent(new HtmlDeckDehydrator<T>()));
		
		// filter unselected children from parameter rehydration
		setDelegate(REHYDRATE_PARAMETERS,
			ComponentVisitors.<T, HydrationContext, HydrationException>selectedComponent());
	}
}
