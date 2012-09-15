/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/FormHtmlEventStateCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import org.hobsoft.symmetry.state.DelegatingStateCodec;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator;
import org.hobsoft.symmetry.ui.html.state.HtmlEventStateCodec.Parameters;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class FormHtmlEventStateCodec extends DelegatingStateCodec
{
	// TODO: extend HtmlEventStateCodec to inherit functionality
	// TODO: use document.location directly instead of doGet
	
	// constructors -----------------------------------------------------------
	
	public FormHtmlEventStateCodec(StateCodec stateCodec)
	{
		super(stateCodec);
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
		
		FormHtmlWindowDehydrator.Parameters formParameters = (FormHtmlWindowDehydrator.Parameters)
			state.getParameter(FormHtmlWindowDehydrator.Parameters.class.getName());
		boolean post = (formParameters != null) && formParameters.isPost();
		boolean event = ((Parameters) state.getParameter(Parameters.class.getName())).isEvent();
		
		if (post)
		{
			builder.insert(0, "doPost('").append("')");
			
			if (!event)
			{
				builder.insert(0, "javascript:");
			}
		}
		else
		{
			if (event)
			{
				builder.insert(0, "doGet('").append("')");
			}
		}
		
		return new EncodedState(builder.toString(), encodedState);
	}
}
