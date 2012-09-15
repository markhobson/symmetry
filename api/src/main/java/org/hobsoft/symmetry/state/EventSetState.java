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

import java.beans.EventSetDescriptor;
import java.util.EventObject;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class EventSetState
{
	// TODO: shouldn't we have an explicit bean since the event object source is not necessarily the bean?
	
	// fields -----------------------------------------------------------------
	
	private final EventSetDescriptor eventSetDescriptor;
	
	private final EventObject eventObject;
	
	// constructors -----------------------------------------------------------
	
	public EventSetState(EventSetDescriptor eventSetDescriptor, EventObject eventObject)
	{
		if (eventSetDescriptor == null)
		{
			throw new NullPointerException("eventSetDescriptor cannot be null");
		}
		
		if (eventObject == null)
		{
			throw new NullPointerException("eventObject cannot be null");
		}
		
		this.eventSetDescriptor = eventSetDescriptor;
		this.eventObject = eventObject;
	}
	
	// public methods ---------------------------------------------------------
	
	public Object getBean()
	{
		return getEventObject().getSource();
	}
	
	public EventSetDescriptor getDescriptor()
	{
		return eventSetDescriptor;
	}
	
	public EventObject getEventObject()
	{
		return eventObject;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = eventSetDescriptor.hashCode();
		hashCode = (hashCode * 31) + eventObject.hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof EventSetState))
		{
			return false;
		}
		
		EventSetState event = (EventSetState) object;
		
		return eventSetDescriptor.equals(event.getDescriptor())
			&& eventObject.equals(event.getEventObject());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(getDescriptor().getName());
		builder.append('=');
		builder.append(getEventObject());
		
		return builder.toString();
	}
}
