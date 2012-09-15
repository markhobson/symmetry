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

import org.hobsoft.symmetry.support.bean.Consumable;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class OptionEvent extends ComponentEvent implements Consumable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// fields -----------------------------------------------------------------
	
	private boolean consumed;
	
	// constructors -----------------------------------------------------------
	
	public OptionEvent(Component source)
	{
		super(source);
		consumed = false;
	}
	
	// Consumable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isConsumed()
	{
		return consumed;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void consume()
	{
		consumed = true;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + (consumed ? 1 : 0);
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof OptionEvent))
		{
			return false;
		}
		
		OptionEvent event = (OptionEvent) object;
		
		return super.equals(event)
			&& consumed == event.consumed;
	}
}
