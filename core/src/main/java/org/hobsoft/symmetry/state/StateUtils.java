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

import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class StateUtils
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(StateUtils.class.getName());
	
	// constructors -----------------------------------------------------------
	
	private StateUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void setProperties(Object bean, PropertyChangeListener listener)
	{
		setProperties(getProperties(bean), listener);
	}
	
	// private methods --------------------------------------------------------
	
	private static void setProperties(List<PropertyState> properties, PropertyChangeListener listener)
	{
		for (PropertyState property : properties)
		{
			PropertyChangeEvent event = toEvent(property);
			
			try
			{
				listener.propertyChange(event);
			}
			catch (Throwable throwable)
			{
				// TODO: handle better
				LOG.log(Level.WARNING, "Error when setting property: " + event.getPropertyName() + " to "
					+ event.getNewValue(), throwable);
			}
		}
	}
	
	private static List<PropertyState> getProperties(Object bean)
	{
		BeanInfo info = BeanUtils.getBeanInfo(bean.getClass());
		List<PropertyState> properties = new ArrayList<PropertyState>();
		
		for (PropertyDescriptor descriptor : info.getPropertyDescriptors())
		{
			if (descriptor.getReadMethod() != null)
			{
				Object value = Properties.get(bean, descriptor);
				
				properties.add(new PropertyState(bean, descriptor, value));
			}
		}
		
		return properties;
	}
	
	private static PropertyChangeEvent toEvent(PropertyState property)
	{
		return new PropertyChangeEvent(property.getBean(), property.getDescriptor().getName(), null,
			property.getValue());
	}
}
