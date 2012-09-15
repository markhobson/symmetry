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
package org.hobsoft.symmetry.test.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the base component type this kit can render
 */
public class StubComponentRenderKit<T> implements ComponentRenderKit<T>
{
	// fields -----------------------------------------------------------------
	
	private final Class<T> componentType;
	
	private final String contentType;
	
	// constructors -----------------------------------------------------------
	
	public StubComponentRenderKit(Class<T> componentType, String contentType)
	{
		this.componentType = componentType;
		this.contentType = contentType;
	}

	// ComponentRenderKit methods ---------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<T> getComponentType()
	{
		return componentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dehydrate(T component, DehydrationContext context) throws HydrationException
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rehydrate(T component, RehydrationContext context) throws HydrationException
	{
		// no-op
	}
}
