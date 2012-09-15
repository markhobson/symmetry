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
package org.hobsoft.symmetry.xml;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * 
 * 
 * @author Mark Hobson
 */
class DefaultIdEncoder implements IdEncoder
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, String> idsByObject;
	
	private int nextId;
	
	// constructors -----------------------------------------------------------
	
	public DefaultIdEncoder()
	{
		idsByObject = new HashMap<Object, String>();
		nextId = 0;
	}

	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode(Object object) throws EncoderException
	{
		String id;
		
		if (idsByObject.containsKey(object))
		{
			id = idsByObject.get(object);
		}
		else
		{
			id = nextId();
			idsByObject.put(object, id);
		}
		
		return id;
	}
	
	// private methods --------------------------------------------------------
	
	private String nextId()
	{
		return String.valueOf(nextId++);
	}
}
