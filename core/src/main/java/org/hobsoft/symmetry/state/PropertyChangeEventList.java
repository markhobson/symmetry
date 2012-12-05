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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 */
class PropertyChangeEventList
{
	// fields -----------------------------------------------------------------
	
	private final Map<PropertyChangeEventKey, PropertyChangeEvent> eventMap;
	
	// constructors -----------------------------------------------------------
	
	public PropertyChangeEventList()
	{
		eventMap = new LinkedHashMap<PropertyChangeEventKey, PropertyChangeEvent>();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeEvent(PropertyChangeEvent event)
	{
		PropertyChangeEventKey key = new PropertyChangeEventKey(event);
		PropertyChangeEvent oldEvent = eventMap.get(key);
		if (oldEvent != null)
		{
			if (!ObjectUtils.equals(oldEvent.getNewValue(), event.getOldValue()))
			{
				throw new IllegalStateException("Inconsistent events for property " + event.getPropertyName() + ": "
					+ ObjectUtils.toString(oldEvent.getNewValue()) + " and "
					+ ObjectUtils.toString(event.getOldValue()));
			}
			
			Object oldValue = oldEvent.getOldValue();
			Object newValue = event.getNewValue();
			
			if (ObjectUtils.equals(oldValue, newValue))
			{
				eventMap.remove(key);
			}
			else
			{
				eventMap.put(key, new PropertyChangeEvent(event.getSource(), event.getPropertyName(), oldValue,
					newValue));
			}
		}
		else
		{
			eventMap.put(key, event);
		}
	}
	
	public PropertyChangeEvent[] getPropertyChangeEvents()
	{
		Collection<PropertyChangeEvent> values = eventMap.values();
		return values.toArray(new PropertyChangeEvent[values.size()]);
	}
}
