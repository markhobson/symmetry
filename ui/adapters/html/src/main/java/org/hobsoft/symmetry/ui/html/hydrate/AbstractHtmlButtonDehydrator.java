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

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Base hydrator for dehydrating a {@code Button} component to HTML.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractHtmlButtonDehydrator<T extends Button> extends NullHierarchicalComponentHydrator<T>
{
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getButtonCssClass(T button)
	{
		CssClassBuilder builder = new CssClassBuilder("button");
		
		if (!button.isEnabled())
		{
			builder.append("button-disabled");
		}
		
		if ((button instanceof Selectable) && ((Selectable) button).isSelected())
		{
			builder.append("button-selected");
		}
		
		return builder;
	}
}
