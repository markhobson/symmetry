/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/model/TableModelAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.model;

import javax.swing.event.TableModelListener;

import org.hobsoft.symmetry.ui.model.TableModel;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableModelAdapter.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class TableModelAdapter implements javax.swing.table.TableModel
{
	// fields -----------------------------------------------------------------
	
	private final TableModel model;
	
	// constructors -----------------------------------------------------------
	
	public TableModelAdapter(TableModel model)
	{
		this.model = model;
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return model.getRowCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return model.getColumnCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return model.getColumnName(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return model.getColumnClass(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return model.getValueAt(row, column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int row, int column)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		// TODO: implement
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		// TODO: implement
	}
}
