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
package org.hobsoft.symmetry.http.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the type of object created by this factory
 */
public class InstantiatingPoolableObjectFactory<T> extends BasePoolableObjectFactory<T>
{
	// fields -----------------------------------------------------------------
	
	private final Class<? extends T> klass;
	
	// constructors -----------------------------------------------------------
	
	public InstantiatingPoolableObjectFactory(Class<? extends T> klass)
	{
		this.klass = klass;
	}
	
	// PoolableObjectFactory methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T makeObject() throws Exception
	{
		return klass.newInstance();
	}
	
	// public methods ---------------------------------------------------------
	
	public Class<? extends T> getPoolableObjectClass()
	{
		return klass;
	}
}
