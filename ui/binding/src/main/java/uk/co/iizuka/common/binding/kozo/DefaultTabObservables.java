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

import org.hobsoft.symmetry.ui.Tab;

import uk.co.iizuka.common.binding.kozo.KozoObservables.TabObservables;

/**
 * Default {@code TabObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTabObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see TabObservables
 */
class DefaultTabObservables extends DefaultButtonObservables implements TabObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultTabObservables(Tab tab)
	{
		super(tab);
	}
	
	// TabObservables methods -------------------------------------------------
	
	// TODO: implement when added
}
