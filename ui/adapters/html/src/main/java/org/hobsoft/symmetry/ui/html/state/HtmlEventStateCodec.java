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
