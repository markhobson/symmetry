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

import org.hobsoft.symmetry.ui.Component;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class PostorderComponentVisitor<P, E extends Exception> extends NullComponentVisitor<P, E>
{
	// ComponentVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <T extends Component> HierarchicalComponentVisitor<T, P, E> visit(Generic<T> componentType,
		T component, P parameter) throws E
	{
		// TODO: cache, although jmock-legacy does not call super constructors
		return new NullHierarchicalComponentVisitor<T, P, E>()
		{
			@Override
			public EndVisit endVisit(T component, P parameter) throws E
			{
				PostorderComponentVisitor.this.visit(component, parameter);
				
				return VISIT_SIBLINGS;
			}
		};
	}
	
	// protected methods ------------------------------------------------------
	
	// TODO: do we want componentType here too?
	protected abstract void visit(Component component, P parameter) throws E;
}
