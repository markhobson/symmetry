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

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Mark Hobson
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
