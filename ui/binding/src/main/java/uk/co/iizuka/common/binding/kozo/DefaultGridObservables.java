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

import org.hobsoft.symmetry.ui.Grid;

import uk.co.iizuka.common.binding.kozo.KozoObservables.GridObservables;

/**
 * Default {@code GridObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultGridObservables.java 99170 2012-03-09 17:09:31Z mark@IIZUKA.CO.UK $
 * @see GridObservables
 */
class DefaultGridObservables extends DefaultContainerObservables implements GridObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultGridObservables(Grid grid)
	{
		super(grid);
	}
	
	// GridObservables methods ------------------------------------------------
	
	// TODO: implement when added
}
