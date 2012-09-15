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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;

/**
 * A {@code PropertyEditor} implementation that delegates to another.
 * 
 * @author Mark Hobson
 * @see PropertyEditor
 */
abstract class DelegatingPropertyEditor implements PropertyEditor
{
	// fields -----------------------------------------------------------------
	
	private final PropertyEditor delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingPropertyEditor(PropertyEditor delegate)
	{
		this.delegate = delegate;
	}
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		delegate.setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return delegate.getValue();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPaintable()
	{
		return delegate.isPaintable();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintValue(Graphics gfx, Rectangle box)
	{
		delegate.paintValue(gfx, box);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJavaInitializationString()
	{
		return delegate.getJavaInitializationString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		return delegate.getAsText();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		delegate.setAsText(text);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return delegate.getTags();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getCustomEditor()
	{
		return delegate.getCustomEditor();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor()
	{
		return delegate.supportsCustomEditor();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		delegate.addPropertyChangeListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		delegate.removePropertyChangeListener(listener);
	}
	
	// protected methods ------------------------------------------------------
	
	protected PropertyEditor getDelegate()
	{
		return delegate;
	}
}
