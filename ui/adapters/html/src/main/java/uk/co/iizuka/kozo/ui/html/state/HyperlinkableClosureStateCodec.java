/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HyperlinkableClosureStateCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.state;

import java.util.EventObject;

import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.state.StateException;
import uk.co.iizuka.kozo.ui.functor.HyperlinkableClosure;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HyperlinkableClosureStateCodec.java 95170 2011-11-15 17:17:14Z mark@IIZUKA.CO.UK $
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
