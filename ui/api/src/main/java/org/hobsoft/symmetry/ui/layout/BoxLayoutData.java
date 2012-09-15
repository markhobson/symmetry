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
package org.hobsoft.symmetry.ui.layout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.common.base.Objects;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class BoxLayoutData
{
	// constants --------------------------------------------------------------
	
	public static final String LENGTH_PROPERTY = "length";
	
	private static final Length DEFAULT_LENGTH = null;

	// fields -----------------------------------------------------------------
	
	private final PropertyChangeSupport support;
	
	private Length length;
	
	// constructors -----------------------------------------------------------
	
	public BoxLayoutData()
	{
		support = new PropertyChangeSupport(this);
		
		setLength(DEFAULT_LENGTH);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(propertyName, listener);
	}
	
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(propertyName, listener);
	}
	
	public Length getLength()
	{
		return length;
	}
	
	public void setLength(Length length)
	{
		Length oldLength = this.length;
		this.length = length;
		support.firePropertyChange(LENGTH_PROPERTY, oldLength, this.length);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof BoxLayoutData))
		{
			return false;
		}
		
		BoxLayoutData data = (BoxLayoutData) object;
		
		return Objects.equal(length, data.length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[length=%d]", getClass().getName(), length);
	}
}
