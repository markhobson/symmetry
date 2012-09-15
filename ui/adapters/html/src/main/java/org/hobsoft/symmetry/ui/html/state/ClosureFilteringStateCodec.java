/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/ClosureFilteringStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import java.util.EventListener;
import java.util.EventObject;

import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.FilteringStateCodec;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.ui.ComponentUtils;
import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the command type
 */
public abstract class ClosureFilteringStateCodec<T extends Closure<? super EventObject>> extends FilteringStateCodec
{
	// TODO: reconsider this as generics restrict use to closures of EventObject, and not subtypes like ActionEvent
	
	// fields -----------------------------------------------------------------
	
	private final Class<T> closureType;

	// constructors -----------------------------------------------------------
	
	public ClosureFilteringStateCodec(StateCodec delegate, Class<? super T> closureType)
	{
		super(delegate);
		
		// TODO: we only allow <? super T> since we need to work with generic closures, e.g. HyperlinkableClosure<?>
		this.closureType = (Class<T>) closureType;
	}
	
	// FilteringStateCodec methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final EncodedState filterEvent(State state, EventListener listener, EventObject event)
		throws StateException
	{
		T closure = ComponentUtils.getFirstClosure(listener, closureType);
		
		if (closure != null)
		{
			return filterClosure(state, closure, event);
		}
		
		return null;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract EncodedState filterClosure(State state, T closure, EventObject event) throws StateException;
}
