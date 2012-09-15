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

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ToolBar extends Box
{
	// constants --------------------------------------------------------------
	
	private static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;
	
	private static final Action[] EMPTY_ACTIONS = new Action[0];

	// constructors -----------------------------------------------------------
	
	public ToolBar()
	{
		this(DEFAULT_ORIENTATION);
	}
	
	public ToolBar(Orientation orientation)
	{
		this(orientation, EMPTY_ACTIONS);
	}
	
	public ToolBar(Component... children)
	{
		this(DEFAULT_ORIENTATION, children);
	}
	
	public ToolBar(Action... actions)
	{
		this(DEFAULT_ORIENTATION, actions);
	}
	
	public ToolBar(Orientation orientation, Component... children)
	{
		super(orientation, children);
	}
	
	public ToolBar(Orientation orientation, Action... actions)
	{
		super(orientation);
		
		add(actions);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, ToolBar.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void add(Action... actions)
	{
		for (Action action : actions)
		{
			add(new Button(action));
		}
	}
	
	public void remove(Action action)
	{
		// TODO: implement
	}
}
