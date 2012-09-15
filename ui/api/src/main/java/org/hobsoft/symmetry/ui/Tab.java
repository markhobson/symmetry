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
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.Visits.nullEndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Tab extends Button
{
	// constants --------------------------------------------------------------
	
	public static final String COMPONENT_PROPERTY = "component";
	
	// fields -----------------------------------------------------------------
	
	private Component component;
	
	// constructors -----------------------------------------------------------
	
	public Tab()
	{
		this(null);
	}
	
	public Tab(Component component)
	{
		this("", component);
	}
	
	public Tab(String text, Component component)
	{
		this(text, null, component);
	}
	
	public Tab(Action action, Component component)
	{
		this("", action, component);
	}
	
	protected Tab(String text, Action action, Component component)
	{
		super(text, action);
		
		setComponent(component);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptTab(visitor, Tab.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public Component getComponent()
	{
		return component;
	}
	
	public void setComponent(Component component)
	{
		Component oldComponent = this.component;
		this.component = component;
		
		if (oldComponent != null)
		{
			oldComponent.setParent(null);
		}
		
		if (component != null)
		{
			component.setParent(this);
		}
		
		firePropertyChange(COMPONENT_PROPERTY, oldComponent, component);
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Tab, P, E extends Exception> EndVisit acceptTab(ComponentVisitor<P, E> visitor,
		Class<T> tabType, T tab, P parameter) throws E
	{
		return acceptTab(visitor, Generic.get(tabType), tab, parameter);
	}
	
	protected static <T extends Tab, P, E extends Exception> EndVisit acceptTab(ComponentVisitor<P, E> visitor,
		Generic<T> tabType, T tab, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(tabType, tab, parameter);
		
		if (subvisitor == null || subvisitor.visit(tab, parameter) != SKIP_CHILDREN)
		{
			if (tab.getComponent() != null)
			{
				tab.getComponent().accept(visitor, parameter);
			}
		}
		
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(tab, parameter));
	}
}
