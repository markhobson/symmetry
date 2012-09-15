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
package org.hobsoft.symmetry.ui.test;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * Dummy component for tests.
 * 
 * @author Mark Hobson
 */
public class DummyComponent extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String STATE_PROPERTY = "state";

	// fields -----------------------------------------------------------------
	
	private boolean state;
	
	// constructors -----------------------------------------------------------
	
	public DummyComponent()
	{
		state = false;
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, DummyComponent.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean getState()
	{
		return state;
	}
	
	public void setState(boolean state)
	{
		this.state = state;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "DummyComponent";
	}
}
