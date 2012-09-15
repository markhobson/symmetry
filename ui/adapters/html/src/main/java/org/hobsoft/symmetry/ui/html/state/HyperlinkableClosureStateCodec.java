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
