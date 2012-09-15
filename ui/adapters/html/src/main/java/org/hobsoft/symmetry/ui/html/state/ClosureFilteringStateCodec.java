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
