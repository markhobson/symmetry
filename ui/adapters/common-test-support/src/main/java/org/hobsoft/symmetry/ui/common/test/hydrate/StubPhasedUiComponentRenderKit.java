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

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.HydrationPhase;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the base component type this kit can render
 */
public class StubPhasedUiComponentRenderKit<T extends Component> extends StubUiComponentRenderKit<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "text/plain";
	
	private static final HydrationPhase[] DEHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.DEHYDRATE_INITIALIZE,
		HydrationPhase.DEHYDRATE,
	};
	
	private static final HydrationPhase[] REHYDRATION_PHASES = new HydrationPhase[] {
		HydrationPhase.REHYDRATE_PROPERTIES,
		HydrationPhase.REHYDRATE_PARAMETERS,
		HydrationPhase.REHYDRATE_EVENTS,
	};
	
	// constructors -----------------------------------------------------------
	
	public StubPhasedUiComponentRenderKit(Class<T> componentType, String contentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		this(componentType, contentType, ComponentVisitors.adapt(componentType, subhydrator));
	}
	
	public StubPhasedUiComponentRenderKit(Class<T> componentType, String contentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		super(componentType, contentType, hydrator);
	}
	
	// ComponentRenderKit methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		for (HydrationPhase phase : DEHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			super.dehydrate(component, context);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		for (HydrationPhase phase : REHYDRATION_PHASES)
		{
			context.set(HydrationPhase.class, phase);
			
			super.rehydrate(component, context);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> StubPhasedUiComponentRenderKit<T> get(Class<T> componentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		return new StubPhasedUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, subhydrator);
	}
	
	public static <T extends Component> StubPhasedUiComponentRenderKit<T> get(Class<T> componentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		return new StubPhasedUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, hydrator);
	}
}
