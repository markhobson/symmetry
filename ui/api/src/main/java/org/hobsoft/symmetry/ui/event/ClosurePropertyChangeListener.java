/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/ClosurePropertyChangeListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class ClosurePropertyChangeListener extends AbstractClosureEventListener<PropertyChangeEvent>
	implements PropertyChangeListener
{
	// constructors -----------------------------------------------------------
	
	public ClosurePropertyChangeListener(Closure<? super PropertyChangeEvent> closure)
	{
		super(closure);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		getClosure().apply(event);
	}
}
