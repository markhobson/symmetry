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
package org.hobsoft.symmetry.ui.html;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.PreorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
class IntegerIdComponentVisitor extends PreorderComponentVisitor<Void, RuntimeException>
{
	// fields -----------------------------------------------------------------
	
	private final Map<Component, Integer> idsByComponent;
	
	private int nextId;
	
	// constructors -----------------------------------------------------------
	
	public IntegerIdComponentVisitor()
	{
		idsByComponent = new HashMap<Component, Integer>();
		nextId = 0;
	}
	
	// PreorderComponentVisitor methods ---------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void visit(Component component, Void parameter)
	{
		int id = nextId++;
		idsByComponent.put(component, id);
	}
	
	// public methods ---------------------------------------------------------
	
	public Map<Component, Integer> getIdsByComponent()
	{
		return idsByComponent;
	}
}
