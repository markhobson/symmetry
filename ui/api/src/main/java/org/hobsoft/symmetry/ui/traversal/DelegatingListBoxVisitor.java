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

import org.hobsoft.symmetry.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingListBoxVisitor<T extends ComboBox<?>, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ListBoxVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingListBoxVisitor(ListBoxVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// ListBoxVisitor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.Visit visitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return getDelegate(comboBox, parameter).visitItem(comboBox, parameter, itemIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.EndVisit endVisitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return getDelegate(comboBox, parameter).endVisitItem(comboBox, parameter, itemIndex);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListBoxVisitor<? super T, P, E> getDelegate(T comboBox, P parameter)
	{
		return (ListBoxVisitor<? super T, P, E>) super.getDelegate(comboBox, parameter);
	}
}
