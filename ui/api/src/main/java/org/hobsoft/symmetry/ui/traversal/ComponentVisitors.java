/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ComponentVisitors.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import java.lang.reflect.Type;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit;

import com.googlecode.jtype.Generic;
import com.googlecode.jtype.TypeUtils;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentVisitors.java 100650 2012-04-23 10:07:01Z mark@IIZUKA.CO.UK $
 */
public final class ComponentVisitors
{
	// constants --------------------------------------------------------------
	
	private static final HierarchicalComponentVisitor<Component, Object, Exception> NULL_HIERARCHICAL_VISITOR
		= new NullHierarchicalComponentVisitor<Component, Object, Exception>();

	private static final ContainerVisitor<Container, Object, Exception> NULL_CONTAINER_VISITOR
		= new NullContainerVisitor<Container, Object, Exception>();

	private static final GridVisitor<Grid, Object, Exception> NULL_GRID_VISITOR
		= new NullGridVisitor<Grid, Object, Exception>();

	private static final ListBoxVisitor<ComboBox<?>, Object, Exception> NULL_LIST_BOX_VISITOR
		= new NullListBoxVisitor<ComboBox<?>, Object, Exception>();

	private static final TableVisitor<Table, Object, Exception> NULL_TABLE_VISITOR
		= new NullTableVisitor<Table, Object, Exception>();
	
	private static final TreeVisitor<Tree, Object, Exception> NULL_TREE_VISITOR
		= new NullTreeVisitor<Tree, Object, Exception>();
	
	private static final HierarchicalComponentVisitor<Component, Object, Exception> SKIP_CHILDREN_VISITOR
		= skipChildren(null);

	private static final HierarchicalComponentVisitor<Component, Object, Exception> SKIP_SIBLINGS_VISITOR
		= skipSiblings(null);
	
	// constructors -----------------------------------------------------------
	
	private ComponentVisitors()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E>
		nullHierarchicalVisitor()
	{
		@SuppressWarnings("unchecked")
		NullHierarchicalComponentVisitor<T, P, E> visitor = (NullHierarchicalComponentVisitor<T, P, E>)
			NULL_HIERARCHICAL_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E>
		nullHierarchicalVisitor(HierarchicalComponentVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullHierarchicalVisitor();
	}

	public static <T extends Container, P, E extends Exception> ContainerVisitor<T, P, E> nullContainerVisitor()
	{
		@SuppressWarnings("unchecked")
		NullContainerVisitor<T, P, E> visitor = (NullContainerVisitor<T, P, E>) NULL_CONTAINER_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Container, P, E extends Exception> ContainerVisitor<T, P, E> nullContainerVisitor(
		ContainerVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullContainerVisitor();
	}
	
	public static <T extends Grid, P, E extends Exception> GridVisitor<T, P, E> nullGridVisitor()
	{
		@SuppressWarnings("unchecked")
		NullGridVisitor<T, P, E> visitor = (NullGridVisitor<T, P, E>) NULL_GRID_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Grid, P, E extends Exception> GridVisitor<T, P, E> nullGridVisitor(
		GridVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullGridVisitor();
	}
	
	public static <T extends ComboBox<?>, P, E extends Exception> ListBoxVisitor<T, P, E> nullListBoxVisitor()
	{
		@SuppressWarnings("unchecked")
		NullListBoxVisitor<T, P, E> visitor = (NullListBoxVisitor<T, P, E>) NULL_LIST_BOX_VISITOR;
		
		return visitor;
	}
	
	public static <T extends ComboBox<?>, P, E extends Exception> ListBoxVisitor<T, P, E> nullListBoxVisitor(
		ListBoxVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullListBoxVisitor();
	}
	
	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> nullTableVisitor()
	{
		@SuppressWarnings("unchecked")
		NullTableVisitor<T, P, E> visitor = (NullTableVisitor<T, P, E>) NULL_TABLE_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> nullTableVisitor(
		TableVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullTableVisitor();
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> nullTreeVisitor()
	{
		@SuppressWarnings("unchecked")
		NullTreeVisitor<T, P, E> visitor = (NullTreeVisitor<T, P, E>) NULL_TREE_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> nullTreeVisitor(
		TreeVisitor<T, P, E> visitor)
	{
		return (visitor != null) ? visitor : ComponentVisitors.<T, P, E>nullTreeVisitor();
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> skipChildren()
	{
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>) SKIP_CHILDREN_VISITOR;

		return visitor;
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> skipChildren(
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		return visit(nullHierarchicalVisitor(delegate), SKIP_CHILDREN);
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> skipSiblings()
	{
		@SuppressWarnings("unchecked")
		HierarchicalComponentVisitor<T, P, E> visitor = (HierarchicalComponentVisitor<T, P, E>) SKIP_SIBLINGS_VISITOR;
		
		return visitor;
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> skipSiblings(
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		return endVisit(nullHierarchicalVisitor(delegate), SKIP_SIBLINGS);
	}
	
	public static <T extends Component, P, E extends Exception> ComponentVisitor<P, E> adapt(Class<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		return adapt(Generic.get(componentType), delegate);
	}

	public static <T extends Component, P, E extends Exception> ComponentVisitor<P, E> adapt(Generic<T> componentType,
		HierarchicalComponentVisitor<T, P, E> delegate)
	{
		CompositeComponentVisitor<P, E> composite = new CompositeComponentVisitor<P, E>();
		composite.setDelegate(componentType, delegate);
		return composite;
	}

	public static <P, E extends Exception> ComponentVisitor<P, E> filter(ComponentVisitor<P, E> delegate,
		ComponentFilter filter)
	{
		return new FilteringComponentVisitor<P, E>(delegate, filter);
	}
	
	public static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> filter(
		HierarchicalComponentVisitor<T, P, E> delegate, ComponentFilter filter)
	{
		return new FilteringHierarchicalComponentVisitor<T, P, E>(delegate, filter);
	}
	
	public static <T extends Deck, P, E extends Exception> ContainerVisitor<T, P, E> selectedComponent()
	{
		return selectedComponent(ComponentVisitors.<T, P, E>nullContainerVisitor());
	}
	
	public static <T extends Deck, P, E extends Exception> ContainerVisitor<T, P, E> selectedComponent(
		ContainerVisitor<T, P, E> delegate)
	{
		return new SelectedComponentDeckVisitor<T, P, E>(delegate);
	}
	
	public static <P, E extends Exception> ComponentVisitor<P, E> visible(ComponentVisitor<P, E> delegate)
	{
		return new VisibleComponentVisitor<P, E>(delegate);
	}
	
	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> visibleRows()
	{
		return visibleRows(ComponentVisitors.<T, P, E>nullTableVisitor());
	}
	
	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> visibleRows(
		TableVisitor<T, P, E> delegate)
	{
		return new VisibleRowTableVisitor<T, P, E>(delegate);
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> visibleNodes()
	{
		return visibleNodes(ComponentVisitors.<T, P, E>nullTreeVisitor());
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> visibleNodes(
		TreeVisitor<T, P, E> delegate)
	{
		return new VisibleNodeTreeVisitor<T, P, E>(delegate);
	}
	
	public static CollectingComponentVisitor collect()
	{
		return new CollectingComponentVisitor();
	}
	
	public static <T extends Container, P, E extends Exception> ContainerVisitor<T, P, E> asContainerVisitor(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		ContainerVisitor<T, P, E> containerVisitor;
		
		if (visitor instanceof ContainerVisitor<?, ?, ?>)
		{
			containerVisitor = (ContainerVisitor<T, P, E>) visitor;
		}
		else
		{
			containerVisitor = new ContainerVisitorAdapter<T, P, E>(nullHierarchicalVisitor(visitor));
		}
		
		return containerVisitor;
	}
	
	public static <T extends Grid, P, E extends Exception> GridVisitor<T, P, E> asGridVisitor(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		GridVisitor<T, P, E> gridVisitor;
		
		if (visitor instanceof GridVisitor<?, ?, ?>)
		{
			gridVisitor = (GridVisitor<T, P, E>) visitor;
		}
		else
		{
			gridVisitor = new GridVisitorAdapter<T, P, E>(asContainerVisitor(visitor));
		}
		
		return gridVisitor;
	}
	
	public static <T extends ComboBox<?>, P, E extends Exception> ListBoxVisitor<T, P, E> asListBoxVisitor(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		ListBoxVisitor<T, P, E> listBoxVisitor;
		
		if (visitor instanceof ListBoxVisitor<?, ?, ?>)
		{
			listBoxVisitor = (ListBoxVisitor<T, P, E>) visitor;
		}
		else
		{
			listBoxVisitor = new ListBoxVisitorAdapter<T, P, E>(nullHierarchicalVisitor(visitor));
		}
		
		return listBoxVisitor;
	}
	
	public static <T extends Table, P, E extends Exception> TableVisitor<T, P, E> asTableVisitor(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		TableVisitor<T, P, E> tableVisitor;
		
		if (visitor instanceof TableVisitor<?, ?, ?>)
		{
			tableVisitor = (TableVisitor<T, P, E>) visitor;
		}
		else
		{
			tableVisitor = new TableVisitorAdapter<T, P, E>(nullHierarchicalVisitor(visitor));
		}
		
		return tableVisitor;
	}
	
	public static <T extends Tree, P, E extends Exception> TreeVisitor<T, P, E> asTreeVisitor(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		TreeVisitor<T, P, E> treeVisitor;
		
		if (visitor instanceof TreeVisitor<?, ?, ?>)
		{
			treeVisitor = (TreeVisitor<T, P, E>) visitor;
		}
		else
		{
			treeVisitor = new TreeVisitorAdapter<T, P, E>(nullHierarchicalVisitor(visitor));
		}
		
		return treeVisitor;
	}
	
	// package methods --------------------------------------------------------
	
	static <T extends Component, P, E extends Exception> Generic<T> getComponentType(
		HierarchicalComponentVisitor<T, P, E> visitor)
	{
		Type resolvedSupertype = TypeUtils.getResolvedSupertype(visitor.getClass(), HierarchicalComponentVisitor.class);
		
		Type componentType = TypeUtils.getActualTypeArgument(resolvedSupertype, 0);
		
		// typesafe by definition
		return (Generic<T>) Generic.get(componentType);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> visit(
		HierarchicalComponentVisitor<T, P, E> delegate, final Visit visit)
	{
		return new DelegatingHierarchicalComponentVisitor<T, P, E>(delegate)
		{
			@Override
			public Visit visit(T component, P parameter) throws E
			{
				super.visit(component, parameter);
				
				return visit;
			}
		};
	}
	
	private static <T extends Component, P, E extends Exception> HierarchicalComponentVisitor<T, P, E> endVisit(
		HierarchicalComponentVisitor<T, P, E> delegate, final EndVisit endVisit)
	{
		return new DelegatingHierarchicalComponentVisitor<T, P, E>(delegate)
		{
			@Override
			public EndVisit endVisit(T component, P parameter) throws E
			{
				super.endVisit(component, parameter);
				
				return endVisit;
			}
		};
	}
}
