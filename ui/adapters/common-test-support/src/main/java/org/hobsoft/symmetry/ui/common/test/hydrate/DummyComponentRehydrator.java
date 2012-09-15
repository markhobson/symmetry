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
package org.hobsoft.symmetry.ui.common.test.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.test.DummyComponent;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DummyComponentRehydrator extends NullHierarchicalComponentHydrator<DummyComponent>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(DummyComponent component, HydrationContext context) throws HydrationException
	{
		component.setState(true);
		
		return super.visit(component, context);
	}
}
