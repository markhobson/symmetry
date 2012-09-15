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

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

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
public abstract class DelegatingTreeVisitor<T extends Tree, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TreeVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingTreeVisitor(TreeVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNode(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).visitNode(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).visitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).endVisitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNode(T tree, P parameter, TreePath path) throws E
	{
		return getDelegate(tree, parameter).endVisitNode(tree, parameter, path);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeVisitor<? super T, P, E> getDelegate(T tree, P parameter)
	{
		return (TreeVisitor<? super T, P, E>) super.getDelegate(tree, parameter);
	}
}
