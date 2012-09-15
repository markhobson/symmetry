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
package org.hobsoft.symmetry.ui.common.hydrate;

import org.hobsoft.symmetry.ui.Component;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanEvents;
import static org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators.beanProperties;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedBeanHydrator<T extends Component> extends PhasedHierarchicalComponentHydrator<T>
{
	// constructors -----------------------------------------------------------

	public PhasedBeanHydrator()
	{
		setDelegate(REHYDRATE_PROPERTIES, beanProperties());
		setDelegate(REHYDRATE_EVENTS, beanEvents());
	}
}
