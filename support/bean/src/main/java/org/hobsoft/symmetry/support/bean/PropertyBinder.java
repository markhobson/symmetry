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
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;

import static org.hobsoft.symmetry.support.bean.Utilities.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
class PropertyBinder implements PropertyChangeListener
{
	// fields -------------------------------------------------------------
	
	private final Object bean;
	
	private final PropertyDescriptor property;
	
	// constructors -------------------------------------------------------
	
	public PropertyBinder(Object bean, PropertyDescriptor property)
	{
		this.bean = checkNotNull(bean, "bean");
		this.property = checkNotNull(property, "property");
	}
	
	// PropertyChangeListener methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		// ignore erroneous event that PropertyChangeSupport fires when old and new values are null
		if (event.getOldValue() == null && event.getNewValue() == null)
		{
			return;
		}
		
		Properties.set(bean, property, event.getNewValue());
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = bean.hashCode();
		hashCode = (hashCode * 31) + property.hashCode();
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof PropertyBinder))
		{
			return false;
		}
		
		PropertyBinder binder = (PropertyBinder) object;
		
		return bean.equals(binder.getBean())
			&& property.equals(binder.getProperty());
	}
	
	// public methods -----------------------------------------------------
	
	public Object getBean()
	{
		return bean;
	}
	
	public PropertyDescriptor getProperty()
	{
		return property;
	}
}
