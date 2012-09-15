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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.ui.model.DefaultListModel;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.model.ListModels;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkElementIndexes;
import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the item type
 */
public class ListBox<T> extends ComboBox<T>
{
	// constants --------------------------------------------------------------
	
	public static final String SELECTED_INDEXES_PROPERTY = "selectedIndexes";
	
	public static final String SELECTION_MODE_PROPERTY = "selectionMode";
	
	public static final String VISIBLE_ROW_COUNT_PROPERTY = "visibleRowCount";
	
	private static final SelectionMode DEFAULT_SELECTION_MODE = SelectionMode.SINGLE;
	
	private static final int DEFAULT_VISIBLE_ROW_COUNT = 5;
	
	private static final int[] EMPTY_SELECTED_INDEXES = new int[0];
	
	// types ------------------------------------------------------------------
	
	/**
	 * Listener that synchronizes the {@code ComboBox.selectedIndex} and {@code ListBox.selectedIndexes} properties.
	 */
	private class SelectedIndexBinder implements PropertyChangeListener
	{
		// fields -----------------------------------------------------------------
		
		private boolean binding;

		// PropertyChangeListener methods -------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			String propertyName = event.getPropertyName();
			
			// prevent unwanted feedback
			if (binding)
			{
				return;
			}
			
			if (SELECTED_INDEX_PROPERTY.equals(propertyName))
			{
				int selectedIndex = (Integer) event.getNewValue();

				binding = true;
				
				try
				{
					setSelectedIndexes(toSelectedIndexes(selectedIndex));
				}
				finally
				{
					binding = false;
				}
			}
			else if (SELECTED_INDEXES_PROPERTY.equals(propertyName))
			{
				int[] selectedIndexes = (int[]) event.getNewValue();

				binding = true;
				
				try
				{
					setSelectedIndex(toSelectedIndex(selectedIndexes));
				}
				finally
				{
					binding = false;
				}
			}
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private int[] selectedIndexes;
	
	private SelectionMode selectionMode;
	
	private int visibleRowCount;
	
	// constructors -----------------------------------------------------------
	
	public ListBox(T... items)
	{
		this(new DefaultListModel<T>(items));
	}
	
	public ListBox(ListModel<T> model)
	{
		super(model);

		selectedIndexes = new int[0];
		
		addPropertyChangeListener(new SelectedIndexBinder());
		
		setSelectionMode(DEFAULT_SELECTION_MODE);
		setVisibleRowCount(DEFAULT_VISIBLE_ROW_COUNT);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptComboBox(visitor, new Generic<ListBox<?>>() { /**/ }, this, parameter);
	}
	
	// public methods ---------------------------------------------------------

	public int[] getSelectedIndexes()
	{
		return selectedIndexes;
	}
	
	public void setSelectedIndexes(int[] selectedIndexes)
	{
		selectedIndexes = firstNonNull(selectedIndexes, EMPTY_SELECTED_INDEXES);
		
		checkArgument(selectedIndexes.length <= 1 || selectionMode == SelectionMode.MULTIPLE,
			"selectedIndexes cannot contain multiple indexes when selectionMode is SINGLE");
		checkElementIndexes(selectedIndexes, getModel().getItemCount(), "selectedIndexes");

		int[] oldSelectedIndexes = this.selectedIndexes;
		this.selectedIndexes = selectedIndexes;
		firePropertyChange(SELECTED_INDEXES_PROPERTY, oldSelectedIndexes, this.selectedIndexes);
	}
	
	public int getSelectedIndexes(int index)
	{
		return selectedIndexes[index];
	}
	
	public void setSelectedIndexes(int index, int selectedIndex)
	{
		selectedIndexes[index] = selectedIndex;
	}
	
	public Object[] getSelectedItems()
	{
		return getSelectedItemsAsList().toArray();
	}
	
	public T[] getSelectedItems(T[] selectedItems)
	{
		return getSelectedItemsAsList().toArray(selectedItems);
	}
	
	// TODO: change argument to T[] to avoid clash with setSelectedItems(int, T) when T is Integer?
	public void setSelectedItems(T... selectedItems)
	{
		setSelectedItems(safeAsList(selectedItems));
	}
	
	public T getSelectedItems(int index)
	{
		return getModel().getItem(selectedIndexes[index]);
	}
	
	public void setSelectedItems(int index, T selectedItem)
	{
		selectedIndexes[index] = getItemIndex(selectedItem);
	}
	
	public List<T> getSelectedItemsAsList()
	{
		return new AbstractList<T>()
		{
			@Override
			public int size()
			{
				return selectedIndexes.length;
			}
			
			@Override
			public T get(int index)
			{
				return getModel().getItem(selectedIndexes[index]);
			}
		};
	}
	
	// TODO: rename to mirror getter?
	public void setSelectedItems(Collection<? extends T> selectedItems)
	{
		selectedItems = firstNonNull(selectedItems, Collections.<T>emptySet());
		
		int[] newSelectedIndexes = ListModels.getItemIndexes(getModel(), selectedItems);
		checkItemIndexes(newSelectedIndexes, selectedItems.toArray(), "selectedItems");
		
		setSelectedIndexes(newSelectedIndexes);
	}
	
	public boolean isIndexSelected(int index)
	{
		// TODO: can we achieve this without hitting the model?
//		checkElementIndex(index, getModel().getItemCount(), "index");
		checkNonNegative(index, "index");
		
		for (int selectedIndex : selectedIndexes)
		{
			if (selectedIndex == index)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public SelectionMode getSelectionMode()
	{
		return selectionMode;
	}
	
	public void setSelectionMode(SelectionMode selectionMode)
	{
		checkNotNull(selectionMode, "selectionMode cannot be null");
		
		SelectionMode oldSelectionMode = this.selectionMode;
		this.selectionMode = selectionMode;
		firePropertyChange(SELECTION_MODE_PROPERTY, oldSelectionMode, this.selectionMode);
	}
	
	public int getVisibleRowCount()
	{
		return visibleRowCount;
	}
	
	public void setVisibleRowCount(int visibleRowCount)
	{
		int oldVisibleRowCount = this.visibleRowCount;
		this.visibleRowCount = visibleRowCount;
		firePropertyChange(VISIBLE_ROW_COUNT_PROPERTY, oldVisibleRowCount, this.visibleRowCount);
	}
	
	// private methods --------------------------------------------------------
	
	public static <T> List<T> safeAsList(T... elements)
	{
		return (elements != null) ? Arrays.asList(elements) : null;
	}
	
	private static int[] toSelectedIndexes(int selectedIndex)
	{
		return (selectedIndex != UNSELECTED_INDEX) ? new int[] {selectedIndex} : EMPTY_SELECTED_INDEXES;
	}
	
	private static int toSelectedIndex(int[] selectedIndexes)
	{
		return (selectedIndexes.length > 0) ? selectedIndexes[0] : UNSELECTED_INDEX;
	}
	
	private static void checkItemIndexes(int[] indexes, Object[] items, String name)
	{
		checkArgument(indexes.length == items.length, "indexes and items must have equal dimensions: %s != %s",
			indexes.length, items.length);
		
		List<Object> invalidItems = new ArrayList<Object>();
		
		for (int i = 0; i < indexes.length; i++)
		{
			if (indexes[i] == UNSELECTED_INDEX)
			{
				invalidItems.add(items[i]);
			}
		}
		
		checkArgument(invalidItems.isEmpty(), "%s contains unknown items: %s", name, invalidItems);
	}
}
