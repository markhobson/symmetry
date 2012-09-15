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
package org.hobsoft.symmetry.xml.test;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.codec.EncoderException;
import org.hobsoft.symmetry.xml.IdEncoder;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StubIdEncoder implements IdEncoder
{
	// fields -----------------------------------------------------------------
	
	private final Map<Object, String> idsByObject;
	
	// constructors -----------------------------------------------------------
	
	public StubIdEncoder()
	{
		idsByObject = new HashMap<Object, String>();
	}
	
	// Encoder methods --------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encode(Object object) throws EncoderException
	{
		return idsByObject.get(object);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setEncodedObject(Object object, String encodedObject)
	{
		idsByObject.put(object, encodedObject);
	}
}