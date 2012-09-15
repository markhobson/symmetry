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

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ToggleButton extends Button implements Selectable
{
	// fields -----------------------------------------------------------------
	
	private boolean selected;
	
	private ActionListener toggleButtonActionListener = new ToggleButtonActionListener();

	// types ------------------------------------------------------------------
	
	private class ToggleButtonActionListener implements ActionListener
	{
		// ActionListener methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			setSelected(!isSelected());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public ToggleButton()
	{
		this("");
	}
	
	public ToggleButton(String text)
	{
		this(text, false);
	}
	
	public ToggleButton(String text, boolean selected)
	{
		this(text, null, selected);
	}
	
	public ToggleButton(Action action)
	{
		this(action, false);
	}
	
	public ToggleButton(Action action, boolean selected)
	{
		this("", action, selected);
	}
	
	protected ToggleButton(String text, Action action, boolean selected)
	{
		super(text, action);
		setSelected(selected);
		addActionListener(toggleButtonActionListener);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, ToggleButton.class, this, parameter);
	}
	
	// Selectable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSelected()
	{
		return selected;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSelected(boolean selected)
	{
		boolean oldSelected = this.selected;
		this.selected = selected;
		firePropertyChange(SELECTED_PROPERTY, oldSelected, selected);
	}
}
