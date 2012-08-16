/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/HierarchicalComponentVisitorContainer.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.Component;

/**
 * A typesafe heterogeneous container for {@code ComponentVisitor}s. See Item 29 in Effective Java Second Edition for a
 * discussion of this pattern.
 * 
 * @author Mark Hobson
 * @version $Id: HierarchicalComponentVisitorContainer.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class HierarchicalComponentVisitorContainer<P, E extends Exception>
{
	// TODO: do we want to lower bound the container?
	// TODO: do we want to relax value type to ComponentVisitor<? super U, P, E>?
	
	// fields -----------------------------------------------------------------
	
	private final Map<Generic<? extends Component>, HierarchicalComponentVisitor<? extends Component, P, E>> container;
	
	// constructors -----------------------------------------------------------
	
	public HierarchicalComponentVisitorContainer()
	{
		container = new HashMap<Generic<? extends Component>,
			HierarchicalComponentVisitor<? extends Component, P, E>>();
	}
	
	// public methods ---------------------------------------------------------
	
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> get(Generic<T> componentType)
	{
		// guaranteed by put()
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>)
			container.get(componentType);
		
		return visitor;
	}
	
	public <T extends Component> void put(Generic<T> componentType, HierarchicalComponentVisitor<T, P, E> visitor)
	{
		container.put(componentType, visitor);
	}
}
