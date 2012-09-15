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

import java.util.Collection;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.CompositeComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

/**
 * Convenience class that adapts a {@code CompositeComponentVisitor} to a {@code ComponentHydrator}.
 * 
 * @author Mark Hobson
 */
public class CompositeComponentHydrator
	extends CompositeComponentVisitor<HydrationContext, HydrationException>
	implements ComponentHydrator
{
	// simple subtype
	
	// constructors -----------------------------------------------------------
	
	public CompositeComponentHydrator()
	{
		super();
	}
	
	// TODO: should be Collection<? extends HierarchicalComponentVisitor<? extends Component, HydrationContext,
	// HydrationException>> when CCONTAINER-57 fixed
	public CompositeComponentHydrator(Collection<HierarchicalComponentVisitor<? extends Component,
		HydrationContext, HydrationException>> delegates)
	{
		super(delegates);
	}
}
