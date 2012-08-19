/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/PropertyEditorMap.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyEditorMap.java 86783 2011-04-11 18:18:45Z mark@IIZUKA.CO.UK $
 */
public class PropertyEditorMap
{
	// TODO: extract replacement PropertyEditorManager interface and make default implementation
	
	// fields -----------------------------------------------------------------
	
	private final PropertyEditorMap parent;
	
	private final Map<Class<?>, Class<? extends PropertyEditor>> editors;
	
	// constructors -----------------------------------------------------------

	public PropertyEditorMap()
	{
		this(null);
	}
	
	public PropertyEditorMap(PropertyEditorMap parent)
	{
		this.parent = parent;
		
		editors = new HashMap<Class<?>, Class<? extends PropertyEditor>>();
	}

	// public methods ---------------------------------------------------------

	public PropertyEditorMap getParent()
	{
		return parent;
	}
	
	public PropertyEditor newPropertyEditor(Class<?> klass)
	{
		Class<? extends PropertyEditor> editorClass = getPropertyEditor(klass);
		
		if (editorClass == null)
		{
			return PropertyEditorManager.findEditor(klass);
		}
		
		try
		{
			return editorClass.newInstance();
		}
		catch (InstantiationException exception)
		{
			throw new BeanException("Cannot create property editor: " + editorClass.getName(), exception);
		}
		catch (IllegalAccessException exception)
		{
			throw new BeanException("Cannot create property editor: " + editorClass.getName(), exception);
		}
	}
	
	public Class<? extends PropertyEditor> getPropertyEditor(Class<?> klass)
	{
		Class<? extends PropertyEditor> editorClass = editors.get(klass);
		
		if (editorClass != null)
		{
			return editorClass;
		}
		
		if (parent != null)
		{
			return parent.getPropertyEditor(klass);
		}
		
		return null;
	}
	
	public void setPropertyEditor(Class<?> klass, Class<? extends PropertyEditor> editorClass)
	{
		if (editorClass != null)
		{
			editors.put(klass, editorClass);
		}
		else
		{
			editors.remove(klass);
		}
	}
}
