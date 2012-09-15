/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/PropertyBinder.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
