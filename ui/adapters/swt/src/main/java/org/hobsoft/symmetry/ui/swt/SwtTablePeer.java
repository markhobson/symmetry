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
package org.hobsoft.symmetry.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.event.TableModelListener;
import org.hobsoft.symmetry.ui.model.TableModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwtTablePeer extends AbstractPeerHandler
{
	// fields -----------------------------------------------------------------
	
	// TODO: no local fields!
	private TableModelListener modelListener;
	
	// inner-classes ----------------------------------------------------------
	
	// TODO: reimplement
//	private class ModelListener implements TableModelListener
//	{
//		public void tableChanged(TableModelEvent event)
//		{
//			org.eclipse.swt.widgets.Table table = (org.eclipse.swt.widgets.Table) getPeer();
//			table.removeAll();
//			table.setItemCount(((Table) getComponent()).getModel().getRowCount());
//		}
//	}
//	
//	private class SetDataListener implements Listener
//	{
//		public void handleEvent(Event event)
//		{
//			TableItem item = (TableItem) event.item;
//			int row = ((org.eclipse.swt.widgets.Table) getPeer()).indexOf(item);
//			
//			Table table = (Table) getComponent();
//			int columns = table.getModel().getColumnCount();
//			
//			for (int j = 0; j < columns; j++)
//			{
//				TableCellRenderer renderer = table.getTableCellRenderer(row, j);
//				Component component = renderer.getTableCellComponent(table, row, j);
//				SWTPeer peer = (SWTPeer) getPeerManager().getPeer(component);
//				peer.asTableItem(item, j);
//			}
//		}
//	}
//	
//	private class TableControlListener implements ControlListener
//	{
//		public void controlMoved(ControlEvent event)
//		{
//			// no-op
//		}
//		
//		public void controlResized(ControlEvent event)
//		{
//			org.eclipse.swt.widgets.Table table = (org.eclipse.swt.widgets.Table) event.widget;
//			
//			int tableWidth = table.getSize().x - (2 * table.getBorderWidth());
//			TableColumn[] columns = table.getColumns();
//			int columnCount = columns.length;
//			int offset = 0;
//			for (int j = 0; j < columnCount; j++)
//			{
//				int newOffset = ((j + 1) * tableWidth) / columnCount;
//				columns[j].setWidth(newOffset - offset);
//				offset = newOffset;
//			}
//		}
//	}
//	
//	private class SelectionListenerAdapter extends ListenerAdapter implements SelectionListener
//	{
//		public SelectionListenerAdapter(TableCellListener listener)
//		{
//			super(listener);
//		}
//		
//		public void widgetSelected(SelectionEvent event)
//		{
//			// widgetDefaultSelected(event);
//		}
//		
//		public void widgetDefaultSelected(SelectionEvent event)
//		{
//			org.eclipse.swt.widgets.Table table = (org.eclipse.swt.widgets.Table) event.widget;
//			TableItem item = (TableItem) event.item;
//			int row = table.indexOf(item);
//			// TODO: set column if possible..
//			TableEvent tableEvent = new TableEvent((Table) getComponent(), row, 0);
//			((TableCellListener) getListener()).tableCellSelected(tableEvent);
//		}
//	}
	
	// constructors -----------------------------------------------------------
	
	public SwtTablePeer(PeerManager peerManager)
	{
		super(peerManager);
		
		// TODO: reimplement
//		modelListener = new ModelListener();
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Composite parent = SwtUtils.getComposite((Widget) getPeerManager().getPeer(((Component) component)
			.getParent()));
		
		org.eclipse.swt.widgets.Table table = new org.eclipse.swt.widgets.Table(parent, SWT.BORDER | SWT.FULL_SELECTION
			| SWT.VIRTUAL);
		// TODO: reimplement
//		table.addListener(SWT.SetData, new SetDataListener());
//		table.addControlListener(new TableControlListener());
		table.setLinesVisible(true);
		return table;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Table component = (Table) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		org.eclipse.swt.widgets.Table table = (org.eclipse.swt.widgets.Table) getPeerManager().getPeer(component);
		
		if (Table.FIRST_VISIBLE_ROW_INDEX_PROPERTY.equals(name))
		{
			int firstVisibleRowIndex = ((Integer) newValue).intValue();
			table.setTopIndex(firstVisibleRowIndex);
		}
		else if (Table.MODEL_PROPERTY.equals(name))
		{
			setModel(table, (TableModel) newValue);
		}
		else if (Table.CELL_LISTENER_PROPERTY.equals(name))
		{
			// TODO: reimplement
//			if (oldValue == null)
//				table.addSelectionListener(new SelectionListenerAdapter((TableCellListener) newValue));
//			else if (newValue == null)
//				table.removeSelectionListener((SelectionListener) getListener((TableCellListener) oldValue));
		}
		else if (Table.CELL_RENDERER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Table.HEADER_LISTENER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Table.HEADER_RENDERER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Table.VISIBLE_ROW_COUNT_PROPERTY.equals(name))
		{
			// TODO: implement
		}
	}
	
	// private methods --------------------------------------------------------
	
	private void setModel(org.eclipse.swt.widgets.Table table, TableModel model)
	{
		int rowCount = model.getRowCount();
		int columnCount = model.getColumnCount();
		table.setHeaderVisible(true);
		table.setItemCount(rowCount);
		
		TableColumn[] columns = table.getColumns();
		for (int j = 0; j < columns.length; j++)
		{
			columns[j].dispose();
		}
		
		// TODO: support header renderer
		for (int j = 0; j < columnCount; j++)
		{
			TableColumn column = new TableColumn(table, SWT.NONE, j);
			column.setText(model.getColumnName(j));
		}
		
		model.addTableModelListener(modelListener);
	}
}
