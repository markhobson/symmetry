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
package org.hobsoft.symmetry.ui.common;

import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;
import java.util.EventObject;

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.state.EventSetState;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BeanDehydrationUtils
{
	// constructors -----------------------------------------------------------
	
	private BeanDehydrationUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static String encodeState(DehydrationContext context) throws HydrationException
	{
		return encodeState(context, (PropertyState) null, null);
	}
	
	public static String encodeState(DehydrationContext context, Object bean, String propertyName, Object value)
		throws HydrationException
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(bean.getClass(), propertyName);
		
		return encodeState(context, new PropertyState(bean, descriptor, value), null);
	}

	public static String encodeState(DehydrationContext context, String eventSetName, EventObject eventObject)
		throws HydrationException
	{
		Object bean = eventObject.getSource();
		EventSetDescriptor descriptor = EventSets.getDescriptor(bean.getClass(), eventSetName);
		
		return encodeState(context, null, new EventSetState(descriptor, eventObject));
	}
	
	public static String encodeState(DehydrationContext context, String eventSetName, String listenerMethodName,
		EventObject eventObject) throws HydrationException
	{
		Object bean = eventObject.getSource();
		EventSetDescriptor descriptor = EventSets.getDescriptor(bean.getClass(), eventSetName,
			listenerMethodName);
		
		return encodeState(context, null, new EventSetState(descriptor, eventObject));
	}

	public static String encodeComponent(HydrationContext context, Object component) throws HydrationException
	{
		try
		{
			return context.get(StateCodec.class).encodeBean(component);
		}
		catch (StateException exception)
		{
			throw new HydrationException("Cannot encode component: " + component, exception);
		}
	}
	
	public static String encodePropertyValue(HydrationContext context, Object bean, String propertyName, Object value)
		throws HydrationException
	{
		PropertyDescriptor propertyDescriptor = Properties.getDescriptor(bean.getClass(), propertyName);
		PropertyState propertyState = new PropertyState(bean, propertyDescriptor, value);
		return encodePropertyValue(context, propertyState);
	}
	
	public static String encodePropertyValue(HydrationContext context, PropertyState propertyState)
		throws HydrationException
	{
		try
		{
			return context.get(StateCodec.class).encodePropertyValue(propertyState);
		}
		catch (StateException exception)
		{
			throw new HydrationException("Cannot encode property value: " + propertyState, exception);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static String encodeState(DehydrationContext context, PropertyState propertyState, EventSetState eventState)
		throws HydrationException
	{
		State state = new State(context.getState());
		
		if (propertyState != null)
		{
			state.addProperty(propertyState);
		}
		
		if (eventState != null)
		{
			state.addEvent(eventState);
		}
		
		return encodeState(context, state);
	}

	private static String encodeState(HydrationContext context, State state) throws HydrationException
	{
		// TODO: remove this when state no longer uses parameters
		// TODO: change state to extend HydrationContext?
		
		for (Class<?> parameterType : context.getParameterTypes())
		{
			Object parameterValue = context.get(parameterType);
			
			state.setParameter(parameterType.getName(), parameterValue);
		}
		
		try
		{
			return context.get(StateCodec.class).encodeState(state).getState();
		}
		catch (StateException exception)
		{
			throw new HydrationException(exception);
		}
	}
}
