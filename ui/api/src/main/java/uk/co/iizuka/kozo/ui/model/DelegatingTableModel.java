/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/DelegatingTableModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static com.google.common.base.Preconditions.checkNotNull;

import uk.co.iizuka.kozo.ui.event.TableModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingTableModel.java 99065 2012-03-08 13:00:14Z mark@IIZUKA.CO.UK $
 */
public abstract class DelegatingTableModel implements TableModel
{
	// fields -----------------------------------------------------------------
	
	private final TableModel delegate;
	
	// constructors -----------------------------------------------------------
	
	public DelegatingTableModel(TableModel delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate cannot be null");
	}
	
	// TableModel methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		delegate.addTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		delegate.removeTableModelListener(listener);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return delegate.getRowCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return delegate.getColumnCount();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return delegate.getColumnName(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return delegate.getColumnClass(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return delegate.getValueAt(row, column);
	}
	
	// public methods ---------------------------------------------------------
	
	public TableModel getDelegate()
	{
		return delegate;
	}
}
