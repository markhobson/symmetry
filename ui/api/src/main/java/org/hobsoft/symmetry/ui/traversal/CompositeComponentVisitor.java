/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/CompositeComponentVisitor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import java.util.Collection;
import java.util.Collections;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: CompositeComponentVisitor.java 99716 2012-03-21 15:00:49Z mark@IIZUKA.CO.UK $
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public class CompositeComponentVisitor<P, E extends Exception> extends NullComponentVisitor<P, E>
{
	// fields -----------------------------------------------------------------
	
	private final HierarchicalComponentVisitorContainer<P, E> delegates;
	
	// constructors -----------------------------------------------------------
	
	public CompositeComponentVisitor()
	{
		this(Collections.<HierarchicalComponentVisitor<? extends Component, P, E>>emptySet());
	}
	
	// TODO: should be Collection<? extends HierarchicalComponentVisitor<? extends Component, P, E>> when CCONTAINER-57
	// fixed
	public CompositeComponentVisitor(Collection<HierarchicalComponentVisitor<? extends Component, P, E>> delegates)
	{
		this.delegates = new HierarchicalComponentVisitorContainer<P, E>();
		
		for (HierarchicalComponentVisitor<? extends Component, P, E> delegate : delegates)
		{
			setDelegate(delegate);
		}
	}
	
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> visitor = getDelegate(componentType);
		
		if (visitor == null)
		{
			throw new IllegalArgumentException("Delegate for component type not found: " + componentType);
		}
		
		return visitor;
	}
	
	// public methods ---------------------------------------------------------
	
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> getDelegate(Generic<T> componentType)
	{
		return delegates.get(componentType);
	}
	
	public <T extends Component> void setDelegate(Class<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		setDelegate(Generic.get(componentType), delegate);
	}
	
	public <T extends Component> void setDelegate(Generic<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		delegates.put(componentType, delegate);
	}
	
	public void setDelegate(HierarchicalComponentVisitor<? extends Component, P, E> delegate)
	{
		setDelegateCapture(delegate);
	}
	
	// private methods --------------------------------------------------------
	
	private <T extends Component> void setDelegateCapture(HierarchicalComponentVisitor<T, P, E> delegate)
	{
		Generic<T> componentType = ComponentVisitors.getComponentType(delegate);
		
		setDelegate(componentType, delegate);
	}
}
