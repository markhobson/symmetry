/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ComponentUtils.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventListener;
import java.util.List;

import org.hobsoft.symmetry.support.bean.EventListeners;
import org.hobsoft.symmetry.ui.event.ClosureEventListener;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.functor.CompositeClosure;
import org.hobsoft.symmetry.ui.traversal.ComponentFilter;
import org.hobsoft.symmetry.ui.traversal.ComponentFilters;

/**
 * Provides various utility methods for working with components.
 * 
 * @author Mark Hobson
 * @see Component
 */
public final class ComponentUtils
{
	// constructors -----------------------------------------------------------
	
	private ComponentUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component> T getAncestor(Component component, Class<T> ancestorType)
	{
		Component ancestor = getAncestor(component, ComponentFilters.type(ancestorType));
		
		return ancestorType.cast(ancestor);
	}
	
	public static Component getAncestor(Component component, ComponentFilter filter)
	{
		component = component.getParent();
		
		while (component != null)
		{
			if (filter.accept(component))
			{
				return component;
			}
			
			component = component.getParent();
		}
		
		return null;
	}
	
	public static List<Closure<?>> getClosures(EventListener[] listeners)
	{
		EventListener[] unproxiedListeners = EventListeners.unproxy(listeners);
		
		List<Closure<?>> closures = new ArrayList<Closure<?>>();
		
		for (ClosureEventListener<?> closureListener : getElementsOfType(unproxiedListeners,
			ClosureEventListener.class))
		{
			Closure<?> closure = closureListener.getClosure();
			
			if (closure != null)
			{
				if (closure instanceof CompositeClosure)
				{
					closures.addAll(((CompositeClosure<?>) closure).getClosures());
				}
				else
				{
					closures.add(closure);
				}
			}
		}
		
		return closures;
	}
	
	public static <T extends Closure<?>> List<T> getClosures(EventListener[] listeners, Class<T> closureType)
	{
		List<Closure<?>> closures = getClosures(listeners);
		
		return getElementsOfType(closures, closureType);
	}
	
	public static <T extends Closure<?>> T getFirstClosure(EventListener listener, Class<T> closureType)
	{
		return getFirstClosure(new EventListener[] {listener}, closureType);
	}
	
	public static <T extends Closure<?>> T getFirstClosure(EventListener[] listeners, Class<T> closureType)
	{
		List<T> closures = getClosures(listeners, closureType);
		
		return !closures.isEmpty() ? closures.get(0) : null;
	}
	
	// private methods --------------------------------------------------------
	
	private static <T, U extends T> List<U> getElementsOfType(T[] array, Class<U> elementType)
	{
		return getElementsOfType(Arrays.asList(array), elementType);
	}
	
	private static <T, U extends T> List<U> getElementsOfType(Collection<T> collection, Class<U> elementType)
	{
		List<U> typedElements = new ArrayList<U>();
		
		for (T element : collection)
		{
			if (elementType.isInstance(element))
			{
				typedElements.add(elementType.cast(element));
			}
		}
		
		return typedElements;
	}
}
