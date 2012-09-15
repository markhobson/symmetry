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
package org.hobsoft.symmetry.ui.test.traversal;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.ui.traversal.GridVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.ListBoxVisitor;
import org.hobsoft.symmetry.ui.traversal.TableVisitor;
import org.hobsoft.symmetry.ui.traversal.TreeVisitor;
import org.jmock.Mockery;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class MockComponentVisitors
{
	// constants --------------------------------------------------------------
	
	private static final HierarchicalComponentVisitor<Component, Object, Exception> NULL_VISIT
		= new HierarchicalComponentVisitor<Component, Object, Exception>()
		{
			@Override
			public HierarchicalComponentVisitor.Visit visit(Component component, Object parameter) throws Exception
			{
				return null;
			}
			
			@Override
			public EndVisit endVisit(Component component, Object parameter) throws Exception
			{
				return VISIT_SIBLINGS;
			}
		};

	private static final HierarchicalComponentVisitor<Component, Object, Exception> NULL_END_VISIT
		= new HierarchicalComponentVisitor<Component, Object, Exception>()
		{
			@Override
			public HierarchicalComponentVisitor.Visit visit(Component component, Object parameter) throws Exception
			{
				return VISIT_CHILDREN;
			}
			
			@Override
			public EndVisit endVisit(Component component, Object parameter) throws Exception
			{
				return null;
			}
		};

	// constructors -----------------------------------------------------------
	
	private MockComponentVisitors()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static <P, E extends Exception> ComponentVisitor<P, E> createVisitor(Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		ComponentVisitor<P, E> visitor = mockery.mock(ComponentVisitor.class);
		
		return visitor;
	}

	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> nullVisit()
	{
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>) NULL_VISIT;
		
		return visitor;
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> nullEndVisit()
	{
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>) NULL_END_VISIT;
		
		return visitor;
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E>
		createHierarchicalVisitor(Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = mockery.mock(HierarchicalComponentVisitor.class);

		return visitor;
	}

	public static <T extends Container, P, E extends Exception> ContainerVisitor<T, P, E> createContainerVisitor(
		Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		ContainerVisitor<T, P, E> visitor = mockery.mock(ContainerVisitor.class);
	
		return visitor;
	}

	public static <T extends Grid, P, E extends Exception> GridVisitor<T, P, E> createGridVisitor(Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		GridVisitor<T, P, E> visitor = mockery.mock(GridVisitor.class);
		
		return visitor;
	}
	
	public static <T extends ComboBox<?>, P, E extends Exception> ListBoxVisitor<T, P, E> createListBoxVisitor(
		Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		ListBoxVisitor<T, P, E> visitor = mockery.mock(ListBoxVisitor.class);
	
		return visitor;
	}

	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> createTableVisitor(Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		TableVisitor<T, P, E> visitor = mockery.mock(TableVisitor.class);
		
		return visitor;
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> createTreeVisitor(Mockery mockery)
	{
		// cannot safely mock parameterized types
		@SuppressWarnings("unchecked")
		TreeVisitor<T, P, E> visitor = mockery.mock(TreeVisitor.class);
		
		return visitor;
	}
	
	public static Object createVisitorParameter()
	{
		// TODO: use random string
		return "x";
	}
}
