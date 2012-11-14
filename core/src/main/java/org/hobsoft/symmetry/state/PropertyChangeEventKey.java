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
package org.hobsoft.symmetry.state;

import java.beans.PropertyChangeEvent;

/**
 * Key class that distinguishes {@code PropertyChangeEvent}s by their source and property name.
 * 
 * @author Mark Hobson
 * @see PropertyChangeEvent
 */
final class PropertyChangeEventKey
{
	// fields -----------------------------------------------------------------
	
	private final Object source;
	
	private final String propertyName;
	
	// constructors -----------------------------------------------------------

	public PropertyChangeEventKey(PropertyChangeEvent event)
	{
		if (event == null)
		{
			throw new NullPointerException("event cannot be null");
		}
		
		source = event.getSource();
		propertyName = event.getPropertyName();
	}
	
	// Object methods ---------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = source.hashCode();
		
		hashCode = (31 * hashCode) + hashCode(propertyName);
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof PropertyChangeEventKey))
		{
			return false;
		}
		
		PropertyChangeEventKey key = (PropertyChangeEventKey) object;
		
		return source.equals(key.source)
			&& equals(propertyName, key.propertyName);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[source=%s, propertyName=%s]", getClass().getSimpleName(), source, propertyName);
	}
	
	// private methods --------------------------------------------------------
	
	private static int hashCode(Object object)
	{
		return (object != null) ? object.hashCode() : 0;
	}
	
	private static boolean equals(Object x, Object y)
	{
		return (x == null) ? (y == null) : x.equals(y);
	}
}
