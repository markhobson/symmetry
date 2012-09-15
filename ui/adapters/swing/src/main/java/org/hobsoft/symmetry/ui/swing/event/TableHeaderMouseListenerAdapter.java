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
package org.hobsoft.symmetry.ui.swing.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.event.TableHeaderListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TableHeaderMouseListenerAdapter extends AbstractAdapter implements MouseListener
{
	// fields -----------------------------------------------------------------
	
	private TableHeaderListener listener;
	
	// constructors -----------------------------------------------------------
	
	public TableHeaderMouseListenerAdapter(Table table, TableHeaderListener listener)
	{
		super(table);
		
		this.listener = listener;
	}
	
	// MouseListener methods --------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseClicked(MouseEvent event)
	{
		JTableHeader header = (JTableHeader) event.getSource();
		TableColumnModel columnModel = header.getColumnModel();
		int viewColumn = columnModel.getColumnIndexAtX(event.getX());
		int column = columnModel.getColumn(viewColumn).getModelIndex();
		
		if (column != -1)
		{
			listener.tableHeaderSelected(new TableEvent((Table) getComponent(), column));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent event)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent event)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseEntered(MouseEvent event)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseExited(MouseEvent event)
	{
		// no-op
	}
}
