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

import org.hobsoft.symmetry.ui.Tab;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Tab} component using an HTML {@code <div/>} tag.
 * 
 * @author Mark Hobson
 * @see Tab
 * @param <T>
 *            the tab type this visitor can visit
 */
public class HtmlTabHydrator<T extends Tab> extends AbstractHtmlButtonHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTabHydrator()
	{
		setDelegate(DEHYDRATE, new HtmlTabDehydrator<T>());
	}
}
