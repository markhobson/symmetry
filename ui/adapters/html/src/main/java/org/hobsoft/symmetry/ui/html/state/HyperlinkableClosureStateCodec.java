/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HyperlinkableClosureStateCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import java.util.EventObject;

import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.ui.functor.HyperlinkableClosure;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class HyperlinkableClosureStateCodec
	extends ClosureFilteringStateCodec<HyperlinkableClosure<? super EventObject>>
{
	// TODO: remove when superseded by HyperlinkableStateCodec
	
	// constructors -----------------------------------------------------------
	
	public HyperlinkableClosureStateCodec(StateCodec stateCodec)
	{
		super(stateCodec, HyperlinkableClosure.class);
	}
	
	// ClosureFilteringStateCodec methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EncodedState filterClosure(State state, HyperlinkableClosure<? super EventObject> closure,
		EventObject event) throws StateException
	{
		if (closure.isAsynchronous())
		{
			return null;
		}
		
		return new EncodedState(closure.toHyperlink(event));
	}
}
