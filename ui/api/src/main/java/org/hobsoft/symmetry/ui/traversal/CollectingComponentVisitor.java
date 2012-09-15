/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/CollectingComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import java.util.ArrayList;
import java.util.List;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CollectingComponentVisitor extends NullComponentVisitor<Void, RuntimeException>
{
	// fields -----------------------------------------------------------------
	
	private final List<Component> components;
	
	// constructors -----------------------------------------------------------
	
	public CollectingComponentVisitor()
	{
		components = new ArrayList<Component>();
	}

	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, Void, RuntimeException> visit(Generic<T> componentType,
		T component, Void parameter)
	{
		components.add(component);
		
		return super.visit(componentType, component, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public List<Component> getComponents()
	{
		return components;
	}
}
