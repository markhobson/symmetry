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
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.test.hydrate.StubComponentRenderKit;
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
public class StubUiComponentRenderKit<T extends Component> extends StubComponentRenderKit<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "text/plain";
	
	// fields -----------------------------------------------------------------
	
	private final ComponentVisitor<HydrationContext, HydrationException> hydrator;

	// constructors -----------------------------------------------------------
	
	public StubUiComponentRenderKit(Class<T> componentType, String contentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		this(componentType, contentType, ComponentVisitors.adapt(componentType, subhydrator));
	}
	
	public StubUiComponentRenderKit(Class<T> componentType, String contentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		super(componentType, contentType);
		
		this.hydrator = hydrator;
	}
	
	// ComponentRenderKit methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		component.accept(hydrator, context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		component.accept(hydrator, context);
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> StubUiComponentRenderKit<T> get(Class<T> componentType,
		HierarchicalComponentHydrator<T> subhydrator)
	{
		return new StubUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, subhydrator);
	}
	
	public static <T extends Component> StubUiComponentRenderKit<T> get(Class<T> componentType,
		ComponentVisitor<HydrationContext, HydrationException> hydrator)
	{
		return new StubUiComponentRenderKit<T>(componentType, DEFAULT_CONTENT_TYPE, hydrator);
	}
}
