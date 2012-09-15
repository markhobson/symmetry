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

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
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
public final class BeanRehydrationUtils
{
	// TODO: privatise
	
	// constructors -----------------------------------------------------------
	
	private BeanRehydrationUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void setProperties(Object bean, HydrationContext context)
	{
		List<PropertyState> properties = context.get(State.class).getProperties(bean);
		
		setProperties(properties);
	}
	
	public static void setProperties(Collection<PropertyState> properties)
	{
		for (PropertyState property : properties)
		{
			setProperty(property);
		}
	}
	
	public static void setProperty(PropertyState property)
	{
		Properties.set(property.getBean(), property.getDescriptor(), property.getValue());
	}
	
	public static void fireEvents(Object bean, HydrationContext context)
	{
		List<EventSetState> events = context.get(State.class).getEvents(bean);
		
		fireEvents(events);
	}
	
	public static void fireEvents(Collection<EventSetState> events)
	{
		for (EventSetState event : events)
		{
			fireEvent(event);
		}
	}
	
	public static void fireEvent(EventSetState event)
	{
		EventSets.fire(event.getBean(), event.getDescriptor(), event.getEventObject());
	}
	
	public static void setParameterProperty(Object bean, String propertyName, RehydrationContext context,
		boolean optional) throws StateException
	{
		PropertyState property = getParameterProperty(bean, propertyName, context, optional);
		
		if (property != null)
		{
			setProperty(property);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyState getParameterProperty(Object bean, String propertyName, RehydrationContext context,
		boolean optional) throws StateException
	{
		EncodedState encodedState = context.getEncodedState();
		StateCodec stateCodec = context.get(StateCodec.class);
		
		String parameterName = stateCodec.encodeBean(bean);
		
		if (optional && !encodedState.getParameterNames().contains(parameterName))
		{
			return null;
		}
		
		PropertyDescriptor propertyDescriptor = Properties.getDescriptor(bean, propertyName);
		List<Object> encodedPropertyValues = encodedState.getParameterValues(parameterName);
		List<Object> propertyValues = decodePropertyValues(stateCodec, bean, propertyDescriptor, encodedPropertyValues);
		
		return createPropertyState(bean, propertyDescriptor, propertyValues, stateCodec);
	}
	
	private static List<Object> decodePropertyValues(StateCodec stateCodec, Object bean,
		PropertyDescriptor propertyDescriptor, Collection<Object> encodedPropertyValues) throws StateException
	{
		List<Object> propertyValues = new ArrayList<Object>();
		
		for (Object encodedPropertyValue : encodedPropertyValues)
		{
			Object propertyValue;

			// TODO: need to revisit StateCodec API, only decoding String properties is nasty..
			if (encodedPropertyValue instanceof String)
			{
				propertyValue = stateCodec.decodePropertyValue(bean, propertyDescriptor, (String) encodedPropertyValue);
			}
			else
			{
				propertyValue = encodedPropertyValue;
			}
			
			propertyValues.add(propertyValue);
		}
		
		return propertyValues;
	}
	
	private static PropertyState createPropertyState(Object bean, PropertyDescriptor propertyDescriptor,
		Collection<Object> propertyValues, StateCodec stateCodec) throws StateException
	{
		Class<?> propertyType = propertyDescriptor.getPropertyType();
		
		Object propertyValue;
		
		if (!propertyType.isArray())
		{
			if (propertyValues.isEmpty())
			{
				// use codec to obtain null property value
				propertyValue = stateCodec.decodePropertyValue(bean, propertyDescriptor, null);
			}
			else if (propertyValues.size() == 1)
			{
				propertyValue = propertyValues.iterator().next();
			}
			else
			{
				throw new StateException(String.format("Bean property '%s' must be an array to decode multi-valued "
					+ "state: %s", propertyDescriptor.getName(), propertyValues));
			}
		}
		else
		{
			Class<?> componentType = propertyType.getComponentType();

			// aggregate property values into a single value
			propertyValue = ArrayUtils.merge(componentType, propertyValues);
		}

		return new PropertyState(bean, propertyDescriptor, propertyValue);
	}
}