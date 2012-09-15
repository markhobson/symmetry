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
package org.hobsoft.symmetry.support.codec.test;

/**
 * Base for test objects used to test codecs.
 * 
 * @author	Mark Hobson
 * @version	$Id: AbstractTestObject.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public abstract class AbstractTestObject
{
	// fields -----------------------------------------------------------------
	
	private final String id;

	// constructors -----------------------------------------------------------
	
	public AbstractTestObject(String id)
	{
		this.id = id;
	}

	// public methods ---------------------------------------------------------
	
	public String getId()
	{
		return id;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object == null || !object.getClass().equals(getClass()))
		{
			return false;
		}
		
		AbstractTestObject testObject = (AbstractTestObject) object;
		
		return getId().equals(testObject.getId());
	}
}
