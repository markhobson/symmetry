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

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.NullHierarchicalComponentVisitor;

/**
 * Convenience class that adapts a {@code NullHierarchicalComponentVisitor} to a {@code HierarchicalComponentHydrator}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class NullHierarchicalComponentHydrator<T extends Component>
	extends NullHierarchicalComponentVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// simple subtype
}
