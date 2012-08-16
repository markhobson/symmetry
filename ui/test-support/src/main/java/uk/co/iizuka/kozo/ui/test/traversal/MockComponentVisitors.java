/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/traversal/MockComponentVisitors.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.test.traversal;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import org.jmock.Mockery;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Container;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.ui.traversal.GridVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.ListBoxVisitor;
import uk.co.iizuka.kozo.ui.traversal.TableVisitor;
import uk.co.iizuka.kozo.ui.traversal.TreeVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: MockComponentVisitors.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
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
