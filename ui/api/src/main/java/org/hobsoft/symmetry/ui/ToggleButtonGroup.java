/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ToggleButtonGroup.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
