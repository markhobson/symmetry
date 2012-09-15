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
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.DelegatingTreeVisitor;
import org.hobsoft.symmetry.ui.traversal.TreeVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class PhasedTreeHydrator<T extends Tree>
	extends DelegatingTreeVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final PhasedHierarchicalComponentHydratorSupport<TreeVisitor<? super T, HydrationContext,
		HydrationException>> support;

	// constructors -----------------------------------------------------------
	
	public PhasedTreeHydrator()
	{
		this(ComponentVisitors.<T, HydrationContext, HydrationException>nullTreeVisitor());
	}
	
	public PhasedTreeHydrator(TreeVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(null);
		
		support = new PhasedHierarchicalComponentHydratorSupport<TreeVisitor<? super T, HydrationContext,
			HydrationException>>(delegate);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeVisitor<? super T, HydrationContext, HydrationException> getDelegate(T tree, HydrationContext context)
	{
		return support.getDelegate(context);
	}
	
	// public methods ---------------------------------------------------------
	
	public TreeVisitor<? super T, HydrationContext, HydrationException> getDelegate(HydrationPhase phase)
	{
		return support.getDelegate(phase);
	}
	
	public void setDelegate(HydrationPhase phase, TreeVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		support.setDelegate(phase, delegate);
	}
}
