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

import java.util.ArrayList;
import java.util.EventListenerProxy;
import java.util.List;

import org.hobsoft.symmetry.ui.Table.ClosureTableCellListener;
import org.hobsoft.symmetry.ui.event.TableCellListener;
import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class TableColumn
{
	// TODO: make iterable for column cells?
	
	// constants --------------------------------------------------------------
	
	private static final Length DEFAULT_WIDTH = null;
	
	private static final TableCellRenderer DEFAULT_HEADER_RENDERER = null;
	
	private static final TableCellRenderer DEFAULT_CELL_RENDERER = null;

	// types ------------------------------------------------------------------
	
	private static final class ColumnTableCellListenerAdapter extends EventListenerProxy implements TableCellListener
	{
		// fields -----------------------------------------------------------------
		
		private final int column;
		
		// constructors -----------------------------------------------------------
		
		public ColumnTableCellListenerAdapter(TableCellListener delegate, int column)
		{
			super(delegate);
			
			this.column = column;
		}

		// TableCellListener methods ----------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void tableCellSelected(TableEvent event)
		{
			if (column == event.getColumn())
			{
				getListener().tableCellSelected(event);
			}
		}
		
		// EventListenerProxy methods ---------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TableCellListener getListener()
		{
			return (TableCellListener) super.getListener();
		}
		
		// public methods ---------------------------------------------------------
		
		public int getColumn()
		{
			return column;
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final Table table;
	
	private final int columnIndex;
	
	private Length width;
	
	private TableCellRenderer headerRenderer;
	
	private TableCellRenderer cellRenderer;
	
	// constructors -----------------------------------------------------------
	
	TableColumn(Table table, int columnIndex)
	{
		this.table = checkNotNull(table, "table cannot be null");
		// TODO: can we ensure columnIndex < columnCount efficiently?
		this.columnIndex = checkNonNegative(columnIndex, "columnIndex");
		
		setWidth(DEFAULT_WIDTH);
		setHeaderRenderer(DEFAULT_HEADER_RENDERER);
		setCellRenderer(DEFAULT_CELL_RENDERER);
	}
	
	// public methods ---------------------------------------------------------
	
	public int getIndex()
	{
		return columnIndex;
	}
	
	public Length getWidth()
	{
		return width;
	}
	
	public void setWidth(Length width)
	{
		this.width = width;
	}
	
	// TODO: addHeaderListener
	
	// TODO: removeHeaderListener
	
	// TODO: getHeaderListeners
	
	public void addCellListener(TableCellListener listener)
	{
		table.addCellListener(new ColumnTableCellListenerAdapter(listener, columnIndex));
	}
	
	// TODO: removeCellListener
	
	public TableCellListener[] getCellListeners()
	{
		List<TableCellListener> columnListeners = new ArrayList<TableCellListener>();
		
		for (TableCellListener listener : table.getCellListeners())
		{
			if (listener instanceof ColumnTableCellListenerAdapter)
			{
				ColumnTableCellListenerAdapter adapter = (ColumnTableCellListenerAdapter) listener;
			
				// only return column listener if for specified column
				if (adapter.getColumn() == columnIndex)
				{
					columnListeners.add(adapter.getListener());
				}
			}
			else
			{
				// TODO: is this really what we want?
				
				// always return global listeners
				columnListeners.add(listener);
			}
		}
		
		return columnListeners.toArray(new TableCellListener[columnListeners.size()]);
	}
	
	public TableColumn onSelect(Closure<? super TableEvent> closure)
	{
		addCellListener(new ClosureTableCellListener(closure));
		
		return this;
	}
	
	public TableCellRenderer getHeaderRenderer()
	{
		return headerRenderer;
	}
	
	public void setHeaderRenderer(TableCellRenderer headerRenderer)
	{
		this.headerRenderer = headerRenderer;
	}
	
	public TableCellRenderer getCellRenderer()
	{
		return cellRenderer;
	}
	
	public void setCellRenderer(TableCellRenderer cellRenderer)
	{
		this.cellRenderer = cellRenderer;
	}
}
