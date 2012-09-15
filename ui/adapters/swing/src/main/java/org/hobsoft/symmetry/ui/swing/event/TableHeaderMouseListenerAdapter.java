/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/event/TableHeaderMouseListenerAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
