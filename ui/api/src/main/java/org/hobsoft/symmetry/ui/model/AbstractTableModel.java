/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/AbstractTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import static com.google.common.base.Preconditions.checkElementIndex;

import javax.swing.event.EventListenerList;

import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.event.TableModelListener;

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
