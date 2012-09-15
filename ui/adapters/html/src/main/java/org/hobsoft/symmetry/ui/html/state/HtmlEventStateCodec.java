/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HtmlEventStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import org.hobsoft.symmetry.state.DelegatingStateCodec;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class HtmlEventStateCodec extends DelegatingStateCodec
{
	// types ------------------------------------------------------------------
	
	/**
	 * State parameters for this state codec.
	 */
	public static final class Parameters
	{
		// fields -----------------------------------------------------------------
		
		private boolean event;
		
		// public methods ---------------------------------------------------------
		
		public boolean isEvent()
		{
			return event;
		}
		
		public void setEvent(boolean event)
		{
			this.event = event;
		}
	}

	// constructors -----------------------------------------------------------
	
	public HtmlEventStateCodec(StateCodec delegate)
	{
		super(delegate);
	}
	
	// StateCodec methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EncodedState encodeState(State state) throws StateException
	{
		EncodedState encodedState = super.encodeState(state);
		
		StringBuilder builder = new StringBuilder();

		builder.append(encodedState.getState());

		if (isEvent(state))
		{
			builder.insert(0, "document.location = '").append("'");
		}
		
		return new EncodedState(builder.toString());
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean isEvent(State state)
	{
		Parameters parameters = (Parameters) state.getParameter(Parameters.class.getName());
		
		return (parameters != null) && parameters.isEvent();
	}
}
