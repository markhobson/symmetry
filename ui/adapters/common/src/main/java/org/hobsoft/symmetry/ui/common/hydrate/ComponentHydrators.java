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
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitors;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_EVENTS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PROPERTIES;
import static org.hobsoft.symmetry.ui.common.BeanRehydrationUtils.fireEvents;
import static org.hobsoft.symmetry.ui.common.BeanRehydrationUtils.setParameterProperty;
import static org.hobsoft.symmetry.ui.common.BeanRehydrationUtils.setProperties;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class ComponentHydrators
{
	// constants --------------------------------------------------------------
	
	private static final HydrationPhase[] REHYDRATION_PHASES = new HydrationPhase[] {
		REHYDRATE_PROPERTIES, REHYDRATE_EVENTS, REHYDRATE_PARAMETERS
	};

	private static final HierarchicalComponentHydrator<Component> BEAN_PROPERTIES_REHYDRATOR =
		new NullHierarchicalComponentHydrator<Component>()
		{
			@Override
			public Visit visit(Component component, HydrationContext context) throws HydrationException
			{
				setProperties(component, context);
				
				return super.visit(component, context);
			}
		};

	private static final HierarchicalComponentHydrator<Component> BEAN_EVENTS_REHYDRATOR =
		new NullHierarchicalComponentHydrator<Component>()
		{
			@Override
			public Visit visit(Component component, HydrationContext context) throws HydrationException
			{
				fireEvents(component, context);
				
				return super.visit(component, context);
			}
		};

	// constructors -----------------------------------------------------------
	
	private ComponentHydrators()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> PhasedHierarchicalComponentHydrator<T> skipChildrenOnRehydrate(
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		return skipChildren(delegate, REHYDRATION_PHASES);
	}
	
	public static <T extends Component> PhasedHierarchicalComponentHydrator<T> skipChildren(
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate,
		HydrationPhase... phases)
	{
		// TODO: how best to generalise phased decoration?
		
		PhasedHierarchicalComponentHydrator<T> hydrator = new PhasedHierarchicalComponentHydrator<T>(delegate);
		
		for (HydrationPhase phase : phases)
		{
			hydrator.setDelegate(phase, ComponentVisitors.skipChildren(hydrator.getDelegate(phase)));
		}
		
		return hydrator;
	}
	
	public static <T extends Component> PhasedHierarchicalComponentHydrator<T> phase(HydrationPhase phase,
		HierarchicalComponentVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		PhasedHierarchicalComponentHydrator<T> hydrator = new PhasedHierarchicalComponentHydrator<T>();
		hydrator.setDelegate(phase, delegate);
		return hydrator;
	}
	
	// TODO: can we replace T with Component?
	public static <T extends Component> HierarchicalComponentHydrator<T> beanProperties()
	{
		// safe
		@SuppressWarnings("unchecked")
		HierarchicalComponentHydrator<T> rehydrator = (HierarchicalComponentHydrator<T>) BEAN_PROPERTIES_REHYDRATOR;
		
		return rehydrator;
	}
	
	// TODO: can we replace T with Component?
	public static <T extends Component> HierarchicalComponentHydrator<T> beanProperty(final String propertyName,
		final boolean optional)
	{
		return new NullHierarchicalComponentHydrator<T>()
		{
			@Override
			public Visit visit(T component, HydrationContext context) throws HydrationException
			{
				try
				{
					setParameterProperty(component, propertyName, (RehydrationContext) context, optional);
				}
				catch (StateException exception)
				{
					throw new HydrationException(exception);
				}
				
				return SKIP_CHILDREN;
			}
		};
	}
	
	// TODO: can we replace T with Component?
	public static <T extends Component> HierarchicalComponentHydrator<T> beanEvents()
	{
		// safe
		@SuppressWarnings("unchecked")
		HierarchicalComponentHydrator<T> rehydrator = (HierarchicalComponentHydrator<T>) BEAN_EVENTS_REHYDRATOR;
		
		return rehydrator;
	}
}
