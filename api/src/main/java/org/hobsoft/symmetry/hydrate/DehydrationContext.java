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
package org.hobsoft.symmetry.hydrate;

import java.io.OutputStream;
import java.util.Locale;

import org.hobsoft.symmetry.state.State;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DehydrationContext extends HydrationContext
{
	// TODO: fold into HydrationContext and remove
	
	// fields -----------------------------------------------------------------
	
	private final State state;
	
	private final Locale locale;
	
	private final OutputStream out;
	
	// constructors -----------------------------------------------------------
	
	public DehydrationContext(DehydrationContext context)
	{
		this(context.getState(), context.getLocale(), context.getOutputStream());
		
		setAll(context.getAll());
	}
	
	public DehydrationContext(State state, Locale locale, OutputStream out)
	{
		this.locale = locale;
		this.state = state;
		this.out = out;
	}
	
	// public methods ---------------------------------------------------------
	
	public State getState()
	{
		return state;
	}
	
	public Locale getLocale()
	{
		return locale;
	}
	
	public OutputStream getOutputStream()
	{
		return out;
	}
}
