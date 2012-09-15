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

import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.MultiLineLabel;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code MultiLineLabel} component using an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @see MultiLineLabel
 * @param <T>
 *            the multi-line label type this visitor can visit
 */
public class HtmlMultiLineLabelHydrator<T extends MultiLineLabel> extends HtmlLabelHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlMultiLineLabelHydrator()
	{
		setDelegate(HydrationPhase.DEHYDRATE, new HtmlMultiLineLabelDehydrator<T>());
	}
}
