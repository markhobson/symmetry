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
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code TreeVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class TreeVisitorAdapter<T extends Tree, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TreeVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final TreeVisitor<? super T, P, E> treeDelegate;

	// constructors -----------------------------------------------------------
	
	public TreeVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullTreeVisitor());
	}
	
	public TreeVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		TreeVisitor<? super T, P, E> treeDelegate)
	{
		super(delegate);
		
		this.treeDelegate = treeDelegate;
	}

	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNode(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.visitNode(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.visitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNodeChildren(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.endVisitNodeChildren(tree, parameter, path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNode(T tree, P parameter, TreePath path) throws E
	{
		return treeDelegate.endVisitNode(tree, parameter, path);
	}
}
