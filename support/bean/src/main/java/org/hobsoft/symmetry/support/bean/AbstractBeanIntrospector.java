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
package org.hobsoft.symmetry.support.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractBeanIntrospector implements BeanIntrospector
{
	// fields -----------------------------------------------------------------
	
	private final Map<String, Object> featureValuesByName;
	
	// constructors -----------------------------------------------------------
	
	public AbstractBeanIntrospector()
	{
		featureValuesByName = new HashMap<String, Object>();
	}
	
	// BeanIntrospector methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getFeature(String name)
	{
		if (!hasFeature(name))
		{
			// TODO: throw better exception
			throw new IllegalArgumentException("Unsupported feature: " + name);
		}
		
		return featureValuesByName.get(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFeature(String name, Object value)
	{
		if (!hasFeature(name))
		{
			// TODO: throw better exception
			throw new IllegalArgumentException("Unsupported feature: " + name);
		}
		
		featureValuesByName.put(name, value);
	}
}
