/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/BeanEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.DefaultPropertyEditorErrorHandler;
import org.hobsoft.symmetry.support.bean.GenericPropertyEditor;
import org.hobsoft.symmetry.support.bean.PropertyEditorErrorHandler;
import org.hobsoft.symmetry.support.bean.PropertyEditorMap;
import org.hobsoft.symmetry.ui.editor.AbstractPropertyEditor;
import org.hobsoft.symmetry.ui.editor.BooleanPropertyEditor;
import org.hobsoft.symmetry.ui.editor.DatePropertyEditor;
import org.hobsoft.symmetry.ui.editor.StringPropertyEditor;
import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public class BeanEditor<T> extends Grid
{
	// constants --------------------------------------------------------------
	
	public static final String BEAN_PROPERTY = "bean";
	
	public static final String ERROR_HANDLER_PROPERTY = "errorHandler";
	
	private static final BeanEditorStrategy DEFAULT_STRATEGY = new DefaultBeanEditorStrategy();
	
	private static final PropertyEditorMap DEFAULT_EDITOR_MAP = new PropertyEditorMap();
	
	static
	{
		DEFAULT_EDITOR_MAP.setPropertyEditor(boolean.class, BooleanPropertyEditor.class);
		DEFAULT_EDITOR_MAP.setPropertyEditor(Boolean.class, BooleanPropertyEditor.class);
		DEFAULT_EDITOR_MAP.setPropertyEditor(String.class, StringPropertyEditor.class);
		DEFAULT_EDITOR_MAP.setPropertyEditor(Date.class, DatePropertyEditor.class);
	}

	// types ------------------------------------------------------------------
	
	private static class BasicPropertyEditor extends AbstractPropertyEditor
	{
		// constructors -----------------------------------------------------------
		
		public BasicPropertyEditor(PropertyEditor editor)
		{
			super(buildComponent(editor));
		}
		
		// GenericPropertyEditorSupport methods -------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean supportsCustomEditor(Class<?> componentClass)
		{
			return (componentClass == Component.class) ? true : super.supportsCustomEditor(componentClass);
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getCustomEditor(Class<?> componentClass)
		{
			return (componentClass == Component.class) ? getComponent() : super.getCustomEditor(componentClass);
		}
		
		// private methods --------------------------------------------------------
		
		private static Component buildComponent(PropertyEditor editor)
		{
			String[] tags = editor.getTags();
			
			if (tags == null)
			{
				return new TextBox(editor.getAsText());
			}
			
			// TODO: return drop-down box of tags
			return new TextBox(editor.getAsText());
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private PropertyEditorMap editorMap;
	
	private Class<T> beanClass;
	
	private T bean;
	
	private BeanEditorStrategy strategy;
	
	private Map<PropertyDescriptor, GenericPropertyEditor> propertyMap;
	
	private PropertyEditorErrorHandler errorHandler;
	
	// constructors -----------------------------------------------------------
	
	public BeanEditor(Class<T> beanClass)
	{
		this(beanClass, null);
	}
	
	public BeanEditor(Class<T> beanClass, PropertyEditorMap editorMap)
	{
		this(BeanUtils.getBeanInfo(beanClass), editorMap);
	}
	
	public BeanEditor(BeanInfo beanInfo)
	{
		this(beanInfo, null);
	}
	
	public BeanEditor(BeanInfo beanInfo, PropertyEditorMap editorMap)
	{
		super(2);
		strategy = DEFAULT_STRATEGY;
		this.editorMap = (editorMap != null) ? editorMap : DEFAULT_EDITOR_MAP;
		this.beanClass = (Class<T>) beanInfo.getBeanDescriptor().getBeanClass();
		propertyMap = new HashMap<PropertyDescriptor, GenericPropertyEditor>();
		
		PropertyDescriptor[] properties = getOrderedPropertyDescriptors(beanInfo);
		
		for (int i = 0; i < properties.length; i++)
		{
			addProperty(properties[i]);
		}
		
		setBean(null);
		setErrorHandler(new DefaultPropertyEditorErrorHandler());
		
		// TODO: remove need for this
		for (PropertyDescriptor property : getProperties())
		{
			GenericPropertyEditor editor = propertyMap.get(property);
			Component component = (Component) editor.getCustomEditor(Component.class);
			component.setTransient(true);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public Class<T> getBeanClass()
	{
		return beanClass;
	}
	
	public T getBean()
	{
		return bean;
	}
	
	public void setBean(T bean)
	{
		T oldBean = this.bean;
		this.bean = bean;
		
		for (PropertyDescriptor property : getProperties())
		{
			Object value = strategy.getPropertyValue(bean, property);
			propertyMap.get(property).setValue(value);
		}
		
		firePropertyChange(BEAN_PROPERTY, oldBean, bean);
	}
	
	public Closure<T> bean()
	{
		return new Closure<T>()
		{
			@Override
			public void apply(T bean)
			{
				setBean(bean);
			}
		};
	}
	
	public BeanEditorStrategy getBeanEditorStrategy()
	{
		return strategy;
	}
	
	public void setBeanEditorStrategy(BeanEditorStrategy strategy)
	{
		this.strategy = strategy;
	}
	
	public Set<PropertyDescriptor> getProperties()
	{
		return Collections.unmodifiableSet(propertyMap.keySet());
	}
	
	public GenericPropertyEditor getPropertyEditor(String propertyName)
	{
		return getPropertyEditor(getPropertyDescriptor(propertyName));
	}
	
	public GenericPropertyEditor getPropertyEditor(PropertyDescriptor property)
	{
		return propertyMap.get(property);
	}
	
	public Object getPropertyValue(String propertyName)
	{
		return getPropertyValue(getPropertyDescriptor(propertyName));
	}
	
	public Object getPropertyValue(PropertyDescriptor property)
	{
		return getPropertyEditor(property).getValue();
	}
	
	public PropertyEditorErrorHandler getErrorHandler()
	{
		return errorHandler;
	}
	
	public void setErrorHandler(PropertyEditorErrorHandler errorHandler)
	{
		PropertyEditorErrorHandler oldErrorHandler = this.errorHandler;
		this.errorHandler = errorHandler;
		firePropertyChange(ERROR_HANDLER_PROPERTY, oldErrorHandler, errorHandler);
	}
	
	public boolean commit()
	{
		// TODO: allow bean creation and property setting to be pluggable
		// i.e. support different strategies - the current one for JavaBeans with default constructors and setters, and
		// then custom ones for stricter beans with no default constructor and read-only properties
		
		T newBean = getBean();
		
		if (newBean == null)
		{
			newBean = strategy.newBean(this);
		}
		
		boolean success = true;
		
		for (PropertyDescriptor property : propertyMap.keySet())
		{
			PropertyEditor editor = propertyMap.get(property);
			try
			{
				strategy.setPropertyValue(bean, property, editor.getValue());
			}
			catch (Throwable exception)
			{
				success = false;
				
				if (errorHandler != null)
				{
					errorHandler.handleError(editor, exception);
				}
			}
		}
		
		if (success)
		{
			setBean(newBean);
		}
		
		return success;
	}
	
	public void rollback()
	{
		setBean(bean);
	}
	
	public static PropertyEditorMap getDefaultPropertyEditorMap()
	{
		return DEFAULT_EDITOR_MAP;
	}
	
	// private methods --------------------------------------------------------
	
	private static PropertyDescriptor[] getOrderedPropertyDescriptors(BeanInfo info)
	{
		PropertyDescriptor[] properties = info.getPropertyDescriptors();
		BeanDescriptor descriptor = info.getBeanDescriptor();
		
		String order = (descriptor == null) ? null : (String) descriptor.getValue("order");
		
		if (order == null)
		{
			return properties;
		}
		
		Map<String, PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();
		
		for (PropertyDescriptor property : properties)
		{
			propertyMap.put(property.getName(), property);
		}
		
		String[] propertyNames = order.split(",");
		
		properties = new PropertyDescriptor[propertyNames.length];
		
		for (int i = 0; i < properties.length; i++)
		{
			properties[i] = propertyMap.get(propertyNames[i]);
		}
		
		return properties;
	}
	
	private void addProperty(PropertyDescriptor property)
	{
		if (property.isHidden())
		{
			return;
		}
		
		PropertyEditor editor = editorMap.newPropertyEditor(property.getPropertyType());
		
		if (editor == null)
		{
			throw new RuntimeException("Cannot find property editor for property: " + property.getName());
		}
		
		GenericPropertyEditor genericEditor;
		
		if ((editor instanceof GenericPropertyEditor)
			&& ((GenericPropertyEditor) editor).supportsCustomEditor(Component.class))
		{
			genericEditor = (GenericPropertyEditor) editor;
		}
		else
		{
			genericEditor = new BasicPropertyEditor(editor);
		}
		
		propertyMap.put(property, genericEditor);
		
		add(new Label(property.getDisplayName()));
		add((Component) genericEditor.getCustomEditor(Component.class));
	}
	
	private PropertyDescriptor getPropertyDescriptor(String propertyName)
	{
		for (PropertyDescriptor property : getProperties())
		{
			if (propertyName.equals(property.getName()))
			{
				return property;
			}
		}
		
		return null;
	}
	
	// serialization methods --------------------------------------------------
	
	private Class<T> getPropertyType(String propertyName)
	{
		return propertyName.equals(BEAN_PROPERTY) ? beanClass : null;
	}
}
