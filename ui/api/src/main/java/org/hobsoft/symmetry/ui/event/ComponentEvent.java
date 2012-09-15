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
package org.hobsoft.symmetry.ui.event;

import java.util.EventObject;

import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class ComponentEvent extends EventObject
{
	// TODO: check hashCode and equals in subclasses
	
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// constructors -----------------------------------------------------------
	
	public ComponentEvent(Component source)
	{
		super(source);
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getSource()
	{
		return (Component) super.getSource();
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return getSource().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		return (object instanceof ComponentEvent)
			&& ((ComponentEvent) object).getSource().equals(getSource());
	}
}
