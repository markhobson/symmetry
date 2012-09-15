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

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <V>
 *            the visitor type
 */
public class PhasedHierarchicalComponentHydratorSupport<V extends HierarchicalComponentVisitor<?, HydrationContext,
	HydrationException>>
{
	// TODO: move default delegate functionality out of here without complicating phased visitor implementations
	
	// fields -----------------------------------------------------------------
	
	private final V defaultDelegate;
	
	private final Map<HydrationPhase, V> delegatesByPhase;

	// constructors -----------------------------------------------------------
	
	public PhasedHierarchicalComponentHydratorSupport(V defaultDelegate)
	{
		this.defaultDelegate = defaultDelegate;
		
		delegatesByPhase = new HashMap<HydrationPhase, V>();
	}
	
	// public methods ---------------------------------------------------------
	
	public V getDelegate(HydrationContext context)
	{
		HydrationPhase phase = context.get(HydrationPhase.class);

		return getDelegate(phase);
	}
	
	public V getDelegate(HydrationPhase phase)
	{
		V delegate = delegatesByPhase.get(phase);
		
		return (delegate != null) ? delegate : defaultDelegate;
	}
	
	public void setDelegate(HydrationPhase phase, V delegate)
	{
		if (phase == null)
		{
			throw new NullPointerException("phase cannot be null");
		}
		
		if (delegate == null)
		{
			delegatesByPhase.remove(phase);
		}
		else
		{
			delegatesByPhase.put(phase, delegate);
		}
	}
}
