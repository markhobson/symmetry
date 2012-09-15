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
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedHierarchicalComponentHydrator<T extends Component> extends DelegatingHierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedHierarchicalComponentHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullHierarchicalVisitor());
	}
	
	public PhasedHierarchicalComponentHydrator(HierarchicalComponentVisitor<? super T, HydrationContext,
		HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<HierarchicalComponentVisitor<? super T,
			HydrationContext, HydrationException>>(delegate);
	}

	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> getDelegate(T component,
		HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> getDelegate(
		HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase,
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
