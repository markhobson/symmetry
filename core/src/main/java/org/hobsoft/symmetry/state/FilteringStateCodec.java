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

import java.util.EventListener;
import java.util.EventObject;

import org.hobsoft.symmetry.support.bean.EventSets;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class FilteringStateCodec extends DelegatingStateCodec
{
	// constructors -----------------------------------------------------------
	
	public FilteringStateCodec(StateCodec delegate)
	{
		super(delegate);
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final EncodedState encodeState(State state) throws StateException
	{
		// filter properties
		
		for (PropertyState property : state.getProperties())
		{
			EncodedState encodedState = filterProperty(state, property);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		// filter events
		
		for (EventSetState event : state.getEvents())
		{
			EncodedState encodedState = filterEvent(state, event);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		return super.encodeState(state);
	}
	
	// protected methods ------------------------------------------------------
	
	protected EncodedState filterProperty(State state, PropertyState property) throws StateException
	{
		return null;
	}
	
	protected EncodedState filterEvent(State state, EventSetState event) throws StateException
	{
		EventObject eventObject = event.getEventObject();
		Object bean = eventObject.getSource();
		EventListener[] listeners = EventSets.getListeners(bean, event.getDescriptor());
		
		for (EventListener listener : listeners)
		{
			EncodedState encodedState = filterEvent(state, listener, eventObject);
			
			if (encodedState != null)
			{
				return encodedState;
			}
		}
		
		return null;
	}
	
	protected EncodedState filterEvent(State state, EventListener listener, EventObject event) throws StateException
	{
		return null;
	}
}
