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
package org.hobsoft.symmetry.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ToggleButtonGroup extends Box
{
	// TODO: support nested ToggleButtons
	
	// constants --------------------------------------------------------------
	
	private static PropertyChangeListener selectionListener = new SelectionListener();

	// types ------------------------------------------------------------------
	
	private static class SelectionListener implements PropertyChangeListener
	{
		// PropertyChangeListener methods -----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			ToggleButton button = (ToggleButton) event.getSource();
			boolean selected = ((Boolean) event.getNewValue()).booleanValue();
			
			// TODO: enforce always one button selected
			if (selected)
			{
				for (Component component : (Box) button.getParent())
				{
					if (component instanceof ToggleButton && (component != button)
						&& ((ToggleButton) component).isSelected())
					{
						((ToggleButton) component).setSelected(false);
					}
				}
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public ToggleButtonGroup()
	{
		super();
	}
	
	public ToggleButtonGroup(Orientation orientation)
	{
		super(orientation);
	}
	
	public ToggleButtonGroup(Component... children)
	{
		super(children);
	}
	
	public ToggleButtonGroup(Orientation orientation, Component... children)
	{
		super(orientation, children);
	}
	
	// Box methods ------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(Component child)
	{
		// NOTE: class may not be fully initialised here, due to super-constructors
		
		super.add(child);
		
		if (child instanceof ToggleButton)
		{
			((ToggleButton) child).addPropertyChangeListener(Selectable.SELECTED_PROPERTY, selectionListener);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Component child)
	{
		super.remove(child);
		
		if (child instanceof ToggleButton)
		{
			((ToggleButton) child).removePropertyChangeListener(Selectable.SELECTED_PROPERTY, selectionListener);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public ToggleButton getSelectedButton()
	{
		for (Component component : this)
		{
			if (component instanceof ToggleButton && ((ToggleButton) component).isSelected())
			{
				return (ToggleButton) component;
			}
		}
		
		return null;
	}
	
	public void setSelectedButton(ToggleButton selectedButton)
	{
		checkArgument(selectedButton == null || indexOf(selectedButton) != -1,
			"selectedButton must be a child of this ToggleButtonGroup: %s", selectedButton);
		
		if (selectedButton == null)
		{
			selectedButton = getSelectedButton();
			
			if (selectedButton != null)
			{
				selectedButton.setSelected(false);
			}
		}
		else
		{
			selectedButton.setSelected(true);
		}
	}
}
