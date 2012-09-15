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

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedTableHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Table} component using an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @see Table
 * @param <T>
 *            the table type this visitor can visit
 */
public class HtmlTableHydrator<T extends Table> extends PhasedTableHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTableHydrator()
	{
		// TODO: remove unnecessary actual type argument when javac can cope
		super(asTableVisitor(ComponentHydrators.<T>skipChildrenOnRehydrate(new PhasedBeanHydrator<T>())));
		
		setDelegate(DEHYDRATE, new HtmlTableDehydrator<T>());
	}
}
