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
package org.hobsoft.symmetry.ui.model;

import javax.swing.event.EventListenerList;

import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.event.TableModelListener;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * 
 * @author Mark Hobson
 */
public abstract class AbstractTableModel implements TableModel
{
	// fields -----------------------------------------------------------------
	
	private final EventListenerList listeners;
	
	// constructors -----------------------------------------------------------
	
	public AbstractTableModel()
	{
		listeners = new EventListenerList();
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		listeners.add(TableModelListener.class, listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		listeners.remove(TableModelListener.class, listener);
	}
	
	// protected methods ------------------------------------------------------
	
	protected final void fireTableChangedEvent()
	{
		Object[] listenerArray = listeners.getListenerList();
		TableModelEvent event = null;
		
		for (int i = 0; i < listenerArray.length; i += 2)
		{
			if (listenerArray[i] == TableModelListener.class)
			{
				if (event == null)
				{
					event = new TableModelEvent(this);
				}
				
				((TableModelListener) listenerArray[i + 1]).tableChanged(event);
			}
		}
	}
	
	// package methods --------------------------------------------------------
	
	final void checkRow(int row)
	{
		checkElementIndex(row, getRowCount(), "row");
	}
	
	final void checkColumn(int column)
	{
		checkElementIndex(column, getColumnCount(), "column");
	}
}
