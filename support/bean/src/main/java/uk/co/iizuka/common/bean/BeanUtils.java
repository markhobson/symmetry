/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/BeanUtils.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import static uk.co.iizuka.common.bean.Utilities.checkNotNull;
import static uk.co.iizuka.common.bean.Utilities.nullEquals;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BeanUtils.java 88817 2011-06-08 17:09:49Z mark@IIZUKA.CO.UK $
 */
public final class BeanUtils
{
	// TODO: move property change utility methods to separate class, e.g. BoundBeans
	
	// constants --------------------------------------------------------------
	
	public static final String PROPERTY_CHANGE_EVENT = "propertyChange";
	
	// constructors -----------------------------------------------------------
	
	private BeanUtils()
	{
		throw new AssertionError();
	}
	
	// feature methods --------------------------------------------------------
	
	public static boolean equals(FeatureDescriptor descriptor1, FeatureDescriptor descriptor2)
	{
		// TODO: check attribute names
		return nullEquals(descriptor1.getName(), descriptor2.getName())
			&& nullEquals(descriptor1.getDisplayName(), descriptor2.getDisplayName())
			&& descriptor1.isExpert() == descriptor2.isExpert()
			&& descriptor1.isHidden() == descriptor2.isHidden()
			&& descriptor1.isPreferred() == descriptor2.isPreferred()
			&& nullEquals(descriptor1.getShortDescription(), descriptor2.getShortDescription());
	}
	
	// bean methods -----------------------------------------------------------
	
	public static BeanInfo getBeanInfo(Class<?> beanClass)
	{
		checkNotNull(beanClass, "beanClass");

		try
		{
			return Introspector.getBeanInfo(beanClass);
		}
		catch (IntrospectionException exception)
		{
			throw new BeanException(exception);
		}
	}
	
	public static boolean equals(BeanDescriptor descriptor1, BeanDescriptor descriptor2)
	{
		return equals((FeatureDescriptor) descriptor1, (FeatureDescriptor) descriptor2)
			&& nullEquals(descriptor1.getBeanClass(), descriptor2.getBeanClass())
			&& nullEquals(descriptor1.getCustomizerClass(), descriptor2.getCustomizerClass());
	}
	
	public static String toString(BeanDescriptor descriptor)
	{
		if (descriptor == null)
		{
			return String.valueOf((Object) null);
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(descriptor.getClass().getName());
		builder.append("[");
		builder.append("beanClass=").append(toClassName(descriptor.getBeanClass()));
		builder.append(",customizerClass=").append(toClassName(descriptor.getCustomizerClass()));
		// TODO: append FeatureDescriptor fields
		builder.append("]");
		
		return builder.toString();
	}
	
	// method methods ---------------------------------------------------------

	public static MethodDescriptor getListenerMethodDescriptor(EventSetDescriptor descriptor, String methodName)
	{
		checkNotNull(descriptor, "descriptor");
		checkNotNull(methodName, "methodName");
		
		for (MethodDescriptor methodDescriptor : descriptor.getListenerMethodDescriptors())
		{
			if (methodName.equals(methodDescriptor.getName()))
			{
				return methodDescriptor;
			}
		}
		
		throw new NoSuchMethodException(descriptor, methodName);
	}
	
	// property change methods ------------------------------------------------
	
	public static void addPropertyChangeListener(Object bean, String propertyName, PropertyChangeListener listener)
	{
		addPropertyChangeListener(bean, new PropertyChangeListenerProxy(propertyName, listener));
	}
	
	public static void addPropertyChangeListener(Object bean, PropertyChangeListener listener)
	{
		EventSets.addListener(bean, PROPERTY_CHANGE_EVENT, listener);
	}
	
	public static void removePropertyChangeListener(Object bean, String propertyName, PropertyChangeListener listener)
	{
		removePropertyChangeListener(bean, new PropertyChangeListenerProxy(propertyName, listener));
	}
	
	public static void removePropertyChangeListener(Object bean, PropertyChangeListener listener)
	{
		EventSets.removeListener(bean, PROPERTY_CHANGE_EVENT, listener);
	}
	
	public static PropertyChangeListener[] getPropertyChangeListeners(Object bean)
	{
		return (PropertyChangeListener[]) EventSets.getListeners(bean, PROPERTY_CHANGE_EVENT);
	}
	
	// TODO: getPropertyChangeListeners(Object bean, String propertyName)
	
	// property editor methods ------------------------------------------------
	
	public static PropertyEditor newPropertyEditor(PropertyDescriptor property)
	{
		Class<?> editorClass = property.getPropertyEditorClass();
		
		if (editorClass != null)
		{
			try
			{
				if (!PropertyEditor.class.isAssignableFrom(editorClass))
				{
					throw new BeanException("Property editor does not implement PropertyEditor");
				}
				
				return (PropertyEditor) editorClass.newInstance();
			}
			catch (InstantiationException exception)
			{
				throw new BeanException("Cannot create property editor", exception);
			}
			catch (IllegalAccessException exception)
			{
				throw new BeanException("Cannot create property editor", exception);
			}
		}
		
		return PropertyEditorManager.findEditor(property.getPropertyType());
	}
	
	public static Object getCustomPropertyEditor(PropertyEditor editor, Class<?> componentClass)
	{
		if (editor instanceof GenericPropertyEditor)
		{
			GenericPropertyEditor genericEditor = (GenericPropertyEditor) editor;
			
			if (genericEditor.supportsCustomEditor(componentClass))
			{
				return genericEditor.getCustomEditor(componentClass);
			}
		}
		
		if (componentClass != java.awt.Component.class)
		{
			return null;
		}
		
		return editor.supportsCustomEditor() ? editor.getCustomEditor() : null;
	}
	
	// private methods --------------------------------------------------------
	
	private static String toClassName(Class<?> klass)
	{
		return (klass != null) ? klass.getName() : null;
	}
}
