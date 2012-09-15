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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code ToolBar}.
 * 
 * @author Mark Hobson
 * @see ToolBar
 */
public class ToolBarTest extends AbstractComponentTest<ToolBar>
{
	// fields -----------------------------------------------------------------
	
	private ToolBar toolBar;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		toolBar = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		assertEquals("Orientation", Orientation.HORIZONTAL, toolBar.getOrientation());
	}
	
	@Test
	public void constructWithOrientation()
	{
		toolBar = new ToolBar(Orientation.VERTICAL);
		
		assertEquals("Orientation", Orientation.VERTICAL, toolBar.getOrientation());
	}
	
	@Test
	public void constructWithComponents()
	{
		Component[] components = new Component[] {new DummyComponent(), new DummyComponent()};
		toolBar = new ToolBar(components);
		
		assertEquals("Orientation", Orientation.HORIZONTAL, toolBar.getOrientation());
		assertEquals("Components", Arrays.asList(components), toolBar.getComponents());
	}
	
	@Test
	public void constructWithActions()
	{
		Action[] actions = new Action[] {new DefaultAction(), new DefaultAction()};
		toolBar = new ToolBar(actions);
		
		assertEquals("Orientation", Orientation.HORIZONTAL, toolBar.getOrientation());
		assertEquals("Actions", Arrays.asList(actions), toActions(toolBar.getComponents()));
	}
	
	@Test
	public void constructWithOrientationAndComponents()
	{
		Component[] components = new Component[] {new DummyComponent(), new DummyComponent()};
		toolBar = new ToolBar(Orientation.VERTICAL, components);
		
		assertEquals("Orientation", Orientation.VERTICAL, toolBar.getOrientation());
		assertEquals("Components", Arrays.asList(components), toolBar.getComponents());
	}
	
	@Test
	public void constructWithOrientationAndActions()
	{
		Action[] actions = new Action[] {new DefaultAction(), new DefaultAction()};
		toolBar = new ToolBar(Orientation.VERTICAL, actions);
		
		assertEquals("Orientation", Orientation.VERTICAL, toolBar.getOrientation());
		assertEquals("Actions", Arrays.asList(actions), toActions(toolBar.getComponents()));
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ToolBar createComponent()
	{
		return new ToolBar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<ToolBar> getComponentType()
	{
		return Generic.get(ToolBar.class);
	}
	
	// private methods --------------------------------------------------------
	
	private static List<Action> toActions(List<Component> components)
	{
		List<Action> actions = new ArrayList<Action>();
		
		for (Component component : components)
		{
			if (component instanceof Button)
			{
				actions.add(((Button) component).getAction());
			}
			else
			{
				actions.add(null);
			}
		}
		
		return actions;
	}
}
