/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/AbstractClosureEventListener.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventObject;

import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: AbstractClosureEventListener.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the event type
 */
public abstract class AbstractClosureEventListener<T extends EventObject> implements ClosureEventListener<T>
{
	// fields -----------------------------------------------------------------
	
	private final Closure<? super T> closure;
	
	// constructors -----------------------------------------------------------
	
	public AbstractClosureEventListener(Closure<? super T> closure)
	{
		this.closure = closure;
	}
	
	// ClosureEventListener methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Closure<? super T> getClosure()
	{
		return closure;
	}
}
