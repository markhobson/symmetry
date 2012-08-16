/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/event/TableHeaderMouseListenerAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.event.TableEvent;
import uk.co.iizuka.kozo.ui.event.TableHeaderListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableHeaderMouseListenerAdapter.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
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
