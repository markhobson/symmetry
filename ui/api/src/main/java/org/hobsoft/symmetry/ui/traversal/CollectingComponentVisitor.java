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
package org.hobsoft.symmetry.ui.traversal;

import java.util.ArrayList;
import java.util.List;

import org.hobsoft.symmetry.ui.Component;

import com.google.common.reflect.TypeToken;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CollectingComponentVisitor extends NullComponentVisitor<Void, RuntimeException>
{
	// fields -----------------------------------------------------------------
	
	private final List<Component> components;
	
	// constructors -----------------------------------------------------------
	
	public CollectingComponentVisitor()
	{
		components = new ArrayList<Component>();
	}

	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, Void, RuntimeException> visit(
		TypeToken<T> componentType, T component, Void parameter)
	{
		components.add(component);
		
		return super.visit(componentType, component, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public List<Component> getComponents()
	{
		return components;
	}
}
