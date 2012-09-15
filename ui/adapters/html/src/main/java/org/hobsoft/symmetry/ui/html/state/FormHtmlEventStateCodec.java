/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
