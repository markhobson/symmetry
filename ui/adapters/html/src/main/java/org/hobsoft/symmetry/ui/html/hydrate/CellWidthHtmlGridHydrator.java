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

import org.hobsoft.symmetry.ui.Grid;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Grid} component using an HTML {@code <table/>} tag without
 * using {@code min-width} on {@code <col/>}.
 * <p>
 * This works around issues in Chrome 18, Safari 5 and Opera 11.
 * 
 * @author Mark Hobson
 * @see <a href="http://code.google.com/p/chromium/issues/detail?id=120886">Chrome issue 120886</a>
 * @param <T>
 *            the grid type this visitor can visit
 */
public class CellWidthHtmlGridHydrator<T extends Grid> extends HtmlGridHydrator<T>
{
	// TODO: remove once Chrome issue 120886 fixed and in stable
	
	// constructors -----------------------------------------------------------
	
	public CellWidthHtmlGridHydrator()
	{
		setDelegate(DEHYDRATE, new CellWidthHtmlGridDehydrator<T>());
	}
}
