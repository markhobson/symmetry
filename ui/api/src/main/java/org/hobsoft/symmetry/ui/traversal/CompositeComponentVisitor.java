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

import java.util.Collection;
import java.util.Collections;

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public class CompositeComponentVisitor<P, E extends Exception> extends NullComponentVisitor<P, E>
{
	// fields -----------------------------------------------------------------
	
	private final HierarchicalComponentVisitorContainer<P, E> delegates;
	
	// constructors -----------------------------------------------------------
	
	public CompositeComponentVisitor()
	{
		this(Collections.<HierarchicalComponentVisitor<? extends Component, P, E>>emptySet());
	}
	
	// TODO: should be Collection<? extends HierarchicalComponentVisitor<? extends Component, P, E>> when CCONTAINER-57
	// fixed
	public CompositeComponentVisitor(Collection<HierarchicalComponentVisitor<? extends Component, P, E>> delegates)
	{
		this.delegates = new HierarchicalComponentVisitorContainer<P, E>();
		
		for (HierarchicalComponentVisitor<? extends Component, P, E> delegate : delegates)
		{
			setDelegate(delegate);
		}
	}
	
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType, T component,
		P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> visitor = getDelegate(componentType);
		
		if (visitor == null)
		{
			throw new IllegalArgumentException("Delegate for component type not found: " + componentType);
		}
		
		return visitor;
	}
	
	// public methods ---------------------------------------------------------
	
	public <T extends Component> HierarchicalComponentVisitor<T, P, E> getDelegate(Generic<T> componentType)
	{
		return delegates.get(componentType);
	}
	
	public <T extends Component> void setDelegate(Class<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		setDelegate(Generic.get(componentType), delegate);
	}
	
	public <T extends Component> void setDelegate(Generic<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		delegates.put(componentType, delegate);
	}
	
	public void setDelegate(HierarchicalComponentVisitor<? extends Component, P, E> delegate)
	{
		setDelegateCapture(delegate);
	}
	
	// private methods --------------------------------------------------------
	
	private <T extends Component> void setDelegateCapture(HierarchicalComponentVisitor<T, P, E> delegate)
	{
		Generic<T> componentType = ComponentVisitors.getComponentType(delegate);
		
		setDelegate(componentType, delegate);
	}
}
