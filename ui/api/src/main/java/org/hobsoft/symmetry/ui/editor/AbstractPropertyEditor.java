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
package org.hobsoft.symmetry.ui.editor;

import org.hobsoft.symmetry.support.bean.GenericPropertyEditorSupport;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractPropertyEditor extends GenericPropertyEditorSupport
{
	// fields -----------------------------------------------------------------
	
	private Component component;
	
	// constructors -----------------------------------------------------------
	
	public AbstractPropertyEditor()
	{
		this(null);
	}
	
	public AbstractPropertyEditor(Component component)
	{
		setComponent(component);
	}
	
	// GenericPropertyEditorSupport methods -----------------------------------
	
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
		return (componentClass == Component.class) ? component : super.getCustomEditor(componentClass);
	}
	
	// protected methods ------------------------------------------------------
	
	protected Component getComponent()
	{
		return component;
	}
	
	protected void setComponent(Component component)
	{
		this.component = component;
	}
}
