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

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedTreeHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Tree} component using an HTML {@code <ul/>} tag.
 * 
 * @author Mark Hobson
 * @see Tree
 * @param <T>
 *            the tree type this visitor can visit
 */
public class HtmlTreeHydrator<T extends Tree> extends PhasedTreeHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTreeHydrator()
	{
		super(asTreeVisitor(new PhasedBeanHydrator<T>()));
		
		setDelegate(DEHYDRATE, new HtmlTreeDehydrator<T>());
	}
}
