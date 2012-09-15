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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.ClosureEventListener;
import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * An action that executes a closure when performed.
 * 
 * @author Mark Hobson
 */
public class ClosureAction extends DefaultAction implements ClosureEventListener<ActionEvent>
{
	// constants --------------------------------------------------------------
	
	public static final String CLOSURE_PROPERTY = "closure";

	// fields -----------------------------------------------------------------
	
	private Closure<? super ActionEvent> closure;
	
	// constructors -----------------------------------------------------------
	
	public ClosureAction()
	{
		super();
	}
	
	public ClosureAction(String name)
	{
		super(name);
	}
	
	public ClosureAction(String name, Closure<? super ActionEvent> closure)
	{
		this(name);
		
		setClosure(closure);
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		closure.apply(event);
	}
	
	// ClosureEventListener methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Closure<? super ActionEvent> getClosure()
	{
		return closure;
	}
	
	// public methods ---------------------------------------------------------
	
	public void setClosure(Closure<? super ActionEvent> closure)
	{
		Closure<? super ActionEvent> oldClosure = this.closure;
		this.closure = closure;
		firePropertyChange(CLOSURE_PROPERTY, oldClosure, closure);
	}
}
