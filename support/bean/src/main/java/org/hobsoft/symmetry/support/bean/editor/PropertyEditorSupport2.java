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
package org.hobsoft.symmetry.support.bean.editor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyEditor;

/**
 * Provides support for implementing {@code PropertyEditor}s.
 * <p>
 * This class differs from {@code PropertyEditorSupport} in that {@code setValue} has the following improvements:
 * <ul>
 * <li>it does not fire property change events when the value has not changed
 * <li>it supplies old and new values when firing property change events
 * </ul>
 * 
 * @author Mark Hobson
 * @see PropertyEditor
 */
public abstract class PropertyEditorSupport2 implements PropertyEditor
{
	// TODO: should we extend PropertyEditorSupport for interoperability?
	
	// fields -----------------------------------------------------------------

	private Object value;
	
	private PropertyChangeSupport support;
	
	// constructors -----------------------------------------------------------
	
	public PropertyEditorSupport2()
	{
		support = new PropertyChangeSupport(this);
	}

	public PropertyEditorSupport2(Object source)
	{
		support = new PropertyChangeSupport(source);
	}

	// PropertyEditor methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		Object oldValue = this.value;
		this.value = value;
		
		// unlike PropertyEditorSupport:
		// - only fire event when value has actually changed
		// - supply old and new values
		support.firePropertyChange(null, oldValue, this.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPaintable()
	{
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintValue(Graphics gfx, Rectangle box)
	{
		// no-op
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJavaInitializationString()
	{
		return "???";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		return String.valueOf(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		throw new IllegalArgumentException(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getCustomEditor()
	{
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor()
	{
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
}
