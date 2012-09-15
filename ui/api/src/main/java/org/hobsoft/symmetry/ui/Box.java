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
package org.hobsoft.symmetry.ui;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.hobsoft.symmetry.ui.layout.BoxLayoutData;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A container that lays its children out along an axis.
 * 
 * @author Mark Hobson
 * @see org.hobsoft.symmetry.ui.HBox
 * @see org.hobsoft.symmetry.ui.VBox
 */
public class Box extends Container
{
	// TODO: create BoxLayoutData lazily like Table.column
	
	// TODO: add Box(Collection<Component>) constructors?
	
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the box's orientation.
	 */
	public static final String ORIENTATION_PROPERTY = "orientation";
	
	private static final Orientation DEFAULT_ORIENTATION = Orientation.VERTICAL;
	
	// fields -----------------------------------------------------------------
	
	private final List<BoxLayoutData> layoutDatas;
	
	private final PropertyChangeListener layoutDataComponentListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			Component oldComponent = (Component) event.getOldValue();
			Component newComponent = (Component) event.getNewValue();
			int index = ((IndexedPropertyChangeEvent) event).getIndex();
			
			if (oldComponent == null && newComponent != null)
			{
				layoutDatas.add(index, new BoxLayoutData());
			}
			else if (oldComponent != null && newComponent == null)
			{
				layoutDatas.remove(index);
			}
		}
	};
	
	/**
	 * The orientation of the box.
	 */
	private Orientation orientation;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty box with vertical orientation.
	 */
	public Box()
	{
		this(DEFAULT_ORIENTATION);
	}
	
	/**
	 * Creates an empty box with the specified orientation.
	 * 
	 * @param orientation
	 *            the orientation of the box
	 */
	public Box(Orientation orientation)
	{
		this(orientation, EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a box with vertical orientation and the specified children.
	 * 
	 * @param children
	 *            the components to be added to the box
	 */
	public Box(Component... children)
	{
		this(DEFAULT_ORIENTATION, children);
	}
	
	/**
	 * Creates a box with the specified orientation and children.
	 * 
	 * @param orientation
	 *            the orientation of the box
	 * @param children
	 *            the components to be added to the box
	 */
	public Box(Orientation orientation, Component... children)
	{
		layoutDatas = new ArrayList<BoxLayoutData>();
		
		addPropertyChangeListener(COMPONENTS_PROPERTY, layoutDataComponentListener);
		
		setOrientation(orientation);
		
		// don't use super constructor to add children since we need to be initialised first
		add(children);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, Box.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets how this box's children are laid out.
	 * 
	 * @return the orientation of this box
	 */
	public Orientation getOrientation()
	{
		return orientation;
	}
	
	/**
	 * Sets how this box's children are laid out to the specified orientation.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param orientation
	 *            the orientation for this box
	 */
	public void setOrientation(Orientation orientation)
	{
		checkNotNull(orientation, "orientation cannot be null");
		
		Orientation oldOrientation = this.orientation;
		this.orientation = orientation;
		firePropertyChange(ORIENTATION_PROPERTY, oldOrientation, orientation);
	}
	
	public BoxLayoutData getLayoutData(Component child)
	{
		checkNotNull(child, "child cannot be null");
		
		int childIndex = indexOf(child);
		checkArgument(childIndex != -1, "child is not a child of this Box: %s", child);
		
		return getLayoutData(childIndex);
	}

	public BoxLayoutData getLayoutData(int childIndex)
	{
		checkChildIndex(childIndex);
		
		return layoutDatas.get(childIndex);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "["
			+ ORIENTATION_PROPERTY + "=" + orientation + ", "
			+ COMPONENTS_PROPERTY + "=" + getComponents()
			+ "]";
	}
}
