/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Tab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
