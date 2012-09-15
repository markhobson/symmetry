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

import java.util.AbstractList;
import java.util.List;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ActionListener;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TabBox extends Deck
{
	// constants --------------------------------------------------------------
	
	private static final Tab[] EMPTY_TAB_ARRAY = new Tab[0];
	
	// fields -----------------------------------------------------------------
	
	private ActionListener selectTabActionListener = new SelectTabActionListener();

	// types ------------------------------------------------------------------
	
	/**
	 * Action listener that selects the tab that is the event source.
	 */
	public final class SelectTabActionListener implements ActionListener
	{
		// ActionListener methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			Tab tab = (Tab) event.getSource();
			setSelectedComponent(tab);
		}
		
		// public methods ---------------------------------------------------------
		
		public TabBox getTabBox()
		{
			return TabBox.this;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public TabBox()
	{
		this(EMPTY_TAB_ARRAY);
	}
	
	public TabBox(Tab... tabs)
	{
		super();
		
		// not using super-constructor to add children since we override add()
		add(tabs);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, TabBox.class, this, parameter);
	}
	
	// Box methods ------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, Component child)
	{
		checkArgument(child == null || child instanceof Tab, "child must be a Tab");
		
		Tab tab = (Tab) child;
		
		super.add(index, tab);
		tab.addActionListener(selectTabActionListener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Component child)
	{
		checkArgument(child == null || child instanceof Tab, "child must be a Tab");
		
		Tab tab = (Tab) child;
		
		tab.removeActionListener(selectTabActionListener);
		super.remove(tab);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tab get(int index)
	{
		return (Tab) super.get(index);
	}
	
	// Deck methods -----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tab getSelectedComponent()
	{
		return (Tab) super.getSelectedComponent();
	}
	
	// public methods ---------------------------------------------------------
	
	public Tab add(String text, Component component)
	{
		Tab tab = new Tab(text, component);
		add(tab);
		return tab;
	}
	
	public Tab add(Action action, Component component)
	{
		Tab tab = new Tab(action, component);
		add(tab);
		return tab;
	}
	
	public List<Tab> getTabs()
	{
		return new AbstractList<Tab>()
		{
			@Override
			public Tab get(int index)
			{
				return TabBox.this.get(index);
			}
			
			@Override
			public int size()
			{
				return getComponentCount();
			}
		};
	}
	
	public Component getSelectedTabComponent()
	{
		return getSelectedComponent().getComponent();
	}
	
	public void setSelectedTabComponent(Component selectedTabComponent)
	{
		int selectedTabIndex = -1;
		
		for (int index = 0; index < getComponentCount() && selectedTabIndex == -1; index++)
		{
			if (get(index).getComponent().equals(selectedTabComponent))
			{
				selectedTabIndex = index;
			}
		}
		
		checkArgument(selectedTabIndex != -1, "Unknown tab component: %s", selectedTabComponent);
		
		setSelectedIndex(selectedTabIndex);
	}
}
