/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Container.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asContainerVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.Visits.nullEndVisit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * A component that can contain other components.
 * 
 * @author Mark Hobson
 * @version $Id: Container.java 101286 2012-05-15 14:33:36Z mark@IIZUKA.CO.UK $
 */
public abstract class Container extends Component implements Iterable<Component>
{
	// TODO: add Container(Collection<Component>) constructors?
	
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the container's children.
	 */
	public static final String COMPONENTS_PROPERTY = "components";
	
	/**
	 * Zero-length component array cached for efficiency.
	 */
	static final Component[] EMPTY_COMPONENT_ARRAY = new Component[0];
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The list of children of this container.
	 */
	private List<Component> children;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty container.
	 */
	public Container()
	{
		this(EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a container with the specified children.
	 * 
	 * @param children
	 *            the components to be added to the container
	 */
	public Container(Component... children)
	{
		this.children = new ArrayList<Component>();
		
		add(children);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Adds the specified component to the end of this container.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param child
	 *            the component to add to this container
	 */
	public void add(Component child)
	{
		add(children.size(), child);
	}
	
	public void add(int childIndex, Component child)
	{
		checkPositionIndex(childIndex, getComponentCount(), "childIndex");
		checkNotNull(child, "child cannot be null");
		
		children.add(childIndex, child);
		child.setParent(this);
		fireIndexedPropertyChange(COMPONENTS_PROPERTY, childIndex, null, child);
	}
	
	/**
	 * Adds the specified components to the end of this container. This calls {@code add(Component)} for each component.
	 * 
	 * @param children
	 *            the components to add to this container
	 */
	public void add(Component... children)
	{
		checkNotNull(children, "children cannot be null");
		
		for (Component child : children)
		{
			add(child);
		}
	}
	
	/**
	 * Adds the specified components to the end of this container. This calls {@code add(Component)} for each component.
	 * 
	 * @param children
	 *            the components to add to this container
	 */
	public void add(Collection<Component> children)
	{
		// argument is not Iterable<Component> to avoid ambiguous invocation with container.add(container)
		
		checkNotNull(children, "children cannot be null");
		
		for (Component child : children)
		{
			add(child);
		}
	}
	
	// TODO: remove(int)
	
	/**
	 * Removes the specified component from this container.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param child
	 *            the component to remove from this container
	 */
	public void remove(Component child)
	{
		checkNotNull(child, "child cannot be null");
		
		int index = indexOf(child);
		if (children.remove(child))
		{
			child.setParent(null);
			fireIndexedPropertyChange(COMPONENTS_PROPERTY, index, child, null);
		}
	}
	
	/**
	 * Removes the specified components from this container. This calls {@code remove(Component)} for each component.
	 * 
	 * @param children
	 *            the components to remove from this container
	 */
	public void remove(Component... children)
	{
		for (Component child : children)
		{
			remove(child);
		}
	}
	
	/**
	 * Removes the specified components from this container. This calls {@code remove(Component)} for each component.
	 * 
	 * @param children
	 *            the components to remove from this container
	 */
	public void remove(Collection<Component> children)
	{
		// argument is not Iterable<Component> to avoid ambiguous invocation with container.remove(container)

		for (Component child : children)
		{
			remove(child);
		}
	}
	
	/**
	 * Gets the component at the specified index in this container.
	 * 
	 * @param childIndex
	 *            the index of the component to get
	 * @return the component at the specified index in this container
	 * @throws IndexOutOfBoundsException
	 *             if a component at the specified index does not exist
	 */
	public Component get(int childIndex)
	{
		checkChildIndex(childIndex);
		
		return children.get(childIndex);
	}
	
	// TODO: set(int, Component)
	
	/**
	 * Gets the index of the specified component in this container.
	 * 
	 * @param component
	 *            the component to get the index of
	 * @return the index of the specified component in this container, or -1 if the component has not been added to this
	 *         container
	 */
	// TODO: rename to getComponentIndex to align with ComboBox.getItemIndex?
	public int indexOf(Component component)
	{
		checkNotNull(component, "component cannot be null");
		
		return children.indexOf(component);
	}
	
	/**
	 * Gets a list of components in this container.
	 * 
	 * @return a read-only list of components in this container
	 */
	public List<Component> getComponents()
	{
		return Collections.unmodifiableList(children);
	}
	
	/**
	 * Gets the number of components in this container.
	 * 
	 * @return the number of components in this container
	 */
	public int getComponentCount()
	{
		return children.size();
	}
	
	// Iterable methods -------------------------------------------------------
	
	/**
	 * Gets an iterator over the components in this container.
	 * 
	 * @return a read-only {@code Component} iterator over the components in this container
	 */
	@Override
	public Iterator<Component> iterator()
	{
		return getComponents().iterator();
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Container, P, E extends Exception> EndVisit acceptContainer(
		ComponentVisitor<P, E> visitor, Class<T> containerType, T container, P parameter) throws E
	{
		return acceptContainer(visitor, Generic.get(containerType), container, parameter);
	}
	
	protected static <T extends Container, P, E extends Exception> EndVisit acceptContainer(
		ComponentVisitor<P, E> visitor, Generic<T> containerType, T container, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(containerType, container, parameter);
		
		if (subvisitor == null || subvisitor.visit(container, parameter) != SKIP_CHILDREN)
		{
			// TODO: should we use the return value?
			acceptChildren(visitor, subvisitor, container, parameter);
		}
			
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(container, parameter));
	}
	
	protected static <T extends Container, P, E extends Exception> EndVisit acceptChildren(
		ComponentVisitor<P, E> visitor, HierarchicalComponentVisitor<T, P, E> subvisitor, T container, P parameter)
		throws E
	{
		EndVisit endVisit = null;
		int childCount = container.getComponentCount();
		
		for (int index = 0; index < childCount && endVisit != SKIP_SIBLINGS; index++)
		{
			endVisit = acceptChild(visitor, subvisitor, container, parameter, index);
		}
			
		return nullEndVisit(endVisit);
	}
	
	// package methods --------------------------------------------------------
	
	static <T extends Container, P, E extends Exception> EndVisit acceptChild(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T container, P parameter, int index) throws E
	{
		if (asContainerVisitor(subvisitor).visitChild(container, parameter, index) != SKIP_CHILDREN)
		{
			Component child = container.get(index);
			
			// TODO: should we use the return value?
			child.accept(visitor, parameter);
		}
		
		return nullEndVisit(asContainerVisitor(subvisitor).endVisitChild(container, parameter, index));
	}
	
	void checkChildIndex(int childIndex)
	{
		checkElementIndex(childIndex, getComponentCount(), "childIndex");
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "["
			+ COMPONENTS_PROPERTY + "=" + children
			+ "]";
	}
}
